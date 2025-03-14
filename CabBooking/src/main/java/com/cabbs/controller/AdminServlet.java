package com.cabbs.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cabbs.config.DatabaseConnection;
import com.cabbs.model.Booking;
import com.cabbs.model.User;
import com.cabbs.model.Cab;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("viewBookings".equals(action)) {
            viewBookings(request, response);
        } else if ("viewUsers".equals(action)) {
            viewUsers(request, response);
        } else if ("viewCabs".equals(action)) {
            viewCabs(request, response);
        } else {
            response.sendRedirect("admin_dashboard.jsp");
        }
    }

    private void viewBookings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Booking> bookings = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM bookings")) {
            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("user_id"),
                        rs.getInt("cab_id"),
                        rs.getString("pickup"),
                        rs.getString("destination"),
                        rs.getString("status")
                ));
            }
            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("admin_view_bookings.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("admin_dashboard.jsp?error=Could not fetch bookings");
        }
    }

    private void viewUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("role")
                ));
            }
            request.setAttribute("users", users);
            request.getRequestDispatcher("admin_view_users.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("admin_dashboard.jsp?error=Could not fetch users");
        }
    }

    private void viewCabs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cab> cabs = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM cabs")) {
            while (rs.next()) {
                cabs.add(new Cab(
                        rs.getInt("cab_id"),
                        rs.getString("model"),
                        rs.getString("license_plate")
                ));
            }
            request.setAttribute("cabs", cabs);
            request.getRequestDispatcher("admin_view_cabs.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("admin_dashboard.jsp?error=Could not fetch cabs");
        }
    }
}
