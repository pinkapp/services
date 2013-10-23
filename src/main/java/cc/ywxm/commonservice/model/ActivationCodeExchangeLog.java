package cc.ywxm.commonservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 激活码兑换日志
 * User: Administrator
 * Date: 13-10-17
 * Time: 下午3:48
 */
@NamedQueries(value = {
        @NamedQuery(name = "countActivationCodeExchangeLog", query = "SELECT COUNT(*) FROM ActivationCodeExchangeLog acel WHERE acel.player = :player AND acel.eventId =:eventId AND acel.serverId = :serverId"),
        @NamedQuery(name = "countUsedActivationCodeExchangeLog", query = "SELECT COUNT(*) FROM ActivationCodeExchangeLog acel WHERE acel.eventId =:eventId"),
        @NamedQuery(name = "findActivationCodeExchangeLog", query = "SELECT acel FROM ActivationCodeExchangeLog acel WHERE acel.player = :player AND acel.eventId =:eventId AND acel.serverId = :serverId"),
        @NamedQuery(name = "findActivationCodeExchangeLogByEventId", query = "SELECT acel FROM ActivationCodeExchangeLog acel WHERE acel.eventId =:eventId"),
        @NamedQuery(name = "findActivationCodeExchangeLogByCode", query = "SELECT acel FROM ActivationCodeExchangeLog acel WHERE acel.code = :code")
})
@Entity
public class ActivationCodeExchangeLog {

    @javax.persistence.Id
    @GeneratedValue
    private Integer id;
    private Integer eventId;
    private String code;
    private Integer serverId;
    private Integer player;
    private Timestamp time;

    public ActivationCodeExchangeLog() {

    }

    public ActivationCodeExchangeLog(Integer eventId, String code, Integer serverId, Integer player) {
        this.eventId = eventId;
        this.code = code;
        this.serverId = serverId;
        this.player = player;
        this.time = new Timestamp(new Date().getTime());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public Integer getPlayer() {
        return player;
    }

    public void setPlayer(Integer player) {
        this.player = player;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}
