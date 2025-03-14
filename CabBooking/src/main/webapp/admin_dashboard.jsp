<%@ page session="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="admin_styles.css"> <!-- Professional CSS -->
</head>
<body>
    <header>
        <h1>Admin Dashboard</h1>
        <nav>
            <ul>
                <li><a href="ViewBookingsServlet">View Bookings</a></li>
                <li><a href="add_booking.jsp">Book a Cab</a></li>
                <li><a href="manage_users.jsp">Manage Users</a></li>
                <li><a href="manage_cabs.jsp">Manage Cabs</a></li>
                <li><a href="add_user.jsp">Add New User</a></li>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>
        </nav>
    </header>

    <section>
        <h2>Dashboard Summary</h2>
        <div class="stats">
            <div class="card">
                <h3>Total Users</h3>
                <p id="totalUsers">Fetching...</p>
            </div>
            <div class="card">
                <h3>Total Bookings</h3>
                <p id="totalBookings">Fetching...</p>
            </div>
            <div class="card">
                <h3>Available Cabs</h3>
                <p id="totalCabs">Fetching...</p>
            </div>
        </div>
    </section>

    <script>
        // Fetch dashboard stats dynamically using AJAX
        fetch('DashboardStatsServlet')
            .then(response => response.json())
            .then(data => {
                document.getElementById('totalUsers').innerText = data.totalUsers;
                document.getElementById('totalBookings').innerText = data.totalBookings;
                document.getElementById('totalCabs').innerText = data.totalCabs;
            })
            .catch(error => console.error('Error fetching stats:', error));
    </script>
</body>
</html>
