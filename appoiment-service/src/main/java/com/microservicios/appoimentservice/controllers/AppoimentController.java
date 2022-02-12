package com.microservicios.appoimentservice.controllers;


import com.microservicios.appoimentservice.Dtos.AppoimentDto;
import com.microservicios.appoimentservice.FeignClients.ClientFeignClient;
import com.microservicios.appoimentservice.entities.Appoiment;
import com.microservicios.appoimentservice.model.AppoimentClient;
import com.microservicios.appoimentservice.model.Client;
import com.microservicios.appoimentservice.services.AppoimentServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/appoiment")
public class AppoimentController extends BaseControllerImpl<Appoiment, AppoimentServiceImpl>{

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseControllerImpl.class);

    //Competable future testing 3 request
    /*
    @GetMapping("/hilo")
    public @ResponseBody ResponseEntity getAllCars() {
        try {
            //testing of 9 request
            CompletableFuture<List<Appoiment>> entitie1=servicio.getAlll();
            CompletableFuture<List<Appoiment>> entitie2=servicio.getAlll();
            CompletableFuture<List<Appoiment>> entitie3=servicio.getAlll();
            CompletableFuture<List<Appoiment>> entitie4=servicio.getAlll();
            CompletableFuture<List<Appoiment>> entitie5=servicio.getAlll();
            CompletableFuture<List<Appoiment>> entitie6=servicio.getAlll();
            CompletableFuture<List<Appoiment>> entitie7=servicio.getAlll();
            CompletableFuture<List<Appoiment>> entitie8=servicio.getAlll();
            CompletableFuture<List<Appoiment>> entitie9=servicio.getAlll();
            CompletableFuture.allOf(entitie1, entitie2, entitie3,entitie3,entitie4,entitie5,entitie6,entitie7,entitie8,entitie9).join();
           // return ResponseEntity.status(HttpStatus.OK).build();
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getAlll());
        } catch(final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */
    //Pruebas dtos
    @GetMapping("/testgetdto")
    public CompletableFuture<ResponseEntity> getTestAllDto(){
        return servicio.getAllDTO().thenApply(ResponseEntity::ok);
    }

    @GetMapping("/testgetdto/{id}")
    public CompletableFuture<ResponseEntity> getOneAsyncDto(@PathVariable Long id) throws Exception {
        return servicio.getOneAppoimentDTO(id).thenApply(ResponseEntity::ok);
    }
    @CircuitBreaker(name ="clientCB",fallbackMethod = "fallBackGetAppoimentAndClient")
    @GetMapping("/testgetone/{id}")
    public ResponseEntity<AppoimentClient> getOneAppoiment(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(servicio.getOneAppoiment(id));
    }

    public ResponseEntity<AppoimentClient> fallBackGetAppoimentAndClient(@PathVariable Long id,RuntimeException e) throws Exception {
        return new  ResponseEntity("problemas al obtener el cliente "+ id,HttpStatus.OK);
    }
}
