package com.nttdata.transaction;

import com.nttdata.transaction.api.TransaccionesApiDelegate;
import com.nttdata.transaction.business.TransactionService;
import com.nttdata.transaction.model.TransaccionHistorial;
import com.nttdata.transaction.model.TransaccionRetiro;
import com.nttdata.transaction.model.TransaccionTransferencia;
import com.nttdata.transaction.model.entity.dto.TransaccionDeposito;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TransactionDelegateImpl implements TransaccionesApiDelegate {

    private final TransactionService transactionService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return TransaccionesApiDelegate.super.getRequest();
    }

    @Override
    @PostMapping("/transacciones/deposito")
    public ResponseEntity<Void> transaccionesDepositoPost(TransaccionDeposito transaccionDeposito) {
        transactionService.realizarDeposito(transaccionDeposito);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<TransaccionHistorial>> transaccionesHistorialGet(Integer cuentaId) {
        return TransaccionesApiDelegate.super.transaccionesHistorialGet(cuentaId);
    }

    @Override
    public ResponseEntity<Void> transaccionesRetiroPost(TransaccionRetiro transaccionRetiro) {
        return TransaccionesApiDelegate.super.transaccionesRetiroPost(transaccionRetiro);
    }

    @Override
    public ResponseEntity<Void> transaccionesTransferenciaPost(TransaccionTransferencia transaccionTransferencia) {
        return TransaccionesApiDelegate.super.transaccionesTransferenciaPost(transaccionTransferencia);
    }
}
