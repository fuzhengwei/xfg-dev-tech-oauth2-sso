package cn.bugstack.xfg.dev.tech.trigger;

import cn.bugstack.xfg.dev.tech.trigger.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class Client01Controller {

    @GetMapping("/create_pay_order")
    public Result createPayOrder() {
        Result result = new Result();
        result.setCode(0);
        result.setData("下单完成");
        return result;
    }

    @GetMapping("/")
    public void callback(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://client1.xfg.com/client1Page/#/home");
    }

}
