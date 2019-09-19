package com.gunar.sistema_de_control.model;

public class Persona {
    private String nombre, carnet, telefono;

    public Persona(String nombre, String carnet, String telefono){
        this.nombre = nombre;
        this.carnet = carnet;
        this.telefono = telefono;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
