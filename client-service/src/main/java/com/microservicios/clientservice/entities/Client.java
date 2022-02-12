package com.microservicios.clientservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Table(name = "client")
@Getter
@Setter
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
