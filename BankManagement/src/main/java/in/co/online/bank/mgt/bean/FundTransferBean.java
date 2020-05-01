package in.co.online.bank.mgt.bean;

import java.util.Date;

public class FundTransferBean extends BaseBean {
	
	private long byAccountNo;
	private String byAccHolderName;
	private Date transferDate;
	private long benAccountNo;
	private String benAccHolderName;
	private String bankName;
	private String routing;
	private long traAmount;
	private String status;
	
	
	
	
	
	
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getByAccountNo() {
		return byAccountNo;
	}

	public void setByAccountNo(long byAccountNo) {
		this.byAccountNo = byAccountNo;
	}

	public String getByAccHolderName() {
		return byAccHolderName;
	}

	public void setByAccHolderName(String byAccHolderName) {
		this.byAccHolderName = byAccHolderName;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public long getBenAccountNo() {
		return benAccountNo;
	}

	public void setBenAccountNo(long benAccountNo) {
		this.benAccountNo = benAccountNo;
	}

	public String getBenAccHolderName() {
		return benAccHolderName;
	}

	public void setBenAccHolderName(String benAccHolderName) {
		this.benAccHolderName = benAccHolderName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getRouting() {
		return routing;
	}

	public void setRouting(String routing) {
		this.routing = routing;
	}

	public long getTraAmount() {
		return traAmount;
	}

	public void setTraAmount(long traAmount) {
		this.traAmount = traAmount;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
