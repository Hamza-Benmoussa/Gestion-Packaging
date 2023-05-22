package com.leoni.packaging.web;

import com.leoni.packaging.dto.CablesByHourDto;
import com.leoni.packaging.dto.ScanDto;
import com.leoni.packaging.enums.ScanKey;
import com.leoni.packaging.model.Cable;
import com.leoni.packaging.model.Package;
import com.leoni.packaging.model.Supplier;
import com.leoni.packaging.service.CableService;
import com.leoni.packaging.service.ScanService;
import com.leoni.packaging.service.StatisticService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/user/scan")
@RequiredArgsConstructor
public class ScanController {
    private final ScanService scanService;
    private final CableService cableService;
    private final StatisticService statisticService;

    @GetMapping(path = {"","/"})
    public String scan(Model model, HttpSession httpSession){
        List<CablesByHourDto> countCablesForEachHour = statisticService.getCountCablesForEachHour();
        model.addAttribute("cablesCount", countCablesForEachHour);
        long totalCables=0L;
        for (CablesByHourDto r: countCablesForEachHour) {
            totalCables+=r.getCablesCount();
        }
        model.addAttribute("totalCables", totalCables);

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
            model.addAttribute("currentPackageCables", cableService.getLastScanCables(currentPackage));
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
                    .started(scan.getScanDateTime())
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
