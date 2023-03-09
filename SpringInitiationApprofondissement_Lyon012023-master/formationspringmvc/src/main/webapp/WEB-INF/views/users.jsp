<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!--Context est utilisé pour spécifier l'application -->
<link rel="stylesheet" href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" context="/springmvc"/>">
<title>Liste des utilisateur</title>
</head>
<body>
	<!-- Pour tester -->
	<!--Test  -->
	<table class="table table-dark table-hover col-xl-6 col-sm-6 mt-5" style="margin-left: 20px;" >
		<thead class="panel-heading">
			<tr>
				<th>Prenom</th>
				<th>Nom</th>
				<th>Email</th>
				<th>Role</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="u" items="${users}">
				<tr>
					<td>${u.prenom}</td>
					<td>${u.nom}</td>
					<td>${u.email}</td>
					<td>${u.role}</td>
					<td>
					
					<c:url  value="/admin/users/delete/${u.id }" context="/springmvc" var="urldelete" />
						<a href="${urldelete}" onclick="return confirm('Are you sure?')">Delete</a>
					</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>