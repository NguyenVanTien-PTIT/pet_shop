package com.migi.migi_project.controller.admin;

import com.migi.migi_project.model.dto.ServiceWorkerDTO;
import com.migi.migi_project.service.admin.ManageServiceWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ManageServiceWorkerController {

    @Autowired
    private ManageServiceWorkerService manageServiceWorkerService;

    @GetMapping(value = "admin/worker")
    public ResponseEntity<List<ServiceWorkerDTO>> getAll(){
        List<ServiceWorkerDTO> list = manageServiceWorkerService.findAll();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping(value = "admin/worker/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Integer id){
        return ResponseEntity.ok(manageServiceWorkerService.delete(id));
    }
}
