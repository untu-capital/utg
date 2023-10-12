package com.untucapital.usuite.utg.DTO;

<<<<<<<< HEAD:src/main/java/com/untucapital/usuite/utg/DTO/BulkSMS.java
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class BulkSMS {
    private String originator;
    private String destination;
    private String messageText;
    private String messageReference;
========
import lombok.Data;

import java.util.List;

@Data
public class BulkSMSDTO {
    private List<String> phoneNumbers;
    private String message;
>>>>>>>> d4f8fa1 (Petty cash utg):src/main/java/com/untucapital/usuite/utg/DTO/BulkSMSDto.java
}
