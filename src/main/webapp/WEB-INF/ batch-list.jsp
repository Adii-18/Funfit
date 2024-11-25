
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Batch List</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 8px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #f2f2f2;
}
</style>
</head>
<body>
	<h1>Batch List</h1>
	<a href="batches?action=new">Add New Batch</a>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Time</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="batch" items="${listBatches}">
				<tr>
					<td>${batch.id}</td>
					<td>${batch.name}</td>
					<td>${batch.time}</td>
					<td><a href="batches?action=edit&id=${batch.id}">Edit</a> <a
						href="batches?action=delete&id=${batch.id}"
						onclick="return confirm('Are you sure you want to delete this batch?');">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>