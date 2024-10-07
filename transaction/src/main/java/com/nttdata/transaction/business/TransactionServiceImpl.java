package com.nttdata.transaction.business;

import com.nttdata.transaction.client.IFeignCuenta;
import com.nttdata.transaction.mapper.TransactionMapper;
import com.nttdata.transaction.model.entity.dto.*;
import com.nttdata.transaction.repository.TransactionRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final IFeignCuenta iFeignCuenta;


    @Override
    public TransaccionDeposito realizarDeposito(TransaccionDeposito depositoDto) {
        try {

            CuentaResponse cuenta =  iFeignCuenta.obtenerCuenta(depositoDto.getCuentaId());
            if (cuenta == null) {
                throw new RuntimeException("La cuenta no existe");
            }

            iFeignCuenta.depositar(depositoDto.getCuentaId(), depositoDto.getMonto());
            TransaccionDeposito deposito = new TransaccionDeposito().builder()
                    .cuentaId(depositoDto.getCuentaId())
                    .tipo(depositoDto.getTipo())
                    .monto(depositoDto.getMonto())
                    .fecha(LocalDateTime.now())
                    .build();
            return transactionMapper.transactionToDeposito(transactionRepository.save(transactionMapper.depositoToTransaction(deposito)));

        } catch (FeignException e) {

            throw new RuntimeException("Error al consultar cuentas: " + e.getMessage());
        } catch (Exception e) {

            throw new RuntimeException("Error al registrar el depósito: " + e.getMessage());
        }
    }

    @Override
    public TransaccionRetiroDto realizarRetiro(TransaccionRetiroDto retiroDto) {
        try {

            CuentaResponse cuenta = iFeignCuenta.obtenerCuenta(retiroDto.getCuentaId());
            if (cuenta == null) {
                throw new RuntimeException("La cuenta no existe");
            }

            iFeignCuenta.retirar(retiroDto.getCuentaId(), retiroDto.getMonto());

            TransaccionRetiroDto retiro = new TransaccionRetiroDto().builder()
                    .cuentaId(retiroDto.getCuentaId())
                    .monto(retiroDto.getMonto())
                    .fecha(LocalDateTime.now())
                    .build();

            return transactionMapper.transactionToRetiro((transactionRepository.save(transactionMapper.retiroToTransaction(retiro))));

        } catch (FeignException e) {

            throw new RuntimeException("Error al consultar cuentas: " + e.getMessage());
        } catch (Exception e) {

            throw new RuntimeException("Error al registrar el retiro: " + e.getMessage());
        }

    }

    @Override
    public TransaccionTransferenciaDto realizarTransferencia(TransaccionTransferenciaDto transferenciaDto) {

        try {
            CuentaResponse cuentaOrigen =iFeignCuenta.obtenerCuenta(transferenciaDto.getCuentaOrigenId());

            if (cuentaOrigen == null) {
                throw new RuntimeException("La cuenta Origen no existe");
            }
            iFeignCuenta.retirar(cuentaOrigen.getId(), transferenciaDto.getMonto());


            CuentaResponse cuentaDestino =iFeignCuenta.obtenerCuenta(transferenciaDto.getCuentaDestinoId());

            if (cuentaDestino == null) {
                throw new RuntimeException("La cuenta destino no existe");
            }

            iFeignCuenta.depositar(cuentaDestino.getId(), transferenciaDto.getMonto());

            TransaccionTransferenciaDto transferencia = new TransaccionTransferenciaDto().builder()
                    .cuentaOrigenId(cuentaOrigen.getId())
                    .cuentaDestinoId(cuentaDestino.getId())
                    .monto(transferenciaDto.getMonto())
                    .fecha(LocalDateTime.now())
                    .build();

            return transactionMapper.transactionToTransferencia(transactionMapper.transferenciaToTransaction(transferencia));

        } catch (FeignException e) {

            throw new RuntimeException("Error al consultar cuentas: " + e.getMessage());
        } catch (Exception e) {

            throw new RuntimeException("Error al registrar el retiro: " + e.getMessage());
        }
    }

    @Override
    public List<TransaccionHistorialDto> obtenerHistorialTransacciones() {
        return transactionRepository.findAll().stream()
                .map(transactionMapper::transactionToHistorialDto).collect(Collectors.toList());
    }
}
