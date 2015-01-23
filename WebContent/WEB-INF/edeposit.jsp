<jsp:include page="template-top.jsp" />
<body>
<!-- container -->
	<div class="container" style="margin-left: 20px">
		<div class="row">
			<div class="col-md-5">
				<br>
				<h3 class="checkout-title">Deposit Check for Customer</h3>
			</div>
		</div>
		

		<div class="row">
			<hr>
			
		</div>
		
		<div class="row">
			<div class="form-group col-md-11">
				<div class="form-group col-md-11">
					<h4 class="col-title">Deposit Check for <a>${customer.userName} </a>:</h4>
				</div>
				<div class="form-group col-md-11">
					<h4 class="col-title">His/Her Available Cash Balance is: ${customer.availableCash}</h4>
				</div>
				<div  class="form-group col-md-2">
					<h4 class="col-title">Enter Amount :</h4>
				</div>
				<form action="edeposit.do" method="post">
				<div  class="form-group col-md-2">
					<div class="quickform-element">
						<input size="40" maxlength="64" name="amount" type="text" class="form-control" value="${form.amount}"/>
			
						<input type="hidden" name="customerId" value="${customer.customerId}">
					</div>
				</div>
				<div  class="form-group col-md-2">
					<button type="submit" id="edit3" class="btn-xs btn-primary" data-toggle="collapse" data-target="#demo1 " style="margin-top: 4px;">Submit</button>
				</div>
				</form>
				<div class="form-group col-md-11">
					<jsp:include page="error-list.jsp" />
					<h4>${success}</h4>
				</div>
			</div>
			
		</div>
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
      <button type="button" class="btn btn-primary">Login</button>
    </div>
  </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div>


<script>
$('#popoverData').popover(); 

$(document).ready(function(){
  $("#edit1").click(function(){
   
  });
  $("#save1").click(function(){
    $("#test1").show();
  });
});


$('#popoverData').popover(); 

$(document).ready(function(){
  $("#edit2").click(function(){

  });
  $("#save2").click(function(){
    $("#test2").show();
  });
});

$('#popoverData').popover(); 

$(document).ready(function(){
  $("#edit3").click(function(){

  });
  $("#save3").click(function(){
    $("#test3").show();
  });
});

</script>
</body>
</html>
<jsp:include page="template-bottom.jsp" />