<jsp:include page="cheader.jsp" />
<div class="container" style="margin-left: 30px">
	<div id="body">
		<h2>Sell Funds</h2>
		<form method="POST" action="sellfund.do">
			<table>
				<tr>
					<td><label>Ticker:</label></td>
					<td>
						${fund.symbol}
						<input type="hidden" name="id" value="${fund.fundId}"/>
					</td>
					
				</tr>
				<tr>
					<td><p>Shares:</p></td>
					<td><input type="text" size="20" value="${form.shares}"
						name="shares" /></td>
				</tr>
			</table>
			<div class="form-group col-md-2">
				<input class="btn btn-primary" type="submit" value="Submit">
			</div>
			<div>
				<h4 style="color: black">${success}</h4>
			</div>
		</form>
	</div>

</div>

<jsp:include page="template-bottom.jsp" />
</body>
</html>