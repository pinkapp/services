package cc.ywxm.commonservice.dao.impl;

import cc.ywxm.commonservice.dao.ActivationCodeDao;
import cc.ywxm.commonservice.model.ActivationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ActivationCodeDaoImpl implements ActivationCodeDao
{

	/** */
	private NamedParameterJdbcTemplate jdbcTemplate;

	/** */
	@Autowired
	public ActivationCodeDaoImpl(DataSource dataSource)
	{
		super();
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public void batch_save(List<ActivationCode> codes)
	{
		String sql = "INSERT INTO activation_code(code, kind, is_valid)VALUES (:code, :kind, :is_valid)";

		for (ActivationCode code : codes)
		{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("code", code.getCode());
			paramMap.put("kind", code.getKind());
			paramMap.put("is_valid", code.getIs_valid());
			jdbcTemplate.update(sql, paramMap);
		}
	}

}
