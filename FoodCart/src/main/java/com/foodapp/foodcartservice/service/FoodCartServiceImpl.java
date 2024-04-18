package com.foodapp.foodcartservice.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.foodcartservice.dto.CartResponseDTO;
import com.foodapp.foodcartservice.dto.FoodCartDTO;
import com.foodapp.foodcartservice.dto.ItemDTO;
import com.foodapp.foodcartservice.dto.ItemInCartDTO;
import com.foodapp.foodcartservice.exceptions.CartException;
import com.foodapp.foodcartservice.exceptions.ItemException;
import com.foodapp.foodcartservice.exceptions.RestaurantException;
import com.foodapp.foodcartservice.model.CartItem;
import com.foodapp.foodcartservice.model.FoodCart;
import com.foodapp.foodcartservice.model.Restaurant;
import com.foodapp.foodcartservice.repository.FoodCartRepository;
import com.foodapp.foodcartservice.repository.ItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FoodCartServiceImpl implements FoodCartService{

	 private final FoodCartRepository foodCartRepository;
	    private final ItemRepository itemRepository;
	    private final ItemService itemService;
	    private final RestaurantService restaurantService;

	    @Autowired
	    public FoodCartServiceImpl(FoodCartRepository foodCartRepository,
	                               ItemRepository itemRepository,
	                               ItemService itemService,
	                               RestaurantService restaurantService) {
	        this.foodCartRepository = foodCartRepository;
	        this.itemRepository = itemRepository;
	        this.itemService = itemService;
	        this.restaurantService = restaurantService;
	    }

    @Override
    public FoodCartDTO createCartForUser(FoodCartDTO foodCartDTO){

        FoodCart foodCart = getCartFromDTO(foodCartDTO);

        FoodCart savedCart = foodCartRepository.save(foodCart);

        foodCartDTO.setCartId(savedCart.getCartId());

        return foodCartDTO;

    }

//@Override
//public FoodCartDTO createCartForUser(FoodCartDTO foodCartDTO) {
//    FoodCart foodCart = new FoodCart();
//    foodCart.setUserId(foodCartDTO.getUserId());
//
//    List<Item> items = new ArrayList<>();
//    for (Item itemDTO : foodCartDTO.getItems()) {
//        Item item = new Item();
//        item.setItemId(itemDTO.getItemId());
//        item.setItemName(itemDTO.getItemName());
//        item.setCategory(itemDTO.getCategory());
//        item.setQuantity(itemDTO.getQuantity());
//        item.setRestaurantId(itemDTO.getRestaurantId());
//        // Set other fields accordingly
//
//        items.add(item);
//    }
//
//    foodCart.setItems(items);
//    foodCartRepository.save(foodCart); // Save the cart to the database
//
//    return foodCartDTO; // Return the saved DTO
//}

    public FoodCartDTO removeCart(Integer cartId){

        FoodCart foodCart = foodCartRepository.findById(cartId).orElseThrow(()-> new CartException("Innvalid cart id : "+cartId));

        foodCartRepository.delete(foodCart);

        return getCartDTOFromCart(foodCart);

    }

    @Override
    public FoodCartDTO viewCart(Integer cartId) {

        FoodCart foodCart = foodCartRepository.findById(cartId).orElseThrow(()-> new CartException("Invalid cart id : "+cartId));

        return getCartDTOFromCart(foodCart);

    }

//    @Override
//    public FoodCart getCartOfUser(Integer userId) {
//
//        return foodCartRepository.findByUserId(userId).orElseThrow(()-> new CartException("Invalid user id : "+userId));
//
//    }
    
    @Override
    public CartResponseDTO getCartOfUser(Integer userId) {
        FoodCart foodCart = foodCartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartException("Invalid user id: " + userId));
 
        List<ItemInCartDTO> itemsInCart = foodCart.getItems().stream()
                .map(item -> new ItemInCartDTO(item.getCartItemId(), item.getItemId(), item.getItemName(), item.getQuantity(), item.getCost()))
                .collect(Collectors.toList());
 
        Double totalCost = foodCart.getItems().stream()
                .mapToDouble(CartItem::getCost)
                .sum();
 
        return new CartResponseDTO(userId, itemsInCart, totalCost);
    }

    @Override
    public FoodCart addItemToCart(Integer cartId, Integer itemId, Integer restaurantId) {

        FoodCart foodCart = validateCart(cartId);

        List<CartItem> items = foodCart.getItems();

       boolean isPresent = items.stream().anyMatch(item-> item.getItemId().equals(itemId));

        if(isPresent) throw new CartException("Item already present in the cart");

        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);

        if(restaurant==null) throw new RestaurantException("Restaurant does not exists with restaurant id : "+restaurantId);

        List<ItemDTO> restaurantItems = restaurant.getItems();

        Optional<ItemDTO> itemOpt = restaurantItems.stream().filter(el-> el.getItemId().equals(itemId)).findAny();

        if(itemOpt.isEmpty()) throw new ItemException("item does not exists in this restaurant");

        ItemDTO itemDTO = itemOpt.get();

        itemDTO.setRestaurant(restaurant);

        itemDTO.setFoodCart(foodCart);

        itemDTO.setQuantity(1);

        CartItem savedItem = itemService.addItem(itemDTO);

        foodCart.getItems().add(savedItem);

        return foodCartRepository.save(foodCart);

    }

//    @Override
//    public FoodCart increaseOrReduceQuantityOfItem(Integer cartId, Integer itemId, Integer quantity) {
//
//        FoodCart foodCart = validateCart(cartId);
//
//        List<CartItem> items = foodCart.getItems();
//
//        Optional<CartItem> itemOpt = items.stream().filter(el-> el.getItemId().equals(itemId)).findAny();
//
//        if(!itemOpt.isPresent()) throw new ItemException("item does not exists with item id : "+itemId);
//
//        CartItem savedItem = itemOpt.get();
//
//        savedItem.setQuantity(quantity+ savedItem.getQuantity());
//
//        itemRepository.save(savedItem);
//
//        return foodCart;
//    }
    
    @Override
    public FoodCart increaseOrReduceQuantityOfItem(Integer cartId, Integer itemId, Integer quantity) {
        FoodCart foodCart = validateCart(cartId);
        List<CartItem> items = foodCart.getItems();

        Optional<CartItem> itemOpt = items.stream().filter(el -> el.getItemId().equals(itemId)).findAny();

        if (!itemOpt.isPresent()) throw new ItemException("Item does not exist with item id: " + itemId);

        CartItem savedItem = itemOpt.get();
        int oldQuantity = savedItem.getQuantity();
        int newQuantity = quantity + oldQuantity;
        double cost=savedItem.getCost();
        double itemPrice=cost/oldQuantity;
        
        // Retrieve the price of the item
      //  double itemPrice = savedItem.getCost();
        	

        // Update the quantity and cost of the item
        savedItem.setQuantity(newQuantity);
        savedItem.setCost(itemPrice * newQuantity);

        // Update the total cost of the food cart
       // double totalCost = savedItem.getCost();
        //totalCost += itemPrice * (newQuantity - oldQuantity);
       // savedItem.setCost(totalCost);
       // foodCart.setTotalCost(totalCost);

        // Save the updated item and food cart
        itemRepository.save(savedItem);
        foodCartRepository.save(foodCart);

        return foodCart;
    }


    @Override
    public CartItem removeItemFromCart(Integer cartItemId) {

    	CartItem itemToRemove = itemService.validateItem(cartItemId);

        itemRepository.delete(itemToRemove);

        return itemToRemove;

    }

    @Override
    public FoodCart clearCart(Integer cartId) {

        FoodCart validatedCart = validateCart(cartId);

//        itemRepository.clearCart(cartId);
        List<CartItem> items = validatedCart.getItems();

        items.forEach(item-> itemRepository.delete(item));

        return validateCart(cartId);

    }

    private FoodCart validateCart(Integer cartId){
        return foodCartRepository.findById(cartId).orElseThrow(()-> new CartException("Cart does not exists with id : "+cartId));
    }

    private FoodCart getCartFromDTO(FoodCartDTO foodCartDTO){

        FoodCart foodCart = new FoodCart();

        foodCart.setUserId(foodCartDTO.getUserId());

        foodCart.setItems(foodCartDTO.getItems());

        foodCart.setCartId(foodCartDTO.getCartId());

        return foodCart;


    }

    private FoodCartDTO getCartDTOFromCart(FoodCart foodCart){

        FoodCartDTO foodCartDTO = new FoodCartDTO();

        foodCartDTO.setUserId(foodCart.getUserId());

        foodCartDTO.setItems(foodCart.getItems());

        foodCartDTO.setCartId(foodCart.getCartId());

        return foodCartDTO;


    }


}
