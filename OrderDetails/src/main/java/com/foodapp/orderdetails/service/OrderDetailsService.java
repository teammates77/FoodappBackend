package com.foodapp.orderdetails.service;

import java.util.List;

import com.foodapp.orderdetails.dto.AddOrderDetailsDTO;
import com.foodapp.orderdetails.dto.ItemsInRestaurantOrderDTO;
import com.foodapp.orderdetails.dto.OrderDetailsDTO;
import com.foodapp.orderdetails.dto.OrderItemDTO;
import com.foodapp.orderdetails.dto.UserOrdersDTO;
import com.foodapp.orderdetails.model.OrderDetails;
import com.foodapp.orderdetails.model.OrderItem;

public interface OrderDetailsService {

 //   public AddOrderDetailsDTO addOrder(Integer cartId);

    public OrderDetails updateOrder(OrderDetails orderDetails);

    public OrderDetails removeOrder(Integer orderId);

    public OrderDetailsDTO viewOrder(Integer orderId);


    public AddOrderDetailsDTO addOrder(Integer cartId, Integer addressId, String paymentId);
    public List<UserOrdersDTO> viewOrderOfCustomer(Integer userId);

	List<ItemsInRestaurantOrderDTO> viewOrderOfRestaurant(Integer restaurantId);



	public UserOrdersDTO updateDeliveryStatus(Integer itemId, String deliveryStatus);


}
