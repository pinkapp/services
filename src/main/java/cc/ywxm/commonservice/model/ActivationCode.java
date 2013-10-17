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
        @NamedQuery(name = "findActivationCodeByCode", query = "SELECT ac FROM ActivationCode ac WHERE ac.codes like :code"),
        @NamedQuery(name = "findActivationCodeByCode2", query = "SELECT ac FROM ActivationCode ac WHERE ac.codes like :code and ac.servers like :servers")
})
@Entity
public class ActivationCode implements Serializable {

    private Long id;
    /**
     * 激活码
     */
    private String codes;
    /**
     * 道具ID
     */
    private int kind;
    /**
     * 可用服务器
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

    /**
     * 已使用的服务器
     */
    private String used_servers;

    /**
     * 已使用的激活码
     */
    private String used_codes;

    public ActivationCode() {
    }

    public ActivationCode(int kind) {
        this.kind = kind;
    }

    public ActivationCode(int kind, String servers, int begin_ts, int end_ts, int n) {
        super();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                sb.append(RandomStringUtils.getRandomString(16));
            } else {
                sb.append("," + RandomStringUtils.getRandomString(16));
            }
        }
        this.codes = sb.toString();
        this.kind = kind;
        this.servers = servers;
        this.begin_time = new Timestamp(begin_ts * 1000l);
        this.end_time = new Timestamp(end_ts * 1000l);
        this.used_servers = "";
        this.used_codes = "";
    }

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "codes")
    public String getCodes() {
        return codes;
    }

    @Transient
    public List<String> getCodesForList() {
        List<String> ss = new ArrayList<String>();
        StringTokenizer stringTokenizer = new StringTokenizer(codes, ",");
        while (stringTokenizer.hasMoreTokens()) {
            ss.add(stringTokenizer.nextToken());
        }
        return ss;
    }

    public void setCodes(String codes) {
        this.codes = codes;
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

    @Transient
    public List<String> getServersForList() {
        List<String> ss = new ArrayList<String>();
        StringTokenizer stringTokenizer = new StringTokenizer(servers, ",");
        while (stringTokenizer.hasMoreTokens()) {
            ss.add(stringTokenizer.nextToken());
        }
        return ss;
    }

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

    public String getUsed_servers() {
        return used_servers;
    }

    public void setUsed_servers(String used_servers) {
        this.used_servers = used_servers;
    }

    public void setUsed_servers(List<String> servers) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < servers.size(); i++) {
            if (i == 0) {
                sb.append(servers.get(i));
            } else {
                sb.append("," + servers.get(i));
            }
        }
        String used_servers = sb.toString();
        this.used_servers = used_servers;
    }

    @Transient
    public List<String> getUsed_serversForList() {
        List<String> servers = new ArrayList<String>();
        StringTokenizer stringTokenizer = new StringTokenizer(used_servers, ",");
        while (stringTokenizer.hasMoreTokens()) {
            servers.add(stringTokenizer.nextToken());
        }
        return servers;
    }

    public String getUsed_codes() {
        return used_codes;
    }

    @Transient
    public List<String> getUsed_codesForList() {
        List<String> servers = new ArrayList<String>();
        StringTokenizer stringTokenizer = new StringTokenizer(used_codes, ",");
        while (stringTokenizer.hasMoreTokens()) {
            servers.add(stringTokenizer.nextToken());
        }
        return servers;
    }

    public void setUsed_codes(String used_codes) {
        this.used_codes = used_codes;
    }

    public void setUsed_codes(List<String> codes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codes.size(); i++) {
            if (i == 0) {
                sb.append(codes.get(i));
            } else {
                sb.append("," + codes.get(i));
            }
        }
        String used_codes = sb.toString();
        this.used_codes = used_codes;
    }
}
