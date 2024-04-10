package com.foodapp.restaurantservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.restaurantservice.dto.ItemsInRestaurantDTO;
import com.foodapp.restaurantservice.dto.RestaurantInfoDTO;
import com.foodapp.restaurantservice.dto.UpdateItemDTO;
import com.foodapp.restaurantservice.exceptions.ItemException;
import com.foodapp.restaurantservice.exceptions.RestaurantException;
import com.foodapp.restaurantservice.model.Category;
import com.foodapp.restaurantservice.model.Item;
import com.foodapp.restaurantservice.model.Restaurant;
import com.foodapp.restaurantservice.repository.ItemRepository;
import com.foodapp.restaurantservice.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService{
	  private static final String ITEM_NOT_FOUND_MESSAGE = "Item does not exist with item id : ";

	    private final ItemRepository itemRepository;
	    private final CategoryService categoryService;
	    private final RestaurantRepository restaurantRepository;

	    @Autowired
	    public ItemServiceImpl(ItemRepository itemRepository, CategoryService categoryService, RestaurantRepository restaurantRepository) {
	        this.itemRepository = itemRepository;
	        this.categoryService = categoryService;
	        this.restaurantRepository = restaurantRepository;
	    }

    
	@Override
	public Item addItemToRestaurant(Item item, Integer restaurantId) {
		
		Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()-> new RestaurantException("Invalid restaurant id : "+restaurantId));
		 item.setRestaurant(restaurant);
        return itemRepository.save(item);
	}

    @Override
    public Item viewItem(Integer itemId) {

        Item item = itemRepository.findById(itemId).orElseThrow(()-> new ItemException(ITEM_NOT_FOUND_MESSAGE+itemId));

        return item;

    }

    @Override
    public UpdateItemDTO updateItem(Item item) {

        Item validatedItem = itemRepository.findById(item.getItemId()).orElseThrow(()-> new ItemException(ITEM_NOT_FOUND_MESSAGE+item.getItemId()));

        validateCategory(item.getCategoryId());

        Item updatedItem = itemRepository.save(item);

        return getDtoFromItem(updatedItem);
    }

    @Override
    public boolean removeItem(Integer itemId) {

        Item validatedItem = itemRepository.findById(itemId).orElseThrow(()-> new ItemException(ITEM_NOT_FOUND_MESSAGE+itemId));

        itemRepository.delete(validatedItem);

        return true;

    }

    @Override
    public List<Item> viewItemsByCategory(Integer categoryId) {

        Category category = categoryService.getCategoryById(categoryId);

       if(category==null)  throw new RuntimeException("Category does not exists with name :"+categoryId);

       return itemRepository.findByCategoryId(categoryId);


    }

    @Override
    public List<ItemsInRestaurantDTO> viewItemsByRestaurant(Integer restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()-> new RestaurantException("Invalid restaurant id : "+restaurantId));
        
        List<Item> items = itemRepository.findByRestaurant_RestaurantId(restaurantId);
        
        List<ItemsInRestaurantDTO> itemDTOs = new ArrayList<>();
        for (Item item : items) {
            itemDTOs.add(getDtoFromItemexceptrestAddress(item));
        }


        return itemDTOs;
    }
    

    
    public UpdateItemDTO getDtoFromItem(Item item){
    	UpdateItemDTO itemDTO = new UpdateItemDTO();
        itemDTO.setItemId(item.getItemId());
        itemDTO.setItemName(item.getItemName());
        itemDTO.setCost(item.getCost());
        itemDTO.setItemImageUrl(item.getItemimageUrl());
        itemDTO.setDescription(item.getDescription());
        Category category = validateCategory(item.getCategoryId());
       
        itemDTO.setCategory(category);
        
//        //itemDTO.setRestaurant(item.getRestaurant());
        
        Restaurant restaurant = item.getRestaurant();
        if (restaurant != null) {
            RestaurantInfoDTO restaurantInfoDTO = new RestaurantInfoDTO();
            restaurantInfoDTO.setRestaurantId(restaurant.getRestaurantId());
            restaurantInfoDTO.setRestaurantName(restaurant.getRestaurantName());
            restaurantInfoDTO.setContact(restaurant.getContact());
            restaurantInfoDTO.setManagerName(restaurant.getManagerName());
            restaurantInfoDTO.setRestaurant_image_Url(restaurant.getRestaurant_image_Url());
            itemDTO.setRestaurant(restaurantInfoDTO);
        }

        return itemDTO;

    }
    
    
    
    //to skip the repeating restaurant details 
    public ItemsInRestaurantDTO getDtoFromItemexceptrestAddress(Item item){

        ItemsInRestaurantDTO itemDTO = new ItemsInRestaurantDTO();

        itemDTO.setItemId(item.getItemId());

        itemDTO.setItemName(item.getItemName());

        itemDTO.setCost(item.getCost());
        itemDTO.setItemimageUrl(item.getItemimageUrl());
        itemDTO.setDescription(item.getDescription());
        Category category = validateCategory(item.getCategoryId());
        itemDTO.setCategory(category);
        
        //itemDTO.setRestaurant(item.getRestaurant());

        return itemDTO;

    }
    

    private Category validateCategory(Integer categoryId){

        Category category = categoryService.getCategoryById(categoryId);

        if(category==null) throw new RuntimeException("Category does not exists with id : "+categoryId);

        return category;

    }


}
