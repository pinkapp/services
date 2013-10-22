package cc.ywxm.commonservice.model;


import cc.ywxm.utils.RandomStringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 激活码
 *
 * @author HDC
 */
@NamedQueries({
        @NamedQuery(name = "findActivationCodeByCode", query = "SELECT ac FROM ActivationCode ac WHERE ac.code = :code"),
        @NamedQuery(name = "findActivationCodeByEventId", query = "SELECT ac FROM ActivationCode ac WHERE ac.eventId =:eventId"),
        @NamedQuery(name = "findActivationCodeByCode2", query = "SELECT ac FROM ActivationCode ac WHERE ac.code = :code and ac.servers like :servers")
})
@Entity
public class ActivationCode implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 激活码
     */
    private String code;
    /**
     * 道具ID
     */
    private Integer kind;
    /**
     * 可用服务器
     */
    private String servers;
    /**
     * 开始生效时间
     */
    private Timestamp beginTime;
    /**
     * 截止生效时间
     */
    private Timestamp endTime;
    /**
     * 活动ID
     */
    private Integer eventId;

    public ActivationCode() {
    }


    public ActivationCode(int eventId, int kind, String servers, int begin_ts, int end_ts) {
        this.eventId = eventId;
        this.code = RandomStringUtils.getRandomString(16);
        this.kind = kind;
        this.servers = servers;
        this.beginTime = new Timestamp(begin_ts * 1000l);
        this.endTime = new Timestamp(end_ts * 1000l);
    }

    public String getCode() {
        return code;

    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getServers() {
        return servers;
    }

    @Transient
    public List<String> getServersForList() {
        List<String> ss = new ArrayList<String>();
        StringTokenizer stringTokenizer = new StringTokenizer(servers, ",");
        while (stringTokenizer.hasMoreTokens()) {
            ss.add(stringTokenizer.nextToken());
        }
        return ss;
    }


    public void setServers(String servers) {
        this.servers = servers;
    }

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
