package com.bbs.command.admin.user;

import com.bbs.command.Command;
import com.bbs.domain.View;
import com.bbs.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 * 아이디 중복 여부 검사 커맨드
 * 1. 프론트에서 AJAX 형태로 중복 검사 요청
 * 2. 아이디 사용 가능 여부 부 확인 후 "idAvailability" JsonObject 설정
 * 3. View 에서 json 반환
 */
public class IdAvailabilityCheckCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		UserService userService = new UserService();

		String account = request.getParameter("account");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("idAvailability", true);

		if (!userService.isAccountAvailable(account)) {
			jsonObject.put("idAvailability", false);
		}

		return View.AJAXResponse(jsonObject);
	}
}