<%@page import="in.co.online.bank.mgt.model.UserModel"%>
<%@page import="in.co.online.bank.mgt.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.bank.mgt.controller.UserListCtl"%>
<%@page import="in.co.online.bank.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User List View</title>
</head>
<body>
<%@ include file="Header.jsp"%>
	<form action="<%=OBMView.USER_LIST_CTL%>" method="post">
		<div class="container">
			<div class="row">
				<center>
					<h3>User Report</h3>
				</center>

				<hr>
				<div class="col-md-12">

					<div class="table-responsive">

						<table class="table table-bordred table-striped">
							<tr>
								<th>First Name</th>
								<td><input type="text" name="name"
									placeholder="Search By First Name"
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
									value="<%=UserListCtl.OP_SEARCH%>"></td>
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
									<th>User Name</th>
									<th>Account No</th>
									<th>Bank Name</th>
									<th>Email Id</th>
									<th>Mobile No</th>
									<th>Date Of Birth</th>
									<th>Action</th>
									
									
								</tr>
							</thead>
							<tbody>
								<%
									int pageNo = ServletUtility.getPageNo(request);
									int pageSize = ServletUtility.getPageSize(request);
									int index = ((pageNo - 1) * pageSize) + 1;
									UserBean bean = null;
									List list = ServletUtility.getList(request);
									Iterator<UserBean> it = list.iterator();

									while (it.hasNext()) {

										bean = it.next();
								%>
								<tr>
									<td><input type="checkbox" class="case"
										name="ids" value="<%=bean.getId()%>"></td>
									<td><%=index++%></td>
									<td><%=bean.getFirstName()+" "+bean.getLastName()%></td>
									<td><%=bean.getAccountNo()%></td>
									<td><%=bean.getBankName()%></td>
									<td><%=bean.getEmailId()%></td>
									<td><%=bean.getMobileNo()%></td>
									<td><%=DataUtility.getDateString(bean.getDob())%></td>
									<td><a class="btn btn-primary" href="UserCtl?id=<%=bean.getId()%>">Edit</a>
									<a class="btn btn-primary" href="BookTransactionListCtl?acc=<%=bean.getAccountNo()%>">View Transaction</a>
									</td>
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
								value="<%=UserListCtl.OP_PREVIOUS%>"
								<%=(pageNo == 1) ? "disabled" : ""%>></li>
								
								<li><input type="submit" name="operation"
								class="btn btn-primary btn btn-info"
								value="<%=UserListCtl.OP_DELETE%>"
								<%=(list.size()==0) ? "disabled" : ""%>></li>
								
								
								<li><input type="submit" name="operation"
								class="btn btn-primary btn btn-info"
								value="<%=UserListCtl.OP_NEW%>"
								></li>
							
						
							<%
								UserModel model = new UserModel();
							%>
							<li><input type="submit" name="operation"
								class="btn btn-primary btn btn-info"
								value="<%=UserListCtl.OP_NEXT%>"
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