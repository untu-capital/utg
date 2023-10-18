package com.untucapital.usuite.utg.DTO.cms;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tjchidanika
 * @created 27/9/2023
 */

@Getter
@Setter
public class VaultRequest {
    private String account;
    private String name;
    private String type;
    private String branchId;
}
