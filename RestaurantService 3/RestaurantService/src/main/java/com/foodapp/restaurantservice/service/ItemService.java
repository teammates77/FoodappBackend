package com.foodapp.restaurantservice.service;

import java.util.List;

import com.foodapp.restaurantservice.dto.ItemsInRestaurantDTO;
import com.foodapp.restaurantservice.dto.UpdateItemRequestDTO;
import com.foodapp.restaurantservice.model.Item;

public interface ItemService {


    public Item viewItem(Integer itemId);

    public UpdateItemRequestDTO updateItem(UpdateItemRequestDTO requestDTO);

    public boolean removeItem(Integer itemId);

    public List<ItemsInRestaurantDTO> viewItemsByCategory(Integer categoryId);
    
    public Item addItemToRestaurant(Item item,Integer restaurantId);
    
    public List<ItemsInRestaurantDTO> viewItemsByRestaurant(Integer restaurantId);

   // public UpdateItemDTO getDtoFromItem(Item item);

	public ItemsInRestaurantDTO getDtoFromItemexceptrestAddress(Item el);


}
