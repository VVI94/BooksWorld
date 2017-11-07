<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<body onload="SidebarChangeConent();">


	<c:if test="${ sessionScope.user == null and not user.isAdmin}">
		<c:redirect url="/LogIn"></c:redirect>
	</c:if>

	<%-- Navbar  --%>
	<jsp:include page="navBarAdmin.jsp"></jsp:include>

	<!-- Page  -->
	<div id="wrapper" class="toggled">

		<!-- Sidebar -->
		<jsp:include page="sideBarAdmin.jsp"></jsp:include>

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div id="content">
				<div class="container-fluid" id="dashboard">
					<h2 class="text-center text-info">${organizationName}</h2>
					<hr>
					<div class="table-responsive">
						<table class="table">
							<thead>
								<td>Project Id</td>
								<td>Project Name</td>
								<td>Project deadline</td>
							</thead>
							<c:forEach var="p" items="${projects}">
								<tr>
									<td>${p.id}</td>
									<td><a
										href="/final_project/projectdetail?projectId=${p.id}">${p.name}</a>
									</td>
									<td>${p.deadline}</td>
								</tr>
							</c:forEach>
						</table>
					</div>

				</div>

			</div>
		</div>
		<!-- /#page-content-wrapper -->

	</div>
</body>
</html>