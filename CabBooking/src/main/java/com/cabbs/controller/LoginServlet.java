package com.cabbs.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.cabbs.config.DatabaseConnection;
import com.cabbs.model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = DatabaseConnection.getConnection();
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String role = rs.getString("role");  // Get user role from the "users" table

                // Create User object with the updated model class
                User user = new User(userId, username, password, role, rs.getString("email"), rs.getString("phone"));

                // Set the user object in session
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                // Redirect based on user role
                if (role.equals("admin")) {
                    response.sendRedirect("admin_dashboard.jsp");  // Admin dashboard
                } else if (role.equals("client")) {
                    response.sendRedirect("user_dashboard.jsp");   // User dashboard
                }
            } else {
                // Redirect to login with an error message if credentials are invalid
                response.sendRedirect("login.jsp?error=Invalid Credentials");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=Server Error");
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
