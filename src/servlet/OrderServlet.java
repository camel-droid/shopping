package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartBean;
import bean.CustomerBean;
import dao.DAOException;
import dao.OrderDAO;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストの文字コードを設定
		request.setCharacterEncoding("utf-8");

		// セッションからカートを取得
		HttpSession session = request.getSession(false);	// すでにセッションに登録されている属性を取得するので引数はfalse

		// セッションがない場合：不正なアクセスが含まれている場合もあるのでエラーページに強制的に遷移
		if (session == null) {
			request.setAttribute("message", "セッションが切れています。もう一度トップページより操作してください。");
			this.gotoPage(request, response, "errInternal.jsp");
			return;
		}

		// カートがない場合：不正アクセスである可能性があるのでエラーページに強制的に遷移
		CartBean cart = (CartBean) session.getAttribute("cart");
		if (cart == null) {
			request.setAttribute("message", "正しく操作してください。");
			this.gotoPage(request, response, "errInternal.jsp");
			return;
		}

		// リクエストパラメータの取得
		String action = request.getParameter("action");

		// actionキーが「input_customer」またはパラメータが存在しない場合：お客様情報入力画面に遷移
		if (action == null || action.length() == 0 || action.equals("input_customer")) {
			this.gotoPage(request, response, "customerInfo.jsp");

		// actionキーが「confirm」の場合：入力情報確認画面に遷移
		} else if (action.equals("confirm")) {
			// リクエストパラメータの取得
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String tel = request.getParameter("tel");
			String email = request.getParameter("email");
			CustomerBean customer = new CustomerBean();
			customer.setName(name);
			customer.setAddress(address);
			customer.setTel(tel);
			customer.setEmail(email);

			// セッションスコープに顧客情報を登録
			session.setAttribute("customer", customer);
			// 確認画面に遷移
			this.gotoPage(request, response, "/confirm.jsp");

		// actionキーが「order」の場合：完了画面に遷移
		} else if (action.equals("order")) {
			// 顧客情報を取得
			CustomerBean customer = (CustomerBean) session.getAttribute("customer");
			// 顧客情報がない場合：：不正アクセスである可能性があるのでエラーページに強制的に遷移
			if (customer == null) {
				request.setAttribute("message", "正しく操作してください。");
				this.gotoPage(request, response, "/errInternal.jsp");
				return;
			}

			try {
				// 注文を確定
				OrderDAO dao = new OrderDAO();
				int orderNumber = dao.saveOrder(customer, cart);
				// リクエストスコープに注文番号を登録
				request.setAttribute("orderNumber", orderNumber);
				// 完了画面に遷移
				this.gotoPage(request, response, "/order.jsp");
			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("message", "内部エラーが発生しました。");
				this.gotoPage(request, response, "/errInternal.jsp");
			}
		} else {
			request.setAttribute("message", "正しく操作してください。");
			this.gotoPage(request, response, "/errInternal.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * 指定されたURLに遷移する。
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page 遷移先URL
	 * @throws ServletException
	 * @throws IOException
	 */
	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
