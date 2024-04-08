package com.BillService.demo.Service;

import com.BillService.demo.DTO.BillDTO;
import com.BillService.demo.Model.Bill;
import java.time.LocalDateTime;
import java.util.List;

public interface BillService {

    public BillDTO addBill(Bill bill);

    public Bill removeBill(Integer billId);

    public Bill updateBill(Bill bill);

    public BillDTO viewBill(Integer billId);

    public List<BillDTO> viewBillByDate(LocalDateTime startDate , LocalDateTime endDate);

    public List<BillDTO> viewBillOfUser(Integer userId);

    public Double calculateBill(Bill bill);

	

	

}
