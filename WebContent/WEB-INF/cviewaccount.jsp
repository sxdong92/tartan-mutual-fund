<jsp:include page="cheader.jsp" />
<!-- Your Information -->
<div class="container" style="margin-left: 20px">
	<div class="row">
		<div class="col-md-5">
			<br>
			<h3 class="checkout-title">View Account</h3>
		</div>
	</div>



	<div class="row">
		<hr>
		<div class="col-md-6">
			<div class="row">

				<div class="col-md-10">
					<label for="address[company]" class="control-label">First
						Name:</label>
					${customer.firstName}
				</div>

				<div class="col-md-10">
					<label for="address[company]" class="control-label">Last
						Name:</label>
					${customer.lastName}

				</div>

				<div class="col-md-10">
					<label for="address[company]" class="control-label">Address1:</label>
					${customer.addrLine1}
					<br>
				</div>
				
				<div class="col-md-10">
					<label for="address[company]" class="control-label">Address2:</label>
					${customer.addrLine2}
					<br>
				</div>
				
				<div class="col-md-10">
					<label for="address[company]" class="control-label">City
						:</label>
					${customer.city}

				</div>
				
				<div class="col-md-10">
					<label for="address[company]" class="control-label">State
					:</label>
					${customer.state}

				</div>
				
				<div class="col-md-10">
					<label for="address[company]" class="control-label">Zip:</label>
					${customer.zip}

				</div>
				
				<div class="col-md-10">
					<label for="address[company]" class="control-label">Last
						Trading Date:</label>
					<div class="quickform-element"></div>

				</div>

				<div class="col-md-10">
					<label for="address[company]" class="control-label">Cash
						Balance:</label>
					${customer.cash}

				</div>
				
				<div class="col-md-10">
					<label for="address[company]" class="control-label">Available Cash
						Balance:</label>
					${customer.availableCash}

				</div>

				<div class="col-md-10">
					<label for="address[company]" class="control-label">Number
						of Shares</label>
					<div class="quickform-element"></div>
					<br>

				</div>
	</div>
	</div>
			</div>
			
				<div class="row">
			<div class="form-group col-md-11">
				<div class="form-group col-md-11">
					<h4 class="col-title">Transaction History of <a>${customer.userName}</a>:</h4>
				</div>
				<div  class="form-group col-md-10">
					<iframe id="messagetext_ifr" frameborder="0" allowtransparency="true" 
	title="Content Editor. Press ALT F10 for toolbar. Press ALT 0 for help." src="cviewaccount.do"
	style="width: 100%; height: 200px; display: block;"scrolling="auto">
					</iframe>
				</div>
			</div>
		</div>
	<jsp:include page="template-bottom.jsp" />
	
	</body>
	</html>