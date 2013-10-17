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


//            for (int i = 1; i <= n; i++) {
//                ActivationCode code = new ActivationCode(id);
//                codes.add(code);
//            }
        }
        activationCodeDao.batch_save(codes);
        return new JSONObject().put("array", new JSONArray(codes)).toString();
    }

    @Override
    public String generate_code(int kind, String servers, int begin_ts, int end_ts, int n) {
        if (kind > 0) {
            ActivationCode activationCode = new ActivationCode(kind, servers, begin_ts, end_ts, n);
            activationCodeDao.save(activationCode);
            activationCode.getCodesForList();
            return new JSONArray(activationCode.getCodesForList()).toString();
        }
        return null;
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
        activationCodeDao.update(activationCode);
        return true;
    }

    @Override
    public String exchangeCode(int serverid, String code) {
        ActivationCode activationCode = activationCodeDao.get(code, serverid);
        if (activationCode == null) {
            ActivationCode activationCode1 = activationCodeDao.get(code);
            if (activationCode1 == null) {
                return new JSONObject().put("result", 1).put("code", code).toString();  //错误cdk
            } else {
                return new JSONObject().put("result", 4).put("code", code).toString();//该cdk不属于该服务器使用
            }
        }
        if (activationCode.getUsed_serversForList().contains(String.valueOf(serverid))) {
            if (activationCode.getUsed_serversForList().contains(code)) {
                return new JSONObject().put("result", 2).put("code", code).toString();//已使用的CDK
            } else {
                return new JSONObject().put("result", 6).put("code", code).toString(); //同批次已使用
            }
        }
        long current = new Date().getTime();
        if (activationCode.getBegin_time().getTime() <= current && current <= activationCode.getEnd_time().getTime()) {
            List<String> used_servers = activationCode.getUsed_serversForList();
            List<String> used_codes = activationCode.getUsed_codesForList();
            used_servers.add(String.valueOf(serverid));
            used_codes.add(code);
            activationCode.setUsed_servers(used_servers);
            activationCode.setUsed_codes(used_codes);
            activationCodeDao.update(activationCode);
            return new JSONObject().put("result", 0).put("code", code).put("kind", activationCode.getKind()).toString();
        } else {
            return new JSONObject().put("result", 5).put("code", code).toString();//cdk过期
        }
    }

}
