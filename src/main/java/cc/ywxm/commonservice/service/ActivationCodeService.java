package cc.ywxm.commonservice.service;

import org.json.JSONException;

/**
 * 激活码业务接口
 * 
 * @author HUANGDECAI
 * 
 */
public interface ActivationCodeService
{
	/**
	 * 生成激活码
	 * 
	 * @param id
	 *            道具ID
	 * @param n
	 *            个数
	 * @return
	 * @throws JSONException
	 */
	String generate_code(int id, int n) throws JSONException;
}
