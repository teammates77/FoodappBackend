package com.foodapp.foodcartservice.service;

import com.foodapp.foodcartservice.dto.CartResponseDTO;
import com.foodapp.foodcartservice.dto.FoodCartDTO;
import com.foodapp.foodcartservice.model.CartItem;
import com.foodapp.foodcartservice.model.FoodCart;

public interface FoodCartService {


    public FoodCartDTO createCartForUser(FoodCartDTO foodCartDTO);

    public FoodCartDTO viewCart(Integer cartId);

    public FoodCartDTO removeCart(Integer cartId);
    public CartResponseDTO getCartOfUser(Integer userId);

    public FoodCart addItemToCart(Integer cartId, Integer itemId,Integer restaurantId);

    public FoodCart increaseOrReduceQuantityOfItem(Integer cartId, Integer itemId, Integer quantity);

    public CartItem removeItemFromCart(Integer cartItemId);

    public FoodCart clearCart(Integer cartId);

}
