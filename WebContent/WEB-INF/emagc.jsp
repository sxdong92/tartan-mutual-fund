<jsp:include page="template-top.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%@page import="java.util.HashMap"%>
<%@page import="edu.cmu.cs.webapp.tartan.databean.FundBean"%>
<%@page import="edu.cmu.cs.webapp.tartan.databean.PositionBean"%>
<!-- container -->
<div class="container" style="margin-left: 50px">
	<div class="row">
		<div class="col-md-5">
			<br>
			<h3 class="checkout-title">Manage Customer Account</h3>
		</div>
	</div>


	<div class="row">
		<hr>

	</div>

	<div class="row">
		<div class="form-group col-md-11">
			<div class="form-group col-md-10">
				<table class="table">
					<thead>
						<tr>
							<td><b>User ID</b></td>
							<td><b>User Name</b></td>
							<td><b>First Name</b></td>
							<td><b>Last Name</b></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a>${customer.customerId}</a></td>
							<td>${customer.userName}</td>
							<td>${customer.firstName}</td>
							<td>${customer.lastName}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-11">
			<div class="form-group col-md-11">
				<h4 class="col-title">Detail Info :</h4>
			</div>
			<div class="form-group col-md-6">
				<table class="table">
					<thead>
						<tr>
							<td><b>Address</b></td>
							<td><b>Last Trading Day</b></td>
							<td><b>Cash Balance</b></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${customer.addrLine1}</td>
							<td>XXXX</td>
							<td>${customer.cash}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="form-group col-md-6">

				<a href="viewhistory.do?customerId=${customer.customerId}">
					<button type="submit" class="btn btn-primary">Transaction
						History</button>
				</a> <a href="edeposit.do?customerId=${customer.customerId}">
					<button type="submit" class="btn btn-primary">Deposit
						Check</button>
				</a> <a href="resetpwd.do?customerId=${customer.customerId}">
					<button type="submit" class="btn btn-primary">Reset
						Password</button>
				</a>

			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-11">
			<div class="form-group col-md-10">
				<h4 class="col-title">Shares Info :</h4>
			</div>
			<div class="form-group col-md-10">
				<table class="table">

					<tr>
						<td><b>Fund Name</b></td>
						<td><b>Ticker</b></td>
						<td><b>shares</b></td>
						<td><b>Position Value</b></td>
					</tr>
					<%
					 PositionBean[] position = (PositionBean[]) (request.getAttribute("positionList"));
					
					 FundBean[] fund = (FundBean[]) request.getAttribute("fundList");
					 
					 long[] price = (long[]) request.getAttribute("priceList");

							
					for (int i = 0; i < position.length;i++ ){
						PositionBean position1 = position[i];
						FundBean fund1 = fund[i];
						long price1 = position1.getShares() * price[i];
						
					%>

					<tr>
						<td><%=fund1.getFundName()%></td>
						<td><%=fund1.getSymbol()%></td>
						<td><%=position1.getShares()%></td>
						<td><%=price1%></td>
					</tr>
					<%
						}
					%>



				</table>
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="login-modal" tabindex="-1"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Login</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label for="exampleInputEmail1">Email address</label> <input
						type="email" class="form-control" id="exampleInputEmail1"
						placeholder="Enter email">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">Password</label> <input
						type="password" class="form-control" id="exampleInputPassword1"
						placeholder="Password">
				</div>
				<div class="checkbox">
					<label> <input type="checkbox"> Remember me
					</label> <label id="forgot-link"> Forgot password </label>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="reg-button">Register</button>
				<button type="button" class="btn btn-primary">Login</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>



<jsp:include page="template-bottom.jsp" />