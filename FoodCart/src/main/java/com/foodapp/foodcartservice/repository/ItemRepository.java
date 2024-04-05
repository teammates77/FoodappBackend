package com.foodapp.foodcartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.foodcartservice.model.CartItem;

public interface ItemRepository extends JpaRepository<CartItem, Integer> {

//    @Query("DELETE item from Item item INNER JOIN item.foodCarts foodCart where item.itemId = :itemId and foodCart.cartId = :cartId" )
//    public void deleteItemFromCart(@Param("itemId") Integer itemId, @Param("cartId") Integer cartId);

//    @Query("DELETE item from Item item INNER JOIN item.foodCarts foodCart where foodCart.cartId = :cartId" )
//    public void clearCart(@Param("cartId") Integer cartId);

}
