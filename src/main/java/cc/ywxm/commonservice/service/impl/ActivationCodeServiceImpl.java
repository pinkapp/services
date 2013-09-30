package cc.ywxm.commonservice.service.impl;

import cc.ywxm.commonservice.dao.ActivationCodeDao;
import cc.ywxm.commonservice.model.ActivationCode;
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

    public String generate_code(int id, int n) throws JSONException {
        List<ActivationCode> codes = new ArrayList<ActivationCode>();
        if (id > 0) {
            for (int i = 1; i <= n; i++) {
                ActivationCode code = new ActivationCode(id);
                codes.add(code);
            }
        }
        activationCodeDao.batch_save(codes);
        return new JSONObject().put("array", new JSONArray(codes)).toString();
    }

    @Override
    public String generate_code(int kind, String servers, int begin_ts, int end_ts, int n) {
        List<ActivationCode> codes = new ArrayList<ActivationCode>();
        if (kind > 0) {
            for (int i = 1; i <= n; i++) {
                ActivationCode code = new ActivationCode(kind, servers, begin_ts, end_ts);
                codes.add(code);
            }
        }
        activationCodeDao.batch_save(codes);
        return new JSONArray(codes).toString();
    }

    @Override
    public String get_info(String code) {
        ActivationCode activationCode = activationCodeDao.get(code);
        if (activationCode == null) {
            return null;
        }
        return new JSONObject(activationCode).toString();
    }

    @Override
    public boolean update_valid(String code, int valid) {
        ActivationCode activationCode = activationCodeDao.get(code);
        activationCode.setValid(valid);
        activationCodeDao.update(activationCode);
        return true;
    }

    @Override
    public int exchangeCode(int serverid, String code) {
        ActivationCode activationCode = activationCodeDao.get(code);
        if (activationCode == null) return 1;  //错误cdk
        if (activationCode.getValid() == 1) return 2; //已使用的CDK
        StringTokenizer tokenizer = new StringTokenizer(activationCode.getServers(), ",");
        while (tokenizer.hasMoreTokens()) {
            String elt = tokenizer.nextToken();
            if (String.valueOf(serverid).equals(elt.trim())) {
                long current = new Date().getTime();
                if (activationCode.getBegin_time().getTime() <= current && current <= activationCode.getEnd_time().getTime()) {
                    activationCode.setValid(1);
                    activationCodeDao.update(activationCode);
                    return 0;
                } else {
                    return 5;//cdk过期
                }
            }
        }
        return 4;//该cdk不属于该服务器使用
    }

}
