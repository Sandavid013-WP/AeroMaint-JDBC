package com.aeromaint.dao;

import com.aeromaint.config.DatabaseConnection;
import com.aeromaint.model.Aeronave;
import com.aeromaint.validation.AeronaveValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AeronaveDAO {

    public void insertarAeronave(Aeronave aeronave) throws SQLException {
    

     AeronaveValidator.validar(aeronave);

        String sql = "INSERT INTO aeronave " +
                     "(matricula, modelo, fabricante, numero_serie, ciclos, estado) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

try (Connection connection = DatabaseConnection.getConnection();
     PreparedStatement statement = connection.prepareStatement(
             sql,
             java.sql.Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, aeronave.getMatricula());
            statement.setString(2, aeronave.getModelo());
            statement.setString(3, aeronave.getFabricante());
            statement.setString(4, aeronave.getNumeroSerie());
            statement.setInt(5, aeronave.getCiclos());
            statement.setString(6, aeronave.getEstado());
            
            statement.executeUpdate();

try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
    if (generatedKeys.next()) {
        aeronave.setIdAeronave(generatedKeys.getInt(1));
    }
}
        }
    }

    public List<Aeronave> listarAeronaves() throws SQLException {

        String sql = "SELECT * FROM aeronave";
        List<Aeronave> aeronaves = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Aeronave aeronave = new Aeronave();

                aeronave.setIdAeronave(resultSet.getInt("id_aeronave"));
                aeronave.setMatricula(resultSet.getString("matricula"));
                aeronave.setModelo(resultSet.getString("modelo"));
                aeronave.setFabricante(resultSet.getString("fabricante"));
                aeronave.setNumeroSerie(resultSet.getString("numero_serie"));
                aeronave.setCiclos(resultSet.getInt("ciclos"));
                aeronave.setEstado(resultSet.getString("estado"));

                aeronaves.add(aeronave);
            }
        }

        return aeronaves;
    }

    public void actualizarAeronave(Aeronave aeronave) throws SQLException {

    AeronaveValidator.validar(aeronave);

        String sql = "UPDATE aeronave SET " +
                     "matricula = ?, modelo = ?, fabricante = ?, " +
                     "numero_serie = ?, ciclos = ?, estado = ? " +
                     "WHERE id_aeronave = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, aeronave.getMatricula());
            statement.setString(2, aeronave.getModelo());
            statement.setString(3, aeronave.getFabricante());
            statement.setString(4, aeronave.getNumeroSerie());
            statement.setInt(5, aeronave.getCiclos());
            statement.setString(6, aeronave.getEstado());
            statement.setInt(7, aeronave.getIdAeronave());

            statement.executeUpdate();
        }
    }

    public void eliminarAeronave(int idAeronave) throws SQLException {

        String sql = "DELETE FROM aeronave WHERE id_aeronave = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idAeronave);

            statement.executeUpdate();
        }
    }
}
