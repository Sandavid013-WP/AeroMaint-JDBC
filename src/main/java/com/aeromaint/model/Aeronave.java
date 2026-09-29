package com.aeromaint.model;

public class Aeronave {

    private int idAeronave;
    private String matricula;
    private String modelo;
    private String fabricante;
    private String numeroSerie;
    private int ciclos;
    private String estado;

    public Aeronave() {
    }

    public Aeronave(String matricula, String modelo, String fabricante,
                    String numeroSerie, int ciclos, String estado) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.numeroSerie = numeroSerie;
        this.ciclos = ciclos;
        this.estado = estado;
    }

    public int getIdAeronave() {
        return idAeronave;
    }

    public void setIdAeronave(int idAeronave) {
        this.idAeronave = idAeronave;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public int getCiclos() {
        return ciclos;
    }

    public void setCiclos(int ciclos) {
        this.ciclos = ciclos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Aeronave{" +
                "idAeronave=" + idAeronave +
                ", matricula='" + matricula + '\'' +
                ", modelo='" + modelo + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", numeroSerie='" + numeroSerie + '\'' +
                ", ciclos=" + ciclos +
                ", estado='" + estado + '\'' +
                '}';
    }
}