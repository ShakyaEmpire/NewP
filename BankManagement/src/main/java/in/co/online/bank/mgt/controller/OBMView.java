package in.co.online.bank.mgt.controller;

public interface OBMView {
	
	public String APP_CONTEXT = "/BankManagement";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	
	
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";	
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	
	public String BANK_VIEW = PAGE_FOLDER + "/BankView.jsp";	
	public String BANK_LIST_VIEW = PAGE_FOLDER + "/BankListView.jsp";
	
	
	public String FUND_TRANSFER_VIEW = PAGE_FOLDER + "/FundTransferView.jsp";	
	public String FUND_TRANSFER_LIST_VIEW = PAGE_FOLDER + "/FundTransferListView.jsp";
	
	public String ACCOUNT_BALANCE_VIEW = PAGE_FOLDER + "/AccountBalanceView.jsp";
		
	public String BOOK_TRANSACTION_VIEW = PAGE_FOLDER + "/BookTransactionView.jsp";	
	public String BOOK_TRANSACTION_LIST_VIEW = PAGE_FOLDER + "/BookTransactionListView.jsp";
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	
	

	public String ERROR_CTL = "/ctl/ErrorCtl";

	
	
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	
	public String BANK_CTL = APP_CONTEXT + "/ctl/BankCtl";
	public String BANK_LIST_CTL = APP_CONTEXT + "/ctl/BankListCtl";
	
	
	public String FUND_TRANSFER_CTL = APP_CONTEXT + "/ctl/FundTransferCtl";
	public String FUND_TRANSFER_LIST_CTL = APP_CONTEXT + "/ctl/FundTransferListCtl";
	
	public String ACCOUNT_BALANCE_CTL = APP_CONTEXT + "/ctl/AccountBalanceCtl";
	
	public String BOOK_TRANSACTION_CTL = APP_CONTEXT + "/ctl/BookTransactionCtl";
	public String BOOK_TRANSACTION_LIST_CTL = APP_CONTEXT + "/ctl/BookTransactionListCtl";
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";



}
