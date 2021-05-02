package dao;

/**
 * DAOクラスで発生する例外
 * @author tutor
 */
public class DAOException extends Exception {

	/**
	 * コンストラクタ
	 * @param message エラーメッセージ
	 */
	public DAOException(String message) {
		super(message);
	}

}
