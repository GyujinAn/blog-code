package springmvc.login.config.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final HandlerInterceptor guestAuthInterceptor;

    private final HandlerInterceptor userAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        registry.addInterceptor(guestAuthInterceptor)
                .addPathPatterns("/test/guest")
                .excludePathPatterns();

        registry.addInterceptor(userAuthInterceptor)
                .addPathPatterns("/test/user")
                .excludePathPatterns();
    }

    }
