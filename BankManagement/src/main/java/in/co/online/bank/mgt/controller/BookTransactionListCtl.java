package in.co.online.bank.mgt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.online.bank.mgt.bean.BaseBean;
import in.co.online.bank.mgt.bean.BookTransactionBean;
import in.co.online.bank.mgt.bean.UserBean;
import in.co.online.bank.mgt.exception.ApplicationException;
import in.co.online.bank.mgt.model.BookTransactionModel;
import in.co.online.bank.mgt.util.DataUtility;
import in.co.online.bank.mgt.util.PropertyReader;
import in.co.online.bank.mgt.util.ServletUtility;

/**
 * Servlet implementation class BookTransactionListCtl
 */
@WebServlet(name = "BookTransactionListCtl", urlPatterns = { "/ctl/BookTransactionListCtl" })
public class BookTransactionListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(BookTransactionListCtl.class);

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("BookTransactionListCtl populateBean method start");
		BookTransactionBean bean = new BookTransactionBean();
		bean.setAccountNo(DataUtility.getLong(request.getParameter("account")));
		bean.setAccHolderName(DataUtility.getString(request.getParameter("name")));
		log.debug("BookTransactionListCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BookTransactionListCtl doGet method start");
		List list = null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		long acc=DataUtility.getLong(request.getParameter("acc"));
		
		BookTransactionModel model = new BookTransactionModel();
		BookTransactionBean bean = (BookTransactionBean) populateBean(request);
		try {
			
			HttpSession session=request.getSession();
			UserBean uBean=(UserBean)session.getAttribute("user");
			
			session.setAttribute("acco", acc);
			if(uBean.getRoleId()==2) {
				bean.setAccountNo(uBean.getAccountNo());
			}else {
				if(acc>0) {
					bean.setAccountNo(acc);
				}
			}
			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}
			ServletUtility.setList(list, request);
			request.setAttribute("size",list.size());
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}
		log.debug("BookTransactionListCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BookTransactionListCtl doPost method start");
		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));

		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		BookTransactionBean bean = (BookTransactionBean) populateBean(request);

		BookTransactionModel model = new BookTransactionModel();
		String[] ids = request.getParameterValues("ids");
		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op)) {

			if (OP_SEARCH.equalsIgnoreCase(op)) {

				pageNo = 1;

			} else if (OP_NEXT.equalsIgnoreCase(op)) {

				pageNo++;
			} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {

				pageNo--;
			}
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OBMView.BOOK_TRANSACTION_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				BookTransactionBean deletebean = new BookTransactionBean();
				for (String id : ids) {
					deletebean.setId(DataUtility.getInt(id));
					try {
						model.delete(deletebean);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						e.printStackTrace();
						return;
					}
				}
				ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
			} else {
				ServletUtility.setErrorMessage("Select at least one record", request);
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OBMView.BOOK_TRANSACTION_LIST_CTL, request, response);
			return;

		}

		try {
			HttpSession session=request.getSession();
			UserBean uBean=(UserBean)session.getAttribute("user");
			if(uBean.getRoleId()==2) {
				bean.setAccountNo(uBean.getAccountNo());
			}else {
				long acco=(long)session.getAttribute("acco");
				if(acco>0) {
					bean.setAccountNo(acco);
				}
			}
			
			
			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("NO Record Found", request);
			}
			ServletUtility.setList(list, request);
			request.setAttribute("size",list.size());
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}

		log.debug("BookTransactionListCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OBMView.BOOK_TRANSACTION_LIST_VIEW;
	}

}
