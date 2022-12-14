package com.portfolio.mrn.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
 
@Getter @Setter
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 1, max = 50, message = "demasiados caractéres")
    private String nombre;
    
    @NotNull
    @Size(min = 1, max = 50, message = "demasiados caractéres")
    private String apellido;
    
    @Size(min = 1, max = 50, message = "demasiados caractéres")
    private String img;
    
    @NotNull
    @Size(min = 1, max = 100, message = "demasiados caractéres")
    private String email;
    
    @NotNull
    @Size(min = 1, max = 150, message = "demasiados caractéres")
    private String titulo;
    
    @NotNull
    @Size(min = 1, max = 50, message = "demasiados caractéres")
    private String nacionalidad;
    
    @NotNull
    @Size(min = 1, max = 50, message = "demasiados caractéres")
    private String nacimiento;
    
    @NotNull
    @Size(min = 1, max = 500, message = "demasiados caractéres")
    private String sobremi;
     
}
