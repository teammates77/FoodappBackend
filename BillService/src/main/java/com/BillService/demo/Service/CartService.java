package com.BillService.demo.Service;

import com.BillService.demo.Model.FoodCart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "FOODCART-SERVICE")
public interface CartService {

    @GetMapping("/fooddelivery/foodcart/cartbyuser/{userId}")
    public FoodCart getFoodCartByUserId(@PathVariable Integer userId);

}
