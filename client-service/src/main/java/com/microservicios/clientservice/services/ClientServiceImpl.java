package com.microservicios.clientservice.services;


import com.microservicios.clientservice.entities.Client;
import com.microservicios.clientservice.repositories.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends BaseServiceImpl<Client, Long> implements ClientService {

    public ClientServiceImpl(BaseRepository<Client, Long> baseRepository) {
        super(baseRepository);
    }

}
