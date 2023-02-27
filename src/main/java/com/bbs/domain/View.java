package com.bbs.domain;

import com.bbs.util.CommandUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Getter;

/**
 * 프론트와 컨트롤러를 잇는 View 객체
 */
@Getter
@Builder
public class View {

	/**
	 * 전송 경로
	 */
	String path;

	/**
	 * response.sendRedirect 여부
	 */
	boolean isRedirect;

	/**
	 * 예외 발생시 프론트 alert 에 사용할 메세지
	 */
	String errorMessage;


	/**
	 * redirect 가 false (forward)인 경우에만 어드민 클라이언트 구분하여 경로명 변경
	 *
	 * @param requestURI 요청 경로
	 */
	public void resolvePath(String requestURI) {

		//어드민 경로인 경우
		if (CommandUtil.isAdminRequest(requestURI)) {
			if (isRedirect) {
				return;
			}
			if (path.equals("/admin")) {
				path = "/WEB-INF/views/admin/index.jsp";
				return;
			}
			path = "/WEB-INF/views" + path + ".jsp";
			return;
		}

		//클라이언트 경로인 경우
		if (isRedirect) {
			return;
		}
		if (path.equals("/")) {
			path = "/WEB-INF/views/client/index.jsp";
			return;
		}
		path = "/WEB-INF/views/client" + path + ".jsp";
	}

	/**
	 * 앞단으로 정보 전달 및 화면 요청
	 *
	 * @param request  요청 객체
	 * @param response 응답 객체
	 */
	public void render(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");

		if (isRedirect) {
			if (errorMessage != null) {
				request.getSession().setAttribute("error", errorMessage);
			}
			response.sendRedirect(path);
			return;
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * path 로 forward
	 *
	 * @param path 경로명
	 * @return
	 */
	public static View forwardTo(String path) {
		return View.builder()
			.path(path)
			.build();
	}

	/**
	 * path 로 단순 redirect
	 *
	 * @param path 경로명
	 * @return
	 */
	public static View redirectTo(String path) {
		return View.builder()
			.isRedirect(true)
			.path(path)
			.build();
	}

	/**
	 * 에러메세지 포함하여 path 로 단순 redirect
	 *
	 * @param path 경로명
	 * @return
	 */
	public static View redirectTo(String path, String errorMessage) {
		return View.builder()
			.isRedirect(true)
			.errorMessage(errorMessage)
			.path(path)
			.build();
	}
}
