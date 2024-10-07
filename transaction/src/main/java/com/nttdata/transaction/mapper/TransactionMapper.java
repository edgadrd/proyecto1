package com.nttdata.transaction.mapper;

import com.nttdata.transaction.model.entity.Transaction;
import com.nttdata.transaction.model.entity.dto.TransaccionDeposito;
import com.nttdata.transaction.model.entity.dto.TransaccionHistorialDto;
import com.nttdata.transaction.model.entity.dto.TransaccionRetiroDto;
import com.nttdata.transaction.model.entity.dto.TransaccionTransferenciaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransaccionDeposito transactionToDeposito (Transaction transaction);
    Transaction depositoToTransaction (TransaccionDeposito depositoDto);


    TransaccionRetiroDto transactionToRetiro (Transaction transaction);
    Transaction retiroToTransaction (TransaccionRetiroDto retiroDto);

    TransaccionTransferenciaDto transactionToTransferencia (Transaction transaction);
    Transaction transferenciaToTransaction (TransaccionTransferenciaDto transferenciaDto);


    TransaccionHistorialDto transactionToHistorialDto(Transaction transaction);
    Transaction transactionHistorialDtoToTransaction(TransaccionHistorialDto transaccionHistorialDto);
}
