package com.migi.migi_project.controller.admin;

import com.migi.migi_project.model.dto.ServiceWorkerDTO;
import com.migi.migi_project.model.response.ResponseNormal;
import com.migi.migi_project.service.admin.ManageServiceWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ManageServiceWorkerController {

    @Autowired
    private ManageServiceWorkerService manageServiceWorkerService;

    @GetMapping(value = "/worker")
    public ResponseEntity<List<ServiceWorkerDTO>> getAll(){
        List<ServiceWorkerDTO> list = manageServiceWorkerService.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "/admin/worker")
    public ResponseEntity<ResponseNormal> save(
            @RequestPart(value = "worker") ServiceWorkerDTO serviceWorkerDTO,
            @RequestPart(value = "image", required = false) MultipartFile file
    ){
        ResponseNormal responseNormal = manageServiceWorkerService.save(serviceWorkerDTO, file);
        return ResponseEntity.ok(responseNormal);
    }

    @DeleteMapping(value = "/admin/worker/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Integer id){
        return ResponseEntity.ok(manageServiceWorkerService.delete(id));
    }
}
