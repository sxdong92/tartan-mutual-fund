<jsp:include page="cheader.jsp" />
<%@page import="java.util.*"%>
<%@page import="java.util.HashMap"%>
<%@page import="edu.cmu.cs.webapp.tartan.databean.FundBean"%>
<%@page import="edu.cmu.cs.webapp.tartan.databean.PositionBean"%>

<div id="body">
    <h2>My Funds</h2>
    <form method="POST">
      <table class="table table-striped">
        <tr>
          <td>FundId</td>
          <td>Ticker</td>
          <td>Name</td>
          <td>Current Value</td>
          <td>Action</td>
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
						<td><%=fund1.getFundId()%></td>
						<td><%=fund1.getSymbol()%></td>
						<td><%=fund1.getFundName()%></td>
						
						<td><%=position1.getShares()%></td>
						<td><%=price1%></td>
						<td>
						<a href="cbuyfund.do?fundId=<%=fund1.getFundId()%>">buy </a>
						<a href="sellfund.do?id=<%=fund1.getFundId()%>">sell</a>
        </td>
					</tr>
					<%
						}
					%>      
      </table>
    </form>
  </div>
  <div>
				<jsp:include page="error-list.jsp" />
				
				</div>
  <jsp:include page="template-bottom.jsp" />
  </body>
</html>