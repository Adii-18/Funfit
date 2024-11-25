<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Participants List</title>
</head>
<body>
<h2>Participants</h2>
<a href="participants?action=new">Add New Participant</a>
<table border="1" width="50%" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="participant" items="${listParticipants}">
        <tr>
            <td>${participant.id}</td>
            <td>${participant.name}</td>
            <td>${participant.email}</td>
            <td>
                <a href="participants?action=edit&id=${participant.id}">Edit</a>
                <a href="participants?action=delete&id=${participant.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>