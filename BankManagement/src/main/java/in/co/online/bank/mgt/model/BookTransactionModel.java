package in.co.online.bank.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.online.bank.mgt.bean.BookTransactionBean;
import in.co.online.bank.mgt.bean.UserBean;
import in.co.online.bank.mgt.exception.ApplicationException;
import in.co.online.bank.mgt.exception.DatabaseException;
import in.co.online.bank.mgt.exception.DuplicateRecordException;
import in.co.online.bank.mgt.exception.RecordNotFoundException;
import in.co.online.bank.mgt.util.JDBCDataSource;

public class BookTransactionModel {

	private static Logger log = Logger.getLogger(BookTransactionModel.class);

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
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM B_BookTransaction");
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

	public BookTransactionBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM B_BookTransaction WHERE ID=?");
		BookTransactionBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BookTransactionBean();
				bean.setId(rs.getLong(1));
				bean.setAccountNo(rs.getLong(2));
				bean.setAccHolderName(rs.getString(3));
				bean.setTransactionType(rs.getString(4));
				bean.setTransactionAmount(rs.getLong(5));
				bean.setBankName(rs.getString(6));
				bean.setRoutingNo(rs.getString(7));
				bean.setTransactionDate(rs.getDate(8));
				bean.setTransactionDescription(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
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

	public BookTransactionBean findByAccountNo(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM B_BookTransaction WHERE AccountNo=?");
		BookTransactionBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BookTransactionBean();
				bean.setId(rs.getLong(1));
				bean.setAccountNo(rs.getLong(2));
				bean.setAccHolderName(rs.getString(3));
				bean.setTransactionType(rs.getString(4));
				bean.setTransactionAmount(rs.getLong(5));
				bean.setBankName(rs.getString(6));
				bean.setRoutingNo(rs.getString(7));
				bean.setTransactionDate(rs.getDate(8));
				bean.setTransactionDescription(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
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

	public long add(BookTransactionBean bean)
			throws ApplicationException, DuplicateRecordException, RecordNotFoundException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		UserModel uModel = new UserModel();
		UserBean uBean = uModel.findByAccountNo(bean.getAccountNo());

		if (uBean == null) {
			throw new RecordNotFoundException("Account No. Do Not EXIST");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO B_BookTransaction VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getAccountNo());
			pstmt.setString(3, uBean.getFirstName() + " " + uBean.getLastName());
			pstmt.setString(4, bean.getTransactionType());
			pstmt.setLong(5, bean.getTransactionAmount());
			pstmt.setString(6, uBean.getBankName());
			pstmt.setString(7, bean.getRoutingNo());
			pstmt.setDate(8, new java.sql.Date(bean.getTransactionDate().getTime()));
			pstmt.setString(9, bean.getTransactionDescription());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());
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

	public void delete(BookTransactionBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM B_BookTransaction WHERE ID=?");
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

	public void update(BookTransactionBean bean) throws ApplicationException, DuplicateRecordException, RecordNotFoundException {
		log.debug("Model update Started");
		Connection conn = null;
		
		UserModel uModel = new UserModel();
		UserBean uBean = uModel.findByAccountNo(bean.getAccountNo());

		if (uBean == null) {
			throw new RecordNotFoundException("Account No. Do Not EXIST");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE B_BookTrasaction SET AccountNo=?,accHolderName=?,TransactionType=?,TransactionAmount=?,BankName=?,RoutingNo=?,TransactionDate=?,TransactionDescription=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setLong(1, bean.getAccountNo());
			pstmt.setString(2, uBean.getFirstName() + " " + uBean.getLastName());
			pstmt.setString(3, bean.getTransactionType());
			pstmt.setLong(4, bean.getTransactionAmount());
			pstmt.setString(5, uBean.getBankName());
			pstmt.setString(6, bean.getRoutingNo());
			pstmt.setDate(7, new java.sql.Date(bean.getTransactionDate().getTime()));
			pstmt.setString(8, bean.getTransactionDescription());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setLong(13, bean.getId());
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
	
	 public List search(BookTransactionBean bean) throws ApplicationException {
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
	     *  @throws ApplicationException
	     */
	    public List search(BookTransactionBean bean, int pageNo, int pageSize)
	            throws ApplicationException {
	        log.debug("Model search Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM B_BookTransaction WHERE 1=1");
	        if (bean != null) {
	            if (bean.getId() > 0) {
	                sql.append(" AND id = " + bean.getId());
	            }
	            if (bean.getAccountNo() > 0) {
	                sql.append(" AND AccountNo = " + bean.getAccountNo());
	            }
	            if (bean.getAccHolderName() != null && bean.getAccHolderName().length() > 0) {
					sql.append(" AND AccHolderName LIKE '" + bean.getAccHolderName() + "%'");
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
	                bean = new BookTransactionBean();
	                bean.setId(rs.getLong(1));
					bean.setAccountNo(rs.getLong(2));
					bean.setAccHolderName(rs.getString(3));
					bean.setTransactionType(rs.getString(4));
					bean.setTransactionAmount(rs.getLong(5));
					bean.setBankName(rs.getString(6));
					bean.setRoutingNo(rs.getString(7));
					bean.setTransactionDate(rs.getDate(8));
					bean.setTransactionDescription(rs.getString(9));
					bean.setCreatedBy(rs.getString(10));
					bean.setModifiedBy(rs.getString(11));
					bean.setCreatedDatetime(rs.getTimestamp(12));
					bean.setModifiedDatetime(rs.getTimestamp(13));
	                list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	           log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in search Role");
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
	        StringBuffer sql = new StringBuffer("select * from B_BookTransaction");
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
	                BookTransactionBean bean = new BookTransactionBean();
	                bean.setId(rs.getLong(1));
					bean.setAccountNo(rs.getLong(2));
					bean.setAccHolderName(rs.getString(3));
					bean.setTransactionType(rs.getString(4));
					bean.setTransactionAmount(rs.getLong(5));
					bean.setBankName(rs.getString(6));
					bean.setRoutingNo(rs.getString(7));
					bean.setTransactionDate(rs.getDate(8));
					bean.setTransactionDescription(rs.getString(9));
					bean.setCreatedBy(rs.getString(10));
					bean.setModifiedBy(rs.getString(11));
					bean.setCreatedDatetime(rs.getTimestamp(12));
					bean.setModifiedDatetime(rs.getTimestamp(13));
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
