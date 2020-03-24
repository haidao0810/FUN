package com.example.bf.kf.net.response;


public abstract class AjaxResponse{

	/**
	 * SerialVersionUID
	 */
	public static final long serialVersionUID = 1751205926949481362L;
	
	public static final int S_SUCCESS = 200;
	//数据为空
	public static final int S_FAILED = 500;

	private int status;
	private String message;
	//这个字段是用非200 有data数据返回时添加的
	private String otherMessage;


    public AjaxResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOtherMessage() {
		return otherMessage;
	}

	public void setOtherMessage(String otherMessage) {
		this.otherMessage = otherMessage;
	}
	//protected abstract T excute(String json) throws AndroidServerException;

}
