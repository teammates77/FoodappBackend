package com.foodapp.restaurantservice.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.restaurantservice.dto.ItemsInRestaurantDTO;
import com.foodapp.restaurantservice.dto.MerchantRestaurantDTO;
import com.foodapp.restaurantservice.dto.RestaurantDTO;
import com.foodapp.restaurantservice.dto.RestaurantInfoDTO;
import com.foodapp.restaurantservice.dto.RestaurantsInItemDTO;
import com.foodapp.restaurantservice.exceptions.AddressException;
import com.foodapp.restaurantservice.exceptions.RestaurantException;
import com.foodapp.restaurantservice.model.Address;
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
	    private final AddressService addressService;

	    @Autowired
	    public RestaurantServiceImpl(RestaurantRepository restaurantRepository,
	                       ItemRepository itemRepository,
	                       ItemService itemService,
	                       AddressService addressService) {
	        this.restaurantRepository = restaurantRepository;
	        this.itemRepository = itemRepository;
	        this.itemService = itemService;
	        this.addressService = addressService;
	    }

    @Override
    public RestaurantsInItemDTO addRestaurant(RestaurantsInItemDTO restaurantDTO) {

        Address address = addressService.saveAddress(restaurantDTO.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setMerchantId(restaurantDTO.getMerchantId());
        restaurant.setRestaurantName(restaurantDTO.getRestaurantName());
        restaurant.setContact(restaurantDTO.getContact());
        restaurant.setAddressId(address.getAddressId());
        restaurant.setManagerName(restaurantDTO.getManagerName());
        restaurant.setRestaurant_image_Url(restaurantDTO.getRestaurant_image_Url());
        
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return getDTOFromRestaurant(savedRestaurant);

    }
    
    public MerchantRestaurantDTO getRestaurantByMerchantId(Integer merchantId) {
        Restaurant restaurant = restaurantRepository.findByMerchantId(merchantId);
        if (restaurant == null) {
            throw new RestaurantException("Restaurant not found for merchantId: " + merchantId);
        }
        return convertToMerchantDTO(restaurant);
    }

    private MerchantRestaurantDTO convertToMerchantDTO(Restaurant restaurant) {
        MerchantRestaurantDTO dto = new MerchantRestaurantDTO();
        dto.setRestaurantId(restaurant.getRestaurantId());
        dto.setMerchantId(restaurant.getMerchantId());
        dto.setRestaurantName(restaurant.getRestaurantName());
        dto.setAddress(validateAddress(restaurant.getAddressId()));
        dto.setRestaurant_image_Url(restaurant.getRestaurant_image_Url());
        dto.setManagerName(restaurant.getManagerName());
        dto.setContact(restaurant.getContact());
        return dto;
    }

    @Override
    public MerchantRestaurantDTO updateRestaurant(MerchantRestaurantDTO restaurantDTO) {

        Restaurant savedRestaurant = validateRestaurant(restaurantDTO.getRestaurantId());

        // Update fields directly from the DTO
        savedRestaurant.setRestaurantName(restaurantDTO.getRestaurantName());
        savedRestaurant.setContact(restaurantDTO.getContact());
        savedRestaurant.setManagerName(restaurantDTO.getManagerName());
        savedRestaurant.setRestaurant_image_Url(restaurantDTO.getRestaurant_image_Url());

        // Update address
        if (restaurantDTO.getAddress() != null) {
            Address updatedAddress = addressService.saveAddress(restaurantDTO.getAddress());
            savedRestaurant.setAddressId(updatedAddress.getAddressId());
        }

        // Save the updated restaurant
        Restaurant updatedRestaurant = restaurantRepository.save(savedRestaurant);

        // Convert the updated restaurant back to DTO
        return convertToMerchantDTO(updatedRestaurant);
    }
    private RestaurantDTO convertToDTO(Restaurant restaurant) {
        // Implement conversion logic from Restaurant entity to RestaurantDTO
  
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setRestaurantId(restaurant.getRestaurantId());
        restaurantDTO.setRestaurantName(restaurant.getRestaurantName());
        restaurantDTO.setAddress(validateAddress(restaurant.getAddressId()));
        restaurantDTO.setManagerName(restaurant.getManagerName());
        restaurantDTO.setContact(restaurant.getContact());
        restaurantDTO.setRestaurant_image_Url(restaurant.getRestaurant_image_Url());
        restaurantDTO.setMerchantId(restaurant.getMerchantId());
        return restaurantDTO;
    }
    
    
    @Override
    public Restaurant removeRestaurant(Integer restaurantId) {

        Restaurant savedRestaurant = validateRestaurant(restaurantId);
        addressService.deleteAddress(savedRestaurant.getAddressId());
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
        savedRestaurantDTO.setAddress(validateAddress(restaurant.getAddressId()));
        savedRestaurantDTO.setManagerName(restaurant.getManagerName());
        savedRestaurantDTO.setRestaurant_image_Url(restaurant.getRestaurant_image_Url());
        return savedRestaurantDTO;
    }

    private Address validateAddress(Integer addressId){

        Address address = addressService.getAddress(addressId);
        if(address==null) throw new AddressException("Invalid address id : "+addressId);
        return address;

    }

    private Restaurant validateRestaurant(Integer restaurantId){

        return restaurantRepository.findById(restaurantId).orElseThrow(()-> new RestaurantException("Invalid restaurant id : "+restaurantId));

    }

	@Override
	public List<RestaurantInfoDTO> getAllRestaurants() {
	  return restaurantRepository.findAllRestaurants();
	}
}




