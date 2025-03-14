package com.cabbs.DAO;

import com.cabbs.config.DatabaseConnection;
import com.cabbs.model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM bookings")) {

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("bookingId"));
                booking.setUserId(rs.getInt("user_id"));
                booking.setCabId(rs.getInt("cab_id"));

                booking.setBookingTime(rs.getTimestamp("booking_time"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public boolean addBooking(int userId, int cabId) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO bookings (user_id, cab_id, booking_time) VALUES (?, ?, ?)")) {

            stmt.setInt(1, userId);
            stmt.setInt(2, cabId);
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis())); // Set the current time for booking

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if the booking was added successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
