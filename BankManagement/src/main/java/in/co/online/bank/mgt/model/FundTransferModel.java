package in.co.online.bank.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.online.bank.mgt.bean.BookTransactionBean;
import in.co.online.bank.mgt.bean.FundTransferBean;
import in.co.online.bank.mgt.bean.UserBean;
import in.co.online.bank.mgt.exception.ApplicationException;
import in.co.online.bank.mgt.exception.DatabaseException;
import in.co.online.bank.mgt.exception.DuplicateRecordException;
import in.co.online.bank.mgt.exception.RecordNotFoundException;
import in.co.online.bank.mgt.util.JDBCDataSource;

public class FundTransferModel {

	private static Logger log = Logger.getLogger(FundTransferModel.class);

	/**
	 * Find next PK of Role
	 * 
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM B_FundTransfer");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	public FundTransferBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM B_FundTransfer WHERE ID=?");
		FundTransferBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FundTransferBean();
				bean.setId(rs.getLong(1));
				bean.setByAccountNo(rs.getLong(2));
				bean.setByAccHolderName(rs.getString(3));
				bean.setTransferDate(rs.getDate(4));
				bean.setBenAccountNo(rs.getLong(5));
				bean.setBenAccHolderName(rs.getString(6));
				bean.setBankName(rs.getString(7));
				bean.setRouting(rs.getString(8));
				bean.setTraAmount(rs.getLong(9));
				bean.setStatus(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	public FundTransferBean findByAccountNo(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM B_FundTransfer WHERE byAccountNo=?");
		FundTransferBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FundTransferBean();
				bean.setId(rs.getLong(1));
				bean.setByAccountNo(rs.getLong(2));
				bean.setByAccHolderName(rs.getString(3));
				bean.setTransferDate(rs.getDate(4));
				bean.setBenAccountNo(rs.getLong(5));
				bean.setBenAccHolderName(rs.getString(6));
				bean.setBankName(rs.getString(7));
				bean.setRouting(rs.getString(8));
				bean.setTraAmount(rs.getLong(9));
				bean.setStatus(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	public FundTransferBean findByBenAccountNo(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM B_FundTransfer WHERE BenAccountNo=?");
		FundTransferBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FundTransferBean();
				bean.setId(rs.getLong(1));
				bean.setByAccountNo(rs.getLong(2));
				bean.setByAccHolderName(rs.getString(3));
				bean.setTransferDate(rs.getDate(4));
				bean.setBenAccountNo(rs.getLong(5));
				bean.setBenAccHolderName(rs.getString(6));
				bean.setBankName(rs.getString(7));
				bean.setRouting(rs.getString(8));
				bean.setTraAmount(rs.getLong(9));
				bean.setStatus(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	public long add(FundTransferBean bean)
			throws ApplicationException, DuplicateRecordException, RecordNotFoundException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		UserModel uModel = new UserModel();
		UserBean uBean = uModel.findByAccountNo(bean.getByAccountNo());
		UserBean bBean = uModel.findByAccountNo(bean.getBenAccountNo());
		bean.setTransferDate(new java.util.Date());
		bean.setByAccHolderName(uBean.getFirstName() + " " + uBean.getLastName());
		bean.setBenAccHolderName(bBean.getFirstName() + " " + bBean.getLastName());
		bean.setBankName(bBean.getBankName());

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO B_FundTransfer VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getByAccountNo());
			pstmt.setString(3, bean.getByAccHolderName());
			pstmt.setDate(4, new java.sql.Date(bean.getTransferDate().getTime()));
			pstmt.setLong(5, bean.getBenAccountNo());
			pstmt.setString(6, bean.getBenAccHolderName());
			pstmt.setString(7, bean.getBankName());
			pstmt.setString(8, bean.getRouting());
			pstmt.setLong(9, bean.getTraAmount());
			pstmt.setString(10, bean.getStatus());
			pstmt.setString(11, bean.getCreatedBy());
			pstmt.setString(12, bean.getModifiedBy());
			pstmt.setTimestamp(13, bean.getCreatedDatetime());
			pstmt.setTimestamp(14, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	public void delete(FundTransferBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM B_FundTransfer WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	public void update(FundTransferBean bean)
			throws ApplicationException, DuplicateRecordException, RecordNotFoundException {
		log.debug("Model update Started");
		Connection conn = null;

		UserModel uModel = new UserModel();
		UserBean uBean = uModel.findByAccountNo(bean.getByAccountNo());
		UserBean bBean = uModel.findByAccountNo(bean.getBenAccountNo());
		bean.setTransferDate(new java.util.Date());
		bean.setByAccHolderName(uBean.getFirstName() + " " + uBean.getLastName());
		bean.setBenAccHolderName(bBean.getFirstName() + " " + bBean.getLastName());
		bean.setBankName(bBean.getBankName());

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE B_FundTransfer SET ByAccountNo=?,ByAccHolderName=?,TransferDate=?,BenAccountNo=?,BenAccHolderName=?,BankName=?,Routing=?,TraAmount=?,Status=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setLong(1, bean.getByAccountNo());
			pstmt.setString(2, bean.getByAccHolderName());
			pstmt.setDate(3, new java.sql.Date(bean.getTransferDate().getTime()));
			pstmt.setLong(4, bean.getBenAccountNo());
			pstmt.setString(5, bean.getBenAccHolderName());
			pstmt.setString(6, bean.getBankName());
			pstmt.setString(7, bean.getRouting());
			pstmt.setLong(8, bean.getTraAmount());
			pstmt.setString(9, bean.getStatus());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());
			pstmt.setLong(14, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Role ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	public List search(FundTransferBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search Role with pagination
	 * 
	 * @return list : List of Roles
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 * @throws ApplicationException
	 */
	public List search(FundTransferBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM B_FundTransfer WHERE 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getByAccountNo() > 0) {
				sql.append(" AND ByAccountNo = " + bean.getByAccountNo());
			}
			if (bean.getBenAccountNo() > 0) {
				sql.append(" AND BenAccountNo = " + bean.getBenAccountNo());
			}
			if (bean.getByAccHolderName() != null && bean.getByAccHolderName().length() > 0) {
				sql.append(" AND ByAccHolderName LIKE '" + bean.getByAccHolderName() + "%'");
			}
			if (bean.getBenAccHolderName() != null && bean.getBenAccHolderName().length() > 0) {
				sql.append(" AND BenAccHolderName LIKE '" + bean.getBenAccHolderName() + "%'");
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FundTransferBean();
				bean.setId(rs.getLong(1));
				bean.setByAccountNo(rs.getLong(2));
				bean.setByAccHolderName(rs.getString(3));
				bean.setTransferDate(rs.getDate(4));
				bean.setBenAccountNo(rs.getLong(5));
				bean.setBenAccHolderName(rs.getString(6));
				bean.setBankName(rs.getString(7));
				bean.setRouting(rs.getString(8));
				bean.setTraAmount(rs.getLong(9));
				bean.setStatus(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model search End");
		return list;
	}
	
	public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * Get List of Role with pagination
     * 
     * @return list : List of Role
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws DatabaseException
     *  @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from B_FundTransfer");
        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + "," + pageSize);
        }
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                FundTransferBean bean = new FundTransferBean();
                bean.setId(rs.getLong(1));
				bean.setByAccountNo(rs.getLong(2));
				bean.setByAccHolderName(rs.getString(3));
				bean.setTransferDate(rs.getDate(4));
				bean.setBenAccountNo(rs.getLong(5));
				bean.setBenAccHolderName(rs.getString(6));
				bean.setBankName(rs.getString(7));
				bean.setRouting(rs.getString(8));
				bean.setTraAmount(rs.getLong(9));
				bean.setStatus(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
          //  log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Role");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model list End");
        return list;

    }

}
