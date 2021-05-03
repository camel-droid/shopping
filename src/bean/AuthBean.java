package bean;

/**
 * authテーブルの1件のレコードを管理するBeanクラス
 * @author tutor
 */
public class AuthBean {

	/**
	 * フィールド
	 */
	private int code;          // ユーザ番号
	private int role;        // ユーザ権限
	private String uid;      // ユーザID
	private String password; // パスワード

	/**
	 * デフォルトコンストラクタ
	 */
	public AuthBean() {

	}

	/**
	 * コンストラクタ
	 * @param code   ユーザ番号
	 * @param role ユーザ権限
	 * @param uid  ユーザID
	 */
	public AuthBean(int code, int role, String uid) {
		this.code = code;
		this.role = role;
		this.uid = uid;
	}

	/**
	 * アクセサメソッド群
	 */

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
