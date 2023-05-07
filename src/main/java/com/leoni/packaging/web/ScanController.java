package com.leoni.packaging.web;

import com.leoni.packaging.dto.ScanDto;
import com.leoni.packaging.enums.ScanKey;
import com.leoni.packaging.model.Cable;
import com.leoni.packaging.model.Package;
import com.leoni.packaging.model.Supplier;
import com.leoni.packaging.service.ScanService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/user/scan")
@RequiredArgsConstructor
public class ScanController {
    private final ScanService scanService;

    @GetMapping(path = {"","/"})
    public String scan(Model model, HttpSession httpSession){
        Package currentPackage = (Package) httpSession.getAttribute("currentPackage");
        Supplier supplier = (Supplier) httpSession.getAttribute("supplier");
        if(supplier==null)
            model.addAttribute("scan", new ScanDto(ScanKey.FOURNISSEUR));
        else if (currentPackage==null){
            model.addAttribute("scan", new ScanDto(ScanKey.ETICKET));
        }
        else if (currentPackage.getBarCode()!=null && currentPackage.getTotalQuantity()<=0)
            model.addAttribute("scan", new ScanDto(ScanKey.QUANTITE));
        else{
            model.addAttribute("scan", new ScanDto(ScanKey.CABLE));
            model.addAttribute("currentPackage",currentPackage);
            if(currentPackage.getTotalQuantity()<=currentPackage.getCurrentQuatity()){
                httpSession.setAttribute("currentPackage",null);
                httpSession.setAttribute("supplier",null);
                return "redirect:";
            }
        }
        return "scan";
    }

    @PostMapping(path = {"","/"})
    public String postScan(Model model, HttpSession httpSession, ScanDto scan){
        Package currentPackage = (Package) httpSession.getAttribute("currentPackage");
        if(scan.getKey()==ScanKey.FOURNISSEUR){
            httpSession.setAttribute("supplier",scanService.getSupplier(scan.getValue()));
        }else if(scan.getKey()==ScanKey.ETICKET){
            Package aPackage = new Package();
            aPackage.setSupplier((Supplier) httpSession.getAttribute("supplier"));
            httpSession.setAttribute("currentPackage", scanService.scanEticket(aPackage,scan.getValue()));
        }else if(scan.getKey()==ScanKey.QUANTITE){
            httpSession.setAttribute("currentPackage", scanService.scanQuantity(currentPackage,scan.getValue()));
        } else if(scan.getKey()==ScanKey.CABLE){
            Cable cable = Cable.builder()
                    .barCode(scan.getValue())
                    .started(LocalDateTime.now())
                    .build();
            Package p = scanService.scanCable(currentPackage, cable);
            httpSession.setAttribute("currentPackage", p);

            if(p.getTotalQuantity()==p.getCurrentQuatity()){
                httpSession.setAttribute("supplier", null);
                httpSession.setAttribute("currentPackage", null);
            }
        }
        return "redirect:";
    }



}
