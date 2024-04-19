package com.Foodfunapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Foodfunapp.model.OrderRequest;

public interface PaymentRepository extends JpaRepository<OrderRequest, String> {

	OrderRequest findByUserId(Integer userId);

	OrderRequest findByPaymentId(String paymentId);

}