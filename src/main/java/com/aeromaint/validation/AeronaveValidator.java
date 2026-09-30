package com.aeromaint.validation;

import com.aeromaint.model.Aeronave;

public class AeronaveValidator {

    /**
     * Valida los datos de una aeronave antes de realizar
     * operaciones sobre la base de datos.
     */
    public static void validar(Aeronave aeronave) {

        if (aeronave == null) {
            throw new IllegalArgumentException(
                    "La aeronave no puede ser nula.");
        }

        validarTexto(aeronave.getMatricula(), "Matrícula", 10);
        validarTexto(aeronave.getModelo(), "Modelo", 50);
        validarTexto(aeronave.getFabricante(), "Fabricante", 50);
        validarTexto(aeronave.getNumeroSerie(), "Número de serie", 50);
        validarEstado(aeronave.getEstado());

        if (aeronave.getCiclos() < 0) {
            throw new IllegalArgumentException(
                    "Los ciclos no pueden ser negativos.");
        }
    }

    /**
     * Valida campos de texto verificando que no estén vacíos,
     * que no superen la longitud máxima y que no contengan
     * caracteres especiales no permitidos.
     */
    private static void validarTexto(String valor,
                                     String campo,
                                     int longitudMaxima) {

        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El campo " + campo + " es obligatorio.");
        }

        if (valor.length() > longitudMaxima) {
            throw new IllegalArgumentException(
                    "El campo " + campo + " supera la longitud máxima de "
                    + longitudMaxima + " caracteres.");
        }

        if (!valor.matches("[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\- ]+")) {
            throw new IllegalArgumentException(
                    "El campo " + campo
                    + " contiene caracteres no permitidos.");
        }
    }

    /**
     * Valida que el estado corresponda a uno de los valores
     * definidos para las aeronaves del sistema.
     */
    private static void validarEstado(String estado) {

        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El estado es obligatorio.");
        }

        if (!estado.equals("ACTIVA")
                && !estado.equals("MANTENIMIENTO")
                && !estado.equals("INACTIVA")) {

            throw new IllegalArgumentException(
                    "El estado debe ser ACTIVA, MANTENIMIENTO o INACTIVA.");
        }
    }
}
