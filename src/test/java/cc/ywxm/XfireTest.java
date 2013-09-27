package cc.ywxm;

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
                    "http://127.0.0.1/service/ActivationCodeService");
            //方法一 测试
            String str = service.generate_code(1,4);
            System.out.println(str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
