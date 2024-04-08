package com.BillService.demo.Service;
 
import com.BillService.demo.DTO.BillDTO;
import com.BillService.demo.Exceptions.BillException;
import com.BillService.demo.Exceptions.OrderException;
import com.BillService.demo.Model.Bill;
import com.BillService.demo.Model.FoodCart;
import com.BillService.demo.Model.Item;
import com.BillService.demo.Model.OrderDetails;
import com.BillService.demo.Repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
 
@Service
public class  BillServiceImpl implements BillService{
 
    
	private final OrderDetailService orderDetailService;
	private final BillRepository billRepository;
	private final CartService cartService;
	
	@Autowired
	public BillServiceImpl(OrderDetailService orderDetailService, CartService cartService,
			BillRepository billRepository) {
		this.orderDetailService = orderDetailService;
		this.cartService = cartService;
		this.billRepository = billRepository;
	}

 
    @Override
    public BillDTO addBill(Bill bill) {
        OrderDetails orderDetails = orderDetailService.getOrderDetails(bill.getOrderDetailId());
        if(orderDetails==null) throw new OrderException("Invalid order id:"+bill.getOrderDetailId());
        bill.setTotalCost(getTotalCost(orderDetails.getFoodCart().getItems()));
        bill.setTotalItem(getTotalItem(orderDetails.getFoodCart().getItems()));
        bill.setTimeSpan(LocalDateTime.now());
        Bill savedBill = billRepository.save(bill);
        return convertBillDTO(savedBill);
 
    }
 
    private Integer getTotalItem(List<Item> items) {
    	 return items.stream().mapToInt(Item::getQuantity).sum();
	}

	private Double getTotalCost(List<Item> items) {
        return items.stream().mapToDouble(item -> item.getQuantity() * item.getCost()).sum();
	}
	
	

	@Override
    public Bill removeBill(Integer billId) {
 
        Bill bill = billRepository.findById(billId).orElseThrow(()-> new BillException("Bill does not exists with bill id : "+billId));
        billRepository.delete(bill);
        return bill;
 
    }
 
    @Override
    public Bill updateBill(Bill bill) {
 
        Bill availableBill = billRepository.findById(bill.getBillId()).orElseThrow(()-> new BillException("Bill does not exists with bill id : "+bill.getBillId()));
 
        if(availableBill.getOrderDetailId().equals(bill.getOrderDetailId())) return availableBill;
 
        OrderDetails orderDetails = orderDetailService.getOrderDetails(bill.getOrderDetailId());
 
        if(orderDetails==null) throw new OrderException("Invalid order id : "+bill.getOrderDetailId());
 
        bill.setTotalCost(getTotalCost(orderDetails.getFoodCart().getItems()));
        bill.setTotalItem(getTotalItem(orderDetails.getFoodCart().getItems()));
        bill.setTimeSpan(LocalDateTime.now());
 
        Bill savedBill = billRepository.save(bill);
 
        return billRepository.save(bill);
 
    }
 
    @Override
    public BillDTO viewBill(Integer billId) {
 
        Bill bill = billRepository.findById(billId).orElseThrow(()-> new BillException("Bill does not exists with bill id : "+billId));
 
        return convertBillDTO(bill);
 
    }
 
    @Override
    public List<BillDTO> viewBillByDate(LocalDateTime startDate, LocalDateTime endDate) {
 
        List<Bill> bills = billRepository.findBillBetweenDates(startDate, endDate);
        if(bills.isEmpty()) throw new BillException("Bills not found");
 
        List<BillDTO> billDTOS = new ArrayList<>();
 
        for (Bill bill : bills){
            BillDTO billDTO = convertBillDTO(bill);
            billDTOS.add(billDTO);
        }
 
        return billDTOS;
 
    }
 
    @Override
    public List<BillDTO> viewBillOfUser(Integer userId) {
 
        FoodCart foodCart = cartService.getFoodCartByUserId(userId);
        if(foodCart==null) throw new RuntimeException("Invalid user id : "+userId);
 
        List<OrderDetails> orderDetails = orderDetailService.getOrderByCartId(foodCart.getCartId());
        if(orderDetails.isEmpty()) throw new BillException("Orders not found");
 
        List<BillDTO> bills = new ArrayList<>();
 
        orderDetails.stream().forEach((el)->{
 
            Bill bill = billRepository.findByOrderDetailId(el.getOrderId()).orElseThrow(()-> new BillException("Invalid order id "+el.getOrderId()));
            bills.add(convertBillDTO(bill));
 
        });
 
        return bills;
 
    }
 
    @Override
    public Double calculateBill(Bill bill) {
        return null;
    }
 
    private BillDTO convertBillDTO(Bill bill){
 
        OrderDetails orderDetails = orderDetailService.getOrderDetails(bill.getOrderDetailId());
 
        if(orderDetails==null) throw new OrderException("Invalid order id : "+bill.getOrderDetailId());
 
        BillDTO billDTO = new BillDTO();
 
        billDTO.setBillId(bill.getBillId());
        billDTO.setTimeSpan(bill.getTimeSpan());
        billDTO.setOrderDetails(orderDetails);
        billDTO.setTotalCost(bill.getTotalCost());
        billDTO.setTotalItem(bill.getTotalItem());
 
        return billDTO;
 
    }
}