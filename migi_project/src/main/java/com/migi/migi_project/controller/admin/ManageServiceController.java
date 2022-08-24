package com.migi.migi_project.controller.admin;

import com.migi.migi_project.entity.Service;
import com.migi.migi_project.model.response.ResponseNormal;
import com.migi.migi_project.service.admin.impl.ManageServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManageServiceController {

    @Autowired
    private ManageServiceServiceImpl manageServiceService;

    @GetMapping(value = "/service")
    public ResponseEntity<List<Service>> getAll(){
        List<Service> list = manageServiceService.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "/service")
    public ResponseEntity<ResponseNormal> save(
            @RequestBody Service service
    ) {
        ResponseNormal responseNormal = manageServiceService.save(service);
        return ResponseEntity.ok(responseNormal);
    }

    @DeleteMapping(value = "/service/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Integer id){
        return ResponseEntity.ok(manageServiceService.delete(id));
    }
}
