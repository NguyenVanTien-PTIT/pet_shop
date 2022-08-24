package com.migi.migi_project.service.admin.impl;

import com.migi.migi_project.entity.ServiceWorker;
import com.migi.migi_project.model.dto.ServiceWorkerDTO;
import com.migi.migi_project.model.response.ResponseNormal;
import com.migi.migi_project.model.response.ResponseUploadFile;
import com.migi.migi_project.repository.user.ServiceWorkerRepository;
import com.migi.migi_project.service.admin.ManageServiceWorkerService;
import com.migi.migi_project.service.admin.ManagerProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ManageServiceWorkerServiceImpl implements ManageServiceWorkerService {
    @Autowired
    private ServiceWorkerRepository serviceWorkerRepository;

    @Autowired
    private ManagerProductService managerProductService;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<ServiceWorkerDTO> findAll() {
        return serviceWorkerRepository.findAll()
                .stream()
                .map(o -> mapper.map(o, ServiceWorkerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseNormal save(ServiceWorkerDTO dto, MultipartFile file) {
        if (Objects.nonNull(file)) {
            ResponseUploadFile<String> responseUploadFile = managerProductService.uploadFile(file);
            dto.setAvatar(responseUploadFile.getData());
        }

        ServiceWorker worker = mapper.map(dto, ServiceWorker.class);
        serviceWorkerRepository.save(worker);

        return new ResponseNormal("Thành công", HttpStatus.OK);
    }

    @Override
    public ResponseNormal delete(Integer id) {
        serviceWorkerRepository.deleteById(id);
        return new ResponseNormal("Xóa thành công", HttpStatus.OK);
    }
}
