<%@page import="in.co.online.bank.mgt.controller.FundTransferCtl"%>
<%@page import="in.co.online.bank.mgt.bean.FundTransferBean"%>
<%@page import="in.co.online.bank.mgt.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.online.bank.mgt.util.DataUtility"%>
<%@page import="in.co.online.bank.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fund Transfer</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<div class="container">    
            
    <div id="signupbox" style=" margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Fund Transfer</div>
                <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b><b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
            </div>  
            <div class="panel-body" >
                <form method="post" action="<%=OBMView.FUND_TRANSFER_CTL%>">
                 <jsp:useBean id="bean" class="in.co.online.bank.mgt.bean.FundTransferBean"
			scope="request"></jsp:useBean>
			
			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
                   
                   <%	UserBean uBean=(UserBean)session.getAttribute("user");
                   		if(uBean.getRoleId()==1){	
                   %>
                   
                       <% HashMap<String,String> map1=new HashMap<String,String>();
                        		map1.put("Processing","Processing");
                        		map1.put("Completed","Completed");
                        		map1.put("Cancel","Cancel");
                        		
                        %>
                        <div id="div_id_email" class="form-group required">
                            <label  class="control-label col-md-4  requiredField">Transaction Type<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 ">
                               <%=HTMLUtility.getList("status",String.valueOf(bean.getStatus()),map1)%>
                            <font color="red"> <%=ServletUtility.getErrorMessage("status", request)%></font>
                            </div>     
                        </div>
                        
                        <%} %>
                       
                        <div id="div_id_password1" class="form-group required">
                            <label  class="control-label col-md-4  requiredField">Beneficial Account No.<span class="asteriskField">*</span></label>
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="accountNo" placeholder="Enter Beneficial Account No" style="margin-bottom: 10px" type="text" 
                                value="<%=(bean.getBenAccountNo()==0)?"":bean.getBenAccountNo()%>" />
                           		<font color="red"> <%=ServletUtility.getErrorMessage("accountNo", request)%></font>
                            </div>
                        </div>
                        
                        
                        <div  class="form-group required">
                            <label  class="control-label col-md-4  requiredField">Transaction Amount<span class="asteriskField">*</span></label>
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="amount" placeholder="Transaction Amount" style="margin-bottom: 10px" type="text" 
                                value="<%=(bean.getTraAmount()==0)?"":bean.getTraAmount()%>" />
                           		<font color="red"> <%=ServletUtility.getErrorMessage("amount", request)%></font>
                            </div>
                        </div>
                        
                       
                        <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">Routing<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="routing" placeholder="Your Routing " style="margin-bottom: 10px" type="text" 
                                value="<%=DataUtility.getStringData(bean.getRouting())%>" />
                            	<font color="red"> <%=ServletUtility.getErrorMessage("routing", request)%></font>
                            </div>
                        </div>
                        
                        
                        
                        
                        
                        
                       
                        <div class="form-group"> 
                            <div class="aab controls col-md-4 "></div>
                            <div class="controls col-md-8 ">
                                <input type="submit" name="operation" value="<%=FundTransferCtl.OP_SAVE%>" class="btn btn-primary btn btn-info"  />
                                or <input type="submit" name="operation" value="<%=FundTransferCtl.OP_RESET %>" class="btn btn btn-primary"  />
                            </div>
                        </div> 
                            
                    </form>

                
            </div>
        </div>
    </div> 
</div>
    




<div style="margin-top: 154px">       
<%@ include file="Footer.jsp" %>
</div>
</body>
</html>