//package com.FoodCartService.demo.Service;
//
//import com.FoodCartService.demo.DTO.FoodCartDTO;
//import com.FoodCartService.demo.DTO.ItemDTO;
//import com.FoodCartService.demo.Exceptions.CartException;
//import com.FoodCartService.demo.Model.Item;
//import com.FoodCartService.demo.Model.Restaurant;
//import com.FoodCartService.demo.Repository.FoodCartRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.function.Executable;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//@SpringBootTest
//class FoodCartServiceImplTest {
//
//    private Integer cartId;
//
//    static Integer uId = 1;
//    @Autowired
//    FoodCartService foodCartService;
//
////    @Autowired
////    FoodCartRepository foodCartRepository;
////
//    @MockBean
//    ItemService itemService;
//
//    @MockBean
//    RestaurantService restaurantService;
//
////    @Test
////    @BeforeEach
////    void createCartForUser() {
////
////        FoodCartDTO foodCartDTO = new FoodCartDTO();
////
////        foodCartDTO.setUserId(uId);
////        uId++;
////
////        FoodCartDTO foodCart = foodCartService.createCartForUser(foodCartDTO);
////
////        this.cartId = foodCart.getCartId();
////
////        Assertions.assertArrayEquals(new Integer[]{foodCart.getUserId()}, new Integer[]{foodCartDTO.getUserId()});
////
////    }
//
////    @Test
////    void removeCart() {
////
////        foodCartService.removeCart(this.cartId);
////
////        Assertions.assertThrows(CartException.class, ()-> foodCartService.viewCart(this.cartId));
////
////    }
//
//    @Test
//    void viewCart() {
//
//        Assertions.assertTrue(foodCartService.viewCart(44)!=null);
//
//    }
//
//    @Test
//    void getCartOfUser() {
//
//        Assertions.assertTrue(foodCartService.getCartOfUser(1)!=null);
//
//    }
//
//    @Test
//    void addItemToCart() {
//
//        Restaurant restaurant = new Restaurant();
//        restaurant.setRestaurantId(1);
//        ItemDTO itemDTO = new ItemDTO();
//        itemDTO.setItemId(1);
//        restaurant.getItems().add(itemDTO);
//        itemDTO.setRestaurant(restaurant);
//
//        Mockito.when(restaurantService.getRestaurant(1)).thenReturn(restaurant);
//
//        Mockito.when(itemService.addItem(itemDTO)).thenReturn(new Item());
//
//        Assertions.assertTrue(foodCartService.addItemToCart(44, 1, 1)!=null);
//
//    }
//
//    @Test
//    void increaseOrReduceQuantityOfItem() {
//
//
//
//    }
////
////    @Test
////    void removeItemFromCart() {
////    }
////
////    @Test
////    void clearCart() {
////    }
//}