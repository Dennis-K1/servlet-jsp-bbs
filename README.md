# 프로젝트 개요
### 대시보드와 회원 및 게시글 관리 기능이 있는 관리자 페이지 (종합 게시판웹)
- 개발 기간 : 3 주<br>

### 기술
- 백엔드 : 
<img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/Servlet-green?style=for-the-badge"> <img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white"> <img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white">

- 프론트 :
<img src="https://img.shields.io/badge/JSP-orange?style=for-the-badge"> <img src="https://img.shields.io/badge/html-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">

-  배포 : 
<img src="https://img.shields.io/badge/ubuntu-red?style=for-the-badge&logo=ubuntu&logoColor=black"> <img src="https://img.shields.io/badge/GCP-blue?style=for-the-badge&logo=googlecloud&logoColor=black">

### 프로젝트 진행 중점 목표
  * 서버 유효성 검사
  * 유지보수성 증대
    * 가독성
    * 가용성
  * 모듈화 (컴포넌트화)
    * 중복 코드 최소화
  * 회원 기능
    * 사용자 / 관리자 분리
  * 프론트 컨트롤러 / 커맨드 패턴 적용을 통한 스프링 프레임워크 모방
    
<br>

# 데모 링크

* <http://34.64.189.244:8080/admin>
  * 테스트 계정 :
    * ID : zxc
    * PW : zxc

<br>
<br>

# 목차

* [핵심 기능 목록](#핵심-기능-목록)
* [ERD](#ERD)
* [애플리케이션 구조](#애플리케이션-구조)
  * [1.로그인 필터](#1.-로그인-필터)
  * [2.프론트 컨트롤러](#2.-프론트-컨트롤러)
  * [3.커맨드 팩토리](#3.-커맨드-팩토리)
  * [4.커맨드](#4.-커맨드)
  * [5.뷰](#5.-뷰)
* [프로젝트 중점 목표 및 솔루션](#프로젝트-중점-목표-및-솔루션)
  * [서버 유효성 검사](#서버-유효성-검사)
  * [모듈화 (중복코드 최소화 및 재활용 최대화)](#모듈화-(중복코드-최소화-및-재활용-최대화))
  * [유지보수성 증대](#유지보수성-증대)
  * [회원 기능](#회원-기능)
  * [스프링 프레임워크 모방](#스프링-프레임워크-모방)
* [기타](#기타)
<br>

# 핵심 기능 목록

1. 회원 기능
  - [x] 로그인 (서블릿 필터를 통한 검증 / 세션을 이용한 로그인 정보 저장)
  - [x] 회원가입 (AJAX 요청을 통한 ID 중복 검사)
  - [x] 비밀번호 인코딩 (서블릿 필터를 통해 요청 비밀번호 암호화하여 저장)
  - [x] 회원 정보 (회원 기본 정보 및 회원이 작성한 글 목록 보기)
  - [x] 마지막 접속일 및 방문 횟수 기록 (로그인 기준)
2. 게시판 기능
  - [x] 검색 (공통 검색 컴포넌트)
  - [x] 페이징 (공통 페이징 컴포넌트)
  - [x] 공지사항 (조회 전용 게시판) 
  - [x] 자유게시판 (댓글 및 대댓글 기능이 있는 게시판)
  - [x] 1:1 문의 (사용자 문의 답글 기능이 있는 게시판)
  - [x] 갤러리 (3가지 뷰 모드가 있는 이미지 게시판)
3. 대시보드
  - [x] 게시글 등록 현황 그래프 (ChartJs)
  - [x] 총계 (총 유저수, 게시글 수 및 미답변 1:1 문의 수)
  - [x] 게시글 목록 (게시판별 최근 게시글 각 3개 / 조회수 상위 5개 게시글 목록)
4. 공통
  - [x] redirectURL (인덱스가 아닌 다른 경로로 접속 시도시, 로그인 후 접속 시도한 페이지로 이동)
  - [x] 클라이언트/서버 유효성 검증 (서버 유효성 검증 실패시 알러트 표시후 인덱스 이동)

<br>


# ERD

![application erd](./src/readmeImage/application_erd.png)
#### (컨트롤러부터 모델까지 모든 게시판을 공통 모듈에서 관리하는 것처럼, DB 역시 확장성을 위해 한 개의 게시글 테이블로 구성하였습니다.)

- Article : 게시글 데이터를 저장하는 테이블입니다. 모든 게시판의 게시글은 통합 테이블인 Article 로 저장되며, board_id 로 각 게시판이 구분됩니다.
- Board_Category : 각 게시판을 구분할 board_id 와 이름이 담긴 테이블입니다.
- Reply : 각 게시판의 게시글에 해당하는 댓글, 대댓글, 답글의 데이터가 담기는 테이블입니다. 별도의 컬럼으로 대댓글임을
  표시합니다.
- File : 업로드 되는 파일 정보가 담긴 테이블입니다. 파일의 원본명, 서버저장명, 저장 디렉토리, 확장자 등의 내용이 저장됩니다.
- User : 모든 사용자의 정보가 담기는 테이블입니다. 일반 사용자와 관리자는 role_id 로 구분됩니다.
- Role : 일반 사용자인지 관리자인지를 구분할 role_id 와 권한명이 담긴 테이블입니다.

<br>

# 애플리케이션 구조

![application structure](./src/readmeImage/application_structure.png)

### 1. 로그인 필터

사용자의 요청이 먼저 거치게 되는 곳으로, 필터는 요청 경로를 분석하여 로그인 필요 여부를 판단합니다.<br>
경로가 'whiteList' 에 해당하거나, 이미 클라이언트가 이미 로그인된 경우 요청은 프론트 컨트롤러로 전달되고,
아닌 경우 바로 로그인 화면을 반환합니다.

- 로그인 필터 코드 예시 : 

```java
@WebFilter("/*")
public class LoginFilter implements Filter {

	/**
	 * 로그인 필터가 적용되지 않는 요청 경로 모음
	 */
	private static final String[] whiteList = {"/", "/login", "/login/", "/loginForm",
		"/loginForm/",
		"/register", "/register/", "/registerForm", "/registerForm/",
		"/admin/login/", "/admin/login", "/admin/loginForm", "/admin/loginForm/",
		".css", ".js", ".png", ".jpg", "/admin/users/idAvailabilityCheck"};

	/**
	 * whiteList 에 해당하는 요청 경로일 경우 pass 아닌 경우 로그인 여부 확인 후 로그인 되어있을 경우 진행, 아닐 경우 로그인 페이지 이동
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String requestURI = httpServletRequest.getRequestURI();
		String queryStrings = httpServletRequest.getQueryString();
		HttpSession session = httpServletRequest.getSession();

		if (isWhiteListPath(requestURI, whiteList)) {
			chain.doFilter(request, response);
			return;
		}

		//어드민 요청일 경우
		if (CommandUtil.isAdminRequest(requestURI)) {
			if (CommandUtil.isUserLoggedIn(session, SessionKeys.LOGIN_ADMIN)) {
				chain.doFilter(request, response);
				return;
			}
			if (requestURI.equals(AdminCommands.INDEX.getPath())) {
				httpServletResponse.sendRedirect(AdminCommands.LOGIN_FORM.getPath());
				return;
			}
			if (queryStrings != null) {
				httpServletResponse.sendRedirect(
					AdminCommands.LOGIN_FORM.getPath() + "?redirectURL=" + requestURI + "?" + queryStrings);
				return;
			}
			httpServletResponse.sendRedirect(
				AdminCommands.LOGIN_FORM.getPath() + "?redirectURL=" + requestURI);
			return;
		}
	}
}
```

로그인 필터 외에 로그인 및 회원 등록시 비밀번호를 인코딩하여 전송하는 '비밀번호 필터'도 존재합니다.

- 비밀번호 필터 코드 예시 : 

```java
/**
 * 해당 경로 접속 시 PasswordEncryptionWrapper 로 Request 를 감싸 비밀번호 hash 처리
 */
@WebFilter({"/register", "/login", "/admin/login", "/admin/register"})
public class PasswordEncryptionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		PasswordEncryptionWrapper passwordEncryptionWrapper = new PasswordEncryptionWrapper((HttpServletRequest)request);
		chain.doFilter(passwordEncryptionWrapper, response);
	}

}
```

<br>

### 2. 프론트 컨트롤러

MVC 패턴의 컨트롤러에 해당하며, 
컨트롤러 중에서도 맨 앞에서 모든 요청을 받아 분배하는 역할을 하는 프론트 컨트롤러 입니다.<br>
필터를 거친 요청을 커맨드 팩토리에 넘겨 해당하는 커맨드(컨트롤러)를 동작시켜 필요한 정보를 반환하는 역할을 합니다.


- 프론트 컨트롤러 코드 예시 : 

```java
/**
 * 모든 요청이 시작되는 frontController
 *
 * 1. request 경로 정보를 이용하여 해당하는 Command 객체를 CommandFactory 에서 생성
 * 2. 잘못된 요청으로 인해 Command 가 null 일 경우 인덱스 페이지 반환
 * 3. Command 객체가 요청 정보를 이용하여 로직 실행 후 해당하는 View 객체 생성 반환
 * 4. View.resolverPath 를 통해 redirect 나 forward 에 맞는 경로 resolve,
 * 5. View.render 를 통해 해당 JSP (프론트) 클라이언트 반환
 */
@WebServlet("/")
public class Controller extends HttpServlet {

	/**
	 * 요청 경로에 해당하는 Command 객체를 실행하여 해당 페이지 반환
	 */
	protected void handleRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String requestURI = getRequestURI(request);

		Command command = CommandFactory.getCommand(requestURI);

		if (isInValidRequestCommand(command)) {
			response.sendRedirect(AdminCommands.INDEX.getPath());
			return;
		}

		View view = command.execute(request, response);

		view.resolvePath(requestURI);

		view.render(request, response);
	}
}
```

<br>

### 3. 커맨드 팩토리

프론트 컨트롤러가 넘겨주는 요청에 맞추어 해당되는 커맨드를 반환해주는 역할을 합니다.<br>
요청을 분석하여 클라이언트/어드민 요청을 판단하고, 경로와 해당하는 커맨드를 정리해둔 커맨드 맵에서 커맨드를 가져옵니다.

- 커맨드 팩토리 코드 예시 :

```java
/**
 * 모든 Controller 에서 사용하는 Command 팩토리
 */
public abstract class CommandFactory {


	/**
	 * 어드민 페이지 커맨드 맵
	 */
	private static final Map<String, Command> adminCommandMap = AdminCommands.getMap();

	/**
	 * 클라이언트 페이지 커맨드 맵
	 */
	private static final Map<String, Command> clientCommandMap = ClientCommands.getMap();


	/**
	 * 요청 경로에 해당하는 Command 객체 반환
	 *
	 * @param requestURI 요청 경로
	 */
	public static Command getCommand(String requestURI) {

		if (CommandUtil.isAdminRequest(requestURI)) {
			return adminCommandMap.get(requestURI);
		}

		return clientCommandMap.get(requestURI);
	}
}
```
 - 커맨드맵 코드 예시 : 

```java
/**
 * 클라이언트 Command 경로 및 CommandMap 을 관리하는 객체
 */
@Getter
@AllArgsConstructor
public enum ClientCommands {

	/**
	 * 메인 페이지
	 */
	INDEX("/"),

	/**
	 * 로그인
	 */
	LOGIN("/login"),

	/**
	 * 로그인 화면
	 */
	LOGIN_FORM("/loginForm"),

	/**
	 * 회원가입
	 */
	REGISTER("/register"),

	/**
	 * 회원가입 화면
	 */
	REGISTER_FORM("/registerForm");

	
	String path;

	/**
	 * 클라이언트 CommandMap
	 */
	@Getter
	private static final Map<String, Command> map = new HashMap<>();

	static {
		map.put(ClientCommands.INDEX.path, new IndexCommand());
		map.put(ClientCommands.LOGIN_FORM.path, new LoginFormCommand());
		map.put(ClientCommands.LOGIN.path, new LoginCommand());
		map.put(ClientCommands.REGISTER_FORM.path, new RegisterFormCommand());
		map.put(ClientCommands.REGISTER.path, new RegisterCommand());
	}
}
```

<br>

### 4. 커맨드

세분화된 컨트롤러의 역할을 합니다. <br>
서비스 객체를 통해 필요한 로직을 수행하며, 
서비스 객체는 매퍼 객체를 통해 DB와 연결하여 데이터를 주고 받습니다.<br>
forward 를 할 지, redirect 를 할 지, Json 을 반활할지에 따라 그에 맞는 View 객체를 반환합니다. <br>


- 게시글 상세 페이지 커맨드 코드 예시:

```java
/**
 * 게시글 상세 보기 관련 커맨드
 *
 * 1. 게시글 정보 가져오고 조회수 증가
 * 2. 해당 게시글 등록된 파일(이미지)이 있을 경우 파일 정보 (Base64 인코딩 이미지) 등록 후 반환
 */
public class DetailCommand implements
	Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		ArticleService articleService = new ArticleService();

		String id = request.getParameter("articleId");
		Long boardId = CommandUtil.getBoardIdByRequest(request);

		if (!articleService.isValidArticleId(id, boardId)) {
			return View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(),
				Errors.VALIDATION_ERROR.getMessage());
		}

		Long articleId = Long.valueOf(id);

		articleService.increaseArticleViewsById(articleId);
		Article article = articleService.getArticleById(articleId);

		FileService fileService = new FileService();

		File file = fileService.getFileByArticleId(articleId);
		if (!Objects.equals(file,null)){
			article.setImage(fileService.getEncodedImageFromFile(file));
		}

		request.setAttribute("article", article);
		return View.forwardTo(AdminCommands.ARTICLE_DETAIL.getPath());
	}
}
```

<br>

### 5. 뷰

요청에 알맞는 프론트를 넘기는 역할을 담당합니다.<br>
resolvePath 를 통해 경로를 성정하며, render 를 통해 최종적으로 프론트를 호출하여 반환합니다.<br>
render 에서 forward, sendRedirect, AJAX 에 맞는 반환을 합니다.


- View.resolvePath 코드 예시 : 

```java
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
}
```
- View.render 코드 예시 :

```java
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
```
<br>


# 프로젝트 중점 목표 및 솔루션


### 서버 유효성 검사

사용자 입력값에 대해, 자바스크립트를 이용하여 클라이언트 단에서 기본적인 유효성 검사를 진행할 수 있지만 
혹시 모를 경우를 대비하여 서버에서도 동일한 유효성 검증이 이루어져야 합니다. <br>
본 프로젝트를 진행하면서 세웠던 목표 중 하나는 매끄러운 서버 유효성 검사를 도입하는 것이었는데,
이를 어떻게 하면 좀 더 가용성 있게 적용하느냐가 프로젝트의 문제점 중 하나이기도 했습니다.

#### CommandUtil 및 Service 활용 : 

우선 가용성을 위해 공통적으로 사용될 수 있는 유효성 검사 로직의 경우 CommandUtil 의 메소드로 분리하였고,
유효성 검사를 위한 기준값은 CommandUtil 에서 상수로 관리하는 방법을 채택하였습니다 :



- 제한값 상수 관리 코드 예시 : 

```java
//example : 

/**
 * 아이디 최소 길이
 */
private static final int ACCOUNT_MIN = 3;

/**
 * 아이디 최대 길이
 */
private static final int ACCOUNT_MAX = 9;

/**
 * 비밀번호 최소 길이
 */
private static final int PASSWORD_MIN = 4;

/**
 * 비밀번호 최대 길이
 */
private static final int PASSWORD_MAX = 15;

/**
 * 비밀번호 형태 제약 (영어 대, 소문자, 숫자, 특수기호 [!,@,#,$,%,^,&,*,?,_,~])
 */
private static final String PASSWORD_FORM = "/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/";
```

- 유효성 검사 유틸 메소드 코드 예시 :
```java
//example:

/**
 * 빈 문자열인지 확인
 *
 * @param parameter
 * @return
 */
public static Boolean isStringEmpty(String parameter) {
	if (Objects.equals(null, parameter) || "".equals(parameter)) {
	return true;
	}
	return false;
	}

/**
 * 아이디 입력값 유효성 검사
 */
public static boolean isAccountValid(String account) {
	if (isStringEmpty(account)) {
	return false;
	}
	if (account.length() < ACCOUNT_MIN || account.length() > ACCOUNT_MAX) {
	return false;
	}
	return true;
	}

/**
 * 비밀번호 입력값 유효성 검사
 */
public static boolean isPasswordValid(String password) {
	if (isStringEmpty(password)) {
	return false;
	}
	if (!password.matches(PASSWORD_FORM)){
	return false;
	}
	if (password.length() < PASSWORD_MIN || password.length() > PASSWORD_MAX) {
	return false;
	}
	return true;
	}
```

이 외에도 도메인에 관련된 공통 메소드는 해당 도메인과 연관있는 서비스 객체에 정의하여 활용하는 방식으로 진행하였습니다.

<br>

#### 공통 Errors 도메인 및 프론트 errorHandler 를 통해 Alert 처리 : 

서버 유효성 검사에서 실패할 경우 프론트에서는 일률적으로 Alert 를 띄우는 방식으로 간소화하였습니다.<br>
만약 에러가 발생한다면 커맨드는 errorHandler 경로와 함께 에러메세지를 View 객체에 전달하고, 
View 는 이 내용을 토대로 errorHandler 를 render 하며, errorHandler 는 에러메세지를 담은 Alert 를 띄우는 방식입니다.<br>
errorHandler 는 모든 페이지의 공통 요소인 탑 네비게이션 바에 포함 (jsp:include) 되어 있기 때문에,
서버에서 내용을 보내면 그에 맞춰 언제든지 Alert 를 띄우는 식으로 설계하여 개발 편리성을 도모하였습니다 : 

- 커맨드에서 View 로 에러메세지 전달 코드 예시 : 

```java
// 내용 유효성 검사
    String content = multipartRequest.getParameter("content");
        if (!CommandUtil.isContentValid(content)) {
        return  View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(),
        Errors.VALIDATION_ERROR.getMessage());
        }
```

- errorHandler 에서 에러메세지 처리 코드 예시 : 

```javascript
<script>

// <%--백엔드에서 error 메세지를 보냈을 경우 alert 후 전 페이지로 이동
//     redirectURL로 접근시 인덱스로--%>

<c:if test="${not empty requestScope.error }">
    alert("${requestScope.error}");
    <c:remove var="error" scope="request"/>
    if (window.location.pathname === `<%=AdminCommands.LOGIN.getPath()%>`) {
      window.location.replace(`<%=AdminCommands.LOGIN_FORM.getPath()%>`);
    }
    window.location.replace(`<%=AdminCommands.INDEX.getPath()%>`);
    if (window.location.search.startsWith('?redirectURL')) {
      window.location.replace(`<%=AdminCommands.INDEX.getPath()%>`);
    }
    history.back()
    window.location.replace(window.location.pathname);
</c:if>

</script>
```

<br>

### 모듈화 (중복코드 최소화 및 재활용 최대화)

이번 프로젝트의 가장 큰 관심사 및 목표는 코드를 모듈화하여 재활용성을 극대화 시키고, 
이를 통해 확장성을 높이는 것이었습니다.

#### 백엔드 

백엔드에서 위 목표를 달성하기 위해 시행한 방법의 예로, 공통 게시판 커맨드를 통해 모든 게시판 로직을 한 군데에서 관리하는 것,
공통 요소인 페이징 관련 데이터를 객체화 하거나 View 객체를 만든 것을 들 수 있습니다.

#### 프론트엔드

백엔드와 마찬가지로 JSP를 활용한 프론트엔드에서도 개발 편의성과 재활용성 증대를 위한 모듈화를 진행하였습니다.
그 예로 사이드바, 탑네비게이션, 페이징, 검색바, 에러핸들러 등 공통 요소를 컴포넌트화 하여 include 로 활용한 것,
각 게시판에 해당하는 화면을 따로 컴포넌트화하여 관리 용이성을 높인 것을 들 수 있습니다.

- 컴포넌트화 관리 예시 : 

###### 게시판 목록 컴포넌트 (공통 컴포넌트인 탑네비와 사이드메뉴의 경우 동일 적용, 목록 페이지는 백엔드에서 넘어오는 게시판 인식번호에 맞추어 렌더링)
```javascript
<body>
<%--  탑 네브 바    --%>
<jsp:include page="<%=JspComponents.TOP_NAV.getPath()%>"/>
<%-- 센터 --%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <%--        사이드바     --%>
        <jsp:include page="<%=JspComponents.SIDE_MENU.getPath()%>"/>
        <%--      메인 콘텐츠   --%>
        <div class="col" style="background-color: #f5f5f5">
            <c:choose>
                <%--      공지사항   --%>
                <c:when test="${pageParameters.boardId == 1}">
                    <jsp:include page="<%=JspComponents.ADMIN_NOTICE_MANAGEMENT.getPath()%>"/>
                </c:when>
                <%--      자유게시판   --%>
                <c:when test="${pageParameters.boardId == 2}">
                    <jsp:include page="<%=JspComponents.ADMIN_COMMUNITY_MANAGEMENT.getPath()%>"/>
                </c:when>
                <%--      1:1문의   --%>
                <c:when test="${pageParameters.boardId == 3}">
                    <jsp:include page="<%=JspComponents.ADMIN_INQUIRY_MANAGEMENT.getPath()%>"/>
                </c:when>
                <%--      갤러리    --%>
                <c:when test="${pageParameters.boardId == 4}">
                    <jsp:include page="<%=JspComponents.ADMIN_GALLERY_MANAGEMENT.getPath()%>"/>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</body>
```

<br>

### 유지보수성 증대

유지보수성 증대는 위의 모듈화화 궤를 같이 하는 목표 중 하나로, 남이 나의 코드를 보거나,
내 자신이 나중에 이 프로젝트를 유지보수할 때도 어려움 없이 할 수 있도록 코드를 짜보자는 목표입니다.

이를 위해 가독성과 가용성을 중시했으며, 
가독성을 위한 방법으로 변수명을 명확히하고 메소드를 최대한 활용하여 한 눈에 볼 수 있는 코드를 짜는 것을 목표하였습니다: 


- 로그인 커맨드 코드 예시 : 

```java
/**
 * 어드민 로그인 관련 커맨드
 *
 * 1. 사용자 입력 정보 유효성 검증 후 틀리다면 로그인 화면으로 redirect
 * 2. 세션에 사용자 로그인 정보 추가후, 사용자 방문 횟수 및 마지막 접속일 업데이트
 * 3. 이전에 접속하려 했던 페이지 (redirectURL) 가 있는 경우 해당 페이지 반환
 */
public class LoginCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserService userService = new UserService();

		User admin = User.builder()
			.account(request.getParameter("account"))
			.password(request.getParameter("password"))
			.build();

		if (!userService.isUserValid(admin) || !userService.isAdmin(admin)) {
			return View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(),
				Errors.LOGIN_FAILURE.getMessage());
		}

		session.setAttribute(SessionKeys.LOGIN_ADMIN, admin.getAccount());

		userService.increaseVisitCount(admin);
		userService.updateLastLogin(admin);

		String redirectURL = request.getParameter("redirectURL");

		if (CommandUtil.isStringEmpty(redirectURL)) {
			return View.redirectTo(AdminCommands.INDEX.getPath());
		}
		return View.redirectTo(redirectURL);
	}
}
```
- 로그인 커맨드에 사용된 isAdmin 메소드 예시:

```java
/**
 * 유저 정보를 통해 어드민인지 확인
 *
 * @param admin 사용자 정보 객체
 * @return boolean
 */
public boolean isAdmin(User admin) {
    try (SqlSession session = sqlSessionFactory.openSession(true)) {
        UserMapper userMapper = session.getMapper(UserMapper.class);
        Long roleId = userMapper.getUserByAccount(admin.getAccount()).getRoleId();

        String roleName = userMapper.getRoleName(roleId);

        if (roleName.equals(Role.ADMIN.toString())) {
            return true;
        }
        return false;
    }
}
```

주석을 적극 활용하여 누가봐도 쉽게 이해할 수 있게 하는 것, 메소드는 최대한 메소드에 맞는 하나의 기능만을 수행하게 하는 것
등을 예로 들 수 있습니다.

가용성을 위해서는 위에서 언급한 프론트 컨트롤러 및 커맨드 패턴을 활용한 설계, View 객체를 통한 화면 관련 기능 분리,
Enum 과 Map 을 활용한 경로 및 커맨드 객체 관리, 프론트 단의 컴포넌트화 등을 예로 들 수 있습니다.

<br>

### 회원 기능

회원 기능은 필터를 적극 활용하여 관리자 회원 및 로그인 회원 여부를 검사하였고, 
필요한 로직의 경우 CommandUtil 로 공통 로직을 관리하며 가용성을 높였습니다.

<br>

### 스프링 프레임워크 모방

서블릿을 활용한 프로젝트이지만, 스프링을 좀 더 이해하고자 하는 목적이 있었던 만큼 스프링 프레임워크의 여러 방식을 모방하였고,
그 예로 프론트 컨트롤러 및 커맨드 패턴 활용, View 객체 활용을 들 수 있으며, View - Controller - Service - Mapper 의 흐름으로 구성된
MVC 패턴을 적극 활용하였습니다.




# 감사합니다.
<br>


