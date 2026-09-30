package com.aeromaint.app;

import com.aeromaint.dao.AeronaveDAO;
import com.aeromaint.model.Aeronave;

import java.util.List;

public class TestConnection {

    public static void main(String[] args) {

        AeronaveDAO aeronaveDAO = new AeronaveDAO();

        try {

            // 1. Crear una nueva aeronave para probar la operación INSERT.
            Aeronave aeronave = new Aeronave(
                    "HK-TEST",
                    "A320-200",
                    "Airbus",
                    "SN-TEST-001",
                    1500,
                    "ACTIVA"
            );

            aeronaveDAO.insertarAeronave(aeronave);

            System.out.println("=== INSERTAR ===");
            System.out.println("Aeronave insertada correctamente.");
            System.out.println(aeronave);

            // 2. Consultar todas las aeronaves registradas.
            System.out.println("\n=== LISTAR ===");

            List<Aeronave> aeronaves = aeronaveDAO.listarAeronaves();

            for (Aeronave item : aeronaves) {
                System.out.println(item);
            }

            // 3. Modificar los datos de la aeronave recién creada.
            aeronave.setCiclos(1600);
            aeronave.setEstado("MANTENIMIENTO");

            aeronaveDAO.actualizarAeronave(aeronave);

            System.out.println("\n=== ACTUALIZAR ===");
            System.out.println("Aeronave actualizada correctamente.");
            System.out.println(aeronave);

            // 4. Eliminar la aeronave utilizada para la prueba.
            aeronaveDAO.eliminarAeronave(aeronave.getIdAeronave());

            System.out.println("\n=== ELIMINAR ===");
            System.out.println("Aeronave eliminada correctamente.");

        } catch (Exception exception) {

            System.out.println("Error durante la prueba CRUD: "
                    + exception.getMessage());
        }
    }
}
