package com.microservicios.clientservice.repositories;

import com.microservicios.clientservice.entities.Client;
import org.springframework.stereotype.Repository;
@Repository
public interface ClientRepository extends BaseRepository<Client,Long> {

}
