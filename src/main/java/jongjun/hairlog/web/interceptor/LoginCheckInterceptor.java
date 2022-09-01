package jongjun.hairlog.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static jongjun.hairlog.web.SessionConst.LoginMember;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        log.info("인증 체크 인터셉터 실행 {}", requestURI);

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute(LoginMember) == null) {
            log.info("미인증 사용자 요청");
            return false;
        }

        log.info("LoginMember = {}", session.getAttribute(LoginMember));

        return true;
    }
}
