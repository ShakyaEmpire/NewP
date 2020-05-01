<%@page import="in.co.online.bank.mgt.controller.BookTransactionCtl"%>
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
<title>Book Transaction</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<div class="container">    
            
    <div id="signupbox" style=" margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Add Book Transaction</div>
                <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b><b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
            </div>  
            <div class="panel-body" >
                <form method="post" action="<%=OBMView.BOOK_TRANSACTION_CTL%>">
                 <jsp:useBean id="bean" class="in.co.online.bank.mgt.bean.BookTransactionBean"
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
                            <label  class="control-label col-md-4  requiredField">Account No.<span class="asteriskField">*</span></label>
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="accountNo" placeholder="Enter Account No" style="margin-bottom: 10px" type="text" 
                                value="<%=(bean.getAccountNo()==0)?"":bean.getAccountNo()%>" />
                           		<font color="red"> <%=ServletUtility.getErrorMessage("accountNo", request)%></font>
                            </div>
                        </div>
                        
                         <% HashMap<String,String> map1=new HashMap<String,String>();
                        		map1.put("Credit","Credit");
                        		map1.put("Debit","Debit");
                        %>
                        <div id="div_id_email" class="form-group required">
                            <label  class="control-label col-md-4  requiredField">Transaction Type<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 ">
                               <%=HTMLUtility.getList("tType",String.valueOf(bean.getTransactionType()),map1)%>
                            <font color="red"> <%=ServletUtility.getErrorMessage("tType", request)%></font>
                            </div>     
                        </div>
                        <div  class="form-group required">
                            <label  class="control-label col-md-4  requiredField">Transaction Amount<span class="asteriskField">*</span></label>
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="amount" placeholder="Transaction Amount" style="margin-bottom: 10px" type="text" 
                                value="<%=(bean.getTransactionAmount()==0)?"":bean.getTransactionAmount()%>" />
                           		<font color="red"> <%=ServletUtility.getErrorMessage("amount", request)%></font>
                            </div>
                        </div>
                        
                       
                        <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">Routing<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control"  name="routing" placeholder="Your Routing " style="margin-bottom: 10px" type="text" 
                                value="<%=DataUtility.getStringData(bean.getRoutingNo())%>" />
                            	<font color="red"> <%=ServletUtility.getErrorMessage("routing", request)%></font>
                            </div>
                        </div>
                        
                         <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">Transaction Date<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <input class="input-md textinput textInput form-control" id="datepicker"  readonly="readonly" name="tDate" placeholder="Transaction Date " style="margin-bottom: 10px" type="text" 
                                value="<%=DataUtility.getDateString(bean.getTransactionDate())%>" />
                            	<font color="red"> <%=ServletUtility.getErrorMessage("tDate", request)%></font>
                            </div>
                        </div>
                        <div  class="form-group required"> 
                            <label  class="control-label col-md-4  requiredField">Transaction Description<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <textarea rows="4" cols="5" name="description" placeholder="Your Transaction Decription " class="input-md textinput textInput form-control" style="margin-bottom: 10px" ><%=DataUtility.getStringData(bean.getTransactionDescription())%></textarea>
                            	<font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font>
                            </div>
                        </div>
                        
                        
                        
                        
                       
                        <div class="form-group"> 
                            <div class="aab controls col-md-4 "></div>
                            <div class="controls col-md-8 ">
                                <input type="submit" name="operation" value="<%=BookTransactionCtl.OP_SAVE%>" class="btn btn-primary btn btn-info"  />
                                or <input type="submit" name="operation" value="<%=BookTransactionCtl.OP_RESET %>" class="btn btn btn-primary"  />
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