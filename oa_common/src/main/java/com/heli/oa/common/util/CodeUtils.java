package com.heli.oa.common.util;

import java.util.List;

import org.springframework.stereotype.Service;

@Service("code")
public class CodeUtils {
	private Integer code;
	private	List<?> paramList;
	private Object paramObject;
	private Object pageObject;
	
	

	public CodeUtils() {
		super();
	}
	
	public CodeUtils(Integer code) {
		super();
		this.code = code;
	}
	
	public CodeUtils(Integer code, List<?> param) {
		super();
		this.code = code;
		this.paramList = param;
	}

	public CodeUtils(Integer code, List<?> param, Object paramObject) {
		super();
		this.code = code;
		this.paramList = param;
		this.paramObject = paramObject;
	}

	public CodeUtils(Integer code, Object paramObject) {
		super();
		this.code = code;
		this.paramObject = paramObject;
	}

	public CodeUtils(Integer code, Object pageObject, Object paramObject) {
		super();
		this.code = code;
		this.paramObject = paramObject;
		this.pageObject = pageObject;
	}

	public CodeUtils(Integer code, List<?> param, Object paramObject, Object pageObject) {
		super();
		this.code = code;
		this.paramList = param;
		this.paramObject = paramObject;
		this.pageObject = pageObject;
	}

	public Object getParamObject() {
		return paramObject;
	}
	public void setParamObject(Object paramObject) {
		this.paramObject = paramObject;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public List<?> getParamList() {
		return paramList;
	}
	public void setParamList(List<?> param) {
		this.paramList = param;
	}
	public Object getPageObject() {
		return pageObject;
	}
	public void setPageObject(Object pageObject) {
		this.pageObject = pageObject;
	}
}
