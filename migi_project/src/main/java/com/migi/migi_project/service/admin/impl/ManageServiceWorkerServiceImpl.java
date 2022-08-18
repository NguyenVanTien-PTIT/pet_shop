package com.migi.migi_project.service.admin.impl;

import com.migi.migi_project.model.dto.ServiceWorkerDTO;
import com.migi.migi_project.model.response.ResponseNormal;
import com.migi.migi_project.repository.user.ServiceWorkerRepository;
import com.migi.migi_project.service.admin.ManageServiceWorkerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManageServiceWorkerServiceImpl implements ManageServiceWorkerService {
    @Autowired
    private ServiceWorkerRepository serviceWorkerRepository;

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
    public ResponseNormal add(ServiceWorkerDTO dto) {
        return null;
    }

    @Override
    public ResponseNormal update(ServiceWorkerDTO dto) {
        return null;
    }

    @Override
    public ResponseNormal delete(Integer id) {
        serviceWorkerRepository.deleteById(id);
        return new ResponseNormal("Xóa thành công", HttpStatus.OK);
    }
}
