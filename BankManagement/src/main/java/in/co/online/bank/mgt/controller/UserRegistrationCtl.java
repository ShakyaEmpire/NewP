package in.co.online.bank.mgt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.online.bank.mgt.bean.BaseBean;
import in.co.online.bank.mgt.bean.RoleBean;
import in.co.online.bank.mgt.bean.UserBean;
import in.co.online.bank.mgt.exception.ApplicationException;
import in.co.online.bank.mgt.exception.DuplicateRecordException;
import in.co.online.bank.mgt.model.BankModel;
import in.co.online.bank.mgt.model.UserModel;
import in.co.online.bank.mgt.util.DataUtility;
import in.co.online.bank.mgt.util.DataValidator;
import in.co.online.bank.mgt.util.PropertyReader;
import in.co.online.bank.mgt.util.ServletUtility;


/**
 * Servlet implementation class UserRegistrationCtl
 */

/**
 * UserRegistration functionality Controller. Performs operation for Validate and add a User 
 * As Student Role
 * 
 * @author NAvigable set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */

@WebServlet(name = "UserRegistrationCtl", urlPatterns = { "/UserRegistrationCtl" })
public class UserRegistrationCtl extends BaseCtl {
	public static final String OP_SIGN_UP = "SignUp";

	private static Logger log = Logger.getLogger(UserRegistrationCtl.class);
	
	
	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("UserCtl preload method start");
		BankModel model = new BankModel();
		try {
			List l = model.list();
			request.setAttribute("bankList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
		log.debug("UserCtl preload method end");
	}
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("UserRegistrationCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("login");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.name", "First Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.name", "Last Name"));
			pass = false;
		}

		if (DataValidator.isNull(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue(
					"error.require", "Confirm Password"));
			pass = false;
		}
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("gender"))) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("bankId"))) {
			request.setAttribute("bankId",
					PropertyReader.getValue("error.require", "Bank Name"));
			pass = false;
		}
		
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("typeOf"))) {
			request.setAttribute("typeOf",
					PropertyReader.getValue("error.require", "Type Of Account"));
			pass = false;
		}

		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(dob)) {
			request.setAttribute("dob","Min Age Must be 17 years");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.require", "Password"));
			pass = false;

		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.password", "Password"));
			return false;
		}else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.password", "Password"));
			return false;
		}

		if (!request.getParameter("password").equals(
				request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			/*ServletUtility.setErrorMessage("Confirm Password did not match",
					request);*/
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.confirmPassword","Confirm Password"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require","Mobile No"));
			pass = false;
		}else if(!DataValidator.isPhoneNo(request.getParameter("mobile"))){
			request.setAttribute("mobile", PropertyReader.getValue("error.invalid","Mobile No"));
			pass=false;
		}
		
		if (DataValidator.isNull(request.getParameter("title"))) {
			request.setAttribute("title", PropertyReader.getValue("error.require","Title"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("accName"))) {
			request.setAttribute("accName", PropertyReader.getValue("error.require","Account Name"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("fax"))) {
			request.setAttribute("fax", PropertyReader.getValue("error.require","Fax"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("nation"))) {
			request.setAttribute("nation", PropertyReader.getValue("error.require","Nationality"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("emailId"))) {
			request.setAttribute("emailId", PropertyReader.getValue("error.require","Email Id"));
			pass = false;
		}else if (!DataValidator.isEmail(request.getParameter("emailId"))) {
			request.setAttribute("emailId",
					PropertyReader.getValue("error.email", "Email Id"));
			pass = false;
		}
		log.debug("UserRegistrationCtl Method validate Ended");
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
		log.debug("UserRegistrationCtl Method populatebean Started");

		UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setRoleId(RoleBean.USER);

		bean.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

			bean.setLogin(DataUtility.getString(request.getParameter("login")));
	
			bean.setPassword(DataUtility.getString(request.getParameter("password")));
	
			bean.setConfirmPassword(DataUtility.getString(request
					.getParameter("confirmPassword")));
	
			bean.setGender(DataUtility.getString(request.getParameter("gender")));
	
			bean.setDob(DataUtility.getDate(request.getParameter("dob")));
			
			bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
			bean.setBankId(DataUtility.getLong(request.getParameter("bankId")));
			bean.setTypeOfAccount(DataUtility.getString(request.getParameter("typeOf")));
			bean.setTitle(DataUtility.getString(request.getParameter("title")));
			bean.setAccountName(DataUtility.getString(request.getParameter("accName")));
			bean.setFax(DataUtility.getString(request.getParameter("fax")));
			bean.setNationality(DataUtility.getString(request.getParameter("nation")));
			bean.setEmailId(DataUtility.getString(request.getParameter("emailId")));
			
			
			populateDTO(bean, request);
	
			log.debug("UserRegistrationCtl Method populatebean Ended");
	
			return bean;
	}

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegistrationCtl() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Contains display logic
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserRegistrationCtl Method doGet Started");
		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		UserModel model = new UserModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			UserBean bean;
            try {
                bean = model.findByPK(id);
             
                ServletUtility.setBean(bean, request);
            
            } catch (ApplicationException e) {
                log.error(e);
            
                ServletUtility.handleException(e, request, response);
                return;
            }
        }

        ServletUtility.forward(getView(), request, response);
        log.debug("UserRegistrationCtl Method doGet Ended");

	}
	/**
	 * Contains submit logic
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in post method");
		log.debug("UserRegistrationCtl Method doPost Started");
	
		String op=DataUtility.getString(request.getParameter("operation"));
		UserModel model=new UserModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			UserBean bean=(UserBean)populateBean(request);
				try {
					if(id>0){
						
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
	                ServletUtility.setBean(bean, request);

					}else {
						long pk=model.registerUser(bean);
						//bean.setId(id);
						ServletUtility.setSuccessMessage("User is successfully Register", request);
						ServletUtility.forward(getView(), request, response);
					}
	              
				} catch (ApplicationException e) {
					e.printStackTrace();
					ServletUtility.forward(OBMView.ERROR_VIEW, request, response);
					return;
				
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("User Is already exists",
						request);
			}
			
		}else if (OP_DELETE.equalsIgnoreCase(op)) {
		UserBean bean=	(UserBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(OBMView.USER_LIST_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OBMView.USER_LIST_CTL, request, response);
			return;
	}else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(OBMView.USER_REGISTRATION_CTL, request, response);
		return;
}
		ServletUtility.forward(getView(), request, response);
		log.debug("UserRegistrationCtl Method doPost Ended");
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return OBMView.USER_REGISTRATION_VIEW;
	}

}
