package com.migi.migi_project.service.admin.impl;

import com.migi.migi_project.entity.Category;
import com.migi.migi_project.entity.Product;
import com.migi.migi_project.model.dto.CategoryDTO;
import com.migi.migi_project.model.dto.ProductDTO;
import com.migi.migi_project.model.mapper.CategoryMapper;
import com.migi.migi_project.model.mapper.ProductMapper;
import com.migi.migi_project.model.response.PageableModel;
import com.migi.migi_project.model.response.ResponseNormal;
import com.migi.migi_project.model.response.ResponseUploadFile;
import com.migi.migi_project.repository.user.CategoryRepository;
import com.migi.migi_project.repository.user.ProductRepository;
import com.migi.migi_project.service.admin.ManagerProductService;
import com.migi.migi_project.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ManagerProductServiceImpl implements ManagerProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductDTO> findAll(Pageable pageable) {
        List<Product> productList = productRepository.findAll(pageable).getContent();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product p : productList ){
            productDTOList.add(ProductMapper.toProductDTO(p));
        }
        return productDTOList;
    }

    @Override
    public PageableModel<ProductDTO> findByCategory(Integer id, Pageable pageable) {
        List<Product> productList = productRepository.findByCategory(id, pageable);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product p : productList ){
            productDTOList.add(ProductMapper.toProductDTO(p));
        }
        PageableModel<ProductDTO> response = new PageableModel<>();
        response.setList(productDTOList);
        response.setTotal(productRepository.countProductByCategory(id));
        return response;
    }

    @Override
    public List<CategoryDTO> findAllCategory(Pageable pageable) {
        List<Category> categories = categoryRepository.findAll(pageable).getContent();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for(Category category : categories ){
            categoryDTOList.add(CategoryMapper.toCategoryDTO(category));
        }
        return categoryDTOList;
    }

    @Override
    public Long countProducts() {
        return productRepository.count();
    }

    @Override
    public Long countCategories() {
        return categoryRepository.count();
    }

    @Override
    public ResponseNormal addProduct(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId()).get();
        Product product = ProductMapper.toProduct(productDTO);
        product.setCategoryByIdCategory(category);
        product.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        productRepository.save(product);
        ResponseNormal response = new ResponseNormal("Th??m m???i th??nh c??ng", HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseNormal updateProduct(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId()).get();
        Product product = ProductMapper.toProduct(productDTO);
        product.setCategoryByIdCategory(category);
        productRepository.save(product);
        ResponseNormal response = new ResponseNormal("S???a th??nh c??ng", HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseNormal deleteProduct(Integer id) {
        try{
            productRepository.deleteById(id);
            return new ResponseNormal("X??a th??nh c??ng", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseNormal("X??a kh??ng th??nh c??ng", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseNormal addCategory(CategoryDTO categoryDTO) {
        //Check tr??ng t??n
        List<Category> categoryList = categoryRepository.findAll();
        long result = categoryList.stream()
                                .filter(c -> categoryDTO.getName().trim().equalsIgnoreCase(c.getName()))
                                .count();
        if(result > 0){
            return new ResponseNormal("T??n danh m???c ???? t???n t???i, kh??ng ???????c th??m!", HttpStatus.BAD_REQUEST);
        }
        categoryRepository.save(CategoryMapper.toCategory(categoryDTO));
        return new ResponseNormal("Th??m m???i th??nh c??ng", HttpStatus.OK);
    }

    @Override
    public ResponseNormal updateCategory(CategoryDTO categoryDTO) {
        //Check tr??ng t??n
        List<Category> categoryList = categoryRepository.findAll();
        long result = categoryList.stream()
                .filter(c -> categoryDTO.getName().trim().equalsIgnoreCase(c.getName()))
                .count();
        if(result > 0){
            return new ResponseNormal("T??n danh m???c ???? t???n t???i, kh??ng ???????c s???a!", HttpStatus.BAD_REQUEST);
        }
        categoryRepository.save(CategoryMapper.toCategory(categoryDTO));
        return new ResponseNormal("S???a th??nh c??ng", HttpStatus.OK);
    }

    @Override
    public ResponseNormal deleteCategory(Integer id) {
        List<Product> productList = productRepository.findProductByCategory(id);
        if(productList.size()>0){
            return new ResponseNormal("C??n s???n ph???m thu???c danh m???c n??y, kh??ng th??? x??a!", HttpStatus.BAD_REQUEST);
        }
        categoryRepository.deleteById(id);
        return new ResponseNormal("X??a th??nh c??ng!", HttpStatus.OK);
    }

    private static String UPLOAD_DIR = FileUtils.getResourceBasePath()+ "\\src\\main\\resources\\images";

    @Override
    public ResponseUploadFile uploadFile(MultipartFile multipartFile) {
        //T???o th?? m???c ch???a ???nh n???u kh??ng t???n t???i
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        if (Objects.isNull(multipartFile)) {
            return new ResponseUploadFile("File kh??ng h???p l???!", HttpStatus.BAD_REQUEST, "");
        }

        //L???y t??n file v?? ??u??i m??? r???ng c???a file
        String originalFilename = multipartFile.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        if (originalFilename.length() > 0) {

            //Ki???m tra xem file c?? ????ng ?????nh d???ng kh??ng
            if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("gif") && !extension.equals("svg") && !extension.equals("jpeg")) {
                return new ResponseUploadFile("Kh??ng h??? tr??? ?????nh d???ng file n??y!", HttpStatus.BAD_REQUEST, "");
            }
            try {
                String nameFile = UUID.randomUUID().toString() + "." +extension;
                String linkFile = UPLOAD_DIR + "\\" + nameFile;
                //T???o file
                File file = new File(linkFile);
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bos.write(multipartFile.getBytes());
                bos.close();

                return new ResponseUploadFile("Upload ???nh th??nh c??ng!", HttpStatus.OK, nameFile);

            } catch (Exception e) {
                System.out.println("C?? l???i trong qu?? tr??nh upload file!");
            }
        }
        return new ResponseUploadFile("File kh??ng h???p l???!", HttpStatus.BAD_REQUEST, "");
    }
}
