package NotificationService.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notification")
public class NotificationController {

    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @PostMapping("/sendnotification/{id}")
    public String sendnotification(@PathVariable  Integer id )
    {
        log.info("ENTER Notification Service, id={}", id);
        if(id%2==0)
        {
         return "Notify send ";
        }
        else{
            return "Notify not send";
        }    }
}
