package com.leoni.packaging.service;

import com.leoni.packaging.model.Cable;
import com.leoni.packaging.model.Package;

import java.util.List;

public interface CableService {
    List<Cable> getLastScanCables(Package aPackage);
}
