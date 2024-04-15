package com.foodapp.restaurantservice.controller;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.foodapp.restaurantservice.dto.ItemsInRestaurantDTO;
import com.foodapp.restaurantservice.dto.UpdateItemDTO;
import com.foodapp.restaurantservice.model.Item;
import com.foodapp.restaurantservice.service.ItemService;

import java.util.List;


@Validated
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/fooddelivery/items")
public class ItemController {

	private final ItemService itemService;
	
	@Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/add/{restaurantId}")
    public ResponseEntity<Item> addItemToRestaurant(@RequestBody Item item,@PathVariable Integer restaurantId){

        Item addedItem = itemService.addItemToRestaurant(item, restaurantId);

        return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
        

    }

    
    @PutMapping("/update")
    public ResponseEntity<UpdateItemDTO> updateItem(@RequestBody Item item){
    	UpdateItemDTO updatedItem = itemService.updateItem(item);

        return new ResponseEntity<>(updatedItem,HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Boolean> removeItem(@PathVariable Integer itemId){

        boolean removedItem = itemService.removeItem(itemId);

        return new ResponseEntity<>(removedItem,HttpStatus.OK);

    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> viewItemById(@PathVariable Integer itemId){

        Item item = itemService.viewItem(itemId);

        return new ResponseEntity<>(item,HttpStatus.OK);

    }

    @GetMapping("/itembycategory/{categoryId}")
    public ResponseEntity<List<ItemsInRestaurantDTO>> viewItemByCategoryName(@PathVariable Integer categoryId){

        List<ItemsInRestaurantDTO> items = itemService.viewItemsByCategory(categoryId);

        return new ResponseEntity<>(items,HttpStatus.OK);

    }

    @GetMapping("/itembyrestaurant/{restaurantId}")
    public ResponseEntity<List<ItemsInRestaurantDTO>> viewItemByRestaurant(@PathVariable Integer restaurantId){

    	
        List<ItemsInRestaurantDTO> items = itemService.viewItemsByRestaurant(restaurantId);

        return new ResponseEntity<List<ItemsInRestaurantDTO>>(items,HttpStatus.OK);

    }
//    public ResponseEntity<String> fallBackRetryHandler(FeignException exc){
//        System.out.println(exc);
//        return new ResponseEntity<>("All retries have been exhausted, please try again later", HttpStatus.SERVICE_UNAVAILABLE);
//    }


}
