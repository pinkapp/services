package cc.ywxm.commonservice.service;

import org.json.JSONException;

/**
 * 激活码业务接口
 *
 * @author HUANGDECAI
 */
public interface ActivationCodeService {
    /**
     * 生成激活码
     *
     * @param kind 道具sid
     * @param n  个数
     * @return
     * @throws JSONException
     */
    String generate_code(int kind, int n) throws JSONException;

    /**
     * 生成激活码
     *
     * @param kind    道具sid
     * @param servers 适用的服务器ID，逗号分隔
     * @param n       个数
     * @return 激活码的列表
     */
    String generate_code(int kind, String servers,int begin_ts, int end_ts, int n);
}
