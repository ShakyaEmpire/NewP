<%@page import="in.co.online.bank.mgt.model.BookTransactionModel"%>
<%@page import="in.co.online.bank.mgt.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.bank.mgt.bean.BookTransactionBean"%>
<%@page import="java.awt.print.Book"%>
<%@page import="in.co.online.bank.mgt.controller.BookTransactionListCtl"%>
<%@page import="in.co.online.bank.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Transaction List</title>
</head>
<body>
<%@ include file="Header.jsp"%>
	<form action="<%=OBMView.BOOK_TRANSACTION_LIST_CTL%>" method="post">
		<div class="container">
			<div class="row">
				<center>
					<h3>Book Transaction Report</h3>
				</center>

				<hr>
				<div class="col-md-12">

					<div class="table-responsive">
						<% UserBean ubBean=(UserBean)session.getAttribute("user"); 
							Long acc=(Long)session.getAttribute("acco");
						%>
							<% if(ubBean.getRoleId()==1 && acc<=0){%>
						<table class="table table-bordred table-striped">
							<tr>
								<th>Name</th>
								<td><input type="text" name="name"
									placeholder="Search By Name"
									value="<%=ServletUtility.getParameter("name", request)%>"
									class="input-md  textinput textInput form-control"></td>
								<th></th>
								<th>Account No</th>
								<td><input type="text" name="account"
									placeholder="Search By Account No"
									value="<%=ServletUtility.getParameter("account", request)%>"
									class="input-md  textinput textInput form-control"></td>
								<td><input type="submit"
									class="btn btn-primary btn btn-info" name="operation"
									value="<%=BookTransactionListCtl.OP_SEARCH%>"></td>
							</tr>
						</table>
						<%} %>
						<center>
							<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
							<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
						</center>
						<table id="mytable" class="table table-bordred table-striped">


							<thead>
								<tr>
									
									<th>S No.</th>
									<th>Account No.</th>
									<th>Name</th>
									<th>Bank</th>
									<th>Transaction Type</th>
									<th>Amount</th>
									<th>Date</th>
									<th>Description</th>
									
									
								</tr>
							</thead>
							<tbody>
								<%
									int pageNo = ServletUtility.getPageNo(request);
									int pageSize = ServletUtility.getPageSize(request);
									int size =(int)request.getAttribute("size");
									int index = ((pageNo - 1) * pageSize) + 1;
									BookTransactionBean bean = null;
									List list = ServletUtility.getList(request);
									Iterator<BookTransactionBean> it = list.iterator();

									while (it.hasNext()) {

										bean = it.next();
								%>
								<tr>
									
									<td><%=index++%></td>
									<td><%=bean.getAccountNo()%></td>
									<td><%=bean.getAccHolderName()%></td>
									<td><%=bean.getBankName()%></td>
									<td><%=bean.getTransactionType()%></td>
									<td><%=bean.getTransactionAmount()%></td>
									<td><%=DataUtility.getDateString(bean.getTransactionDate())%></td>
									<td><%=bean.getTransactionDescription()%></td>
								</tr>
								<%
									}
								%>
							</tbody>

						</table>

						<div class="clearfix"></div>
						<ul class="pagination pull-right">
							<li><input type="submit" name="operation"
								class="btn btn-primary btn btn-info"
								value="<%=BookTransactionListCtl.OP_PREVIOUS%>"
								<%=(pageNo == 1) ? "disabled" : ""%>></li>
								
								
								
								
								
							
						
							<%
								BookTransactionModel model = new BookTransactionModel();
							%>
							<li><input type="submit" name="operation"
								class="btn btn-primary btn btn-info"
								value="<%=BookTransactionListCtl.OP_NEXT%>"
								<%=((list.size() < pageSize) || size==pageNo*pageSize) ? "disabled" : ""%>></li>
						</ul>

					</div>

				</div>
			</div>
		</div>
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
<div style="margin-top: 225px">
	<%@ include file="Footer.jsp"%>
</div>
</body>
</html>