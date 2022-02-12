package com.microservicios.appoimentservice.controllers;

import com.microservicios.appoimentservice.entities.Base;
import com.microservicios.appoimentservice.services.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

public abstract class BaseControllerImpl<E extends Base, S extends BaseServiceImpl<E, Long>> implements BaseController<E, Long> {

    @Autowired
    protected S servicio;

/*
    @GetMapping("")
    public @ResponseBody ResponseEntity getAll() {
        // return ResponseEntity.status(HttpStatus.OK).build();
        try {
            CompletableFuture<List<E>> entity = servicio.getAlll();
            CompletableFuture.allOf(entity).join();
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll());
        } catch(final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */

    @GetMapping("")
    public CompletableFuture<ResponseEntity> getAll() {
        return servicio.pruebaGetall().thenApply(ResponseEntity::ok);
    }

    /*
        @GetMapping("/{id}")
        public ResponseEntity<?> getOne(@PathVariable Long id){
            try {
                return ResponseEntity.status(HttpStatus.OK).body(servicio.findById(id));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
            }
        }*/
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity> getOneAsync(@PathVariable Long id) throws Exception {
        return servicio.findByIdAsync(id).thenApply(ResponseEntity::ok);
    }

    @PostMapping("")
    public CompletableFuture<ResponseEntity> save(@RequestBody E entity) throws Exception {
        return servicio.save(entity).thenApply(ResponseEntity::ok);
    }

    /*
        @PostMapping("")
        public ResponseEntity<?> save(@RequestBody E entity){
            try {
                return ResponseEntity.status(HttpStatus.OK).body(servicio.save(entity));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
            }
        }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody E entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
    */
    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity> update(@PathVariable Long id, @RequestBody E entity) throws Exception {
        return servicio.update(id, entity).thenApply(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity> delete(@PathVariable Long id) throws Exception {
            return servicio.delete(id).thenApply(ResponseEntity::ok);
    }

}
