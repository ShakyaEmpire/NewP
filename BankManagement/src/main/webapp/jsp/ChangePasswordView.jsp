<%@page import="in.co.online.bank.mgt.controller.ChangePasswordCtl"%>
<%@page import="in.co.online.bank.mgt.util.DataUtility"%>
<%@page import="in.co.online.bank.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<div class="container">    
            
    <div id="signupbox" style=" margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Change Password</div>
                <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b><b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
            </div>  
            <div class="panel-body" >
                <form method="post" action="<%=OBMView.CHANGE_PASSWORD_CTL%>">
                 <jsp:useBean id="bean" class="in.co.online.bank.mgt.bean.UserBean"
			scope="request"></jsp:useBean>
			
			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
                   
                   
                   <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">Old Password<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="oldPassword" placeholder="Your Old Password " style="margin-bottom: 10px" type="password" 
                                value="<%=DataUtility
                    .getString(request.getParameter("oldPassword") == null ? ""
                            : DataUtility.getString(request
                                    .getParameter("oldPassword")))%>" />
                            	<font color="red"> <%=ServletUtility.getErrorMessage("oldPassword", request)%></font>
                            </div>
                        </div> 
                   
                        <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">New Password<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="newPassword" placeholder="Your New Password " style="margin-bottom: 10px" type="password" 
                                value="<%=DataUtility.getString(request.getParameter("newPassword") == null ? ""
                            : DataUtility.getString(request.getParameter("newPassword")))%>" />
                            	<font color="red"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font>
                            </div>
                        </div> 
                        <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">Confirm Password<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="confirmPassword" placeholder="Your Confirm Password " style="margin-bottom: 10px" type="password" 
                                value="<%=DataUtility.getString(request
                    .getParameter("confirmPassword") == null ? "" : DataUtility
                    .getString(request.getParameter("confirmPassword")))%>" />
                            	<font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
                            </div>
                        </div> 

                        <div class="form-group"> 
                            <div class="aab controls col-md-4 "></div>
                            <div class="controls col-md-8 ">
                                <input type="submit" name="operation" value="<%=ChangePasswordCtl.OP_SAVE%>" class="btn btn-primary btn btn-info"  />
                                or <input type="submit" name="operation" value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>" class="btn btn btn-primary"  />
                            </div>
                        </div> 
                            
                    </form>
               
            </div>
        </div>
    </div> 
</div>
    




<div style="margin-top:154px ">       
<%@ include file="Footer.jsp" %>
</div>
</body>
</html>