package cc.ywxm;

import cc.ywxm.commonservice.service.ActivationCodeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestRMI2 {
	public static void main(String[] args) {
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("springbeans.xml");
         ActivationCodeService activationCodeService = (ActivationCodeService) ctx.getBean("activationCodeServiceProxy");
		 System.out.println(activationCodeService.exchangeCode(2,"2oQVMvX4d7l5E7sg"));
	}
}