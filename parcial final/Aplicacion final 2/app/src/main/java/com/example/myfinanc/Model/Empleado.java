package com.example.myfinanc.Model;

public class Empleado {

    private Long id;

    private String nombre;

    private String password;

    private String email;

    private String numero;


    public Empleado() {

    }

    public Empleado(Long id, String nombre, String password, String email, String numero){
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return  email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getNumero(){
        return  numero;
    }

    public void setNumero(String numero){
        this.numero = numero;
    }

    @Override
    public String toString(){
        return "Empleado{"  +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }

}