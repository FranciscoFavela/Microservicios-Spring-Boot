package com.microservicios.appoimentservice.FeignClients;

import com.microservicios.appoimentservice.entities.Base;
import com.microservicios.appoimentservice.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
@FeignClient(name = "client-service",url = "http://localhost:8070")
//@RequestMapping(path = "api/v1/clients")
public interface ClientFeignClient{
    @GetMapping("api/v1/clients/{id}")
    Client getOneAsync(@PathVariable Long id) throws Exception;
}
