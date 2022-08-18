package com.migi.migi_project.service.admin;

import com.migi.migi_project.model.dto.ServiceWorkerDTO;
import com.migi.migi_project.model.response.ResponseNormal;

import java.util.List;

public interface ManageServiceWorkerService {
    List<ServiceWorkerDTO> findAll();
    ResponseNormal add(ServiceWorkerDTO dto);
    ResponseNormal update(ServiceWorkerDTO dto);
    ResponseNormal delete(Integer id);
}
