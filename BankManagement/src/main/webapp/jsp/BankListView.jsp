<%@page import="in.co.online.bank.mgt.model.BankModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.bank.mgt.bean.BankBean"%>
<%@page import="in.co.online.bank.mgt.controller.BankListCtl"%>
<%@page import="in.co.online.bank.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bank Report</title>
</head>
<body>
<%@ include file="Header.jsp"%>
	<form action="<%=OBMView.BANK_LIST_CTL%>" method="post">
		<div class="container">
			<div class="row">
				<center>
					<h3>Bank Report</h3>
				</center>

				<hr>
				<div class="col-md-12">

					<div class="table-responsive">

						<table class="table table-bordred table-striped">
							<tr>
								<th>Name</th>
								<td><input type="text" name="name"
									placeholder="Search By Name"
									value="<%=ServletUtility.getParameter("name", request)%>"
									class="input-md  textinput textInput form-control"></td>
								<th></th>
								<th>Short Code</th>
								<td><input type="text" name="sCode"
									placeholder="Search By Short Code"
									value="<%=ServletUtility.getParameter("sCode", request)%>"
									class="input-md  textinput textInput form-control"></td>
								<td><input type="submit"
									class="btn btn-primary btn btn-info" name="operation"
									value="<%=BankListCtl.OP_SEARCH%>"></td>
							</tr>
						</table>
						<center>
							<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
							<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
						</center>
						<table id="mytable" class="table table-bordred table-striped">


							<thead>
								<tr>
									<th>Select</th>
									<th>S No.</th>
									<th>Bank Name</th>
									<th>Short Code</th>
									<th>IFSC Code</th>
									<th>Description</th>
									<th>Action</th>
									
									
								</tr>
							</thead>
							<tbody>
								<%
									int pageNo = ServletUtility.getPageNo(request);
									int pageSize = ServletUtility.getPageSize(request);
									int index = ((pageNo - 1) * pageSize) + 1;
									BankBean bean = null;
									List list = ServletUtility.getList(request);
									Iterator<BankBean> it = list.iterator();

									while (it.hasNext()) {

										bean = it.next();
								%>
								<tr>
									<td><input type="checkbox" class="case"
										name="ids" value="<%=bean.getId()%>"></td>
									<td><%=index++%></td>
									<td><%=bean.getName()%></td>
									<td><%=bean.getShortCode()%></td>
									<td><%=bean.getIFSECode()%></td>
									<td><%=bean.getDescription()%></td>
									<td><a class="btn btn-primary" href="BankCtl?id=<%=bean.getId()%>">Edit</a></td>
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
								value="<%=BankListCtl.OP_PREVIOUS%>"
								<%=(pageNo == 1) ? "disabled" : ""%>></li>
								
								<li><input type="submit" name="operation"
								class="btn btn-primary btn btn-info"
								value="<%=BankListCtl.OP_DELETE%>"
								<%=(list.size()==0) ? "disabled" : ""%>></li>
								
								
								<li><input type="submit" name="operation"
								class="btn btn-primary btn btn-info"
								value="<%=BankListCtl.OP_NEW%>"
								></li>
							
						
							<%
								BankModel model = new BankModel();
							%>
							<li><input type="submit" name="operation"
								class="btn btn-primary btn btn-info"
								value="<%=BankListCtl.OP_NEXT%>"
								<%=((list.size() < pageSize) || model.nextPK() - 1 == bean.getId()) ? "disabled" : ""%>></li>
						</ul>

					</div>

				</div>
			</div>
		</div>
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
<div style="margin-top: 168px">
	<%@ include file="Footer.jsp"%>
</div>
</body>
</html>