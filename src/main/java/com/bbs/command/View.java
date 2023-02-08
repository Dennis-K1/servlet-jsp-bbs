package com.bbs.command;

import com.bbs.util.CommandUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
/**
 * 프론트와 컨트롤러를 잇는 View 객체
 */
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
	 * 유효성 검증 오류 여부
	 */
	boolean isValidationError;


	/**
	 * redirect 가 false (forward)인 경우에만 어드민 클라이언트 구분하여 경로명 변경
	 *
	 * @param requestURI 요청 경로
	 */
	public void resolveView(String requestURI) {

		//어드민 경로인 경우
		if (CommandUtil.isAdminRequest(requestURI)) {
			if (this.isRedirect) {
				this.path = "/admin" + this.path;
				return;
			}
			this.path = "/WEB-INF/views/admin" + this.path + ".jsp";
			return;
		}

		//클라이언트 경로인 경우
		if (this.isRedirect) {
			return;
		}
		this.path = "/WEB-INF/views/client" + this.path + ".jsp";
	}

	/**
	 * 앞단으로 정보 전달 및 화면 요청
	 *
	 * @param request 요청 객체
	 * @param response 응답 객체
	 */
	public void render(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		if (this.isRedirect) {
			response.sendRedirect(this.path);
			return;
		}
		request.getRequestDispatcher(this.path).forward(request, response);
	}
}
