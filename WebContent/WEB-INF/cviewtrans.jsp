<jsp:include page="cheader.jsp" />
<!-- container -->
	<div class="container" style="margin-left: 30px">
		<div class="row">
			<div class="col-md-5">
				<br>
				<h3 class="checkout-title">View Your Transaction History</h3>
			</div>
		</div>
		

		<div class="row">
			<hr>
			
		</div>
		
		<div class="row">
			<div class="form-group col-md-11">
				<div class="form-group col-md-11">
					<h4 class="col-title">Your Transaction History:</h4>
				</div>
				<div  class="form-group col-md-10">
					<iframe id="messagetext_ifr" frameborder="0" allowtransparency="true" 
	title="Content Editor. Press ALT F10 for toolbar. Press ALT 0 for help." src="showhistory.do"
	style="width: 100%; height: 200px; display: block;"scrolling="auto">
					</iframe>
				</div>
			</div>
		</div>
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
<jsp:include page="template-bottom.jsp" />