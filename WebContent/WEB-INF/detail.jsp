<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
<%@page import="java.util.HashMap"%>
<%@page import="edu.cmu.cs.webapp.tartan.databean.FundBean"%>
<%@page import="edu.cmu.cs.webapp.tartan.databean.PositionBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/jquery.ui.core.min.css" rel="stylesheet" type="text/css">
<link href="css/jquery.ui.theme.min.css" rel="stylesheet"
	type="text/css">
<link href="css/jquery.ui.autocomplete.min.css" rel="stylesheet"
	type="text/css">
<link href="css/jquery.ui.menu.min.css" rel="stylesheet" type="text/css">

<!-- SmartMenus core CSS (required) -->
<link href='css/sm-core-css.css' rel='stylesheet' type='text/css' />
<!-- sm-customer -->
<link href='css/sm-custom.css' rel='stylesheet' type='text/css' />
<!-- bxSlider CSS file -->
<link href="css/jquery.bxslider.css" rel="stylesheet" />
<!-- bootstrap CSS file -->
<link href="css/bootstrap.min.css" rel="stylesheet" />

<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="javascript/jquery.js" type="text/javascript"></script>
<!-- bxSlider Javascript file -->
<script src="javascript/jquery.bxslider.min.js"></script>
<!-- SmartMenus jQuery plugin -->
<script src="javascript/jquery.smartmenus.js" type="text/javascript"></script>
<!-- jQuery UI plugin -->
<script src="javascript/jquery-ui.js" type="text/javascript"></script>
<script src="javascript/jquery.ui-1.10.4.autocomplete.min.js"
	type="text/javascript"></script>
<!--Bootstrap plugin -->
<script src="javascript/bootstrap.js"></script>
<!--index javascript -->
<script src="javascript/index.js"></script>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"
	href="css/checkout.css" />
</head>
<title>Insert title here</title>
</head>
<body>
<div class="row">
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
</body>
</html>