package PaymentService.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("NOTIFICATIONSERVICE")
public interface NotificationFeign {

    @PostMapping("/notification/sendnotification/{id}")
    public String sendnotification(@PathVariable  Integer id );
}
