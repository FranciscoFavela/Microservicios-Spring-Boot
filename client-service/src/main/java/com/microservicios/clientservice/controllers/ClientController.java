package com.microservicios.clientservice.controllers;


import com.microservicios.clientservice.entities.Client;
import com.microservicios.clientservice.services.ClientServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/clients")
public  class ClientController extends BaseControllerImpl<Client, ClientServiceImpl>{



}
