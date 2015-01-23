<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/jquery.ui.core.min.css" rel="stylesheet" type="text/css">
<link href="css/jquery.ui.theme.min.css" rel="stylesheet"
	type="text/css">
<link href="css/jquery.ui.autocomplete.min.css" rel="stylesheet"
	type="text/css">
<link href="css/jquery.ui.menu.min.css" rel="stylesheet" type="text/css">

<!-- SmartMenus core CSS (required) -->
<link href='css/sm-core-css.css' rel='stylesheet' type='text/css' />
<!-- sm-customer -->
<link href='css/sm-custom.css' rel='stylesheet' type='text/css' />
<!-- bxSlider CSS file -->
<link href="css/jquery.bxslider.css" rel="stylesheet" />
<!-- bootstrap CSS file -->
<link href="css/bootstrap.min.css" rel="stylesheet" />

<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="javascript/jquery.js" type="text/javascript"></script>
<!-- bxSlider Javascript file -->
<script src="javascript/jquery.bxslider.min.js"></script>
<!-- SmartMenus jQuery plugin -->
<script src="javascript/jquery.smartmenus.js" type="text/javascript"></script>
<!-- jQuery UI plugin -->
<script src="javascript/jquery-ui.js" type="text/javascript"></script>
<script src="javascript/jquery.ui-1.10.4.autocomplete.min.js"
	type="text/javascript"></script>
<!--Bootstrap plugin -->
<script src="javascript/bootstrap.js"></script>
<!--index javascript -->
<script src="javascript/index.js"></script>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"
	href="css/checkout.css" />
<title>Tartans Employee System</title>
</head>
<body>
	<div class="header">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-7">
					<div>
						<img src="image/tartan.jpg" width="250" height="106"
							alt="Tartans Mutual Fund" />
					</div>
				</div>


			</div>
		</div>
		<a href="logout.do">Logout</a>
		<div id="menu">
			<ul id="main-menu" class="sm sm-blue">
				<li><a href="showcaccount.do">My Account</a>
					<ul>
						<li><a href="showcaccount.do">View Account Information</a></li>
						<li><a href="cchangepwd.do">Change Password</a></li>
						<li><a href="requestcheck.do">Request Check</a></li>
					</ul></li>
				<li><a href="cmagfunds.do">Transaction</a>
					<ul>
						<li><a href="cmagfunds.do">My Fund</a></li>
						<li><a href="viewfunds.do">Search Fund</a></li>
					</ul></li>

				<li><a href="cviewtrans.do">Transaction History</a></li>
			</ul>
		</div>
	</div>