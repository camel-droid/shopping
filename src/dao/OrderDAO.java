package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import bean.CartBean;
import bean.CustomerBean;
import bean.ItemBean;

/**
 * 注文に関連したテーブルにアクセスするDAOクラス
 * @author tutor
 */
public class OrderDAO {

	/**
	 * フィールド
	 */
	private Connection con; // データベース接続オブジェクト

	/**
	 * コンストラクタ
	 * @throws DAOException
	 */
	public OrderDAO() throws DAOException {
		this.getConnection();
	}

	/**
	 * データベースに接続する：データベース接続情報を取得する。
	 * @throws DAOException
	 */
	private void getConnection() throws DAOException {

		/**
		 * データベース接続情報
		 */
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql:sample";
		String user = "student";
		String password = "himitu";

		try {
			Class.forName(driver);
			this.con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DAOException("データベースの接続に失敗しました。");
		}
	}

	/**
	 * 注文を確定する。
	 * @param customer 注文した顧客
	 * @param cart 注文した商品を格納したカート
	 * @return 注文番号
	 * @throws DAOException
	 */
	public int saveOrder(CustomerBean customer, CartBean cart) throws DAOException {
		// データベース接続関連オブジェクトの初期化
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 顧客番号の取得：顧客番号シーケンスから取得
			int customerNo = 0;
			String sql = "select nextval('customer_code_seq')";
			pstmt = this.con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) customerNo = rs.getInt(1); // インデックスを指定して取得

			rs.close();
			pstmt.close();

			// 顧客情報をcustomerテーブルに追加
			sql = "insert into customer values (?, ?, ?, ?, ?)";
			pstmt = this.con.prepareStatement(sql);
			// プレースホルダの設定
			pstmt.setInt(1, customerNo);
			pstmt.setString(2, customer.getName());
			pstmt.setString(3, customer.getAddress());
			pstmt.setString(4, customer.getTel());
			pstmt.setString(5, customer.getEmail());
			// SQLの実行
			pstmt.executeUpdate();
			pstmt.close();

			// 注文番号の取得：注文番号シーケンスから取得
			int orderNumber = 0;
			sql = "select nextval('ordered_code_seq')";
			pstmt = this.con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) orderNumber = rs.getInt(1);

			rs.close();
			pstmt.close();

			// 注文情報をorderテーブに追加
			sql = "insert into ordered values (?, ?, ?, ?)";
			pstmt = this.con.prepareStatement(sql);
			// プレースホルダの設定
			pstmt.setInt(1, orderNumber);
			pstmt.setInt(2, customerNo);
			Date today = new Date(System.currentTimeMillis());
			pstmt.setDate(3, today);
			pstmt.setInt(4, cart.getTotal());
			// SQLの実行
			pstmt.executeUpdate();
			pstmt.close();

			// 注文明細をordered_detailテーブルに追加
			sql = "insert into ordered_detail values (?, ?, ?)";
			pstmt = this.con.prepareStatement(sql);

			// カートから商品リストを取得
			Map<Integer, ItemBean> items = cart.getItems();
			Collection<ItemBean> list = items.values();
			// 商品リストの件数だけ繰り返す
			for (ItemBean item : list) {
				// プレースホルダの設定
				pstmt.setInt(1, orderNumber);
				pstmt.setInt(2, item.getCode());
				pstmt.setInt(3, item.getQuantity());
				// SQLの実行
				pstmt.executeLargeUpdate();
			}
			pstmt.close();

			// 注文番号を返却
			return orderNumber;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				this.close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	/**
	 * データベース接続を切断する：データベース接続オブジェクトを解放する。
	 * @throws SQLException
	 */
	private void close() throws SQLException {
		if (this.con != null) {
			this.con.close();
			this.con = null;
		}
	}



}
