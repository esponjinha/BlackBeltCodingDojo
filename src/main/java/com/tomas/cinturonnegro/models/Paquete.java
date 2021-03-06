package com.tomas.cinturonnegro.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "paquetes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Paquete extends ModeloBase {

    @NotNull
    @NotBlank
    @Size(min = 5, max = 100)
    private String packageName;

    @Min(0)
    private float packageCost;

    private boolean available;



    @OneToMany(mappedBy = "paquete", fetch = FetchType.LAZY)
    private List<User> users;

}