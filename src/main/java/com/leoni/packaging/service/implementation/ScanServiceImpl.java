package com.leoni.packaging.service.implementation;

import com.leoni.packaging.dao.CableRepository;
import com.leoni.packaging.dao.PackageRepository;
import com.leoni.packaging.dao.SupplierRepository;
import com.leoni.packaging.model.AppUser;
import com.leoni.packaging.model.Cable;
import com.leoni.packaging.model.Package;
import com.leoni.packaging.model.Supplier;
import com.leoni.packaging.service.ScanService;
import com.leoni.packaging.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ScanServiceImpl implements ScanService {
    private final SupplierRepository supplierRepository;
    private final CableRepository cableRepository;
    private final PackageRepository packageRepository;
    private final UserService userService;

    private AppUser getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userService.findUserByUsername(currentUserName);
        }
        throw new RuntimeException("not authenticated");
    }
    
    private Package findPackage(Long id){
        return packageRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Internal Error")
                );
    }

    @Override
    public Supplier getSupplier(String codeFournisseur) {
        Optional<Supplier> supplier = supplierRepository.findByCode(codeFournisseur);
        if(supplier.isPresent())
            return supplier.get();
        else
            return supplierRepository.save(new Supplier(codeFournisseur));
    }

    @Override
    public Package scanEticket(Package aPackage,String eticket) {
        Optional<Package> savedPackage = packageRepository.findByCode(eticket);
        Optional<Supplier> savedSupplier = supplierRepository.findById(aPackage.getSupplier().getId());
        if(savedSupplier.isEmpty())
            throw new RuntimeException("Internal Error");
        if(savedPackage.isPresent())
            return savedPackage.get();
        return packageRepository.save(
                        Package.builder()
                                .barCode(eticket)
                                .supplier(savedSupplier.get())
                                .build()
                );
    }

    @Override
    public Package scanQuantity(Package aPackage,String quantity) {
        Package savedPackage = findPackage(aPackage.getId());
        if(aPackage==null) return null;
        savedPackage.setTotalQuantity(Integer.parseInt(quantity));
        return savedPackage;
    }

    @Override
    public Package scanCable(Package aPackage,Cable cable) {
        AppUser authenticatedUser = getAuthenticatedUser();
        Package savedPackage = findPackage(aPackage.getId());
        if(aPackage==null) return null;
        cable.setLine(authenticatedUser.getLine());
        cable.setAPackage(savedPackage);
        cable.setCompleted(LocalDateTime.now());
        cable.setDuration(Duration.between(cable.getStarted(), cable.getCompleted()).getSeconds()%60);
        cableRepository.save(cable);
        savedPackage.setCurrentQuatity(savedPackage.getCurrentQuatity()+1);
        return savedPackage;
    }
}
