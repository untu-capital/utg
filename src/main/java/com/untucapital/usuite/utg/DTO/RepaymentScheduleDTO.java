package com.untucapital.usuite.utg.DTO;

import lombok.Data;

@Data
public class RepaymentScheduleDTO {
    private Object loanTermInDays;
    private Object dueDate;
    private Object totalPaid;
    private Object totalDue;
    private Object totalOutstanding;

}
