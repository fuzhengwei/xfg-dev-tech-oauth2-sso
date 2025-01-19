package cn.bugstack.xfg.dev.tech.trigger;

import cn.bugstack.xfg.dev.tech.trigger.response.Result;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class Client02Controller {

    @GetMapping("/lottery")
    public Result lottery() {
        Result result = new Result();
        result.setCode(0);
        result.setData("下单红包，金额：" + RandomStringUtils.randomNumeric(10) + "元");
        return result;
    }

    @GetMapping("/")
    public void callback(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://client2.xfg.com/client2Page/#/home");
    }

}
