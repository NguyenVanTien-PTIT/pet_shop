package com.migi.migi_project.controller.user;

import com.migi.migi_project.model.dto.OrderProductDTO;
import com.migi.migi_project.model.dto.OrdersDTO;
import com.migi.migi_project.model.dto.ProductDTO;
import com.migi.migi_project.model.dto.UserDTO;
import com.migi.migi_project.model.mapper.UserMapper;
import com.migi.migi_project.model.response.AddProductToOrderResponse;
import com.migi.migi_project.model.response.ProductPage;
import com.migi.migi_project.service.user.CategoryService;
import com.migi.migi_project.service.user.UserService;
import com.migi.migi_project.service.user.OrderService;
import com.migi.migi_project.service.user.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@RestController
@CrossOrigin
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;


    //Lấy list danh mục, tổng số sản phẩm, list sản phẩm và page tương ứng
    @GetMapping(value = {"/product-page"})
    public ResponseEntity<?> showProduct(@RequestParam(value = "page", required = true ) Integer page,
                                         @RequestParam(value = "limit", required = true) Integer limit){
        ProductPage productPage = new ProductPage();
        //Get products in one page
        productPage.setPage(page);
        Pageable pageable = PageRequest.of(page-1, limit);
        productPage.setProductDTOList(productService.findAll(pageable));
        productPage.setTotalPage((int)Math.ceil((double) (productService.totalProduct()) / limit ));
        productPage.setCategoryDTOList(categoryService.findAll());

        return ResponseEntity.ok(productPage);

    }

    //Tìm kiếm sản phẩm theo id
    @GetMapping(value = {"/product-page/{id}"})
    public ResponseEntity<?> findById(@PathVariable Integer id){
        return ResponseEntity.ok(productService.findById(id));
    }

    //Tìm kiếm sản phẩm theo id danh mục
    @GetMapping(value = {"/product-page/category/{id}"})
    public ResponseEntity<?> findProductByCategory(@PathVariable Integer id){
        return ResponseEntity.ok(productService.findByCategory(id));
    }

    //Lấy danh sách sản phẩm và trang tương ứng khi tìm theo price
    @GetMapping(value = {"/product-page/search"})
    public ResponseEntity<?> findProductByPrice(@RequestParam(value = "price") Double price,
                                                @RequestParam(value = "page") Integer page,
                                                @RequestParam(value = "limit") Integer limit){
        ProductPage productPage= new ProductPage();
        productPage.setPage(page);
        Pageable pageable = PageRequest.of(page-1, limit);
        productPage.setProductDTOList(productService.findByPrice(price, pageable));
        int count = (productService.countProductFindByPrice(price));
        productPage.setTotalPage(
                (int)Math.ceil((double) count / limit )
        );
        productPage.setTotalProduct(count);
        return ResponseEntity.ok(productPage);
    }

    //Lấy sản phẩm có trong database
    @GetMapping(value = {"/product-page/count"})
    public int totalProduct(){
        return productService.totalProduct();
    }

    //Lấy các sản phẩm liên quan theo id sản phẩm
    @GetMapping(value = "/single-product/relate/{id}")
    public ResponseEntity<?> findProductRelateById(@PathVariable(value = "id") Integer id){
        return ResponseEntity.ok(productService.findProductRelateById(id));
    }

    //Thêm sản phẩm được chọn vào giỏ hàng
    @GetMapping(value = {"/product-page/cart"})
    public ResponseEntity<?> addProductToOrder(@RequestParam(value = "iduser") Integer idUser,
                                               @RequestParam( value = "idproduct") Integer idProduct){
        //Tìm kiếm giỏ hàng hiện tại của user tại status = 0

        OrdersDTO ordersDTO = orderService.findOrderByIdUser(idUser);
        ProductDTO productDTO = productService.findById(idProduct);
        if(ordersDTO == null){
            //Tạo mới order cho user
            //1. Lấy user
            UserDTO userDTO = UserMapper.toUserDTO(userService.findUserById(idUser));
            //2.Tạo mới order
            OrdersDTO newOrderDTO = new OrdersDTO();
            newOrderDTO.setTotalprice(productDTO.getPrice());
            newOrderDTO.setAddress(userDTO.getAddress());
            newOrderDTO.setPhoneNumber(userDTO.getPhoneNumber());
            newOrderDTO.setNameUser(userDTO.getUsername());
            //Convert Timestamp to string
            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
            String result = simpleDateFormat.format(Timestamp.valueOf(LocalDateTime.now()));
            newOrderDTO.setOrderDate(result);
            newOrderDTO.setStatus(0);
            newOrderDTO.setIdUser(idUser);
            //Thêm mới order vào database
            orderService.addOrder(newOrderDTO);
            //Thêm product vào order_product
            OrderProductDTO orderProductDTO = new OrderProductDTO();
            orderProductDTO.setPrice(productDTO.getPrice());
            orderProductDTO.setQuantity(1);
            orderProductDTO.setProductDTO(productDTO);
            orderProductDTO.setOrdersDTO(newOrderDTO);
            //Lưu orderProduct vào database
            orderService.addOrderProduct(orderProductDTO);
            //Trả về order hiện tại và thông báo thành công
            AddProductToOrderResponse addResponse = new AddProductToOrderResponse(
                                                        orderService.findOrderByIdUser(idUser),
                                                        "Thêm thành công"
                                                    );
            return ResponseEntity.ok(addResponse);
        }else{
            //Thêm sản phẩm vào order hiện có
            //Tìm kiếm trong order_product đã có sản phẩm muốn mua chưa
            //Đã có thì sl tăng 1
            //Chưa có thì tạo mới 1 order_product
            OrderProductDTO orderProductDTO = orderService
                                                .findOrderProductByIdOrderAndIdProduct(
                                                        ordersDTO.getId(), idProduct
                                                );
            if(orderProductDTO == null){
                //Thêm mới sản phẩm vào order
                System.out.println("Sản phẩm chưa có trong order");
                OrderProductDTO newOrderProductDTO = new OrderProductDTO();
                ordersDTO.setIdUser(idUser);
                newOrderProductDTO.setOrdersDTO(ordersDTO);
                newOrderProductDTO.setProductDTO(productDTO);
                newOrderProductDTO.setQuantity(1);
                newOrderProductDTO.setPrice(productDTO.getPrice());
                orderService.addOrderProduct(newOrderProductDTO);
                //Update tổng giá đơn hàng của order hiện tại
                double price = ordersDTO.getTotalprice() + newOrderProductDTO.getPrice();
                ordersDTO.setTotalprice(price);
                ordersDTO.setIdUser(idUser);
            }else{
                //Tăng số lượng và giá sp trong order-product hiện có
                System.out.println("Sản phẩm đã có trong order");
                int quantityProduct = orderProductDTO.getQuantity() + 1;
                double priceOrderProduct = orderProductDTO.getPrice() + productDTO.getPrice();
                orderProductDTO.setQuantity(quantityProduct);
                orderProductDTO.setPrice(priceOrderProduct);
                orderProductDTO.setOrdersDTO(ordersDTO);
                orderProductDTO.setProductDTO(productDTO);
                orderService.updateOrderProduct(orderProductDTO);
                //Update tổng chi phí đơn hàng (order)
                double price = ordersDTO.getTotalprice() + productDTO.getPrice();
                ordersDTO.setTotalprice(price);
                ordersDTO.setIdUser(idUser);
            }
            AddProductToOrderResponse addResponse = new AddProductToOrderResponse(
                    orderService.updateOrder(ordersDTO),
                    "Thêm vào order thành công"
            );
            return ResponseEntity.ok(addResponse);
        }
    }
}
