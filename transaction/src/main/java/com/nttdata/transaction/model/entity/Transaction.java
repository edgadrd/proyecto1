package com.nttdata.transaction.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transacciones")
public class Transaction {

    @Id
    private String id;

    private TypeTransaction tipo;

    private BigDecimal monto;

    private String cuentaOrigenId;

    private String cuentaDestinoId;

    private LocalDateTime fecha;
}
