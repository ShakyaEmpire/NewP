package in.co.online.bank.mgt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.online.bank.mgt.bean.AccountBalanceBean;
import in.co.online.bank.mgt.bean.BankBean;
import in.co.online.bank.mgt.bean.BaseBean;
import in.co.online.bank.mgt.bean.BookTransactionBean;
import in.co.online.bank.mgt.exception.ApplicationException;
import in.co.online.bank.mgt.exception.DuplicateRecordException;
import in.co.online.bank.mgt.exception.RecordNotFoundException;
import in.co.online.bank.mgt.model.AccountBalanceModel;
import in.co.online.bank.mgt.model.BankModel;
import in.co.online.bank.mgt.model.BookTransactionModel;
import in.co.online.bank.mgt.util.DataUtility;
import in.co.online.bank.mgt.util.DataValidator;
import in.co.online.bank.mgt.util.PropertyReader;
import in.co.online.bank.mgt.util.ServletUtility;

/**
 * Servlet implementation class BookTransactionCtl
 */
@WebServlet(name = "BookTransactionCtl", urlPatterns = { "/ctl/BookTransactionCtl" })
public class BookTransactionCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(BookTransactionCtl.class);

	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("BookTransactionCtl validate method start");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("accountNo"))) {
			request.setAttribute("accountNo", PropertyReader.getValue("error.require", "Account No."));
			pass = false;
		} else if (!DataValidator.isLong(request.getParameter("accountNo"))) {
			request.setAttribute("accountNo", "Account No Must Be Integer");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("amount"))) {
			request.setAttribute("amount", PropertyReader.getValue("error.require", "Amount"));
			pass = false;
		} else if (DataUtility.getLong(request.getParameter("amount")) < 0) {
			request.setAttribute("amount", "Amount can not be Nagative");
			pass = false;
		} else if (!DataValidator.isLong(request.getParameter("amount"))) {
			request.setAttribute("amount", "Amount Must Be Integer");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("routing"))) {
			request.setAttribute("routing", PropertyReader.getValue("error.require", "Routing No."));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("tDate"))) {
			request.setAttribute("tDate", PropertyReader.getValue("error.require", "Transaction Date"));
			pass = false;
		}

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("tType"))) {
			request.setAttribute("tType", PropertyReader.getValue("error.require", "Transaction Type"));
			pass = false;
		}

		log.debug("BookTransactionCtl validate method end");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("BookTransactionCtl populateBean method start");
		BookTransactionBean bean = new BookTransactionBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setAccountNo(DataUtility.getLong(request.getParameter("accountNo")));
		bean.setTransactionAmount(DataUtility.getLong(request.getParameter("amount")));
		bean.setTransactionType(DataUtility.getString(request.getParameter("tType")));
		bean.setTransactionDate(DataUtility.getDate(request.getParameter("tDate")));
		bean.setRoutingNo(DataUtility.getString(request.getParameter("routing")));
		bean.setTransactionDescription(DataUtility.getString(request.getParameter("description")));
		populateDTO(bean, request);
		log.debug("BookTransactionCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("BookTransactionCtl doGet method start");
		String op = DataUtility.getString(request.getParameter("operation"));

		BookTransactionModel model = new BookTransactionModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		ServletUtility.setOpration("Add", request);
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			BookTransactionBean bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setOpration("Edit", request);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("BookTransactionCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("BookTransactionCtl doPost method start");
		String op = DataUtility.getString(request.getParameter("operation"));
		BookTransactionModel model = new BookTransactionModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)) {

			BookTransactionBean bean = (BookTransactionBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
					ServletUtility.setBean(bean, request);

				} else {
					long pk = model.add(bean);
					
					if (pk > 0) {
						AccountBalanceModel aModel = new AccountBalanceModel();
						AccountBalanceBean aBean = aModel.findByAcoountNo(bean.getAccountNo());
						if (aBean != null) {
								if("Credit".equalsIgnoreCase(bean.getTransactionType())) {
									aBean.setAmount(aBean.getAmount()+bean.getTransactionAmount());
									aModel.update(aBean);
								}else if("Debit".equalsIgnoreCase(bean.getTransactionType())) {
									aBean.setAmount(aBean.getAmount()-bean.getTransactionAmount());
									aModel.update(aBean);
								}
						} else {
							AccountBalanceBean acBean = new AccountBalanceBean();
							acBean.setAccountNo(bean.getAccountNo());
							acBean.setAmount(bean.getTransactionAmount());
							aModel.add(acBean);
						}
					}
					ServletUtility.setSuccessMessage("Data is successfully Saved", request);
					ServletUtility.forward(getView(), request, response);
				}

			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.forward(OBMView.ERROR_VIEW, request, response);
				return;

			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(), request);
			} catch (RecordNotFoundException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(), request);
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			BookTransactionBean bean = (BookTransactionBean) (request);
			try {
				model.delete(bean);
				ServletUtility.redirect(OBMView.BOOK_TRANSACTION_LIST_CTL, request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OBMView.BOOK_TRANSACTION_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OBMView.BOOK_TRANSACTION_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("BookTransactionCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OBMView.BOOK_TRANSACTION_VIEW;
	}

}
