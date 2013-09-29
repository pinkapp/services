package cc.ywxm.commonservice.dao.impl;

import cc.ywxm.commonservice.dao.ActivationCodeDao;
import cc.ywxm.commonservice.model.ActivationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class ActivationCodeDaoImpl implements ActivationCodeDao {

    /** */
    private NamedParameterJdbcTemplate jdbcTemplate;

    /** */
    @Autowired
    public ActivationCodeDaoImpl(DataSource dataSource) {
        super();
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void batch_save(List<ActivationCode> codes) {
        String sql = "INSERT INTO activation_code(code, kind, servers, begin_time, end_time, valid)VALUES (:code, :kind, :servers, :begin_time, :end_time, :valid)";

        for (ActivationCode code : codes) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("code", code.getCode());
            paramMap.put("kind", code.getKind());
            paramMap.put("servers", code.getServers());
            paramMap.put("begin_time", code.getBegin_time());
            paramMap.put("end_time", code.getEnd_time());
            paramMap.put("valid", code.getValid());
            jdbcTemplate.update(sql, paramMap);
        }
    }

    @Override
    public ActivationCode get(String code) {
        String sql = "SELECT * FROM activation_code WHERE code = :code";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", code);
        List<ActivationCode> list = jdbcTemplate.query(sql, paramMap, ParameterizedBeanPropertyRowMapper.newInstance(ActivationCode.class));
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void update(ActivationCode code) {
        String sql = "UPDATE activation_code" +
                " SET kind = :kind, servers = :servers, begin_time = :begin_time, end_time = :end_time, valid = :valid WHERE code = :code";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", code.getCode());
        paramMap.put("kind", code.getKind());
        paramMap.put("servers", code.getServers());
        paramMap.put("begin_time", code.getBegin_time());
        paramMap.put("end_time", code.getEnd_time());
        paramMap.put("valid", code.getValid());
        jdbcTemplate.update(sql, paramMap);
    }

}
