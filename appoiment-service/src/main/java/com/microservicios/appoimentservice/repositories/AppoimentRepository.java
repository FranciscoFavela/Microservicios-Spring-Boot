package com.microservicios.appoimentservice.repositories;

import com.microservicios.appoimentservice.entities.Appoiment;
import org.springframework.stereotype.Repository;

@Repository
public interface AppoimentRepository extends BaseRepository<Appoiment, Long>    {
}
