package com.bbs.domain;

import com.bbs.util.CommandUtil;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Getter;
import org.json.simple.JSONObject;

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
	 * AJAX 응답 객체
	 */
	JSONObject jsonObject;

	/**
	 * response.sendRedirect 여부
	 */
	boolean isRedirect;

	/**
	 * AJAX 응답 여부
	 */
	boolean isAJAX;

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
		if (isAJAX){
			return;
		}
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
	 * AJAX 일 경우 커맨드에서 대입된 JsonObject 반환
	 * redirect 는 바로,
	 * forward 는 에러 메세지 있을 경우 setAttribute 하여 프론트 전달
	 *
	 * @param request  요청 객체
	 * @param response 응답 객체
	 */
	public void render(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		if (isAJAX) {
			response.setContentType("application/json");
			response.getWriter().print(jsonObject);
			return;
		}
		response.setContentType("text/html;charset=UTF-8");
		if (isRedirect) {
			response.sendRedirect(path);
			return;
		}
		if (errorMessage != null) {
			request.setAttribute("error", errorMessage);
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
	 * 에러메세지 포함하여 path 로 forward
	 *
	 * @param path 경로명
	 * @return
	 */
	public static View forwardTo(String path, String errorMessage) {
		return View.builder()
			.path(path)
			.errorMessage(errorMessage)
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
	 * AJAX 요청일 경우 JsonObject 반환
	 *
	 * @param jsonObject 커맨드에서 입력한 반환 객체
	 * @return
	 */
	public static View AJAXResponse(JSONObject jsonObject) {
		return View.builder()
			.isAJAX(true)
			.jsonObject(jsonObject)
			.build();
	}
}
