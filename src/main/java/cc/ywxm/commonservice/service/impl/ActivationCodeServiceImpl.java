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
import java.util.List;

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
    public String generate_code(int kind, String servers, int begin_ts, int end_ts,int n) {
        List<ActivationCode> codes = new ArrayList<ActivationCode>();
        if (kind > 0) {
            for (int i = 1; i <= n; i++) {
                ActivationCode code = new ActivationCode(kind,servers,begin_ts,end_ts);
                codes.add(code);
            }
        }
        activationCodeDao.batch_save(codes);
        return new JSONArray(codes).toString();
    }

}
