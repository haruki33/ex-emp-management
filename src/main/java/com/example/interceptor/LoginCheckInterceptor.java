package com.example.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

// なぜHandlerInterceptorを実装するのかというと
// HandlerInterceptorのpreHandleを実装するだけで、 controller内のエンドポイント前に実行するようになるため
// もし、自分で実装となるとControllerに接続して、、、とかを自力で書く必要がある
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private HttpSession session;

    // いつもはHttpServletRequestみたいなやつはDIコンテナに任せていたけど、
    // 今回はインターフェースの実装をするためオーバーライドする必要がある。
    // すると、引数もインタフェースと同様にしないといけないためDIはできない
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (session.getAttribute("administratorName") == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        return true;
    }
}
