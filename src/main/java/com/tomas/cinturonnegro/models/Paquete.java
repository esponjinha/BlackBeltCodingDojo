package com.tomas.cinturonnegro.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "paquetes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Paquete {

    private String packageName;
    private float packageCost;
    private boolean available;


}