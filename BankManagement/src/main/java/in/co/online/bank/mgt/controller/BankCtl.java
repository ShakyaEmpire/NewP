package in.co.online.bank.mgt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.online.bank.mgt.bean.BankBean;
import in.co.online.bank.mgt.bean.BaseBean;
import in.co.online.bank.mgt.exception.ApplicationException;
import in.co.online.bank.mgt.exception.DuplicateRecordException;
import in.co.online.bank.mgt.model.BankModel;
import in.co.online.bank.mgt.util.DataUtility;
import in.co.online.bank.mgt.util.DataValidator;
import in.co.online.bank.mgt.util.PropertyReader;
import in.co.online.bank.mgt.util.ServletUtility;

/**
 * Servlet implementation class BankCtl
 */
@WebServlet(name = "BankCtl", urlPatterns = { "/ctl/BankCtl" })
public class BankCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(BankCtl.class);
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("BankCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("name"))) {
            request.setAttribute("name",
                    PropertyReader.getValue("error.require", "Name"));
            pass = false;
        }else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.name", "Name"));
			pass = false;
		}

        if (DataValidator.isNull(request.getParameter("description"))) {
            request.setAttribute("description",
                    PropertyReader.getValue("error.require", "Description"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("IFSC"))) {
            request.setAttribute("IFSC",
                    PropertyReader.getValue("error.require", "IFSC Code"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("short"))) {
            request.setAttribute("short",
                    PropertyReader.getValue("error.require", "Short Code"));
            pass = false;
        }

        log.debug("BankCtl validate method end");
        return pass;
    }

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("BankCtl populateBean method start");
		BankBean bean=new BankBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setIFSECode(DataUtility.getString(request.getParameter("IFSC")));
		bean.setShortCode(DataUtility.getString(request.getParameter("short")));
		populateDTO(bean, request);
		log.debug("BankCtl populateBean method end");
		return bean;
	}
	/**
	 * Contains display logic
	 */
	@Override
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			log.debug("BankCtl doGet method start"); 
			String op = DataUtility.getString(request.getParameter("operation"));
				
			   BankModel model = new BankModel();
				long id = DataUtility.getLong(request.getParameter("id"));
				ServletUtility.setOpration("Add", request);
				if (id > 0 || op != null) {
					System.out.println("in id > 0  condition");
					BankBean bean;
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
				log.debug("BankCtl doGet method end");
    }

	/**
	 * Contains submit logic
	 */
	@Override
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 log.debug("BankCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		BankModel model=new BankModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			BankBean bean=(BankBean)populateBean(request);
				try {
					if(id>0){
						
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
	                ServletUtility.setBean(bean, request);

					}else {
						long pk=model.add(bean);
						//bean.setId(id);
						ServletUtility.setSuccessMessage("Data is successfully Saved", request);
						ServletUtility.forward(getView(), request, response);
					}
	              
				} catch (ApplicationException e) {
					e.printStackTrace();
					ServletUtility.forward(OBMView.ERROR_VIEW, request, response);
					return;
				
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Roll no already exists",
						request);
			}
			
		}else if (OP_DELETE.equalsIgnoreCase(op)) {
		BankBean bean=	(BankBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(OBMView.BANK_LIST_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OBMView.BANK_LIST_CTL, request, response);
			return;
	}else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(OBMView.BANK_CTL, request, response);
		return;
}
				
		
		ServletUtility.forward(getView(), request, response);
		 log.debug("BankCtl doPost method end");
	}
	
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OBMView.BANK_VIEW;
	}


}
