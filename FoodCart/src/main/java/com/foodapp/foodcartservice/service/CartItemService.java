package com.foodapp.foodcartservice.service;

import com.foodapp.foodcartservice.dto.ItemDTO;
import com.foodapp.foodcartservice.model.CartItem;

public interface CartItemService {

    public CartItem addItem(ItemDTO itemDTO);

    public CartItem getItemFromDTO(ItemDTO itemDTO);

    public CartItem validateItem(Integer itemId);

    public void removeItem(Integer itemId, Integer cartId);

}
