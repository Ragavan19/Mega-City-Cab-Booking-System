package com.cabbs.controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;
import com.cabbs.config.DatabaseConnection;
import com.cabbs.model.Booking;
import com.cabbs.model.Cab;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {
            con = DatabaseConnection.getConnection();

            // Fetch bookings
            String bookingQuery = "SELECT booking_id, user_id, cab_id, pickup, destination, status FROM bookings";
            stmt1 = con.prepareStatement(bookingQuery);
            rs1 = stmt1.executeQuery();

            List<Booking> bookings = new ArrayList<>();
            while (rs1.next()) {
                bookings.add(new Booking(
                        rs1.getInt("booking_id"),
                        rs1.getInt("user_id"),
                        rs1.getInt("cab_id"),
                        rs1.getString("pickup"),
                        rs1.getString("destination"),
                        rs1.getString("status")
                ));
            }
            request.setAttribute("bookings", bookings);

            // Fetch cabs
            String cabQuery = "SELECT cab_id, model FROM cabs";
            stmt2 = con.prepareStatement(cabQuery);
            rs2 = stmt2.executeQuery();

            List<Cab> cabs = new ArrayList<>();
            while (rs2.next()) {
                cabs.add(new Cab(
                        rs2.getInt("cab_id"),
                        rs2.getString("model")
                ));
            }
            request.setAttribute("cabs", cabs);

            // Forward the request to the JSP
            request.getRequestDispatcher("admin_dashboard.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources safely
            try {
                if (rs1 != null) rs1.close();
                if (stmt1 != null) stmt1.close();
                if (rs2 != null) rs2.close();
                if (stmt2 != null) stmt2.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
