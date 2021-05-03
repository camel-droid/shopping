package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * すべてのDAOクラスが継承する基底クラス
 * @author tutor
 */
public class BaseDAO {

	/**
	 * クラス定数：データベース接続情報
	 */
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql:sample";
	private static final String DB_USER = "student";
	private static final String DB_PASSWORD = "himitu";

	/**
	 * フィールド
	 */
	protected Connection con; // データベース接続オブジェクト

	/**
	 * コンストラクタ
	 * @throws DAOException
	 */
	public BaseDAO() throws DAOException {
		this.getConnection();
	}

	/**
	 * データベース接続を切断する：データベース接続オブジェクトを解放する。
	 * @throws SQLException
	 */
	protected void close() throws SQLException {
		if (this.con != null) {
			this.con.close();
			this.con = null;
		}
	}

	/**
	 * データベースに接続する：データベース接続オブジェクトを取得する。
	 * @throws DAOException
	 */
	private void getConnection() throws DAOException {
		// JDBCドライバの登録
		try {
			Class.forName(DB_DRIVER);
			this.con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DAOException("データベースの接続に失敗しました。");
		}
	}
}
