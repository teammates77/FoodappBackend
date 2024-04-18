package com.BillService.demo.Controller;

import com.BillService.demo.DTO.BillDTO;
import com.BillService.demo.Model.Bill;
import com.BillService.demo.Model.Category;
import com.BillService.demo.Service.BillService;
import com.BillService.demo.Service.CategoryService;
//import com.foodapp.restaurantservice.model.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/fooddelivery/bill")
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
    	this.billService = billService;
    }
  
    
    @PostMapping
    public ResponseEntity<BillDTO> addBill(@RequestBody Bill bill) {
        try {
            
            BillDTO savedBillDTO = billService.addBill(bill);
            
           
            return new ResponseEntity<>(savedBillDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{billId}")    
    public ResponseEntity<Bill> removeBill(@PathVariable Integer billId){

        Bill removedBill = billService.removeBill(billId);

        return new ResponseEntity<>(removedBill,HttpStatus.OK);

    }

    @PutMapping("/{billId}")
    
    public ResponseEntity<Bill> updateBill(@PathVariable Long billId, @RequestBody Bill bill){
    	
        Bill updatedBill = billService.updateBill(bill);

        return new ResponseEntity<>(updatedBill,HttpStatus.ACCEPTED);

    }

    @GetMapping("/{billId}")
    
    public ResponseEntity<BillDTO> viewBill(@PathVariable Integer billId){

        BillDTO billDTO = billService.viewBill(billId);

        return new ResponseEntity<>(billDTO,HttpStatus.OK);

    }
    /*@GetMapping("/itembycategory/{categoryId}")
    public ResponseEntity<List<Category>> viewItemByCategoryName(@PathVariable Integer categoryId){

        List<Category> categories = CategoryService.viewItemsByCategory(categoryId);

        return new ResponseEntity<>(categories,HttpStatus.OK);

    }*/

    @GetMapping("/billbydate/{startDate}/{endDate}")
    
    public ResponseEntity<List<BillDTO>> viewBillByDate(
    		@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<BillDTO> billsDTo = billService.viewBillByDate(startDate,endDate);

        return new ResponseEntity<>(billsDTo,HttpStatus.OK);

    }

    @GetMapping("/billsofuser/{userId}")
    
    public ResponseEntity<List<BillDTO>> viewBillOfUser(@PathVariable Integer userId){

        List<BillDTO> bills = billService.viewBillOfUser(userId);

        return new ResponseEntity<>(bills, HttpStatus.OK);

    }

}
