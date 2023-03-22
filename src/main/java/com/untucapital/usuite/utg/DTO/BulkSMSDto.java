package com.untucapital.usuite.utg.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BulkSMSDto {
    private String originator;
    private String destination;
    private String messageText;
    private String messageReference;
}
