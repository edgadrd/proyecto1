package com.nttdata.transaction.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionRetiroDto {

    private Long cuentaId;
    private Double monto;
    private LocalDateTime fecha;
}
