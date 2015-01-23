<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-top.jsp" />

<c:forEach var="error" items="${errors}">
			<div style="color:red"> ${error} </div>
</c:forEach>
<h4>${success }</h4>
 <div class="container" style="margin-left: 170px">
 	<div class="row">
	    <div class="col-md-5">        
	      <br>
	      <h3 class="checkout-title">Create Fund</h3>
	    </div>
  	</div>

    

 	<div class="row">
 		<hr>
    		<div class="col-md-6">   
      			<form name="form" action="ecreatefunds.do" method="post">
				<div class="row">

	            	<br>
	
		            <div class="col-md-10">
		              <label for="fundName" class="control-label">Fund Name</label>
		              <div class="quickform-element"><input size="40" maxlength="128" name="fundName" value="${form.fundName}" type="text" class="form-control" required/>
		              </div>
		              <br> 
		            </div>
	
	
		            <div class="col-md-10">
		              <label for="symbol" class="control-label">Ticker</label>
		              <div class="quickform-element"><input size="40" maxlength="128" name="symbol" value="${form.symbol}" type="text" class="form-control" required/>
		              </div>
		              <br>  
		            </div>   
          		</div>


          		<div class="row">
            
                <br>
                <br>


                  <div class="quickform-element">
                  <input class="btn btn-primary" id = "submit" type="submit" value="action" name="register me"> 
                  </div>
                

            	</div>
			</form>
          </div>
	</div>
</body>
</html>

<jsp:include page="template-bottom.jsp" />