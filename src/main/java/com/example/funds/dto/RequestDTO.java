package com.example.funds.dto;


import com.example.funds.entities.Enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDTO {

    private Long id;

    private String groupId;

    private RequestStatus requestStatus;
}
