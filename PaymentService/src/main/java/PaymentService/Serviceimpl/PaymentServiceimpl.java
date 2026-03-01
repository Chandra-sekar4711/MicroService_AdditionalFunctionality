package PaymentService.Serviceimpl;

import PaymentService.FeignClient.NotificationFeign;
import PaymentService.Model.PaymentModel;
import PaymentService.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceimpl {
    @Autowired
    PaymentRepository payrepo;

    @Autowired
    NotificationFeign notificationfeign;

    public PaymentModel processpayment(String id) {
        PaymentModel obj = new PaymentModel();
        if(Integer.parseInt(id)%2==0)
        {
            obj.setPaymentStatus(id+""+"SUCCESS");
            obj.setOrderId(id);
        }
        else{
            obj.setPaymentStatus(id+""+"FAILED");
            obj.setOrderId(id);
        }
        String notify = notificationfeign.sendnotification(Integer.parseInt(id));
        obj.setNotificationStatus(notify);
        return payrepo.save(obj);
    }
}
