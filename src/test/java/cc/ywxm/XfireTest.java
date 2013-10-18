package cc.ywxm;

import cc.ywxm.commonservice.model.ActivationCode;
import cc.ywxm.commonservice.service.ActivationCodeService;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import java.net.MalformedURLException;
import java.util.Date;

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
            String code = service.exchangeCode(6,44445,"BeKhKtBsM1crLgBE");
//            int begin_ts = new Long(new Date().getTime() / 1000l).intValue();
//            int end_ts = new Long(new Date().getTime() / 1000l + 60 * 60 * 12).intValue();
//            String code = service.generate_code(2, "33,44,55,6", begin_ts, end_ts, 7);
            System.out.println(code);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
