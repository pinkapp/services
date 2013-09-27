package cc.ywxm.commonservice.model;


import cc.ywxm.utils.RandomStringUtils;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 激活码
 *
 * @author HDC
 */
public class ActivationCode implements Serializable {
    private Long id;
    /**
     * 激活码
     */
    private String code;
    /**
     * 道具ID
     */
    private int kind;
    /**
     * 服务器
     */
    private String servers;
    /**
     * 开始生效时间
     */
    private Timestamp begin_time;
    /**
     * 截止生效时间
     */
    private Timestamp end_time;

    public Timestamp getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Timestamp begin_time) {
        this.begin_time = begin_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    /**
     * 是否有效
     */

    private int valid;

    public ActivationCode(int kind) {
        this.kind = kind;
    }

    public ActivationCode(int kind, String servers, int begin_ts, int end_ts) {
        super();
        this.code = RandomStringUtils.getRandomString(16);
        this.kind = kind;
        this.servers = servers;
        this.begin_time = new Timestamp(begin_ts * 1000l);
        this.end_time = new Timestamp(end_ts * 1000l);
        this.valid = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
}
