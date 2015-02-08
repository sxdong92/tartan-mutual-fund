<!-- This is a line of comment.... JSP SUCKS! -->
<!-- Yup, XP said that -->

<jsp:include page="login-top.jsp" />




<div class="row">
		<div class="col-md-7">
			<hr>
			<div id="gallery" class="slider">
			    <ul class="bxslider">
			      <li><img src="image/mfad1.jpg"/></li>
			      <li><img src="image/mfad2.png"/></li>
			      <li><img src="image/mfad3.png"/></li>
			    </ul>
			</div>
		</div>
		<div class="col-md-1">
		
		
			</div>
		<div class="col-md-4">
			<br>
			<h3 class="col-title">Login</h3>
			
			<jsp:include page="error-list.jsp" />
			<br>
			
			<form class="form-horizontal" method="post" action="login.do">
			  <div class="form-group">
			    <label for="inputEmail3" class="col-sm-3 control-label">UserName:</label>
			    <div class="col-sm-8">
			      <input type="text" class="form-control" name="userName"  value="" placeholder="UserName">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-3 control-label">Password:</label>
			    <div class="col-sm-8">
			      <input type="password" class="form-control" name="password" value="" placeholder="Password">
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-3 col-sm-10">
			      <div class="checkbox">
			        <label>
			          <input type="checkbox"> Remember me
			        </label>
			      </div>
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-3 col-sm-10">
			      <input type="submit" name="button" class="btn btn-default" value="Customer Login">
			      <input type="submit" name="button" class="btn btn-default" value="Employee Login">
			    </div>
			  </div>
			</form>
		</div>
	</div>


<jsp:include page="template-bottom.jsp" />
