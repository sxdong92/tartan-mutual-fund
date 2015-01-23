<jsp:include page="template-top.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="edu.cmu.cs.webapp.tartan.databean.FundBean"%>
<jsp:include page="error-list.jsp" />

<div class="container" style="margin-left: 170px">
	<div class="row">
		<div class="col-md-5">
			<br>
			<h3 class="checkout-title">Search Funds</h3>
		</div>
	</div>

	<form method="post" action="searchfund.do">
	<div class="row">
		<hr>
		<div class="col-md-10">

			

			<div class="form-group col-md-2">
				<h4 class="col-title">Enter Ticker :</h4>
			</div>

			<div class="form-group col-md-2">
				<div class="quickform-element">
					<input size="40" maxlength="64" name="ticker" type="text"
						value="${form.ticker}" class="form-control" />
				</div>
			</div>

			<div class="form-group col-md-4">
				<input class="btn btn-primary" type="submit" name="action"
					value="Search"> <input class="btn btn-primary"
					type="submit" name="action" value="Show All">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-11">
			<div class="form-group col-md-10">
				<table class="table">
					<tr>
						<td><b>Ticker</b></td>
						<td><b>Name</b></td>
						<td><b>Price</b></td>
						<td></td>
						<td></td>
					</tr>
					<%
						long[] price = (long[]) request.getAttribute("priceList");
						FundBean[] fund = (FundBean[]) request.getAttribute("fundList");
						for (int i = 0; i < fund.length; i++) {
							FundBean fund1 = fund[i];
							long price1 = price[i];
					%>

					<tr>
						<td><a href="research.do?id=<%=fund1.getSymbol()%>"><%=fund1.getSymbol()%></a></td>
						<td><%=fund1.getFundName()%></td>
						<td><%=price1%></td>
						<td><a href="cbuyfund.do?fundId=<%=fund1.getFundId()%>">buy</a></td>
					</tr>
					<%
						}
					%>


				</table>
			</div>
		</div>
	</div>
	</form>
</div>


<jsp:include page="template-bottom.jsp" />
