package com.migi.migi_project.service.admin.impl;

import com.migi.migi_project.model.response.ResponseNormal;
import com.migi.migi_project.repository.user.ServiceRepository;
import com.migi.migi_project.service.admin.ManageServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageServiceServiceImpl implements ManageServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<com.migi.migi_project.entity.Service> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    public ResponseNormal save(com.migi.migi_project.entity.Service dto) {
        serviceRepository.save(dto);
        return new ResponseNormal("Thành công", HttpStatus.OK);
    }

    @Override
    public ResponseNormal delete(Integer id) {
        serviceRepository.deleteById(id);
        return new ResponseNormal("Xóa thành công", HttpStatus.OK);
    }
}
