package com.nttdata.transaction.client;

import com.nttdata.transaction.model.entity.dto.CuentaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "feignCuenta", url = "http://localhost:8081/cuenta/cuenta/")
public interface IFeignCuenta {


    @GetMapping(value = "/{id}")
    CuentaResponse obtenerCuenta(@PathVariable("id") Long id);

    @PostMapping(value = "/{id}/deposit")
    CuentaResponse depositar(@PathVariable("id") Long id, @RequestParam("amount") Double amount);

    @PostMapping(value = "/{id}/withdraw")
    CuentaResponse retirar(@PathVariable("id") Long id, @RequestParam("amount") Double amount);

    @GetMapping(value = "/cliente/{clienteId}/exists")
    boolean clienteCuenta(@PathVariable("clienteId") Long clienteId);
}
