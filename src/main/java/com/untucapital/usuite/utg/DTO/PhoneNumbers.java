package com.untucapital.usuite.utg.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PhoneNumbers {
    private String messageText;
    private List<String> number;
}
