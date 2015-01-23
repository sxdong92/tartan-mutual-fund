<jsp:include page="cheader.jsp" />
<!-- container -->
<jsp:include page="error-list.jsp" />
<div class="container" style="margin-left: 30px">
	<div class="row">
		<div class="col-md-5">
			<br>
			<h3 class="checkout-title">Request Check</h3>
		</div>
	</div>

<div class="row">
	<form method="post" action="requestcheck.do">
		<div class="row">
			<hr>
			<div class="col-md-12"></div>
		</div>


		<div class="form-group col-md-12">
			<div class="form-group col-md-12">
				<h4 class="col-title">Your Available Cash is:
					${customer.availableCash}</h4>
			</div>
			<br>
			<div class="form-group col-md-2">
				<h4 class="col-title">Enter Value:</h4>
			</div>
			<div class="form-group col-md-2">
				<div class="quickform-element">
					<input size="40" maxlength="64" name="requestAmount"
						value="${form.requestAmount}" type="text" class="form-control" />
				</div>
			</div>
			<div class="form-group col-md-2">
				<input class="btn btn-primary" type="submit" value="Submit">
			</div>
			<div>
			<h4 style="color: black">${success}</h4>
			</div>
		</div>
	</form>
</div>

	<jsp:include page="template-bottom.jsp" />
	</body>
	</html>