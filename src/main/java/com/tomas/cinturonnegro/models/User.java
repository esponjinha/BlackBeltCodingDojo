package com.tomas.cinturonnegro.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User extends ModeloBase {
    @NotNull
    @NotBlank
    @Size(min = 5, max = 45, message = "el nombre debe estar entre 5 y 45 caracteres")
    private String firstName;

    @NotBlank
    @NotNull
    @Size(min = 5, max = 45, message = "el apellido debe estar entre 5 y 45 caracteres")
    private String lastName;


    @Size(min = 8, message = "la password debe estar entre 8 y 50 caracteres")
    private String password;


    @Transient
    private String passwordConfirmation;

    private int rol;

    @NotNull
    @NotBlank
    @Email(message = "ingrese un email valido")
    private String email;

    private Date fechaPago;

    //Relaciones

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paquete")
    private Paquete paquete;
}
