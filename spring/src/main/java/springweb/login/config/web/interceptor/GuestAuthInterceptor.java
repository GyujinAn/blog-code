package springweb.login.config.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import springweb.login.config.web.SessionUser;
import springweb.login.domain.Role;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class GuestAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        SessionUser sessionUser = (SessionUser)httpSession.getAttribute("user");
        if(sessionUser == null){
            response.setStatus(401);
            return false;
        }


        if(sessionUser.getRole() == Role.GUEST) {
            return true;
        }else{
            response.setStatus(403);
            return false;
        }


    }
}
