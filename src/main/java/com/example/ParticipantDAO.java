package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {

    public void addParticipant(Participant participant) throws SQLException {
        String query = "INSERT INTO Participants (name, email) VALUES (?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, participant.getName());
            preparedStatement.setString(2, participant.getEmail());
            preparedStatement.executeUpdate();
        }
    }

    public Participant selectParticipant(int id) throws SQLException {
        Participant participant = null;
        String query = "SELECT id, name, email FROM Participants WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                participant = new Participant();
                participant.setId(id);
                participant.setName(name);
                participant.setEmail(email);
            }
        }
        return participant;
    }

    public List<Participant> selectAllParticipants() throws SQLException {
        List<Participant> participants = new ArrayList<>();
        String query = "SELECT * FROM Participants";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Participant participant = new Participant();
                participant.setId(id);
                participant.setName(name);
                participant.setEmail(email);
                participants.add(participant);
            }
        }
        return participants;
    }

    public boolean updateParticipant(Participant participant) throws SQLException {
        boolean rowUpdated;
        String query = "UPDATE Participants SET name = ?, email = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, participant.getName());
            preparedStatement.setString(2, participant.getEmail());
            preparedStatement.setInt(3, participant.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteParticipant(int id) throws SQLException {
        boolean rowDeleted;
        String query = "DELETE FROM Participants WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}