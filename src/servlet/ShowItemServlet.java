package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CategoryBean;
import bean.ItemBean;
import dao.DAOException;
import dao.ItemDAO;

/**
 * Servlet implementation class ShowItemServlet
 */
@WebServlet("/ShowItemServlet")
public class ShowItemServlet extends ActionServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowItemServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// パラメータの解析：actionキーを取得して処理を分岐
		String action = request.getParameter("action");

		// 次画面URLを初期化
		String nextPage = "";
		// actionキーが「top」またはパラメータが存在しない場合はトップページに遷移
		if (this.isDefaultAction(request, "top")) {
			nextPage = "/top.jsp";

		// actionキーが「list」の場合：商品一覧に遷移
		} else if (action.equals("list")) {
			// リクエストパラメータを取得
			String code = request.getParameter("code");
			int categoryCode = Integer.parseInt(code);
			try {
				ItemDAO dao = new ItemDAO();
				List<ItemBean> list = dao.findByCategory(categoryCode);
				// リクエストスコープに商品リストを登録
				 request.setAttribute("items", list);
				// 商品一覧に遷移
				nextPage = "/list.jsp";
			} catch (DAOException e) {
				e.printStackTrace();
				this.gotoInternalErrorUrl(request, response);
			}
		}

		// 次画面に遷移
		this.gotoPage(request, response, nextPage);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	/**
	 * 商品分類リストを取得する。
	 */
	public void init() throws ServletException {
		try {
			ItemDAO dao = new ItemDAO();
			List<CategoryBean> list = dao.findByAllCategory();
			getServletContext().setAttribute("categories", list);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}
}
