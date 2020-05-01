<%@page import="in.co.online.bank.mgt.controller.LoginCtl"%>
<%@page import="in.co.online.bank.mgt.controller.OBMView"%>
<%@page import="in.co.online.bank.mgt.bean.UserBean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
 <link rel="stylesheet" href="/BankManagement/css/bootstrap.min.css">
  <script src="/BankManagement/js/jquery.min.js"></script>
  <script src="/BankManagement/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker({
    	dateFormat : 'mm/dd/yy',
      changeMonth: true,
      changeYear: true,
      minDate:'0d'
    });
  } );
  
  $( function() {
	    $( "#datepicker1" ).datepicker({
	    	dateFormat : 'mm/dd/yy',
	      changeMonth: true,
	      changeYear: true,
	     
	    });
	  } );
  
 
	$(function() {
		$("#selectall").click(function() {
			$('.case').attr('checked', this.checked);
		});
		$(".case").click(function() {

			if ($(".case").length == $(".case:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}

		});
	});
</script>
 
</head>
<body>
<%
    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;

    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
        String role = (String) session.getAttribute("role");
        welcomeMsg += userBean.getFirstName() + " (" + role + ")";
    } else {
        welcomeMsg += "Guest";
    }

%>
<nav class="navbar navbar-inverse ml-auto">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="<%=OBMView.WELCOME_CTL%>">Bank Management</a>
    </div>
    <ul class="nav navbar-nav">
      <li ><a href="<%=OBMView.WELCOME_CTL%>">Home</a></li>
      <%if(userLoggedIn){%>
      
      <%if(userBean.getRoleId()==1){%>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Administration<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="<%=OBMView.USER_CTL%>">Add User</a></li>
          <li><a href="<%=OBMView.BANK_CTL%>">Add Bank</a></li>
          <li><a href="<%=OBMView.BOOK_TRANSACTION_CTL%>">Add Book Transaction</a></li>
        </ul>
       </li>
         <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Report<span class="caret"></span></a>
        <ul class="dropdown-menu">
        <li><a href="<%=OBMView.USER_LIST_CTL%>">User Report</a></li>
          <li><a href="<%=OBMView.FUND_TRANSFER_LIST_CTL%>">Fund Transfer Report</a></li>
          <li><a href="<%=OBMView.BANK_LIST_CTL%>">Bank Report</a></li>
          <li><a href="<%=OBMView.BOOK_TRANSACTION_LIST_CTL%>">All Transaction Report</a></li>
        </ul>
      </li>
    
     
    </ul>
    <%}else if(userBean.getRoleId()==2){%>
    <li><a href="<%=OBMView.ACCOUNT_BALANCE_CTL%>">Account Balance</a></li>
    <li><a href="<%=OBMView.FUND_TRANSFER_CTL%>">Fund Transfer</a></li>
     <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Report<span class="caret"></span></a>
        <ul class="dropdown-menu">
          
          <li><a href="<%=OBMView.FUND_TRANSFER_LIST_CTL%>">Fund Transfer Report</a></li>
          <li><a href="<%=OBMView.BOOK_TRANSACTION_LIST_CTL%>">Book Transaction Report</a></li>
        </ul>
      </li>
   </ul>
    <%} %>
    <ul class="nav navbar-nav navbar-right">
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><%=welcomeMsg%><span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="<%=OBMView.MY_PROFILE_CTL%>">My Profile</a></li>
          <li><a href="<%=OBMView.CHANGE_PASSWORD_CTL%>">Change Password</a></li>
          <li><a href="<%=OBMView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">LogOut</a></li>
        </ul>
      </li>
      
   
    <%}else{%>
     
      <li><a href="<%=OBMView.LOGIN_CTL%>">Sign In</a></li>
       <li><a href="<%=OBMView.USER_REGISTRATION_CTL%>">Sign Up</a></li>
      <li><a href="#"><%=welcomeMsg%></a></li>
      </ul>
    
    <%} %>
    
     
    
  </div>
</nav>
  

</body>
</html>