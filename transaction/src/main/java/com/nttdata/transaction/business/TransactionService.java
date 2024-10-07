package com.nttdata.transaction.business;

import com.nttdata.transaction.model.entity.dto.TransaccionDeposito;
import com.nttdata.transaction.model.entity.dto.TransaccionHistorialDto;
import com.nttdata.transaction.model.entity.dto.TransaccionRetiroDto;
import com.nttdata.transaction.model.entity.dto.TransaccionTransferenciaDto;

import java.util.List;

public interface TransactionService {

    TransaccionDeposito realizarDeposito(TransaccionDeposito depositoDto);

    TransaccionRetiroDto realizarRetiro(TransaccionRetiroDto retiroDto);

    TransaccionTransferenciaDto realizarTransferencia(TransaccionTransferenciaDto transferenciaDto);

    List<TransaccionHistorialDto> obtenerHistorialTransacciones();
}
