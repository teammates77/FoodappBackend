package com.BillService.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime timeSpan;

    private Integer orderDetailId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer totalItem;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double totalCost;

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public LocalDateTime getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(LocalDateTime timeSpan) {
		this.timeSpan = timeSpan;
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Integer getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", timeSpan=" + timeSpan + ", orderDetailId=" + orderDetailId + ", totalItem="
				+ totalItem + ", totalCost=" + totalCost + "]";
	}
    
    
}

