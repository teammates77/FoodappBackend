package com.foodapp.orderdetails.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapp.orderdetails.dto.AddOrderDetailsDTO;
import com.foodapp.orderdetails.dto.ItemsInRestaurantOrderDTO;
import com.foodapp.orderdetails.dto.OrderDetailsDTO;
import com.foodapp.orderdetails.dto.UserOrdersDTO;
import com.foodapp.orderdetails.model.OrderDetails;
import com.foodapp.orderdetails.model.OrderItem;
import com.foodapp.orderdetails.service.OrderDetailsService;

@Validated
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/fooddelivery/orderdetails")
public class OrderDetailsController {

    
    private final OrderDetailsService orderDetailsService;

    
    @Autowired
    public OrderDetailsController(OrderDetailsService orderDetailsService) {
    	this.orderDetailsService=orderDetailsService;
    	}

//    @PostMapping("/{cartId}")
//  
//    public ResponseEntity<AddOrderDetailsDTO> addOrder( @PathVariable Integer cartId){
//
//        AddOrderDetailsDTO savedOrder = orderDetailsService.addOrder(cartId);
//
//        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
//
//    }
//    
    
    @PostMapping("/{cartId}/{addressId}")
    public ResponseEntity<AddOrderDetailsDTO> addOrder(@PathVariable Integer cartId, @PathVariable Integer addressId) {
        AddOrderDetailsDTO savedOrder = orderDetailsService.addOrder(cartId, addressId);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    
    public ResponseEntity<OrderDetailsDTO> viewOrder(@PathVariable Integer orderId){

        OrderDetailsDTO orderDetailsdto = orderDetailsService.viewOrder(orderId);

        return new ResponseEntity<>(orderDetailsdto,HttpStatus.OK);

    }
    
 
    @DeleteMapping("/{orderId}")
    
    public ResponseEntity<String> removeOrder(@PathVariable Integer orderId){

        OrderDetails removedOrder = orderDetailsService.removeOrder(orderId);

        String message = "Order with ID " + orderId + " is deleted";
        
        return new ResponseEntity<>(message, HttpStatus.OK);

    }
    
 
    @PutMapping
    public ResponseEntity<OrderDetails> updateOrder(@RequestBody OrderDetails orderDetails){

        OrderDetails updatedOrder = orderDetailsService.updateOrder(orderDetails);

        return new ResponseEntity<>(updatedOrder,HttpStatus.ACCEPTED);

    }
    
    @GetMapping("/ordersofacustomer/{userid}")
    
    public ResponseEntity<List<UserOrdersDTO>> viewOrderOfCustomer(@PathVariable Integer userid){
 
        List<UserOrdersDTO> userOrdersDTO = orderDetailsService.viewOrderOfCustomer(userid);
 
        return new ResponseEntity<>(userOrdersDTO,HttpStatus.OK);
    }
    
    
//    /*------- written by  -----------*/
//    @GetMapping("/ordersofacustomer/{cartId}")
//
//    public ResponseEntity<List<OrderDetailsDTO>> viewOrderOfCustomer(@PathVariable Integer cartId){
//
//        List<OrderDetailsDTO> orderDetailsDTO = orderDetailsService.viewOrderOfCustomer(cartId);
//
//        return new ResponseEntity<>(orderDetailsDTO,HttpStatus.OK);
//    }
//    
    
    
    
    @GetMapping("/ordersofarestaurant/{restaurantId}")
    public ResponseEntity<List<ItemsInRestaurantOrderDTO>> viewOrderOfRestaurant(@PathVariable Integer restaurantId) {
    	
        List<ItemsInRestaurantOrderDTO> itemsInRestaurantOrderDTO = orderDetailsService.viewOrderOfRestaurant(restaurantId);
        
        return new ResponseEntity<>(itemsInRestaurantOrderDTO, HttpStatus.OK);
    }
    
    
//  @GetMapping("/ordersofarestaurant/{restaurantId}")
//  public ResponseEntity<List<ItemsInRestaurantOrderDTO>> viewOrderOfRestaurant(Integer restaurantId){
//
//      List<ItemsInRestaurantOrderDTO> ItemsInRestaurantOrderDTO = restaurantOrdersService.viewOrderOfRestaurant(restaurantId);
//
//      return new ResponseEntity<>(ItemsInRestaurantOrderDTO,HttpStatus.OK);
//
//  }
    //based on restaurant id we need to filter the items present in the foodcart that is ordered 
    // Get ordered items based on restaurantId
  

}
