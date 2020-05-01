package in.co.online.bank.mgt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.online.bank.mgt.bean.AccountBalanceBean;
import in.co.online.bank.mgt.bean.BankBean;
import in.co.online.bank.mgt.bean.UserBean;
import in.co.online.bank.mgt.exception.ApplicationException;
import in.co.online.bank.mgt.model.AccountBalanceModel;
import in.co.online.bank.mgt.model.BankModel;
import in.co.online.bank.mgt.model.BookTransactionModel;
import in.co.online.bank.mgt.util.DataUtility;
import in.co.online.bank.mgt.util.ServletUtility;

/**
 * Servlet implementation class AccountBalanceCtl
 */
@WebServlet(name = "AccountBalanceCtl", urlPatterns = { "/ctl/AccountBalanceCtl" })
public class AccountBalanceCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(AccountBalanceCtl.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("AccountBalanceCtl doGet method start"); 
			
		   AccountBalanceModel model = new AccountBalanceModel();
		
			HttpSession session=request.getSession();
			UserBean uBean=(UserBean) session.getAttribute("user");
		
				AccountBalanceBean bean;
				try {
					bean = model.findByAcoountNo(uBean.getAccountNo());
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setBean(bean, request);
				} catch (ApplicationException e) {
					ServletUtility.handleException(e, request, response);
					return;
				}
		

			ServletUtility.forward(getView(), request, response);
			log.debug("AccountBalanceCtl doGet method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OBMView.ACCOUNT_BALANCE_VIEW;
	}

}
