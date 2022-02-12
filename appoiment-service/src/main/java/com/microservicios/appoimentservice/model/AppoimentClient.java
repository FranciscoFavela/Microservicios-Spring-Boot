package com.microservicios.appoimentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppoimentClient {
    @Column(name = "appoimentreason")
    private String appoimentreason;

    @Column(name = "appoimentroom")
    private int appoimentroom;

    @Column(name = "date")
    @DateTimeFormat(pattern="yyyy.MM.dd")
    private Date date;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_client")
    private Client client;
}

