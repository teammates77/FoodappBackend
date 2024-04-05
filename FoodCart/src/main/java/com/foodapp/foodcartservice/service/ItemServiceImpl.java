package com.foodapp.foodcartservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.foodcartservice.dto.ItemDTO;
import com.foodapp.foodcartservice.exceptions.ItemException;
import com.foodapp.foodcartservice.model.CartItem;
import com.foodapp.foodcartservice.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService{

    
    private final ItemRepository itemRepository;
    
    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
    	this.itemRepository=itemRepository;
    }
    
    @Override
    public CartItem addItem(ItemDTO itemDTO) {

    	CartItem item = getItemFromDTO(itemDTO);

        return itemRepository.save(item);

    }

    public CartItem getItemFromDTO(ItemDTO itemDTO){

    	CartItem item = new CartItem();

        item.setItemId(itemDTO.getItemId());

        item.setItemName(itemDTO.getItemName());

        item.setCategory(itemDTO.getCategory());

        item.setQuantity(itemDTO.getQuantity());

        item.setCost(itemDTO.getCost());

        item.setRestaurantId(itemDTO.getRestaurant().getRestaurantId());

        item.setFoodCart(itemDTO.getFoodCart());

        return item;

    }

    @Override
    public CartItem validateItem(Integer cartItemId) {

        return itemRepository.findById(cartItemId).orElseThrow(()-> new ItemException("Invalid Item Id"+cartItemId));

    }

    @Override
    public void removeItem(Integer cartItemId, Integer cartId) {

//        itemRepository.deleteItemFromCart(itemId,cartId);


    }
}
