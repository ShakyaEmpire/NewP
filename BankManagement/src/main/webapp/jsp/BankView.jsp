<%@page import="in.co.online.bank.mgt.controller.BankCtl"%>
<%@page import="in.co.online.bank.mgt.util.DataUtility"%>
<%@page import="in.co.online.bank.mgt.controller.OBMView"%>
<%@page import="in.co.online.bank.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bank</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<div class="container">    
            
    <div id="signupbox" style=" margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Add Bank</div>
                <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b><b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
            </div>  
            <div class="panel-body" >
                <form method="post" action="<%=OBMView.BANK_CTL%>">
                 <jsp:useBean id="bean" class="in.co.online.bank.mgt.bean.BankBean"
			scope="request"></jsp:useBean>
			
			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
                   
                      
                       
                        <div id="div_id_password1" class="form-group required">
                            <label  class="control-label col-md-4  requiredField">Bank Name<span class="asteriskField">*</span></label>
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="name" placeholder="Enter Name" style="margin-bottom: 10px" type="text" 
                                value="<%=DataUtility.getStringData(bean.getName())%>" />
                           		<font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font>
                            </div>
                        </div>
                        <div  class="form-group required">
                            <label  class="control-label col-md-4  requiredField">IFSC Code<span class="asteriskField">*</span></label>
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="IFSC" placeholder="IFSC Code" style="margin-bottom: 10px" type="text" 
                                value="<%=DataUtility.getStringData(bean.getIFSECode())%>" />
                           		<font color="red"> <%=ServletUtility.getErrorMessage("IFSC", request)%></font>
                            </div>
                        </div>
                        
                       
                        <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">Short Code<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="short" placeholder="Your Short Code " style="margin-bottom: 10px" type="text" 
                                value="<%=DataUtility.getStringData(bean.getShortCode())%>" />
                            	<font color="red"> <%=ServletUtility.getErrorMessage("short", request)%></font>
                            </div>
                        </div>
                        <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">Description<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <textarea rows="4" cols="5" name="description" placeholder="Your Decription " class="input-md textinput textInput form-control" style="margin-bottom: 10px" ><%=DataUtility.getStringData(bean.getDescription())%></textarea>
                            	<font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font>
                            </div>
                        </div>
                        
                        
                        
                        
                       
                        <div class="form-group"> 
                            <div class="aab controls col-md-4 "></div>
                            <div class="controls col-md-8 ">
                                <input type="submit" name="operation" value="<%=BankCtl.OP_SAVE%>" class="btn btn-primary btn btn-info"  />
                                or <input type="submit" name="operation" value="<%=BankCtl.OP_RESET %>" class="btn btn btn-primary"  />
                            </div>
                        </div> 
                            
                    </form>

                
            </div>
        </div>
    </div> 
</div>
    




<div style="margin-top: 50px">       
<%@ include file="Footer.jsp" %>
</div>
</body>
</html>