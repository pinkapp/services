package cc.ywxm.service;

import cc.ywxm.commonservice.service.ActivationCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


/**
 * @author Rainisic
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ActivationCodeServiceTest extends AbstractJUnit4SpringContextTests {

    @Resource(name="activationCodeWS")
    private ActivationCodeService activationCodeService;
    
    @Test
    public void exchangeCode() {

        //String retult = activationCodeService.exchangeCode(2,"vSGfKXAvI50TNgwu");
       // System.out.println(retult);
    }
}