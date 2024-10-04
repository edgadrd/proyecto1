package com.nttdata.accountms.service;

import com.nttdata.accountms.dto.CuentaDto;

import java.math.BigDecimal;
import java.util.List;

public interface ICuentaService {
    CuentaDto createAccount(CuentaDto cuentaDto);
    List<CuentaDto> findAllAccount();
    CuentaDto getAccountById(Long id);
    CuentaDto depositAccount(Long id, Double amount);
    CuentaDto withdrawAccount( Long id, Double amount);
    void deleteAccount(Long id);
    boolean clientWhitAccount(Long clienteId);
}
