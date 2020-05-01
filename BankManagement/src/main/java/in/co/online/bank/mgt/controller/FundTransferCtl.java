package in.co.online.bank.mgt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sun.org.apache.regexp.internal.recompile;

import in.co.online.bank.mgt.bean.AccountBalanceBean;
import in.co.online.bank.mgt.bean.BankBean;
import in.co.online.bank.mgt.bean.BaseBean;
import in.co.online.bank.mgt.bean.BookTransactionBean;
import in.co.online.bank.mgt.bean.FundTransferBean;
import in.co.online.bank.mgt.bean.UserBean;
import in.co.online.bank.mgt.exception.ApplicationException;
import in.co.online.bank.mgt.exception.DuplicateRecordException;
import in.co.online.bank.mgt.exception.RecordNotFoundException;
import in.co.online.bank.mgt.model.AccountBalanceModel;
import in.co.online.bank.mgt.model.BankModel;
import in.co.online.bank.mgt.model.BookTransactionModel;
import in.co.online.bank.mgt.model.FundTransferModel;
import in.co.online.bank.mgt.model.UserModel;
import in.co.online.bank.mgt.util.DataUtility;
import in.co.online.bank.mgt.util.DataValidator;
import in.co.online.bank.mgt.util.PropertyReader;
import in.co.online.bank.mgt.util.ServletUtility;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Servlet implementation class FundTransferCtl
 */
@WebServlet(name = "FundTransferCtl", urlPatterns = { "/ctl/FundTransferCtl" })
public class FundTransferCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(FundTransferCtl.class);
	
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("FundTransferCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("accountNo"))) {
			request.setAttribute("accountNo", PropertyReader.getValue("error.require", "Account No."));
			pass = false;
		} else if (!DataValidator.isLong(request.getParameter("accountNo"))) {
			request.setAttribute("accountNo", "Account No Must Be Integer");
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

		HttpSession session=request.getSession();
		UserBean uBean=(UserBean)session.getAttribute("user");
		if(uBean.getRoleId()==1) {

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "Tranfer Status"));
			pass = false;
		}
		
		}
		

        log.debug("FundTransferCtl validate method end");
        return pass;
    }

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("FundTransferCtl populateBean method start");
		FundTransferBean bean = new FundTransferBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setBenAccountNo(DataUtility.getLong(request.getParameter("accountNo")));
		bean.setTraAmount(DataUtility.getLong(request.getParameter("amount")));
		HttpSession session=request.getSession();
		UserBean uBean=(UserBean)session.getAttribute("user");
		if(uBean.getRoleId()==1) {
		bean.setStatus(DataUtility.getString(request.getParameter("status")));
		}else {
			bean.setStatus("Processing");
		}
		bean.setRouting(DataUtility.getString(request.getParameter("routing")));
		populateDTO(bean, request);
		log.debug("FundTransferCtl populateBean method end");
		return bean;
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FundTransferCtl doGet method start");
		String op = DataUtility.getString(request.getParameter("operation"));

		FundTransferModel model = new FundTransferModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		ServletUtility.setOpration("Add", request);
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			FundTransferBean bean;
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
		log.debug("FundTransferCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 log.debug("FundTransferCtl doPost method start");
			String op=DataUtility.getString(request.getParameter("operation"));
			FundTransferModel model=new FundTransferModel();
			long id=DataUtility.getLong(request.getParameter("id"));
			if(OP_SAVE.equalsIgnoreCase(op)){
				
				FundTransferBean bean=(FundTransferBean)populateBean(request);
					try {
			
						if(id>0){
						if("Cancel".equalsIgnoreCase(bean.getStatus()) || "Processing".equalsIgnoreCase(bean.getStatus())) {
						FundTransferBean fBean=model.findByPK(bean.getId());
						fBean.setStatus(bean.getStatus());
						model.update(fBean);
						}else {
							AccountBalanceBean aBean=null;
							AccountBalanceModel aModel=new AccountBalanceModel();
							 aBean=aModel.findByAcoountNo(bean.getBenAccountNo());
							if(aBean==null){
								AccountBalanceBean abBean=new AccountBalanceBean();
								abBean.setAccountNo(bean.getBenAccountNo());
								aModel.add(abBean);
							}
							FundTransferBean fBean=model.findByPK(bean.getId());
							BookTransactionModel bModel=new BookTransactionModel();
							BookTransactionBean bBean=new BookTransactionBean();
							bBean.setAccountNo(fBean.getByAccountNo());
							bBean.setTransactionType("Debit");
							bBean.setRoutingNo(fBean.getRouting());
							bBean.setTransactionDate(new java.util.Date());
							bBean.setTransactionAmount(fBean.getTraAmount());
							bBean.setTransactionDescription("Fund Transfer");
							bModel.add(bBean);
							AccountBalanceBean AccBean=aModel.findByAcoountNo(fBean.getByAccountNo());
							AccBean.setAmount(AccBean.getAmount()-fBean.getTraAmount());
							aModel.update(AccBean);
							//-----------Benificial
							BookTransactionBean AcbBean=new BookTransactionBean();
							AcbBean.setAccountNo(fBean.getBenAccountNo());
							AcbBean.setTransactionType("Credit");
							AcbBean.setRoutingNo(fBean.getRouting());
							AcbBean.setTransactionDate(new java.util.Date());
							AcbBean.setTransactionAmount(fBean.getTraAmount());
							AcbBean.setTransactionDescription("Fund Transfer");
							bModel.add(AcbBean);
							AccountBalanceBean AccbBean=aModel.findByAcoountNo(fBean.getBenAccountNo());
							AccbBean.setAmount(AccbBean.getAmount()+fBean.getTraAmount());
							aModel.update(AccbBean);
							
							fBean.setStatus(bean.getStatus());
							model.update(fBean);
						}
						ServletUtility.setOpration("Edit", request);
						ServletUtility.setSuccessMessage("Data is successfully Updated", request);
		                ServletUtility.setBean(bean, request);

						}else {
						
							HttpSession session=request.getSession();
							UserBean uBean=(UserBean)session.getAttribute("user");
							AccountBalanceModel acModel=new AccountBalanceModel();
							AccountBalanceBean abBean=acModel.findByAcoountNo(uBean.getAccountNo());
							if(abBean!=null) {
							if(abBean.getAmount()>=bean.getTraAmount()&&abBean.getAmount()>0) {
								UserModel umModel=new UserModel();
								UserBean ubBean=umModel.findByAccountNo(bean.getBenAccountNo());
							if(ubBean!=null) {
							bean.setByAccountNo(uBean.getAccountNo());
							long pk=model.add(bean);
							//bean.setId(id);
							ServletUtility.setSuccessMessage("Data is successfully Saved", request);
							}else {
								ServletUtility.setErrorMessage("Beneficial Account No Is not Register please inter Correct Account No", request);
							}
							
							}else {
								ServletUtility.setErrorMessage("Insufficient Account Balance", request);
							}
							}else {
								ServletUtility.setErrorMessage("Insufficient Account Balance", request);
							}
							ServletUtility.forward(getView(), request, response);
						}
		              
					} catch (ApplicationException e) {
						e.printStackTrace();
						ServletUtility.forward(OBMView.ERROR_VIEW, request, response);
						return;
					
				} catch (DuplicateRecordException e) {
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage(e.getMessage(),
							request);
				} catch (RecordNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if (OP_DELETE.equalsIgnoreCase(op)) {
				FundTransferBean bean=	(FundTransferBean)populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(OBMView.FUND_TRANSFER_LIST_CTL, request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
			}
			}else if (OP_CANCEL.equalsIgnoreCase(op)) {
				ServletUtility.redirect(OBMView.FUND_TRANSFER_LIST_CTL, request, response);
				return;
		}else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OBMView.FUND_TRANSFER_CTL, request, response);
			return;
	}
					
			
			ServletUtility.forward(getView(), request, response);
			 log.debug("FundTransferCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OBMView.FUND_TRANSFER_VIEW;
	}

}
