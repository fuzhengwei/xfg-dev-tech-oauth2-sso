package cn.bugstack.xfg.dev.tech.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component("unauthorizedEntryPoint")
public class AppUnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Map<String, String[]> paramMap = request.getParameterMap();
        StringBuilder param = new StringBuilder();
        paramMap.forEach((k, v) -> {
            param.append("&").append(k).append("=").append(v[0]);
        });

        param.deleteCharAt(0);
        String isRedirectValue = request.getParameter("isRedirect");

        if (!StringUtils.isEmpty(isRedirectValue) && Boolean.parseBoolean(isRedirectValue)) {
            response.sendRedirect("http://sso.xfg.com/authPage/#/login?" + param);
            return;
        }

        String authUrl = "http://sso.xfg.com/auth/oauth/authorize?" + param + "&isRedirect=true";

        Map<String, Object> result = new HashMap<>();
        result.put("code", 800);
        result.put("msg", "授权地址");
        result.put("data", authUrl);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        writer.print(mapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }

}
