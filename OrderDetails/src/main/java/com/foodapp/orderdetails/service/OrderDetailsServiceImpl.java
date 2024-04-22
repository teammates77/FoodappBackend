package com.foodapp.orderdetails.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.orderdetails.dto.AddOrderDetailsDTO;
import com.foodapp.orderdetails.dto.ItemsInRestaurantOrderDTO;
import com.foodapp.orderdetails.dto.OrderDetailsDTO;
import com.foodapp.orderdetails.dto.OrderItemDTO;
import com.foodapp.orderdetails.dto.UserOrdersDTO;
import com.foodapp.orderdetails.exceptions.CartException;
import com.foodapp.orderdetails.exceptions.OrderException;
import com.foodapp.orderdetails.model.Address;
import com.foodapp.orderdetails.model.FoodCart;
import com.foodapp.orderdetails.model.Item;
import com.foodapp.orderdetails.model.OrderDetails;
import com.foodapp.orderdetails.model.OrderItem;
import com.foodapp.orderdetails.model.OrderRequest;
import com.foodapp.orderdetails.repository.OrderDetailsRepository;
import com.foodapp.orderdetails.repository.OrderItemRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	private final OrderDetailsRepository orderDetailsRepository;
	private final CartService cartService;
	private final OrderItemRepository orderItemRepository;
	private final PaymentService paymentService;
	private final AddressService addressService;

	@Autowired
	public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository, CartService cartService,
			OrderItemRepository orderItemRepository, AddressService addressService, PaymentService paymentService) {
		this.orderDetailsRepository = orderDetailsRepository;
		this.cartService = cartService;
		this.orderItemRepository = orderItemRepository;
		this.addressService = addressService;
		this.paymentService=paymentService;
	}

	@Override
	public AddOrderDetailsDTO addOrder(Integer cartId, Integer addressId,String razorpayOrderId) {
		validateCart(cartId);

		Address address = addressService.getAddress(addressId);
		//OrderRequest paymentDetails = paymentService.getPaymentByPaymentId(paymentId);
		OrderRequest paymentDetails =paymentService.getPaymentByRazorpayOrderId(razorpayOrderId);
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setCartId(cartId);
       
        if (paymentDetails != null) {
            orderDetails.setStatus("Success");
        }
	
		orderDetails.setTimeSpan(LocalDateTime.now());
		orderDetails.setAddressId(addressId);
		orderDetails.setRazorpayOrderId(razorpayOrderId);
		orderDetails.setPaymentId(paymentDetails.getPaymentId());;
		//orderDetails.setPaymentId(paymentId);

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
			orderItem.setDeliveryStatus("Pending");

			orderDetails.getItems().add(orderItem);
		}

		orderDetails = orderDetailsRepository.save(orderDetails);
		return convertToDTO(orderDetails);
	}

	
	@Override
	public List<UserOrdersDTO> viewOrderOfCustomer(Integer userId) {
		List<OrderItem> orderedItems = orderItemRepository.findByUserId(userId);
		List<UserOrdersDTO> itemsInUserOrderDTOList = new ArrayList<>();
		for (OrderItem orderItem : orderedItems) {
			itemsInUserOrderDTOList.add(convertToUserOrdersDTO(orderItem));
		}
		return itemsInUserOrderDTOList;
	}
	
	@Override
	public List<ItemsInRestaurantOrderDTO> viewOrderOfRestaurant(Integer restaurantId) {

		List<OrderItem> orderedItems = orderItemRepository.findByRestaurantId(restaurantId);

		List<ItemsInRestaurantOrderDTO> itemsInRestaurantOrderDTOList = new ArrayList<>();
		for (OrderItem orderItem : orderedItems) {
			ItemsInRestaurantOrderDTO dto = new ItemsInRestaurantOrderDTO();
			dto.setItemId(orderItem.getItemId());
			dto.setItemName(orderItem.getItemName());
			dto.setQuantity(orderItem.getQuantity());
			dto.setCost(orderItem.getCost());
			dto.setRestaurantId(orderItem.getRestaurantId());
			dto.setDeliveryStatus(orderItem.getDeliveryStatus());
			dto.setOrderItemId(orderItem.getOrderItemId());
			Integer addressId = orderItem.getOrderDetails().getAddressId();
			Address address = addressService.getAddress(addressId);
			dto.setAddress(address);

			itemsInRestaurantOrderDTOList.add(dto);
		}
		return itemsInRestaurantOrderDTOList;
	}


	@Override
	public UserOrdersDTO updateDeliveryStatus(Integer orderItemId, String deliveryStatus) {
		OrderItem orderItem = getOrderByOrderItemId(orderItemId);
		orderItem.setDeliveryStatus(deliveryStatus);
		orderItemRepository.save(orderItem);
		return convertToUserOrdersDTO(orderItem);
	}
	
	@Override
	public OrderDetails updateOrder(OrderDetails orderDetails) {
		validateCart(orderDetails.getCartId());
		return orderDetailsRepository.save(orderDetails);
	}
	
	@Override
	public OrderDetails removeOrder(Integer orderId) {
		OrderDetails orderDetails = validateOrderDetails(orderId);
		orderDetailsRepository.delete(orderDetails);
		return orderDetails;

	}

	@Override
	public OrderDetailsDTO viewOrder(Integer orderId) {
		OrderDetails orderDetails = validateOrderDetails(orderId);
		return getDTOFromOrder(orderDetails);

	}



	private OrderItem getOrderByOrderItemId(Integer orderItemId) {
		return orderItemRepository.findByOrderItemId(orderItemId)
				.orElseThrow(() -> new IllegalArgumentException("Order item not found with ID: " + orderItemId));
	}

	private UserOrdersDTO convertToUserOrdersDTO(OrderItem orderItem) {
		
	  //String paymentId = orderItem.getOrderDetails().getPaymentId();
	  //OrderRequest paymentDetails = paymentService.getOrdersByPaymentId(paymentId);
		UserOrdersDTO dto = new UserOrdersDTO();
		dto.setItemId(orderItem.getItemId());
		dto.setItemName(orderItem.getItemName());
		dto.setQuantity(orderItem.getQuantity());
		dto.setCost(orderItem.getCost());
		dto.setUserid(orderItem.getUserId());
		dto.setDeliveryStatus(orderItem.getDeliveryStatus());
		dto.setStatus(orderItem.getOrderDetails().getStatus());
		dto.setOrderId(orderItem.getOrderDetails().getOrderId());
		dto.setPaymentId(orderItem.getOrderDetails().getPaymentId());
		dto.setOrderItemId(orderItem.getOrderItemId());
		//dto.setPaymentId(orderItem.getOrderDetails().getPaymentId());
	  //dto.setPaymentDetails(paymentDetails);
		return dto;
	}

	private FoodCart validateCart(Integer cartId) {
		FoodCart foodCart = cartService.getCart(cartId);
		if (foodCart == null)
			throw new CartException("Food Cart does not exists");
		return foodCart;

	}

	private OrderDetails validateOrderDetails(Integer orderId) {
		return orderDetailsRepository.findById(orderId)
				.orElseThrow(() -> new OrderException("Invalid order id : " + orderId));

	}

	private OrderDetailsDTO getDTOFromOrder(OrderDetails orderDetails) {
		OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();

		FoodCart foodCart = validateCart(orderDetails.getCartId());
		orderDetailsDTO.setFoodCart(foodCart);
		orderDetailsDTO.setStatus(orderDetails.getStatus());
		orderDetailsDTO.setOrderId(orderDetails.getOrderId());
		orderDetailsDTO.setTimeSpan(orderDetails.getTimeSpan());

		return orderDetailsDTO;
	}


	private AddOrderDetailsDTO convertToDTO(OrderDetails orderDetails) {
		AddOrderDetailsDTO dto = new AddOrderDetailsDTO();
		dto.setTimeSpan(orderDetails.getTimeSpan());
		dto.setCartId(orderDetails.getCartId());
		dto.setStatus(orderDetails.getStatus());
		dto.setAddressId(orderDetails.getAddressId());
		//dto.setPaymentId(orderDetails.getPaymentId());
		dto.setRazorpayOrderId(orderDetails.getRazorpayOrderId());
		dto.setItems(orderDetails.getItems().stream().map(this::convertToDTOitems).collect(Collectors.toList()));
		return dto;
	}

	private OrderItemDTO convertToDTOitems(OrderItem orderItem) {
		OrderItemDTO dto = new OrderItemDTO();
		dto.setItemId(orderItem.getItemId());
		dto.setItemName(orderItem.getItemName());
		dto.setQuantity(orderItem.getQuantity());
		dto.setCost(orderItem.getCost());
		dto.setUserId(orderItem.getUserId());
		dto.setRestaurantId(orderItem.getRestaurantId());
		dto.setDeliveryStatus(orderItem.getDeliveryStatus());
		return dto;
	}


}
