package com.microservicios.appoimentservice.entities;

import com.microservicios.appoimentservice.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appoiment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appoiment extends Base {

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
