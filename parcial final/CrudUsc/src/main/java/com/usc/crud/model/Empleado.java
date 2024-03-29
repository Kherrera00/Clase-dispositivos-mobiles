package com.usc.crud.model;

import javax.persistence.*;

@Entity
@Table(name="empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    private String password;

    private String email;

    private String numero;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {this.nombre = nombre;
    }

    public String getPassword() { return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public  String getNumero() {return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
