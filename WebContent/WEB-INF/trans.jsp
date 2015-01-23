<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="edu.cmu.cs.webapp.tartan.databean.TransactionBean"%>
<%@page import="edu.cmu.cs.webapp.tartan.databean.FundBean"%>
<%@page import="java.util.Iterator"%>
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
<body>
	<div class="row">
		<div class="form-group col-md-10">
			<% 
			 HashMap<TransactionBean,FundBean> transHistory = (HashMap<TransactionBean,FundBean>)request.getAttribute("transHistory");
			if(transHistory==null||transHistory.size()==0){
			%>
					<h2>You do not have any transactions yet.</h2>
			<%}else{
				%>
				<table class="table">

						<tr>
							<td><b>Transaction ID</b></td>
							<td><b>Fund ID</b></td>
							<td><b>Fund Name</b></td>
							<td><b>Shares</b></td>
							<td><b>Amount</b></td>
							<td><b>Transaction Type</b></td>
							<td><b>Execute Date</b></td>
						</tr>
						
				<%
					Iterator<TransactionBean> it=transHistory.keySet().iterator();
					while (it.hasNext()){
						TransactionBean tran=(TransactionBean)it.next();
						out.println("<tr><td>"+tran.getTransactionId()+"</td>");
						out.println("<td>"+tran.getFundId()+"</td>");
					    if(tran.getFundId()<=0){
							out.println("<td>--</td>");
						}else{
							out.println("<td>"+transHistory.get(tran).getFundName()+"</td>");
							
						 }
					    out.println("<td>"+tran.getShares()+"</td>");
						out.println("<td>"+tran.getAmount()+"</td>");
						out.println("<td>"+tran.getTransactionType()+"</td>");
						if(tran.getExecuteDate()==null){
							out.println("<td>Pending</td></tr>");
						}else{ 
							out.println("<td>"+tran.getExecuteDate()+"</td></tr>");
						} 
					}
				}
				%>

					
			</table>		

		</div>
	</div>
</body>
</html>