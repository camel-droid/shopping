package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AuthBean;
import bean.CustomerBean;
import dao.CustomerDAO;
import dao.DAOException;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends ActionServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		String nextPage = "";
		if (this.isDefaultAction(request, "entry")) {
			nextPage = "/user/entry.jsp";
		} else if (action.equals("confirm")) {
			nextPage = "/user/confirm.jsp";
			// リクエストパラメータを取得
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String tel = request.getParameter("tel");
			String email = request.getParameter("email");
			String uid = request.getParameter("uid");
			String password = request.getParameter("password");

			CustomerBean customer = new CustomerBean(0, name, address, tel, email);
			AuthBean auth = new AuthBean();
			auth.setUid(uid);
			auth.setPassword(password);

			HttpSession session = request.getSession();
			session.setAttribute("signup", customer);
			session.setAttribute("auth", auth);

		} else if (action.equals("signup")) {
			HttpSession session = request.getSession(false);
			if (session == null) {
				request.setAttribute("message", "セッションが切れています。サイドトップページより操作してください。");
				this.gotoInternalErrorUrl(request, response);
				return;
			}
			CustomerBean customer = (CustomerBean) session.getAttribute("signup");
			AuthBean auth = (AuthBean) session.getAttribute("auth");
			if (customer == null || auth == null) {
				request.setAttribute("message", "正しい操作をしてください。");
				this.gotoInternalErrorUrl(request, response);
				return;
			}

			try {

				CustomerDAO customerDao = new CustomerDAO();
				customerDao.insert(customer, auth);

				nextPage = "/user/complete.jsp";
			} catch (DAOException | SQLException e) {
				e.printStackTrace();
				this.gotoInternalErrorUrl(request, response);
			} finally {
				if (session.getAttribute("signup") != null) session.removeAttribute("signup");
				if (session.getAttribute("auth") != null) session.removeAttribute("auth");
			}
		}

		this.gotoPage(request, response, nextPage);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
