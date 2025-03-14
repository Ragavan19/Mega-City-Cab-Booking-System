<%@ page import="java.util.List" %>
<%@ page import="com.cabbs.model.Cab" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
</head>
<body>

<h2>User Dashboard</h2>

<form action="BookCabServlet" method="post">
    <label for="cab_id">Choose a Cab:</label>
    <select name="cab_id" required>
        <option value="">-- Select a Cab --</option>
        <%
            List<Cab> cabs = (List<Cab>) request.getAttribute("cabs");
            if (cabs != null) {
                for (Cab cab : cabs) {
        %>
            <option value="<%= cab.getCabId() %>"><%= cab.getModel() %></option>
        <%
                }
            }
        %>
    </select>

    <br><br>
    <label for="pickup">Pickup Location:</label>
    <input type="text" name="pickup" required>

    <br><br>
    <label for="destination">Destination:</label>
    <input type="text" name="destination" required>

    <br><br>
    <button type="submit">Book Now</button>
</form>

</body>
</html>
