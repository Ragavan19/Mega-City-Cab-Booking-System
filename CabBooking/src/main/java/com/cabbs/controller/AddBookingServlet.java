package com.cabbs.controller;

import com.cabbs.config.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/AddBookingServlet")
public class AddBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int cabId = Integer.parseInt(request.getParameter("cab_id"));
        String pickup = request.getParameter("pickup");
        String destination = request.getParameter("destination");

        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO bookings (user_id, cab_id, pickup, destination, status) VALUES (?, ?, ?, ?, 'Pending')";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, cabId);
            stmt.setString(3, pickup);
            stmt.setString(4, destination);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                response.sendRedirect("ViewBookingsServlet");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
