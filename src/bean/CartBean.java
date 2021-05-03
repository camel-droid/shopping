package bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * カートを管理するBeanクラス
 * @author tutor
 */
public class CartBean {

	/**
	 * フィールド
	 */
	private Map<Integer, ItemBean> items = new HashMap<Integer, ItemBean>(); // 商品リスト
	private int total;                    // 商品総額

	/**
	 * デフォルトコンストラクタ
	 */
	public CartBean() {

	}

	/**
	 * アクセサメソッド群：明示的にはgetterメソッドのみを実装
	 */

	public Map<Integer, ItemBean> getItems() {
		return this.items;
	}

	public int getTotal() {
		return this.total;
	}

	/**
	 * カートに商品を追加する。
	 * @param bean 追加する商品のItemBeanのインスタンス
	 * @param quantity 追加する商品の数量（購入数）
	 */
	public void addCart(ItemBean bean, int nums) {
		// 追加する商品と同じ商品をカートから取得
		ItemBean item = (ItemBean) this.items.get(Integer.valueOf(bean.getCode()));

		// 商品がカート内にない場合：追加する商品ははじめてカートに追加する商品である
		if (item == null) {
			bean.setQuantity(nums);
			items.put(Integer.valueOf(bean.getCode()), bean);

		// すでにカートに入っている商品を追加する場合
		} else {
			item.setQuantity(nums + item.getQuantity());
		}
		// 購入金額を再計算
		this.recalcTotal();
	}

	/**
	 * 商品の総額を計算する。
	 */
	private void recalcTotal() {
		// 総額を初期化
		this.total = 0;
		// 商品のキーに対応する値を取得
		Collection<ItemBean> list = items.values();
		for (ItemBean item : list) {
			this.total += item.getPrice() * item.getQuantity();
		}
	}

	/**
	 * カートから商品を削除する。
	 * @param itemCode 削除対象の商品の商品番号
	 */
	public void deleteCart(int itemCode) {
		this.items.remove(Integer.valueOf(itemCode));
		this.recalcTotal();
	}
}
