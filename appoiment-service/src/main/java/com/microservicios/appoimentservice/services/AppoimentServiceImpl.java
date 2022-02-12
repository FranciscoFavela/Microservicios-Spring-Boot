package com.microservicios.appoimentservice.services;

import com.microservicios.appoimentservice.Dtos.AppoimentDto;
import com.microservicios.appoimentservice.Dtos.BaseMapperDTO;
import com.microservicios.appoimentservice.FeignClients.ClientFeignClient;
import com.microservicios.appoimentservice.entities.Appoiment;
import com.microservicios.appoimentservice.model.AppoimentClient;
import com.microservicios.appoimentservice.model.Client;
import com.microservicios.appoimentservice.repositories.AppoimentRepository;
import com.microservicios.appoimentservice.repositories.BaseRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


@Service
public class AppoimentServiceImpl extends BaseServiceImpl<Appoiment, Long> implements AppoimentService {

    @Autowired
    private AppoimentRepository appoimentRepository;
    static BaseMapperDTO baseMapperDTO = BaseMapperDTO.singleInstance();

    @Autowired
    ClientFeignClient clientFeignClient;

    public AppoimentServiceImpl(BaseRepository<Appoiment, Long> baseRepository) {
        super(baseRepository);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<List<AppoimentDto>> getAllDTO(){
        System.out.println("Current Thread" + Thread.currentThread().getName());
        LOGGER.info("Request to Get ALL Dto");
        System.out.println(Thread.currentThread().getName());
        List<Appoiment> entities =appoimentRepository.findAll();
        List<AppoimentDto> dtos = new ArrayList<>();
        for(Appoiment appoiment: entities){
            AppoimentDto appoimentDto;
            appoimentDto = baseMapperDTO.mapToAppoimentDto(appoiment);
            dtos.add(appoimentDto);
        }
        return CompletableFuture.completedFuture(dtos);
    }

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<AppoimentDto> getOneAppoimentDTO(Long id){
        System.out.println("Current Thread" + Thread.currentThread().getName());
        LOGGER.info("Request to Get OneDto");
        ModelMapper modelMapper = new ModelMapper();
        AppoimentDto appoimentDto = modelMapper.map(appoimentRepository.findById(id),AppoimentDto.class);
        return CompletableFuture.completedFuture(appoimentDto);
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<List<Appoiment>> pruebaGetall() {
        System.out.println("Current Thread" + Thread.currentThread().getName());
        LOGGER.info("Request to Get ALL");
        return CompletableFuture.completedFuture(appoimentRepository.findAll());
    }

    @Async("taskExecutor")
    @Override
    public AppoimentClient getOneAppoiment(Long id) throws Exception {
        System.out.println("Current Thread" + Thread.currentThread().getName());
        LOGGER.info("Request to Get One Appoiment-Client");
        Optional<Appoiment> appoiment =appoimentRepository.findById(id);
        Client client = clientFeignClient.getOneAsync(id);

        AppoimentClient appoimentClient = new AppoimentClient();
        appoimentClient.setClient(client);
        appoimentClient.setAppoimentroom(appoiment.get().getAppoimentroom());
        appoimentClient.setAppoimentreason(appoiment.get().getAppoimentreason());
        appoimentClient.setDate(appoiment.get().getDate());
        return appoimentClient;
    }

}
