<jsp:include page="template-top.jsp" />
<body>
	<div class="container" style="margin-left: 20px">
		</br>
		<h3>Change Employee Password</h3>
		<jsp:include page="error-list.jsp" />
		<h4>${success}</h4>
		</br>
		<form class="form-horizontal" method="post" action="echangepwd.do">
		  <div class="form-group">
		    <label class="col-sm-3 control-label">Old Password:</label>
		    <div class="col-sm-8">
		      <input type="password" class="form-control" size="25" maxlength="64" name="oldPassword"  value="" placeholder="Old Password">
		    </div>
		  </div>
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">New Password:</label>
		    <div class="col-sm-8">
		      <input type="password" class="form-control" size="25" maxlength="64" name="newPassword" value="" placeholder="New Password">
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