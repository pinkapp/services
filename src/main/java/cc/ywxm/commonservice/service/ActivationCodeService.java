package cc.ywxm.commonservice.service;

/**
 * 激活码业务接口
 *
 * @author HUANGDECAI
 */
public interface ActivationCodeService {


    /**
     * 生成激活码
     *
     * @param kind    道具sid
     * @param servers 适用的服务器ID，逗号分隔
     * @param n       个数
     * @return 激活码的列表
     */
    String generate_code(int kind, String servers,int begin_ts, int end_ts, int n);

    /**
     *兑换激活码
     * @param serverid
     * @param player
     * @param code
     * @return
     */
    String exchangeCode(int serverid,int player, String code);

    /**
     * 相同活动ID的激活码
     * @param code
     * @return
     */
    String sameEventCodes(String code);

    /**
     * 查询激活码信息
     * @param code
     * @return
     */
    String  getActivationCodeInfo(String code);

}
