package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.AuthBean;
import bean.CustomerBean;

public class CustomerDAO extends BaseDAO {

	public CustomerDAO() throws DAOException {
		super();
	}

	public void insert(CustomerBean customer, AuthBean auth) throws DAOException, SQLException {
		PreparedStatement pstmt = null;

		try {
			this.con.setAutoCommit(false);
			String sql = "insert into customer (name, address, tel, email) values(?, ?, ?, ?)";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getAddress());
			pstmt.setString(3, customer.getTel());
			pstmt.setString(4, customer.getEmail());
			pstmt.executeUpdate();

			pstmt.close();

			sql = "insert into auth (role, uid, password) values (?, ?, ?)";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, 2);
			pstmt.setString(2, auth.getUid());
			pstmt.setString(3, auth.getPassword());
			pstmt.executeUpdate();

			this.con.commit();
		} catch (Exception e) {
			this.con.rollback();
			e.printStackTrace();
			throw new DAOException("レコードの追加に失敗しました。");
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				this.close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}



}
