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
