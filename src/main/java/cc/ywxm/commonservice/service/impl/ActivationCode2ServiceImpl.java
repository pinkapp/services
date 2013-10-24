package cc.ywxm.commonservice.service.impl;

import cc.ywxm.commonservice.dao.ActivationCodeDao;
import cc.ywxm.commonservice.dao.ActivationCodeExchangeLogDao;
import cc.ywxm.commonservice.model.ActivationCode;
import cc.ywxm.commonservice.model.ActivationCodeExchangeLog;
import cc.ywxm.commonservice.model.ActivationCodeInfo;
import cc.ywxm.commonservice.service.ActivationCode2Service;
import cc.ywxm.commonservice.service.ActivationCodeService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 激活码业务实现类
 *
 * @author HUANGDECAI
 */
@Service
public class ActivationCode2ServiceImpl implements ActivationCode2Service {
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

    @Override
    public String sameEventCodes(String code) {
        ActivationCode activationCode = activationCodeDao.get(code);
        List<ActivationCodeInfo> activationCodeInfos = new ArrayList<ActivationCodeInfo>();
        if (activationCode != null) {
            List<ActivationCode> activationCodes = activationCodeDao.findByEventId(activationCode.getEventId());
            List<ActivationCodeExchangeLog> activationCodeExchangeLogs = activationCodeExchangeLogDao.findByEventId(activationCode.getEventId());
            Map<String, ActivationCodeExchangeLog> activationCodeExchangeLogMap = new HashMap<String, ActivationCodeExchangeLog>();
            for (ActivationCodeExchangeLog log : activationCodeExchangeLogs) {
                activationCodeExchangeLogMap.put(log.getCode(), log);
            }
            for (ActivationCode ac : activationCodes) {
                if (activationCodeExchangeLogMap.containsKey(ac.getCode())) {
                    ActivationCodeExchangeLog log = activationCodeExchangeLogMap.get(ac.getCode());
                    ActivationCodeInfo activationCodeInfo = new ActivationCodeInfo(ac.getCode(), log.getTime(), log.getPlayer());
                    activationCodeInfos.add(activationCodeInfo);
                } else {
                    ActivationCodeInfo activationCodeInfo = new ActivationCodeInfo(ac.getCode(), null, null);
                    activationCodeInfos.add(activationCodeInfo);
                }
            }
        }
        return new JSONArray(activationCodeInfos).toString();
    }

    @Override
    public String getActivationCodeInfo(String code) {
        ActivationCode activationCode = activationCodeDao.get(code);
        if (activationCode != null) {
            ActivationCodeInfo activationCodeInfo = null;
            ActivationCodeExchangeLog activationCodeExchangeLog = activationCodeExchangeLogDao.findByCode(code);
            if (activationCodeExchangeLog == null) {
                activationCodeInfo = new ActivationCodeInfo(code, null, null);
            } else {
                activationCodeInfo = new ActivationCodeInfo(code, activationCodeExchangeLog.getTime(), activationCodeExchangeLog.getPlayer());
            }
            int total = activationCodeDao.countByEventId(activationCode.getEventId());
            int used = activationCodeExchangeLogDao.countUsed(activationCode.getEventId());
            activationCodeInfo.setEventUsed(used);
            activationCodeInfo.setEventUnused(total - used);
            return new JSONObject(activationCodeInfo).toString();
        }
        return new JSONObject().toString();
    }

}
