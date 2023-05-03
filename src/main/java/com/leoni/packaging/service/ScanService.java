package com.leoni.packaging.service;

import com.leoni.packaging.model.Cable;
import com.leoni.packaging.model.Package;
import com.leoni.packaging.model.Supplier;

public interface ScanService {
    Supplier getSupplier(String codeFournisseur);
    Package scanEticket(Package aPackage,String eticket);
    Package scanQuantity(Package aPackage,String quantity);
    Package scanCable(Package aPackage, Cable cable);

}
