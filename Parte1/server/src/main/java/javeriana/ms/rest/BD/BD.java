package javeriana.ms.rest.BD;

import javeriana.ms.rest.Trip;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BD {

    public static final String URL="jdbc:mariadb://localhost:3306/paseo";
    public static final String USERNAME="root";
    public static final String PASSWORD="1234";

    private Connection Conexion() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Trip> getAvailableTrips() throws SQLException {
        try {

            Connection conn = this.Conexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from trip;");
            List<Trip> trips = new ArrayList<Trip>();
            while (rs.next()) {
                trips.add(new Trip(rs.getInt("id"), rs.getString("origen"), rs.getString("destino")));
            }
            return trips;
        } finally {

        }
    }

    public int update(Integer id, String destino, String origen) throws SQLException {
        try (Connection conn =  this.Conexion()) {
            PreparedStatement stmt = conn.prepareStatement("update trip set origen=?,destino=? where id= ?");
            stmt.setString(1, origen);
            stmt.setString(2, destino);
            stmt.setInt(3, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                return 200;
            } else {
                return 404;
            }
        }
    }

    public int deleteTrip(Integer id) throws SQLException {
        try (Connection conn =  this.Conexion()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE from trip where id= ?");
            stmt.setInt(1, Integer.valueOf(id));
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                return 200;
            } else {
                return 404;
            }
        }
    }

    public int createTrip(Trip trip) throws SQLException {

        try (Connection conn =  this.Conexion()) {

            PreparedStatement statement = conn.prepareStatement("INSERT INTO trip (id,origen, destino) VALUES (?, ?, ?)");
            statement.setInt(1, trip.id);
            statement.setString(2, trip.origen);
            statement.setString(3, trip.destino);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                return 201;
            }
            return -1;
        }
    }
}
