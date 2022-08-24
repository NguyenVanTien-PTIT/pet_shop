package com.migi.migi_project.service.admin;

import com.migi.migi_project.entity.Service;
import com.migi.migi_project.model.dto.ServiceWorkerDTO;
import com.migi.migi_project.model.response.ResponseNormal;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ManageServiceService {
    List<Service> findAll();
    ResponseNormal save(Service dto);
    ResponseNormal delete(Integer id);
}
