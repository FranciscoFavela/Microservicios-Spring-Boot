package com.microservicios.clientservice.services;

;import com.microservicios.clientservice.entities.Base;
import com.microservicios.clientservice.repositories.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {
    protected BaseRepository<E, ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    //Competable future testing 3 request
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<List<E>> getAlll() {

        LOGGER.info("Request to get a list");

        return CompletableFuture.completedFuture(baseRepository.findAll());
    }

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<Optional<E>> findByIdAsync(ID id) throws Exception {
        try {
            System.out.println("Current Thread " + Thread.currentThread().getName());
            LOGGER.info("Request to get a OneByID");
            return CompletableFuture.completedFuture(baseRepository.findById(id));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Async("taskExecutor")
    @Override
    @Transactional
    public CompletableFuture<List<E>> pruebaGetall() {
        System.out.println("Current Thread" + Thread.currentThread().getName());
        LOGGER.info("Request to get a list");
        return CompletableFuture.completedFuture(baseRepository.findAll());
    }

    /*
        @Override
        @Transactional
        public List<E> findAll() throws Exception {
            try{
                System.out.println("Current Thread"+Thread.currentThread().getName());
                List<E> entities = baseRepository.findAll();
                return entities;
            } catch (Exception e){
                throw new Exception(e.getMessage());
            }
        }
    */
    /*
    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        try{
            Optional<E> entityOptional = baseRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try{
            entity =baseRepository.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
     @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        try{
            Optional<E> entityOptional = baseRepository.findById(id);
            E entityUpdate = entityOptional.get();
            entityUpdate = baseRepository.save(entity);
            return entityUpdate;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
*/
    @Async("taskExecutor")
    @Transactional
    @Override
    public CompletableFuture<E> update(ID id, E entity) throws Exception {
        System.out.println("Current Thread" + Thread.currentThread().getName());
        LOGGER.info("Request to update");
        Optional<E> entityOptional = baseRepository.findById(id);
        E entityUpdate = entityOptional.get();
        return CompletableFuture.completedFuture(baseRepository.save(entity));
    }

    @Async("taskExecutor")
    @Transactional
    @Override
    public CompletableFuture<E> save(E entity) throws Exception {
        System.out.println("Current Thread" + Thread.currentThread().getName());
        LOGGER.info("Request to save");
        return CompletableFuture.completedFuture(baseRepository.save(entity));
    }


    @Async("taskExecutor")
    @Override
    @Transactional
    public CompletableFuture<Boolean> delete(ID id) throws Exception {
        try {
            System.out.println("Current Thread" + Thread.currentThread().getName());
            LOGGER.info("Request to Delete");
            if (baseRepository.existsById(id)) {
                baseRepository.deleteById(id);
                return CompletableFuture.completedFuture(true);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
