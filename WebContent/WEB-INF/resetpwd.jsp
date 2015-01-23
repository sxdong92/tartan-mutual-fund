<jsp:include page="cheader.jsp" />
<body>
	<div class="container" style="margin-left: 20px">
		</br>
		<h3>Reset Customer Password</h3>
		<jsp:include page="error-list.jsp" />
		<h4>${success }</h4>
		</br>
		<p style="font-size:18px" >You may reset the password for ${customer.firstName} ${customer.lastName} here.</p>
		
		<form class="form-horizontal" method="post" action="resetpwd.do">
			<input type="hidden" name="allFundId" value="${customer.customerId}"/>
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">New Password:</label>
		    <div class="col-sm-8">
		      <input type="password" class="form-control" size="25" maxlength="64" name="newPassword" value="" placeholder="Reset a New Password">
		    </div>
		  </div>
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">Confirm Password:</label>
			<div class="col-sm-8">
		      <input type="password" class="form-control" size="25" maxlength="64" name="confirmPassword" value="" placeholder="Re-enter your new password">
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-3 col-sm-10">
		      <input type="submit" name="button" class="btn btn-default" value="Submit">
		    </div>
		  </div>
		</form>
	</div>
</body>
<jsp:include page="template-bottom.jsp" />
</html>