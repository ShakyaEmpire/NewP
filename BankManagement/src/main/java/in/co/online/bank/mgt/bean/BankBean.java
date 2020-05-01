package in.co.online.bank.mgt.bean;

public class BankBean extends BaseBean {
	
	
	private String name;
	private String IFSECode;
	private String description;
	private String shortCode;
	
	
	

	
	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIFSECode() {
		return IFSECode;
	}

	public void setIFSECode(String iFSECode) {
		IFSECode = iFSECode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}

}
