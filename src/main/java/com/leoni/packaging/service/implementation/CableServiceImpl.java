package com.leoni.packaging.service.implementation;

import com.leoni.packaging.dao.CableRepository;
import com.leoni.packaging.model.Cable;
import com.leoni.packaging.model.Package;
import com.leoni.packaging.service.CableService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CableServiceImpl implements CableService {
    private final CableRepository cableRepository;

    @Override
    public List<Cable> getLastScanCables(Package aPackage) {
        return cableRepository.findLastCablesByPackage(aPackage.getId());
    }
}
