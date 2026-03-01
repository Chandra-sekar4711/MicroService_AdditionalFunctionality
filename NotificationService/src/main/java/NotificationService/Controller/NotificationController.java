package NotificationService.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notification")
public class NotificationController {

    @PostMapping("/sendnotification/{id}")
    public String sendnotification(@PathVariable  Integer id )
    {
        System.out.println("*************   ENTER INTO NOTIFICATION SERVICE  *************");
        if(id%2==0)
        {
         return "Notify send ";
        }
        else{
            return "Notify not send";
        }    }
}
