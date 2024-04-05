//package com.RestaurantService.demo.Service;
//
//import com.RestaurantService.demo.DTO.ItemsInRestaurantDTO;
//import com.RestaurantService.demo.DTO.RestaurantsInItemDTO;
//import com.RestaurantService.demo.Model.Address;
//import com.RestaurantService.demo.Model.Item;
//import com.RestaurantService.demo.Model.Restaurant;
//import com.RestaurantService.demo.Repository.RestaurantRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class RestaurantServiceImplTest {
//
//    @Autowired
//    RestaurantService restaurantService;
//
//    @MockBean
//    RestaurantRepository restaurantRepository;
//
//    @MockBean
//    AddressService addressService;
//
//    @Test
//    void addRestaurant() {
//
//        Restaurant restaurant = new Restaurant();
//        restaurant.setAddressId(1);
//        List<Item> items = new ArrayList<>();
//        Item items1 = new Item();
//        //items1.getRestaurant().add(restaurant);
//        items1.setItemId(1);
//        items.add(items1);
//        restaurant.getItems().add(items1);
//        when(restaurantRepository.save(new Restaurant())).thenReturn(restaurant);
//
//        Address address = new Address();
//        address.setAddressId(1);
//
//        when(addressService.saveAddress(new Address())).thenReturn(address);
//
//        when(addressService.getAddress(1)).thenReturn(address);
//
//        RestaurantsInItemDTO restaurants = new RestaurantsInItemDTO();
//        restaurants.setAddress(address);
//        System.out.println(restaurantService.addRestaurant(restaurants));
//
//
//    }
//    @Test
//    void updateRestaurant() {
//    }
//
//    @Test
//    void removeRestaurant() {
//    }
//
//    @Test
//    void viewRestaurant() {
//    }
//
//    @Test
//    void viewRestaurantByLocation() {
//    }
//
//    @Test
//    void viewRestaurantByItem() {
//    }
//
//    @Test
//    void addItemToRestaurantMenu() {
//    }
//}