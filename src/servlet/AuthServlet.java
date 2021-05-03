package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AuthBean;
import dao.AuthDAO;
import dao.DAOException;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet("/AuthServlet")
public class AuthServlet extends ActionServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public AuthServlet() {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// パラメータの解析：actionキーの取得と処理の分岐
		String action = request.getParameter("action");
		// 遷移先URLを初期化
		String nextPage = "";
		// actionキーが「signin」の場合
		if (this.isDefaultAction(request, "auth")) {
			nextPage = "/auth.jsp";

		// actionキーが「signin」の場合：ログイン処理を実行
		} else if (action.equals("signin")) {
			// リクエストパラメータを取得
			String uid = request.getParameter("uid");
			String password = request.getParameter("password");

			try {
				// 取得したパラメータからAunthBeanのインスタンスを生成
				AuthBean targetAuth = new AuthBean();
				targetAuth.setUid(uid);
				targetAuth.setPassword(password);
				// 認証を実行
				AuthDAO dao = new AuthDAO();
				AuthBean signin = dao.isAuth(targetAuth);

				// 認証に失敗した場合
				if (signin == null) {
					nextPage = "/index.jsp";

				// 認証に成功した場合
				} else {
					// セッションに認証情報を登録
					HttpSession session = request.getSession();
					AuthBean auth = (AuthBean) session.getAttribute("signin");
					if (auth == null) {
						session.setAttribute("signin", signin);
					} else {
						this.gotoInternalErrorUrl(request, response);
						return;
					}
					// 管理者権限の場合は遷移先URLに管理用ページを指定
					if (signin.getRole() == 1) nextPage = "/system/system.jsp";
					// 一般ユーザ権限の場合は遷移先URLにトップページを指定
					if (signin.getRole() == 2) nextPage = "/ShowItemServlet?action=top";
				}

				// 遷移先URLに遷移
				//this.gotoPage(request, response, nextPage);

			} catch (DAOException e) {
				e.printStackTrace();
				this.gotoInternalErrorUrl(request, response);
			}
		} else if (action.equals("signout")) {
			HttpSession session = request.getSession(false);
			if (session == null) {
				this.gotoErrorUrl(request, response, "セッションが切れています。もう一度トップページより操作してください。");
				return;
			}

			AuthBean auth = (AuthBean) session.getAttribute("signin");
			if (auth == null) {
				this.gotoErrorUrl(request, response, "正しい操作をしてください。");
				return;
			}

			// セッションから認証情報を削除
			session.removeAttribute("signin");
			/* TODO ログアウト時にカート情報・顧客情報を削除する？
			if (session.getAttribute("cart") != null) session.removeAttribute("cart");
			if (session.getAttribute("customer") != null) session.removeAttribute("customer");
			*/
			// 認証画面に遷移
			nextPage = "/signout.jsp";
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

}
