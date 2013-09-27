package cc.ywxm.commonservice.model;


import cc.ywxm.utils.RandomStringUtils;

import java.io.Serializable;

/**
 * 激活码
 * 
 * @author HDC
 * 
 */
public class ActivationCode implements Serializable
{
	/** 激活码 */
	private String code;
	/** 道具ID */
	private int kind;
	/** 是否有效 */
	private int is_valid;

	public ActivationCode(int kind)
	{
		super();
        this.code = RandomStringUtils.getRandomString(16);
		this.kind = kind;
		this.is_valid = 1;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public int getKind()
	{
		return kind;
	}

	public void setKind(int kind)
	{
		this.kind = kind;
	}

	public int getIs_valid()
	{
		return is_valid;
	}

	public void setIs_valid(int isValid)
	{
		is_valid = isValid;
	}

}
