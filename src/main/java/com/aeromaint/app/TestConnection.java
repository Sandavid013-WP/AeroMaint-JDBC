package com.aeromaint.app;

import com.aeromaint.dao.AeronaveDAO;

public class TestConnection {

    public static void main(String[] args) {

        AeronaveDAO aeronaveDAO = new AeronaveDAO();

        try {

            aeronaveDAO.eliminarAeronave(1);

            System.out.println("Aeronave eliminada correctamente.");

        } catch (Exception exception) {

            System.out.println("Error al eliminar la aeronave: "
                    + exception.getMessage());
        }
    }
}