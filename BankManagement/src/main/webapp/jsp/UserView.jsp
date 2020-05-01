<%@page import="in.co.online.bank.mgt.controller.UserCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.online.bank.mgt.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.bank.mgt.util.DataUtility"%>
<%@page import="in.co.online.bank.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<div class="container">    
            
    <div id="signupbox" style=" margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Add User</div>
                <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b><b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
            </div>  
            <div class="panel-body" >
                <form method="post" action="<%=OBMView.USER_CTL%>">
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
                   
                   <% List list=(List)request.getAttribute("bankList"); %>
                      <p>Bank Detail</p>
                       <hr>
                        <div id="div_id_username" class="form-group required">
                            <label  class="control-label col-md-4  requiredField">Bank Name<span class="asteriskField">*</span></label>
                            <div class="controls col-md-8 ">
							<%=HTMLUtility.getList("bankId",String.valueOf(bean.getBankId()),list)%>
                            <font color="red"> <%=ServletUtility.getErrorMessage("bankId", request)%></font>
                            </div>
                        </div>
                        
                        <% HashMap<String,String> map=new HashMap<String,String>();
                        		map.put("Saving","Saving");
                        		map.put("Current","Current");
                        		map.put("Fix Diposite","Fix Diposite");
                        %>
                        <div id="div_id_email" class="form-group required">
                            <label  class="control-label col-md-4  requiredField">Type Of Account<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 ">
                               <%=HTMLUtility.getList("typeOf",String.valueOf(bean.getTypeOfAccount()),map)%>
                            <font color="red"> <%=ServletUtility.getErrorMessage("typeOf", request)%></font>
                            </div>     
                        </div>
                        <div id="div_id_password1" class="form-group required">
                            <label  class="control-label col-md-4  requiredField">Title<span class="asteriskField">*</span></label>
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="title" placeholder="Enter Title" style="margin-bottom: 10px" type="text" 
                                value="<%=DataUtility.getStringData(bean.getTitle())%>" />
                           		<font color="red"> <%=ServletUtility.getErrorMessage("title", request)%></font>
                            </div>
                        </div>
                        <div id="div_id_password1" class="form-group required">
                            <label  class="control-label col-md-4  requiredField">Account Name<span class="asteriskField">*</span></label>
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name=accName placeholder="Account Name" style="margin-bottom: 10px" type="text" 
                                value="<%=DataUtility.getStringData(bean.getAccountName())%>" />
                           		<font color="red"> <%=ServletUtility.getErrorMessage("accName", request)%></font>
                            </div>
                        </div>
                        
                       <p>User Detail</p>
                       <hr>
                        <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">First Name<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="firstName" placeholder="Your Frist Name " style="margin-bottom: 10px" type="text" 
                                value="<%=DataUtility.getStringData(bean.getFirstName())%>" />
                            	<font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font>
                            </div>
                        </div>
                        <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">Last Name<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="lastName" placeholder="Your Last Name " style="margin-bottom: 10px" type="text" 
                                value="<%=DataUtility.getStringData(bean.getLastName())%>" />
                            	<font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font>
                            </div>
                        </div>
                        <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">Login Id<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="login" placeholder="Your Login " style="margin-bottom: 10px" type="text" 
                                value="<%=DataUtility.getStringData(bean.getLogin())%>" />
                            	<font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
                            </div>
                        </div> 
                        <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">Password<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="password" placeholder="Your Password " style="margin-bottom: 10px" type="password" 
                                value="<%=DataUtility.getStringData(bean.getPassword())%>" />
                            	<font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
                            </div>
                        </div> 
                        <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">Confirm Password<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="confirmPassword" placeholder="Your Confirm Password " style="margin-bottom: 10px" type="password" 
                                value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>" />
                            	<font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
                            </div>
                        </div> 
                        <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">Email Id<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="emailId" placeholder="Your Email Id" style="margin-bottom: 10px" type="text" 
                                value="<%=DataUtility.getStringData(bean.getEmailId())%>" />
                            	<font color="red"> <%=ServletUtility.getErrorMessage("emailId", request)%></font>
                            </div>
                        </div>
                        <p>Personal Detail</p>
                       <hr>
                        <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">Date Of Birth<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="dob" placeholder="Your Date Of Birth " style="margin-bottom: 10px" type="text" 
                                value="<%=DataUtility.getDateString(bean.getDob())%>" />
                            	<font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font>
                            </div>
                        </div>
                        <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">Mobile No.<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="mobile" placeholder="Your mobile " style="margin-bottom: 10px" type="text" 
                                value="<%=DataUtility.getStringData(bean.getMobileNo())%>" />
                            	<font color="red"> <%=ServletUtility.getErrorMessage("mobile", request)%></font>
                            </div>
                        </div>
                         <% HashMap<String,String> map1=new HashMap<String,String>();
                        		map1.put("Male","Male");
                        		map1.put("Female","Female");
                        %>
                        <div id="div_id_email" class="form-group required">
                            <label  class="control-label col-md-4  requiredField">Gender<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 ">
                               <%=HTMLUtility.getList("gender",String.valueOf(bean.getGender()),map1)%>
                            <font color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font>
                            </div>     
                        </div>
                        <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">Fax<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="fax" placeholder="Your Fax " style="margin-bottom: 10px" type="text" 
                                value="<%=DataUtility.getStringData(bean.getFax())%>" />
                            	<font color="red"> <%=ServletUtility.getErrorMessage("fax", request)%></font>
                            </div>
                        </div>
                        <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">Nationality<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="nation" placeholder="Your Nationality " style="margin-bottom: 10px" type="text" 
                                value="<%=DataUtility.getStringData(bean.getNationality())%>" />
                            	<font color="red"> <%=ServletUtility.getErrorMessage("nation", request)%></font>
                            </div>
                        </div>
                        
                        
                        
                       
                        <div class="form-group"> 
                            <div class="aab controls col-md-4 "></div>
                            <div class="controls col-md-8 ">
                                <input type="submit" name="operation" value="<%=UserCtl.OP_SAVE%>" class="btn btn-primary btn btn-info"  />
                                or <input type="submit" name="operation" value="<%=UserCtl.OP_RESET %>" class="btn btn btn-primary"  />
                            </div>
                        </div> 
                            
                    </form>

                
            </div>
        </div>
    </div> 
</div>
    




       
<%@ include file="Footer.jsp" %>
</body>
</html>