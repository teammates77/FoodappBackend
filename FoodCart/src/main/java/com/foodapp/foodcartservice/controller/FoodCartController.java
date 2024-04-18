package com.foodapp.foodcartservice.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.foodapp.foodcartservice.dto.CartResponseDTO;
import com.foodapp.foodcartservice.dto.FoodCartDTO;
import com.foodapp.foodcartservice.model.CartItem;
import com.foodapp.foodcartservice.model.FoodCart;
import com.foodapp.foodcartservice.service.FoodCartService;


@Validated
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/fooddelivery/foodcart")
public class FoodCartController {

	   private final FoodCartService foodCartService;

	    @Autowired
	    public FoodCartController(FoodCartService foodCartService) {
	        this.foodCartService = foodCartService;
	    }
    @PostMapping

    public ResponseEntity<FoodCartDTO> registerCart(@RequestBody FoodCartDTO foodCartDTO){

        FoodCartDTO savedCartDTO = foodCartService.createCartForUser(foodCartDTO);

        return new ResponseEntity<>(savedCartDTO,HttpStatus.CREATED);

    }

    @PostMapping("/{foodCartId}/{restaurantId}/{itemId}")

    public ResponseEntity<FoodCart> addItemToCart(@PathVariable Integer foodCartId,@PathVariable Integer restaurantId, @PathVariable Integer itemId){

       FoodCart foodCart = foodCartService.addItemToCart(foodCartId,itemId,restaurantId);

       return new ResponseEntity<>(foodCart, HttpStatus.CREATED);

    }

    @DeleteMapping("/{cartId}")

    public ResponseEntity<FoodCartDTO> deleteCart(@PathVariable Integer cartId){

        FoodCartDTO savedCartDTO = foodCartService.removeCart(cartId);

        return new ResponseEntity<>(savedCartDTO,HttpStatus.OK);

    }
    @GetMapping("/{cartId}") //done

    public ResponseEntity<FoodCartDTO> viewCart(@PathVariable Integer cartId){

        FoodCartDTO savedCartDTO = foodCartService.viewCart(cartId);

        return new ResponseEntity<>(savedCartDTO,HttpStatus.OK);

    }
//    @GetMapping("cartbyuser/{userId}")
//
//    public ResponseEntity<FoodCart> viewCartOfUser(@PathVariable Integer userId){
//
//        FoodCart savedCart = foodCartService.getCartOfUser(userId);
//
//        return new ResponseEntity<>(savedCart,HttpStatus.OK);
//
//    }
    @GetMapping("cartbyuser/{userId}")
    
    public ResponseEntity<CartResponseDTO> viewCartOfUser(@PathVariable Integer userId){
 
        CartResponseDTO savedCart = foodCartService.getCartOfUser(userId);
 
        return new ResponseEntity<>(savedCart,HttpStatus.OK);
 
    }
    @PatchMapping("/{cartId}/{itemId}/{quantity}")

    public ResponseEntity<FoodCart> increaseOrReduceQuantityOfItem(@PathVariable Integer cartId, @PathVariable Integer itemId, @PathVariable Integer quantity){

        FoodCart foodCart = foodCartService.increaseOrReduceQuantityOfItem(cartId,itemId,quantity);

        return new ResponseEntity<>(foodCart,HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/cartitem/{cartItemId}")

    public ResponseEntity<CartItem> removeItemFromCart(@PathVariable Integer cartItemId){

    	CartItem item = foodCartService.removeItemFromCart(cartItemId);

        return new ResponseEntity<>(item,HttpStatus.OK);

    }

    @DeleteMapping("/cart/{cartId}")

    public ResponseEntity<FoodCart> clearCart(@PathVariable Integer cartId){

        FoodCart foodCart = foodCartService.clearCart(cartId);

        return new ResponseEntity<>(foodCart,HttpStatus.OK);

    }

}
