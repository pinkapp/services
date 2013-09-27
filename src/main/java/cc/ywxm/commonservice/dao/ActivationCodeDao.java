package cc.ywxm.commonservice.dao;

import cc.ywxm.commonservice.model.ActivationCode;

import java.util.List;

/**
 * 激活码表数据访问类
 * 
 * @author HUANGDECAI
 * 
 */
public interface ActivationCodeDao
{

	void batch_save(List<ActivationCode> codes);

}