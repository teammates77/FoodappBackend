package com.foodapp.orderdetails.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.orderdetails.dto.AddOrderDetailsDTO;
import com.foodapp.orderdetails.dto.ItemsInRestaurantOrderDTO;
import com.foodapp.orderdetails.dto.OrderDetailsDTO;
import com.foodapp.orderdetails.dto.OrderItemDTO;
import com.foodapp.orderdetails.exceptions.CartException;
import com.foodapp.orderdetails.exceptions.OrderException;
import com.foodapp.orderdetails.model.FoodCart;
import com.foodapp.orderdetails.model.Item;
import com.foodapp.orderdetails.model.OrderDetails;
import com.foodapp.orderdetails.model.OrderItem;
import com.foodapp.orderdetails.repository.OrderDetailsRepository;
import com.foodapp.orderdetails.repository.OrderItemRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

//    @Autowired
//    OrderDetailsRepository orderDetailsRepository;
//
//    @Autowired
//    CartService cartService;

	private final OrderDetailsRepository orderDetailsRepository;
	private final CartService cartService;
	private final OrderItemRepository orderItemRepository;

	@Autowired
	public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository, CartService cartService,
			OrderItemRepository orderItemRepository) {
		this.orderDetailsRepository = orderDetailsRepository;
		this.cartService = cartService;
		this.orderItemRepository = orderItemRepository;
	}

	/*------- written by  -----------*/
	@Override
	public OrderDetails updateOrder(OrderDetails orderDetails) {

		validateCart(orderDetails.getCartId());

		return orderDetailsRepository.save(orderDetails);

	}

	/*------- written by  -----------*/

	@Override
	public OrderDetails removeOrder(Integer orderId) {

		OrderDetails orderDetails = validateOrderDetails(orderId);

		orderDetailsRepository.delete(orderDetails);

		return orderDetails;

	}

	/*------- written by  JeevanReddy-----------*/
	@Override
	public OrderDetailsDTO viewOrder(Integer orderId) {

		OrderDetails orderDetails = validateOrderDetails(orderId);

		return getDTOFromOrder(orderDetails);

	}

	/*------- written by    -----------*/
	@Override
	public List<OrderDetailsDTO> viewOrderOfCustomer(Integer cartId) {

		List<OrderDetails> orderDetails = orderDetailsRepository.findByCartId(cartId);

		if (orderDetails.isEmpty())
			throw new OrderException("No orders found");

		List<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();

		orderDetails.stream().forEach(el -> orderDetailsDTOS.add(getDTOFromOrder(el)));

		return orderDetailsDTOS;

	}

	/*------- written by  JeevanReddy-----------*/
	private FoodCart validateCart(Integer cartId) {

		FoodCart foodCart = cartService.getCart(cartId);

		if (foodCart == null)
			throw new CartException("Food Cart does not exists");

		return foodCart;

	}

	/*------- written by  JeevanReddy-----------*/
	private OrderDetails validateOrderDetails(Integer orderId) {

		return orderDetailsRepository.findById(orderId)
				.orElseThrow(() -> new OrderException("Invalid order id : " + orderId));

	}
	

	/*------- written by  JeevanReddy-----------*/

	private OrderDetailsDTO getDTOFromOrder(OrderDetails orderDetails) {

		OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();

		FoodCart foodCart = validateCart(orderDetails.getCartId());

		orderDetailsDTO.setFoodCart(foodCart);

		orderDetailsDTO.setStatus(orderDetails.getStatus());

		orderDetailsDTO.setOrderId(orderDetails.getOrderId());

		orderDetailsDTO.setTimeSpan(orderDetails.getTimeSpan());

		return orderDetailsDTO;

	}

	/*------------Written by JeevanReddy---------------*/
//  @Override
//  public OrderDetails addOrder(Integer cartId) {
//
//      validateCart(cartId);
//
//      OrderDetails orderDetails = new OrderDetails();
//
//      orderDetails.setCartId(cartId);
//
//      orderDetails.setStatus("Pending");
//
//      orderDetails.setTimeSpan(LocalDateTime.now());
//      
//      FoodCart foodCart = cartService.getCart(cartId);
//      for (Item foodItem : foodCart.getItems()) {
//          OrderItem orderItem = new OrderItem();
//          orderItem.setItemId(foodItem.getItemId());
//          orderItem.setItemName(foodItem.getItemName());
//          orderItem.setQuantity(foodItem.getQuantity());
//          orderItem.setCost(foodItem.getCost());
//          orderItem.setRestaurantId(foodItem.getRestaurantId());
//          orderItem.setUserId(foodCart.getUserId());
//          //orderItem.setOrderDetails(orderDetails);
//          
//          orderDetails.getItems().add(orderItem);
//      }
//
//      return orderDetailsRepository.save(orderDetails);
//
//  }

	/*------- written by  JeevanReddy-----------*/
	@Override
	public AddOrderDetailsDTO addOrder(Integer cartId) {
		validateCart(cartId);

		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setCartId(cartId);
		orderDetails.setStatus("Pending");
		orderDetails.setTimeSpan(LocalDateTime.now());

		FoodCart foodCart = cartService.getCart(cartId);
		for (Item foodItem : foodCart.getItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setItemId(foodItem.getItemId());
			orderItem.setItemName(foodItem.getItemName());
			orderItem.setQuantity(foodItem.getQuantity());
			orderItem.setCost(foodItem.getCost());
			orderItem.setRestaurantId(foodItem.getRestaurantId());
			orderItem.setUserId(foodCart.getUserId());
			orderItem.setOrderDetails(orderDetails);

			orderDetails.getItems().add(orderItem);
		}

		orderDetails = orderDetailsRepository.save(orderDetails);
		return convertToDTO(orderDetails);
	}

	private AddOrderDetailsDTO convertToDTO(OrderDetails orderDetails) {
		AddOrderDetailsDTO dto = new AddOrderDetailsDTO();
		dto.setTimeSpan(orderDetails.getTimeSpan());
		dto.setCartId(orderDetails.getCartId());
		dto.setStatus(orderDetails.getStatus());
		dto.setItems(orderDetails.getItems().stream().map(this::convertToDTO).collect(Collectors.toList()));
		return dto;
	}

	private OrderItemDTO convertToDTO(OrderItem orderItem) {
		OrderItemDTO dto = new OrderItemDTO();
		dto.setItemId(orderItem.getItemId());
		dto.setItemName(orderItem.getItemName());
		dto.setQuantity(orderItem.getQuantity());
		dto.setCost(orderItem.getCost());
		dto.setUserId(orderItem.getUserId());
		dto.setRestaurantId(orderItem.getRestaurantId());
		return dto;
	}

	/*------- written by  JeevanReddy-----------*/
	@Override
	public List<ItemsInRestaurantOrderDTO> viewOrderOfRestaurant(Integer restaurantId) {

		List<OrderItem> orderedItems = orderItemRepository.findByRestaurantId(restaurantId);

		// Convert the list of ordered items to DTOs
		List<ItemsInRestaurantOrderDTO> itemsInRestaurantOrderDTOList = new ArrayList<>();
		for (OrderItem orderItem : orderedItems) {
			ItemsInRestaurantOrderDTO dto = new ItemsInRestaurantOrderDTO();
			dto.setItemId(orderItem.getItemId());
			dto.setItemName(orderItem.getItemName());
			dto.setQuantity(orderItem.getQuantity());
			dto.setCost(orderItem.getCost());
			dto.setRestaurantId(orderItem.getRestaurantId());

			itemsInRestaurantOrderDTOList.add(dto);
		}

		return itemsInRestaurantOrderDTOList;
	}

}
