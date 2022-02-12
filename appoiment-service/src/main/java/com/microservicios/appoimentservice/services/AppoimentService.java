package com.microservicios.appoimentservice.services;

import com.microservicios.appoimentservice.Dtos.AppoimentDto;
import com.microservicios.appoimentservice.entities.Appoiment;
import com.microservicios.appoimentservice.model.AppoimentClient;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface AppoimentService extends BaseService<Appoiment, Long> {
    CompletableFuture<List<Appoiment>> pruebaGetall();
    CompletableFuture<List<AppoimentDto>> getAllDTO();
    CompletableFuture<AppoimentDto> getOneAppoimentDTO(Long id);
    AppoimentClient getOneAppoiment(Long id) throws Exception;
}
