
package com.Userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

    private Integer orderId;

    private LocalDateTime timeSpan;

    private List<Item> items = new ArrayList<>();

    private String status;

}

