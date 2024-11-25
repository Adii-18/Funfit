package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BatchDAO {

    public void addBatch(Batch batch) throws SQLException {
        String query = "INSERT INTO Batches (name, time) VALUES (?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, batch.getName());
            preparedStatement.setString(2, batch.getTime());
            preparedStatement.executeUpdate();
        }
    }

    public Batch selectBatch(int id) throws SQLException {
        Batch batch = null;
        String query = "SELECT id, name, time FROM Batches WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String time = rs.getString("time");
                batch = new Batch();
                batch.setId(id);
                batch.setName(name);
                batch.setTime(time);
            }
        }
        return batch;
    }

    public List<Batch> selectAllBatches() throws SQLException {
        List<Batch> batches = new ArrayList<>();
        String query = "SELECT * FROM Batches";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String time = rs.getString("time");
                Batch batch = new Batch();
                batch.setId(id);
                batch.setName(name);
                batch.setTime(time);
                batches.add(batch);
            }
        }
        return batches;
    }

    public boolean updateBatch(Batch batch) throws SQLException {
        boolean rowUpdated;
        String query = "UPDATE Batches SET name = ?, time = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, batch.getName());
            preparedStatement.setString(2, batch.getTime());
            preparedStatement.setInt(3, batch.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteBatch(int id) throws SQLException {
        boolean rowDeleted;
        String query = "DELETE FROM Batches WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}