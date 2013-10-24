package cc.ywxm.commonservice.dao;

import cc.ywxm.commonservice.model.ActivationCodeExchangeLog;

import java.util.List;

/**
 * 激活码兑换日志表数据访问接口
 * 
 * @author HUANGDECAI
 * 
 */
public interface ActivationCodeExchangeLogDao
{
    /**
     *
     * @param activationCodeExchangeLog
     */
    void save(ActivationCodeExchangeLog activationCodeExchangeLog);

    /**
     *
     * @param serverId
     * @param eventId
     * @param player
     * @return
     */
    int count(int serverId,int eventId,int player);

    /**
     *
     * @param eventId
     * @return
     */
    int countUsed(int eventId);

    /**
     *
     * @param serverId
     * @param eventId
     * @param player
     * @return
     */
    List<ActivationCodeExchangeLog> find(int serverId,int eventId,int player);

    /**
     *
     * @param code
     * @return
     */
    ActivationCodeExchangeLog findByCode(String code);

    /**
     *
     * @param eventId
     * @return
     */
    List<ActivationCodeExchangeLog> findByEventId(int eventId);

}
