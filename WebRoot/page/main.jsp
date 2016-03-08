<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>导航栏</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="">
	<meta http-equiv="description" content="Dropdowns">

	<link href="/CarManageSystem/css/bootstrap.css" rel="stylesheet" type="text/css" />
	<script src="/CarManageSystem/js/jquery-1.10.1.js"></script> 
	<script src="/CarManageSystem/js/bootstrap.js"></script> 
	
  </head>
  
<body>
   
	<h3>Within pills</h3>
  <div class="bs-example">
    <ul class="nav nav-pills" role="tablist">
      <li role="presentation" class="active"><a href="#">Regular link</a></li>
      <li role="presentation" class="dropdown">
        <a id="drop4" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
          Dropdown
          <span class="caret"></span>
        </a>
        <ul id="menu1" class="dropdown-menu" aria-labelledby="drop4">
          <li><a href="#">Action</a></li>
          <li><a href="#">Another action</a></li>
          <li><a href="#">Something else here</a></li>
          <li role="separator" class="divider"></li>
          <li><a href="#">Separated link</a></li>
        </ul>
      </li>
      <li role="presentation" class="dropdown">
        <a id="drop5" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
          Dropdown
          <span class="caret"></span>
        </a>
        <ul id="menu2" class="dropdown-menu" aria-labelledby="drop5">
          <li><a href="#">Action</a></li>
          <li><a href="#">Another action</a></li>
          <li><a href="#">Something else here</a></li>
          <li role="separator" class="divider"></li>
          <li><a href="#">Separated link</a></li>
        </ul>
      </li>
      <li role="presentation" class="dropdown">
        <a id="drop6" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
          Dropdown
          <span class="caret"></span>
        </a>
        <ul id="menu3" class="dropdown-menu" aria-labelledby="drop6">
          <li><a href="#">Action</a></li>
          <li><a href="#">Another action</a></li>
          <li><a href="#">Something else here</a></li>
          <li role="separator" class="divider"></li>
          <li><a href="#">Separated link</a></li>
        </ul>
      </li>
    </ul> <!-- /pills -->
  </div> <!-- /example -->

	
	
	
	
	
	
	
<script>
      $(document).ready(function () {
          $('.dropdown-toggle').dropdown();
      });
</script>

</body>
</html>
