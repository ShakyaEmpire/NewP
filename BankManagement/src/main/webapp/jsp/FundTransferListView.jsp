<%@page import="in.co.online.bank.mgt.model.FundTransferModel"%>
<%@page import="in.co.online.bank.mgt.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.bank.mgt.bean.FundTransferBean"%>
<%@page import="in.co.online.bank.mgt.controller.FundTransferListCtl"%>
<%@page import="in.co.online.bank.mgt.controller.FundTransferCtl"%>
<%@page import="in.co.online.bank.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fund Transfer Report</title>
</head>
<body>
<%@ include file="Header.jsp"%>
	<form action="<%=OBMView.FUND_TRANSFER_LIST_CTL%>" method="post">
		<div class="container">
			<div class="row">
				<center>
					<h3>Fund Transfer Report</h3>
				</center>

				<hr>
				<div class="col-md-12">

					<div class="table-responsive">

						<table class="table table-bordred table-striped">
							<tr>
							<% UserBean ubBean=(UserBean)session.getAttribute("user"); %>
							<% if(ubBean.getRoleId()==1){%>
								<th>By Account No.</th>
								<td><input type="text" name="byAccount"
									placeholder="Search By By Account No."
									value="<%=ServletUtility.getParameter("byAccount", request)%>"
									class="input-md  textinput textInput form-control"></td>
								<th></th>
								<%} %>
								<th>Beneficial Account No </th>
								<td><input type="text" name="benAccount"
									placeholder="Search By Beneficial Account No."
									value="<%=ServletUtility.getParameter("benAccount", request)%>"
									class="input-md  textinput textInput form-control"></td>
								<td><input type="submit"
									class="btn btn-primary btn btn-info" name="operation"
									value="<%=FundTransferListCtl.OP_SEARCH%>"></td>
							</tr>
						</table>
						<center>
							<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
							<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
						</center>
						<table id="mytable" class="table table-bordred table-striped">


							<thead>
								<tr>
									<th>S No.</th>
									<th>Beneficial Account No</th>
									<th>Name</th>
									<th>Bank Name</th>
									<th>By Account No</th>
									<th>By Name</th>
									<th>Date</th>
									<th>Amount</th>
									<th>Status</th>
									<%
										UserBean uBean=(UserBean)session.getAttribute("user");	
									%>
									<%if(uBean.getRoleId()==1){ %>
									<th>Action</th>
									<%} %>
									
								</tr>
							</thead>
							<tbody>
								<%
									int pageNo = ServletUtility.getPageNo(request);
									int pageSize = ServletUtility.getPageSize(request);
									int size =(int)request.getAttribute("size");
									int index = ((pageNo - 1) * pageSize) + 1;
									FundTransferBean bean = null;
									List list = ServletUtility.getList(request);
									Iterator<FundTransferBean> it = list.iterator();

									while (it.hasNext()) {

										bean = it.next();
								%>
								<tr>
									<td><%=index++%></td>
									<td><%=bean.getBenAccountNo()%></td>
									<td><%=bean.getBenAccHolderName()%></td>
									<td><%=bean.getBankName()%></td>
									<td><%=bean.getByAccountNo()%></td>
									<td><%=bean.getByAccHolderName()%></td>
									<td><%=DataUtility.getDateString(bean.getTransferDate())%></td>
									<td><%=bean.getTraAmount()%></td>
									<% if("Processing".equalsIgnoreCase(bean.getStatus())){%>
									<td><p class="btn btn-info"><%=bean.getStatus()%></p></td>
									<%}else if("Cancel".equalsIgnoreCase(bean.getStatus())){ %>
									<td><p class="btn btn-danger"><%=bean.getStatus()%></p></td>
									<%}else if("Completed".equalsIgnoreCase(bean.getStatus())){ %>
									<td><p class="btn btn-success"><%=bean.getStatus()%></p></td>
									<%} %>
									<%if(uBean.getRoleId()==1){
									if("Processing".equalsIgnoreCase(bean.getStatus())||"Cancel".equalsIgnoreCase(bean.getStatus())){		
									%>
									<td><a class="btn btn-primary" href="FundTransferCtl?id=<%=bean.getId()%>">Update Status</a></td>
									<%}else{ %>
									<td>No Update Status</td>
									<%} %>
									<%} %>
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
								value="<%=FundTransferCtl.OP_PREVIOUS%>"
								<%=(pageNo == 1) ? "disabled" : ""%>></li>
							
						
							<%
								FundTransferModel model = new FundTransferModel();
							%>
							<li><input type="submit" name="operation"
								class="btn btn-primary btn btn-info"
								value="<%=FundTransferCtl.OP_NEXT%>"
								<%=((list.size() < pageSize) || size==pageNo*pageSize) ? "disabled" : ""%>></li>
						</ul>

					</div>

				</div>
			</div>
		</div>
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
<div style="margin-top: 170px">
	<%@ include file="Footer.jsp"%>
</div>
</body>
</html>