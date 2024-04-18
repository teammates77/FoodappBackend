package com.foodapp.restaurantservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.restaurantservice.dto.ItemsInRestaurantDTO;
import com.foodapp.restaurantservice.dto.UpdateItemRequestDTO;
import com.foodapp.restaurantservice.exceptions.ItemException;
import com.foodapp.restaurantservice.exceptions.RestaurantException;
import com.foodapp.restaurantservice.model.Category;
import com.foodapp.restaurantservice.model.Item;
import com.foodapp.restaurantservice.model.Restaurant;
import com.foodapp.restaurantservice.repository.ItemRepository;
import com.foodapp.restaurantservice.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {
	private static final String ITEM_NOT_FOUND_MESSAGE = "Item does not exist with item id : ";

	private final ItemRepository itemRepository;
	private final CategoryService categoryService;
	private final RestaurantRepository restaurantRepository;

	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository, CategoryService categoryService,
			RestaurantRepository restaurantRepository) {
		this.itemRepository = itemRepository;
		this.categoryService = categoryService;
		this.restaurantRepository = restaurantRepository;
	}

	@Override
	public Item addItemToRestaurant(Item item, Integer restaurantId) {

		Restaurant restaurant = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new RestaurantException("Invalid restaurant id : " + restaurantId));
		item.setRestaurant(restaurant);
		return itemRepository.save(item);
	}

	@Override
	public Item viewItem(Integer itemId) {

		Item item = itemRepository.findById(itemId)
				.orElseThrow(() -> new ItemException(ITEM_NOT_FOUND_MESSAGE + itemId));

		return item;

	}


	public UpdateItemRequestDTO updateItem(UpdateItemRequestDTO requestDTO) {
		Item validatedItem = itemRepository.findById(requestDTO.getItemId())
				.orElseThrow(() -> new ItemException(ITEM_NOT_FOUND_MESSAGE + requestDTO.getItemId()));

		validateCategory(requestDTO.getCategoryId());

		Restaurant restaurant = restaurantRepository.findById(requestDTO.getRestaurantId())
				.orElseThrow(() -> new ItemException("Restaurant not found with id: " + requestDTO.getRestaurantId()));

		validatedItem.setItemName(requestDTO.getItemName());
		validatedItem.setDescription(requestDTO.getDescription());
		validatedItem.setItemimageUrl(requestDTO.getItemimageUrl());
		validatedItem.setCost(requestDTO.getCost());
		validatedItem.setRestaurant(restaurant);
		validatedItem.setCategoryId(requestDTO.getCategoryId());

		itemRepository.save(validatedItem);

		return requestDTO;
	}

	@Override
	public boolean removeItem(Integer itemId) {

		Item validatedItem = itemRepository.findById(itemId)
				.orElseThrow(() -> new ItemException(ITEM_NOT_FOUND_MESSAGE + itemId));

		itemRepository.delete(validatedItem);

		return true;

	}

	@Override
	public List<ItemsInRestaurantDTO> viewItemsByCategory(Integer categoryId) {

		Category category = categoryService.getCategoryById(categoryId);

		if (category == null)
			throw new ItemException("Category does not exists with name :" + categoryId);

		List<Item> items = itemRepository.findByCategoryId(categoryId);
		List<ItemsInRestaurantDTO> itemDTOs = new ArrayList<>();
		for (Item item : items) {
			itemDTOs.add(getDtoFromItemexceptrestAddress(item));
		}

		return itemDTOs;
	}

	@Override
	public List<ItemsInRestaurantDTO> viewItemsByRestaurant(Integer restaurantId) {

		Restaurant restaurant = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new RestaurantException("Invalid restaurant id : " + restaurantId));

		List<Item> items = itemRepository.findByRestaurant_RestaurantId(restaurantId);

		List<ItemsInRestaurantDTO> itemDTOs = new ArrayList<>();
		for (Item item : items) {
			itemDTOs.add(getDtoFromItemexceptrestAddress(item));
		}

		return itemDTOs;
	}


	// to skip the repeating restaurant details
	public ItemsInRestaurantDTO getDtoFromItemexceptrestAddress(Item item) {

		ItemsInRestaurantDTO itemDTO = new ItemsInRestaurantDTO();

		itemDTO.setItemId(item.getItemId());

		itemDTO.setItemName(item.getItemName());

		itemDTO.setCost(item.getCost());
		itemDTO.setItemimageUrl(item.getItemimageUrl());
		itemDTO.setDescription(item.getDescription());
		Category category = validateCategory(item.getCategoryId());
		itemDTO.setCategory(category);
		itemDTO.setRestaurantId(item.getRestaurant().getRestaurantId());

		return itemDTO;

	}

	private Category validateCategory(Integer categoryId) {

		Category category = categoryService.getCategoryById(categoryId);

		if (category == null)
			throw new ItemException("Category does not exists with id : " + categoryId);

		return category;

	}

}
