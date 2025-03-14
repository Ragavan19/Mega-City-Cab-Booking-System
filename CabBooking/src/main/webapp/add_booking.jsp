<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Booking</title>
</head>
<body>
    <h1>Add New Booking</h1>
    <form action="AddBookingServlet" method="POST">
        <label for="user_id">User ID:</label>
        <input type="text" id="user_id" name="user_id" required><br><br>

        <label for="cab_id">Cab ID:</label>
        <input type="text" id="cab_id" name="cab_id" required><br><br>

        <label for="pickup">Pickup Location:</label>
        <input type="text" id="pickup" name="pickup" required><br><br>

        <label for="destination">Destination:</label>
        <input type="text" id="destination" name="destination" required><br><br>

        <input type="submit" value="Book Now">
    </form>
    <br>
    <a href="view_bookings.jsp">View Bookings</a>
</body>
</html>
