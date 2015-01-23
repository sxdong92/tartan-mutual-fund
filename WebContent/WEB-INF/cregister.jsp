<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-top.jsp" />

<c:forEach var="error" items="${errors}">
			<div style="color:red"> ${error} </div>
</c:forEach>
<h4>${success }</h4>

	<!-- Your Information -->
<div class="container" style="margin-left: 170px">
	<div class="row">
		<div class="col-md-5">				
			<br>
			<h3 class="checkout-title">Create Customer Account</h3>
		</div>
	</div>

		

	<div class="row">
		<hr>
		<div class="col-md-6">

				
			<form name="form" action="cregister.do" method="POST">
					
				




					<div class="row">

						<br>

						<div class="col-md-10">
							<label for="firstName" class="control-label">First Name</label>
							<div class="quickform-element"><input size="40" maxlength="128" name="firstName" value="${form.firstName}" type="text" class="form-control" required/>
							</div>
							<br>
							
						</div>

						<div class="col-md-10">
							<label for="lastName" class="control-label">Last Name</label>
							<div class="quickform-element"><input size="40" maxlength="128" name="lastName" value="${form.lastName}" type="text" class="form-control" required/>
							</div>
							<br>
							
						</div>

						<div class="col-md-10">
							<label for="userName" class="control-label">Username</label>
							<div class="quickform-element"><input size="40" maxlength="128" name="userName" value="${form.userName}" type="text" class="form-control" required/>
							</div>
							<br>
						</div>

            			<div class="form-group col-md-10">
							<label for="addrLine1" class="control-label">Address line 1</label>
							<div class="quickform-element"><input size="40" maxlength="128" autocomplete="off" name="addrLine1" value="${form.addrLine1}" type="text" class="form-control"/>
							</div>
						</div>

					

					<div class="form-group col-md-10">
						<label  for="addrLine2" class="control-label">Address line 2</label>
						<div class="quickform-element"><input size="40" maxlength="128" autocomplete="off" name="addrLine2" value="${form.addrLine2}" type="text" class="form-control"/>
						</div>
					</div>

					</div>


					<div class="row">

					<div class="form-group col-md-5">
						<label for="city" class="control-label required">City</label>
						<div class="quickform-element"><input size="40" maxlength="64" name="city" value="${form.city}" type="text" class="form-control" required/>
						</div>
					</div>

					<div class="form-group col-md-5">
						<label for="state" class="control-label">State <span class="small"></span></label>
						<div class="quickform-element"><input size="40" maxlength="64" name="state" value="${form.state}" type="text" class="form-control" required/>
						</div>
					</div>
				

					<div class="form-group col-md-10">
						<label for="zip" class="control-label required">Zip</label>
						<div class="quickform-element"><input size="16" maxlength="16" name="zip" value="${form.zip}" type="text" class="form-control" required/>
						</div>
					</div>

						<div class="form-group col-md-10">
							<br>
							
							<label for="password">Password</label>
							<input type="password" class="form-control" id="password" name="password" placeholder="Password">
							
							<br>
							
							<label for="password">Confirm Password</label>
							<input type="password" class="form-control" id="password" name="confirm" value="" placeholder="Password">

							
							
						</div>
					</div>


					<div class="row">
						
								<br>
								<br>


									<div class="quickform-element">
									<input class="btn btn-primary" id = "submit" type="submit" name="button" value="Register">
									
									</div>
								

						</div>
						</form>
					</div>
				
</div>

<a href="#top" class ="top-button-left"></a>

<br>
<br>

</div>



<div class="modal fade" id="login-modal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
 <div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
      <h4 class="modal-title">Login</h4>
    </div>
    <div class="modal-body">
      <div class="form-group">
        <label for="exampleInputEmail1">Email address</label>
        <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
      </div>
      <div class="checkbox">
        <label>
          <input type="checkbox"> Remember me
        </label>
        
        <label id="forgot-link">
        	Forgot password
        </label>
      </div>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-primary"id="reg-button">Register</button>
      <a type="button" class="btn btn-primary" id="login-button" >Login</a>
    </div>
  </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div>
</body>
</html>
<jsp:include page="template-bottom.jsp" />