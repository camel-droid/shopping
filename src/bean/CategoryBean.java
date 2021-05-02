package bean;

/**
 * categoryテーブルの1レコードを管理するBeanクラス
 * @author tutor
 */
public class CategoryBean {

	/**
	 * フィールド
	 */
	private int code;    // 商品分類番号
	private String name; // 商品分類名

	/**
	 * デフォルトコンストラクタ
	 */
	public CategoryBean() {

	}

	/**
	 * コンストラクタ
	 * @param code 商品分類番号
	 * @param name 商品分類名
	 */
	public CategoryBean(int code, String name) {
		super();
		this.code = code;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
