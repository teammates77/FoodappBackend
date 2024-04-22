package com.Foodfunapp.controller;

import java.math.BigInteger;
import java.util.List;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import com.Foodfunapp.model.OrderRequest;
import com.Foodfunapp.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Validated
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/fooddelivery/payment")
public class PaymentController {
	private RazorpayClient client;
	private static final String SECRET_ID1 = "rzp_test_UAAdUZ22B4b8ux";
	private static final String SECRET_KEY1 = "gLEJlQJutBAp1sLZOuPP8WFx";
	private PaymentRepository paymentRepository;

	@Autowired
	public PaymentController(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
		try {
			this.client = new RazorpayClient(SECRET_ID1, SECRET_KEY1);
		} catch (RazorpayException e) {
			throw new RuntimeException("Failed to initialize Razorpay client", e);
		}
	}

	@PostMapping("/createOrder")
	public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
		try {
			OrderRequest response = new OrderRequest();
			Order order = createRazorPayOrder(orderRequest.getAmount());
			String orderId = (String) order.get("id");
			//System.out.println("Order ID: " + orderId);
			response.setRazorpayOrderId(orderId);
			orderRequest.setRazorpayOrderId(orderId);
			response.setAmount(orderRequest.getAmount());
			response.setCustomerName(orderRequest.getCustomerName());
			response.setEmail(orderRequest.getEmail());
			response.setPhoneNumber(orderRequest.getPhoneNumber());
			response.setUserId(orderRequest.getuserId());
			response.setPaymentId(orderRequest.getPaymentId());
			paymentRepository.save(orderRequest);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RazorpayException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error occurred while processing the order", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{userId}")
    public ResponseEntity<?> getPaymentByUserId(@PathVariable Integer userId) {
        List<OrderRequest> orders = paymentRepository.findByUserId(userId);
        if (orders.isEmpty()) {
            return new ResponseEntity<>("No payments found for user ID: " + userId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
//	public ResponseEntity<?> getPaymentByUserId(@PathVariable Integer userId) {
//		OrderRequest order = paymentRepository.findByUserId(userId);
//		if (order == null) {
//			return new ResponseEntity<>("No orders found for the user", HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(order, HttpStatus.OK);
//	}

	@GetMapping("/paymentdetails/{paymentId}")
	public ResponseEntity<?> getPaymentByPaymentId(@PathVariable String paymentId) {
		OrderRequest order = paymentRepository.findByPaymentId(paymentId);
		if (order == null) {
			return new ResponseEntity<>("No payment found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

    @GetMapping("/paymentdetails/razorpay/{razorpayOrderId}")
    public ResponseEntity<?> getPaymentByRazorpayOrderId(@PathVariable String razorpayOrderId) {
        OrderRequest order = paymentRepository.findByRazorpayOrderId(razorpayOrderId);
        if (order == null) {
        	 return new ResponseEntity<>("No payment found for razorpayOrderId: " + razorpayOrderId, HttpStatus.NOT_FOUND);
        	   
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    
    
	private Order createRazorPayOrder(BigInteger amount) throws RazorpayException {
		JSONObject options = new JSONObject();
		options.put("amount", amount.multiply(BigInteger.valueOf(100)));
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		options.put("payment_capture", 1);
		return client.orders.create(options);
	}

}
