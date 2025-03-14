package com.cabbs.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.cabbs.config.DatabaseConnection;

@WebServlet("/BookCabServlet")
public class BookCabServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int cabId = Integer.parseInt(request.getParameter("cab_id"));
        String pickup = request.getParameter("pickup");
        String destination = request.getParameter("destination");

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement("INSERT INTO bookings (user_id, cab_id, pickup, destination, status) VALUES (?, ?, ?, ?, 'Pending')")) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, cabId);
            pstmt.setString(3, pickup);
            pstmt.setString(4, destination);
            pstmt.executeUpdate();

            response.sendRedirect("UserDashboardServlet?success=Booking Successful");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("UserDashboardServlet?error=Booking Failed");
        }
    }
}
