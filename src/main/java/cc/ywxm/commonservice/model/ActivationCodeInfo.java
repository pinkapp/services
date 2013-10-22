package cc.ywxm.commonservice.model;

import java.sql.Timestamp;

/**
 * 激活码信息.
 * User: Administrator
 * Date: 13-10-22
 * Time: 下午4:31
 */
public class ActivationCodeInfo {
    private String code;
    private Timestamp useTime;
    private Integer usePlayer;
    private String status;

    public ActivationCodeInfo(String code, Timestamp useTime, Integer usePlayer) {
        this.code = code;
        this.useTime = useTime;
        this.usePlayer = usePlayer;
        if (useTime == null) {
            this.status = "未使用";
        } else {
            this.status = "已使用";
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getUseTime() {
        return useTime;
    }

    public void setUseTime(Timestamp useTime) {
        this.useTime = useTime;
    }

    public Integer getUsePlayer() {
        return usePlayer;
    }

    public void setUsePlayer(Integer usePlayer) {
        this.usePlayer = usePlayer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
