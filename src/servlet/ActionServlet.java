package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionServlet extends HttpServlet {

	/**
	 * actionキーが指定されたアクションであるか、パラメータがないかを判定する。
	 * @param request HttpServletRequest
	 * @param actionString actionキーの値
	 * @return リクエストのactionキーが指定されたactionStringに等しいかパラメータがない場合はtrue、それ以外はfalse
	 */
	protected boolean isDefaultAction(HttpServletRequest request, String actionString) {
		String action = request.getParameter("action");
		return (action == null || action.length() == 0 || action.equals(actionString));
	}

	/**
	 * 指定されたURLに遷移する。
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page 遷移先URL
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void gotoPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	/**
	 * 内部エラーページに遷移する。
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void gotoInternalErrorUrl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.gotoErrorUrl(request, response, "内部エラーが発生しました。");
	}

	/**
	 * エラーペジに遷移する。
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void gotoErrorUrl(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		request.setAttribute("message", message);
		this.gotoPage(request, response, "errInternal.jsp");
	}
}
