package com.cabbs.controller;

import com.cabbs.config.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Database connection
        try (Connection connection = DatabaseConnection.getConnection()) {
            // SQL query to insert user
            String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, email);
                statement.setString(3, password);  // You should hash the password before saving it!

                // Execute the query
                int result = statement.executeUpdate();

                // Response handling
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                if (result > 0) {
                    out.println("<h2>Registration Successful! Please <a href='login.jsp'>Login</a></h2>");
                } else {
                    out.println("<h2>Registration Failed. Please try again!</h2>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<h2>Error while connecting to database</h2>");
        }
    }
}
