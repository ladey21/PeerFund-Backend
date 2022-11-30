package com.example.funds.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupDTO {

    private Long id;

    private String name;

    private String purpose;

    private int slots;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime paymentStartDate;

    private LocalDateTime paymentEndDate;

    private Double amount;

    public GroupDTO(String name, String purpose, int slots, String description, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime paymentStartDate, LocalDateTime paymentEndDate, Double amount) {
        this.name = name;
        this.purpose = purpose;
        this.slots = slots;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentStartDate = paymentStartDate;
        this.paymentEndDate = paymentEndDate;
        this.amount = amount;
    }

    public GroupDTO(String name, String purpose, int slots, String description, Double amount) {
        this.name = name;
        this.purpose = purpose;
        this.slots = slots;
        this.description = description;
        this.amount = amount;
    }
}
