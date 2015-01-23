<jsp:include page="cheader.jsp" />


<div id="body" style="margin-left: 30px">
	<div class="col-md-5">
		<br>
		<h3 class="checkout-title">Buy Funds</h3>
	</div>
</div>
<div class="row">
	<hr>

</div>
<div class="row">
	<div class="form-group col-md-11">
		<form method="POST" action="cbuyfund.do">
			<div class="form-group col-md-2">
				<h4 class="col-title">Ticker : ${fund.symbol}</h4>
				<input type="hidden" name="fundId" value="${fund.fundId}"/>
			</div>
			

			<div class="form-group col-md-2">
				<h4 class="col-title">Enter Amount $:</h4>
			</div>
			<div class="form-group col-md-2">
				<div class="quickform-element">
					<input size="20" maxlength="64" name="buyAmount" type="text"
						class="form-control" />
				</div>
			</div>
			<div class="form-group col-md-2">
				<h4 class="col-title">Your Available Cash
					is:${customer.availableCash}</h4>
			</div>
			<div class="form-group col-md-2">
				<button type="submit" id="edit3" class="btn-xs btn-primary"
					data-toggle="collapse" data-target="#demo1 "
					style="margin-top: 4px;">Submit</button>
			</div>
		</form>
		<div>
			<jsp:include page="error-list.jsp" />
			<h4>${success}</h4>
		</div>
	</div>
</div>
<jsp:include page="template-bottom.jsp" />

</html>