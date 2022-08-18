package com.migi.migi_project.controller.user;

import com.migi.migi_project.model.dto.ProductDTO;
import com.migi.migi_project.model.response.HomePageResponse;
import com.migi.migi_project.service.user.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class HomeController {
    @Autowired
    ProductService productService;

    @GetMapping(value = {"/","/home"})
    public ResponseEntity<?> homePage(){
        //Lấy 4 sản phẩm mới nhất
        List<ProductDTO> listNewProduct = productService.findNewProduct(4);
        //Lấy 4 sản phẩm bán chạy nhất
        List<ProductDTO> listBestSeller = productService.findBestSellerProduct(4);
        HomePageResponse response = new HomePageResponse(listNewProduct, listBestSeller);
        return ResponseEntity.ok(response);
    }
}
