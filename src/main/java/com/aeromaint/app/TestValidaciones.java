package com.aeromaint.app;

import com.aeromaint.dao.AeronaveDAO;
import com.aeromaint.model.Aeronave;

public class TestValidaciones {

    public static void main(String[] args) {

        AeronaveDAO aeronaveDAO = new AeronaveDAO();

        System.out.println("==========================================");
        System.out.println("   PRUEBAS DE VALIDACIÓN - AEROMAINT");
        System.out.println("==========================================");

        // Prueba 1: Datos válidos.
        probarCaso(
                aeronaveDAO,
                "PRUEBA 1 - Datos válidos",
                new Aeronave(
                        "HK-TEST",
                        "A320-200",
                        "Airbus",
                        "SN-VALID-001",
                        1500,
                        "ACTIVA"
                ),
                true
        );

        // Prueba 2: Matrícula vacía.
        probarCaso(
                aeronaveDAO,
                "PRUEBA 2 - Matrícula vacía",
                new Aeronave(
                        "",
                        "A320-200",
                        "Airbus",
                        "SN-VALID-002",
                        1500,
                        "ACTIVA"
                ),
                false
        );

        // Prueba 3: Ciclos negativos.
        probarCaso(
                aeronaveDAO,
                "PRUEBA 3 - Ciclos negativos",
                new Aeronave(
                        "HK-TEST",
                        "A320-200",
                        "Airbus",
                        "SN-VALID-003",
                        -500,
                        "ACTIVA"
                ),
                false
        );

        // Prueba 4: Matrícula demasiado larga.
        probarCaso(
                aeronaveDAO,
                "PRUEBA 4 - Matrícula demasiado larga",
                new Aeronave(
                        "HK-12345678901",
                        "A320-200",
                        "Airbus",
                        "SN-VALID-004",
                        1500,
                        "ACTIVA"
                ),
                false
        );

        // Prueba 5: Caracteres especiales no permitidos.
        probarCaso(
                aeronaveDAO,
                "PRUEBA 5 - Caracteres especiales",
                new Aeronave(
                        "HK-TEST@#",
                        "A320-200",
                        "Airbus",
                        "SN-VALID-005",
                        1500,
                        "ACTIVA"
                ),
                false
        );

        // Prueba 6: Estado inválido.
        probarCaso(
                aeronaveDAO,
                "PRUEBA 6 - Estado inválido",
                new Aeronave(
                        "HK-TEST",
                        "A320-200",
                        "Airbus",
                        "SN-VALID-006",
                        1500,
                        "VOLANDO"
                ),
                false
        );

        // Prueba 7: Modelo vacío.
        probarCaso(
                aeronaveDAO,
                "PRUEBA 7 - Modelo vacío",
                new Aeronave(
                        "HK-TEST",
                        "",
                        "Airbus",
                        "SN-VALID-007",
                        1500,
                        "ACTIVA"
                ),
                false
        );

        System.out.println("\n==========================================");
        System.out.println("       FIN DE LAS PRUEBAS");
        System.out.println("==========================================");
    }

    /**
     * Ejecuta un caso de prueba y determina si el resultado
     * corresponde al comportamiento esperado.
     */
    private static void probarCaso(
            AeronaveDAO aeronaveDAO,
            String nombrePrueba,
            Aeronave aeronave,
            boolean debeSerValido) {

        System.out.println("\n" + nombrePrueba);

        try {

            aeronaveDAO.insertarAeronave(aeronave);

            if (debeSerValido) {

                System.out.println("[OK] Validación aceptada correctamente.");
                System.out.println("     Aeronave insertada con ID: "
                        + aeronave.getIdAeronave());

                // El registro válido se elimina después de la prueba
                // para no modificar permanentemente la base de datos.
                aeronaveDAO.eliminarAeronave(
                        aeronave.getIdAeronave());

            } else {

                System.out.println("[ERROR] La validación no rechazó "
                        + "los datos incorrectos.");

                // Si por alguna razón se insertó un dato inválido,
                // se elimina para mantener limpia la base de datos.
                if (aeronave.getIdAeronave() > 0) {
                    aeronaveDAO.eliminarAeronave(
                            aeronave.getIdAeronave());
                }
            }

        } catch (IllegalArgumentException exception) {

            if (debeSerValido) {

                System.out.println("[ERROR] Se rechazaron datos "
                        + "que deberían ser válidos.");
                System.out.println("     Motivo: "
                        + exception.getMessage());

            } else {

                System.out.println("[OK] Validación rechazada correctamente.");
                System.out.println("     Motivo: "
                        + exception.getMessage());
            }

        } catch (Exception exception) {

            System.out.println("[ERROR] Error inesperado: "
                    + exception.getMessage());
        }
    }
}
