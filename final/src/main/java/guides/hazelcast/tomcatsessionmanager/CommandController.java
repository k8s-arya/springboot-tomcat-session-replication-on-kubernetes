package guides.hazelcast.tomcatsessionmanager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class CommandController {

    @Value("#{environment.MY_POD_NAME}")
    private String podName;

    @RequestMapping("/put")
    public CommandResponse put(HttpSession session, @RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
        session.setAttribute(key, value);
        return new CommandResponse(value, podName);
    }

    @RequestMapping("/get")
    public CommandResponse get(HttpSession session, @RequestParam(value = "key") String key) {
        String value = (String) session.getAttribute(key);
        return new CommandResponse(value, podName);
    }


}
