<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Cab Booking System</title>
    <link rel="stylesheet" href="assets/css/login.css">
</head>
<body>
    <header>
        <h1>Login to Cab Booking System</h1>
    </header>

    <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="register.jsp">Register</a></li>
        </ul>
    </nav>

    <section>
        <form action="LoginServlet" method="POST">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br><br>
            <button type="submit">Login</button>
        </form>

        <!-- Show error message if invalid credentials -->
        <c:if test="${not empty param.error}">
            <div class="error-message">${param.error}</div>
        </c:if>
    </section>

    <footer>
        <p>&copy; 2025 Cab Booking System</p>
    </footer>
</body>
</html>
