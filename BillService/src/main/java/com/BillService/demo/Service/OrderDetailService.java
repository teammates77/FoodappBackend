package com.BillService.demo.Service;

import com.BillService.demo.Model.OrderDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@FeignClient(name = "ORDER-SERVICE")
public interface OrderDetailService {

	
    @GetMapping("/fooddelivery/orderdetails/{orderId}")
    public OrderDetails getOrderDetails(@PathVariable Integer orderId);

    @GetMapping("/fooddelivery/orderdetails/ordersofacustomer/{cartId}")
    public List<OrderDetails> getOrderByCartId(@PathVariable Integer cartId);

}
