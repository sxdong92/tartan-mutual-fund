<jsp:include page="template-top.jsp" />
<body style="margin-left:5%;margin-right:5%">



<br> 
<div class="container">
<div class="row">
    <div class="col-md-5">        
      <br>
      <h3>Transition System</h3>
    </div>
    <div class="col-md-8">        
      <hr/>
    </div>
</div>
<br>

<jsp:include page="error-list.jsp" />



<form method="post" action="transition-day.do">
<table>
<tr>        
        <td style="padding-right: 20px"><b>Date:</b></td> 
        <%@ page import="edu.cmu.cs.webapp.tartan.databean.LastDayBean" %>
        <%@ page import="java.util.Date" %>
		<%
			//LastDayBean lastDay = (LastDayBean)request.getAttribute("lastDay");
			Date lastDay = (Date)request.getAttribute("lastDay");
			String lastDayString = "";
			if (lastDay != null) {
				java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy");
				lastDayString = dateFormat.format(lastDay);
			}
			else {
				lastDayString = "No Last Day";
			}
		%>
        <td style="padding-right: 20px">
        	<input type="text" name="date" class="form-control" placeholder = "Month/Day/Year" value="<%=lastDayString %>"/> 
        </td>
	</tr>
</table>
<br><br>
<div><b>Last Transition Date:</b></div> 

<br> <br> <br>


	<div class="col-xs-13" >
		<table frame = "box" class ="table table-bordered">  
		<thead>
    		<tr class="active" >
     			<th rowspan="2">Fund ID</th>
     			<th rowspan="2">Name</th>
     			<th rowspan="2">Ticker</th>
     			<th rowspan="2">closing price</th>
    		</tr>
		</thead>
		<tbody>
    	<!--c:forEach var="fund" items="${fundList}"-->

<%@ page import="edu.cmu.cs.webapp.tartan.databean.FundBean" %>
<%
		FundBean[] allFunds = (FundBean[])request.getAttribute("allFunds");
        for (int i=0; i<allFunds.length; i++) {
%>
    		<tr>
      			<td>
      				<%=allFunds[i].getFundId()%>
      				<input type="hidden" name="allFundId" value="<%=allFunds[i].getFundId()%>"/>
      			</td>
      			<td><%=allFunds[i].getFundName()%></td>
      			<td><%=allFunds[i].getSymbol()%></td>
     		 	<td class="col-xs-2"><input type="Price" name="closingPrice" class="form-control" placeholder="Amount"></td>
    		</tr>
   	 		<!--/c:forEach-->
<%
		}
%>
		</tbody>
		</table>  
	</div>

	<br>
	<table align = "center">
		<tr>
			<td style="padding-right: 200px"><input class="btn btn-primary" name="button" type="submit" value="Submit"></td>
			<td style="padding-right: 200px"><input class="btn btn-primary" type="submit" value="Clear"></td>
		</tr>
	</table>
</form>


</div>
  
        
      
      
     
      

      
  


<a class="handle" ></a>

</div>
<jsp:include page="template-bottom.jsp" />