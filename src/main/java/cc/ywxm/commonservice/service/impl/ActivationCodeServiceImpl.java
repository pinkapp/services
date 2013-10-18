package cc.ywxm.commonservice.service.impl;

import cc.ywxm.commonservice.dao.ActivationCodeDao;
import cc.ywxm.commonservice.dao.ActivationCodeExchangeLogDao;
import cc.ywxm.commonservice.model.ActivationCode;
import cc.ywxm.commonservice.model.ActivationCodeExchangeLog;
import cc.ywxm.commonservice.service.ActivationCodeService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 激活码业务实现类
 *
 * @author HUANGDECAI
 */
@Service
public class ActivationCodeServiceImpl implements ActivationCodeService {
    @Autowired
    private ActivationCodeDao activationCodeDao;
    @Autowired
    private ActivationCodeExchangeLogDao activationCodeExchangeLogDao;

    @Override
    public String generate_code(int kind, String servers, int begin_ts, int end_ts, int n) {
        List<ActivationCode> activationCodes = new ArrayList<ActivationCode>();
        int eventId = new Long(new Date().getTime() / 1000).intValue();
        for (int i = 0; i < n; i++) {
            ActivationCode activationCode = new ActivationCode(eventId, kind, servers, begin_ts, end_ts);
            activationCodes.add(activationCode);
        }
        activationCodeDao.batch_save(activationCodes);
        return new JSONArray(activationCodes).toString();
    }

    @Override
    public String exchangeCode(int serverid, int player, String code) {
        ActivationCode activationCode = activationCodeDao.get(code);
        if (activationCode == null) {
            return new JSONObject().put("result", 1).put("code", code).toString();  //错误cdk
        }
        if (!activationCode.getServersForList().contains(String.valueOf(serverid))) {
            return new JSONObject().put("result", 4).put("code", code).toString();//该cdk不属于该服务器使用
        }
        int eventId = activationCode.getEventId();
        List<ActivationCodeExchangeLog> activationCodeExchangeLogs = activationCodeExchangeLogDao.find(serverid, eventId, player);
        if (activationCodeExchangeLogs.size() > 0) {
            String code1 = activationCodeExchangeLogs.get(0).getCode();
            if (code.equals(code1)) {
                return new JSONObject().put("result", 2).put("code", code).toString();//已使用的CDK
            } else {
                return new JSONObject().put("result", 6).put("code", code).toString(); //活动已参与
            }
        }
        ActivationCodeExchangeLog activationCodeExchangeLog = activationCodeExchangeLogDao.findByCode(code);
        if (activationCodeExchangeLog != null) {
            return new JSONObject().put("result", 2).put("code", code).toString();//已使用的CDK
        }
        long current = new Date().getTime();
        if (activationCode.getBeginTime().getTime() <= current && current <= activationCode.getEndTime().getTime()) {
            //activationCodeDao.update(activationCode);
            activationCodeExchangeLogDao.save(new ActivationCodeExchangeLog(activationCode.getEventId(), code, serverid, player)); //保存兑换日志
            return new JSONObject().put("result", 0).put("code", code).put("kind", activationCode.getKind()).toString();
        } else {
            return new JSONObject().put("result", 5).put("code", code).toString();//cdk过期
        }
    }

}
