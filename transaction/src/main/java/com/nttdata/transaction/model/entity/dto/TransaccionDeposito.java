package com.nttdata.transaction.model.entity.dto;

import com.nttdata.transaction.model.entity.TypeTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionDeposito {

    private TypeTransaction tipo;
    private Long cuentaId;
    private Double monto;
    private LocalDateTime fecha;
}
