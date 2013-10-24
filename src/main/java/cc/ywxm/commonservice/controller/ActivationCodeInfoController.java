package cc.ywxm.commonservice.controller;

import cc.ywxm.commonservice.service.ActivationCode2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ActivationCodeInfoController {

	@Autowired
	private ActivationCode2Service activationCodeService;

	@RequestMapping(value = "/activation-code-info/{code}",method = RequestMethod.GET,produces="text/plain;charset=utf-8")
	public @ResponseBody String show(@PathVariable String code) {
        return activationCodeService.getActivationCodeInfo(code);
	}

	@RequestMapping(value = "/activation-code-info/{code}/same-event-codes",method = RequestMethod.GET,produces="text/plain;charset=utf-8")
	public @ResponseBody String sameEventCodes(@PathVariable String code) {
        return activationCodeService.sameEventCodes(code);
	}
}
