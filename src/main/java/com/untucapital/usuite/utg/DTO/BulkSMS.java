package com.untucapital.usuite.utg.DTO;

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
//
//========
//import lombok.Data;
//
//import java.util.List;
//
//@Data
//public class BulkSMSDTO {
//    private List<String> phoneNumbers;
//    private String message;
//>>>>>>>> feature/christabel:src/main/java/com/untucapital/usuite/utg/DTO/BulkSMSDto.java
}
