package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.AuthBean;

/**
 * authテーブルにアクセスするDAOクラス
 * @author tutor
 */
public class AuthDAO extends BaseDAO {

	public AuthDAO() throws DAOException {
		super();
	}

	public AuthBean isAuth(AuthBean target) throws DAOException {
		// データベース接続関連オブジェクトの初期化
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from auth where uid = ? and password = ?";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setString(1, target.getUid());
			pstmt.setString(2, target.getPassword());
			rs = pstmt.executeQuery();
			AuthBean auth = null;
			if (rs.next()) {
				auth = new AuthBean();
				auth.setCode(rs.getInt("code"));
				auth.setUid(rs.getString("uid"));
				auth.setRole(rs.getInt("role"));
			}

			return auth;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				this.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}
}
