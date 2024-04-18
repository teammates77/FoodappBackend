package com.foodapp.restaurantservice.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.restaurantservice.dto.ItemsInRestaurantDTO;

import com.foodapp.restaurantservice.dto.RestaurantDTO;
import com.foodapp.restaurantservice.dto.RestaurantInfoDTO;
import com.foodapp.restaurantservice.dto.RestaurantsInItemDTO;
import com.foodapp.restaurantservice.exceptions.RestaurantException;
import com.foodapp.restaurantservice.model.Item;
import com.foodapp.restaurantservice.model.Restaurant;
import com.foodapp.restaurantservice.repository.ItemRepository;
import com.foodapp.restaurantservice.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService{
	
	  private final RestaurantRepository restaurantRepository;
	    private final ItemRepository itemRepository;
	    private final ItemService itemService;
	   

	    @Autowired
	    public RestaurantServiceImpl(RestaurantRepository restaurantRepository,
	                       ItemRepository itemRepository,
	                       ItemService itemService
	                      ) {
	        this.restaurantRepository = restaurantRepository;
	        this.itemRepository = itemRepository;
	        this.itemService = itemService;
	      
	    }

    @Override
    public RestaurantsInItemDTO addRestaurant(RestaurantsInItemDTO restaurantDTO) {
    	
        Restaurant restaurant = new Restaurant();
        restaurant.setMerchantId(restaurantDTO.getMerchantId());
        restaurant.setRestaurantName(restaurantDTO.getRestaurantName());
        restaurant.setContact(restaurantDTO.getContact());
        restaurant.setAddressLine(restaurantDTO.getAddressLine());
        restaurant.setCity(restaurantDTO.getCity());
        restaurant.setState(restaurantDTO.getState());
        restaurant.setCountry(restaurantDTO.getCountry());
        restaurant.setPinCode(restaurantDTO.getPinCode());
        restaurant.setManagerName(restaurantDTO.getManagerName());
        restaurant.setRestaurant_image_Url(restaurantDTO.getRestaurant_image_Url());
        
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return getDTOFromRestaurant(savedRestaurant);
     
    }
    
    
    
    @Override
    public RestaurantsInItemDTO getRestaurantByMerchantId(Integer merchantId) {
        Restaurant restaurant = restaurantRepository.findByMerchantId(merchantId);
        if (restaurant == null) {
            return null;
        }
        return getDTOFromRestaurant(restaurant);
    }

    @Override
    public RestaurantsInItemDTO updateRestaurant(RestaurantsInItemDTO restaurantDTO) {

        Restaurant savedRestaurant = validateRestaurant(restaurantDTO.getRestaurantId());

        // Update fields directly from the DTO
        savedRestaurant.setRestaurantName(restaurantDTO.getRestaurantName());
        savedRestaurant.setContact(restaurantDTO.getContact());
        savedRestaurant.setManagerName(restaurantDTO.getManagerName());
        savedRestaurant.setRestaurant_image_Url(restaurantDTO.getRestaurant_image_Url());
        savedRestaurant.setAddressLine(restaurantDTO.getAddressLine());
        savedRestaurant.setCity(restaurantDTO.getCity());
        savedRestaurant.setState(restaurantDTO.getState());
        savedRestaurant.setCountry(restaurantDTO.getCountry());
        savedRestaurant.setPinCode(restaurantDTO.getPinCode());

        Restaurant updatedRestaurant = restaurantRepository.save(savedRestaurant);

        // Convert the updated restaurant back to DTO
        return getDTOFromRestaurant(updatedRestaurant);
    }
    
    
    @Override
    public Restaurant removeRestaurant(Integer restaurantId) {

        Restaurant savedRestaurant = validateRestaurant(restaurantId);
        restaurantRepository.delete(savedRestaurant);

        return savedRestaurant;
    }

    @Override
    public RestaurantDTO viewRestaurant(Integer restaurantId) {

        Restaurant restaurant = validateRestaurant(restaurantId);   
        RestaurantDTO restaurantDTO = convertToDTO(restaurant);
        
        List<ItemsInRestaurantDTO> items = new ArrayList<>();
        List<Item> savedItems = restaurant.getItems();
        savedItems.stream().forEach(el-> items.add(itemService.getDtoFromItemexceptrestAddress(el)));

        restaurantDTO.setItems(items);

        return restaurantDTO;
    	
    }
    
    private RestaurantsInItemDTO getDTOFromRestaurant(Restaurant restaurant){

        RestaurantsInItemDTO savedRestaurantDTO = new RestaurantsInItemDTO();

        savedRestaurantDTO.setRestaurantId(restaurant.getRestaurantId());
        savedRestaurantDTO.setMerchantId(restaurant.getMerchantId());
        savedRestaurantDTO.setRestaurantName(restaurant.getRestaurantName());
        savedRestaurantDTO.setContact(restaurant.getContact());
        savedRestaurantDTO.setAddressLine(restaurant.getAddressLine());
        savedRestaurantDTO.setCity(restaurant.getCity());
        savedRestaurantDTO.setState(restaurant.getState());
        savedRestaurantDTO.setCountry(restaurant.getCountry());
        savedRestaurantDTO.setPinCode(restaurant.getPinCode());
        savedRestaurantDTO.setManagerName(restaurant.getManagerName());
        savedRestaurantDTO.setRestaurant_image_Url(restaurant.getRestaurant_image_Url());
        return savedRestaurantDTO;
    }
    
    private RestaurantDTO convertToDTO(Restaurant restaurant) {
    	  
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setRestaurantId(restaurant.getRestaurantId());
        restaurantDTO.setRestaurantName(restaurant.getRestaurantName());
        restaurantDTO.setAddressLine(restaurant.getAddressLine());
        restaurantDTO.setCity(restaurant.getCity());
        restaurantDTO.setState(restaurant.getState());
        restaurantDTO.setCountry(restaurant.getCountry());
        restaurantDTO.setPinCode(restaurant.getPinCode());
        restaurantDTO.setManagerName(restaurant.getManagerName());
        restaurantDTO.setContact(restaurant.getContact());
        restaurantDTO.setRestaurant_image_Url(restaurant.getRestaurant_image_Url());
        restaurantDTO.setMerchantId(restaurant.getMerchantId());
        return restaurantDTO;
    }
    
    
	@Override
	public List<RestaurantInfoDTO> getAllRestaurants() {
	  return restaurantRepository.findAllRestaurants();
	}



    private Restaurant validateRestaurant(Integer restaurantId){

        return restaurantRepository.findById(restaurantId).orElseThrow(()-> new RestaurantException("Invalid restaurant id : "+restaurantId));

    }


}




