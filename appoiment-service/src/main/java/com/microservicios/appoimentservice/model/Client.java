package com.microservicios.appoimentservice.model;

import com.microservicios.appoimentservice.entities.Base;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
public class Client extends Base {
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "ssn")
    private int ssn;
    @Column(name = "dni")
    private String dni;
    @Column(name = "mail")
    private String mail;
    @Column(name = "phone")
    private int phone;


}
