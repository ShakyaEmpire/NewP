<%@page import="in.co.online.bank.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Balance</title>
</head>
<body>
<%@ include file="Header.jsp" %>

<div class="container">    
   
    <div id="signupbox" style=" margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Account Balance</div>
                  
            </div>  
            <div class="panel-body" >
               
                <jsp:useBean id="bean" class="in.co.online.bank.mgt.bean.AccountBalanceBean"
            scope="request"></jsp:useBean>

                        <div  class="form-group required">
                           
                            <div class="controls col-md-8 ">
                               <p>Your Account Balance is <%=bean.getAmount()%>/-</p>
                            </div>
                        </div>
  
            </div>
        </div>
    </div> 
</div>

<div style="margin-top: 305px">           
<%@ include file="Footer.jsp" %>
</div>
</body>
</html>