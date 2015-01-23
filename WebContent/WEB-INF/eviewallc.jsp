<jsp:include page="template-top.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="error-list.jsp" />

<!-- container -->
<div class="container" style="margin-left: 170px">
	<div class="row">
		<div class="col-md-5">
			<br>
			<h3 class="checkout-title">Manage Customer Account</h3>
		</div>
	</div>

	<form method="post" action="search.do">
		<div class="row">
			<hr>
			<div class="col-md-10">

				<div class="form-group col-md-3">
					<h4 class="col-title">Enter Customer ID :</h4>
				</div>

				<div class="form-group col-md-2">
					<div class="quickform-element">
						<input size="40" maxlength="64" name="customerId" type="text"
							value="${form.customerId}" class="form-control" />
					</div>
				</div>

				<div class="form-group col-md-4">
					<input class="btn btn-primary" type="submit" name = "action" value="Search">
					<input class="btn btn-primary" type="submit" name = "action" value="Show All">
				</div>
			</div>
		</div>
	</form>

	<div class="row">
		<div class="form-group col-md-11">
			<div class="form-group col-md-10">
				<table class="table">
					<tr>
						<td><b>User ID</b></td>
						<td><b>User Name</b></td>
						<td><b>First Name</b></td>
						<td><b>Last Name</b></td>
					</tr>

					<c:forEach var="customer" items="${customerList}">
						<tr>
							<td><a href = "viewdetail.do?id=${customer.customerId}">${customer.customerId}</a></td>
							<td>${customer.userName}</td>
							<td>${customer.firstName}</td>
							<td>${customer.lastName}</td>
						</tr>
					</c:forEach>

				</table>
			</div>
		</div>
	</div>

</div>
	



	<jsp:include page="template-bottom.jsp" />