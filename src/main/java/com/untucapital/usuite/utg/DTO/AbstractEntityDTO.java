package com.untucapital.usuite.utg.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class AbstractEntityDTO implements Serializable {

    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
