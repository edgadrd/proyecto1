package com.nttdata.transaction.model.entity.dto;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionHistorialDto {

    private String tipo;
    private Long cuentaOrigenId;
    private Long cuentaDestinoId;
    private Double monto;
    private LocalDateTime fecha;
}
