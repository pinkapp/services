package cc.ywxm;

import cc.ywxm.commonservice.model.ActivationCode;
import cc.ywxm.commonservice.service.ActivationCodeService;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import java.net.MalformedURLException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-27
 * Time: 上午9:36
 */
public class XfireTest {
    public static void main(String[] args) {
        try {
            Service serviceModel = new ObjectServiceFactory().create(ActivationCodeService.class);
            ActivationCodeService service = (ActivationCodeService) new XFireProxyFactory().create(
                    serviceModel,
                    "http://127.0.0.1:81/service/ActivationCodeService");
            //方法一 测试
            String code = service.exchangeCode(44,"dKnSFKlVCuAeKZL3");
            //String code = service.generate_code(2,"33,44,55,6",33434343,453544444,7);
            System.out.println(code);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
