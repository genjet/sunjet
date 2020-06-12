package com.sunjet.front.common.payload.response;

public class ApiResponse {
	private Integer code = 20000;
	private String message = "";
	private Object data ;
	
	/***
     * 过期
     *
     * @param message:
     * @return: com.zhengqing.modules.common.dto.output.ApiResponse
     */
	public static ApiResponse unLogin(String message){
		 return new ApiResponse(ResultCode.UN_LOGIN.getCode(), message, null);
	}
	
    public static ApiResponse expired(String message) {
        return new ApiResponse(ResultCode.EXPIRED_TOKEN.getCode(), message, null);
    }

    public static ApiResponse fail(String message) {
        return new ApiResponse(ResultCode.FAILURE.getCode(), message, null);
    }

    /***
     * 自定义错误返回码
     *
     * @param code
     * @param message:
     * @return: com.zhengqing.modules.common.dto.output.ApiResponse
     */
    public static ApiResponse fail(Integer code, String message) {
        return new ApiResponse(code, message, null);
    }

    public static ApiResponse ok(String message) {
        return new ApiResponse(ResultCode.SUCCESS.getCode(), message, null);
    }

    public static ApiResponse ok() {
        return new ApiResponse(ResultCode.SUCCESS.getCode(), "OK", null);
    }

    public static ApiResponse build(Integer code, String msg, Object data) {
        return new ApiResponse(ResultCode.SUCCESS.getCode(), msg, data);
    }

    public static ApiResponse ok(String message, Object data) {
        return new ApiResponse(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 自定义返回码
     */
    public static ApiResponse ok(Integer code, String message) {
        return new ApiResponse(code, message);
    }

    /**
     * 自定义
     *
     * @param code：验证码
     * @param message：返回消息内容
     * @param data：返回数据
     * @return: com.zhengqing.modules.common.dto.output.ApiResponse
     */
    public static ApiResponse ok(Integer code, String message, Object data) {
        return new ApiResponse(code, message, data);
    }

    public ApiResponse() { }

    public static ApiResponse build(Integer code, String msg) {
        return new ApiResponse(code, msg, null);
    }

    public ApiResponse(Integer code, String msg, Object data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public ApiResponse(Object data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = "OK";
        this.data = data;
    }

    public ApiResponse(String message) {
        this(ResultCode.SUCCESS.getCode(), message, null);
    }

    public ApiResponse(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public ApiResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

	
	
	public enum ResultCode {
	    //成功
	    SUCCESS( 20000, "SUCCESS" ),
	    //失败
	    FAILURE( 40000, "FAILURE" ),
	    // 未登录
	    UN_LOGIN( 40100, "未登录" ),
	    EXPIRED_TOKEN(50014, "token 過期"),
	    //未认证（签名错误、token错误）
	    UNAUTHORIZED( 40300, "未认证或Token失效" ),
	    //未通过认证
	    USER_UNAUTHORIZED( 40200, "用户名或密码不正确" ),
	    //接口不存在
	    NOT_FOUND( 40400, "接口不存在" ),
	    //服务器内部错误
	    INTERNAL_SERVER_ERROR( 50000, "服务器内部错误" );

	    private int code;
	    private String desc;

	    ResultCode(int code, String desc) {
	        this.code = code;
	        this.desc = desc;
	    }

	    public int getCode() {
	        return code;
	    }

	    public void setCode(int code) {
	        this.code = code;
	    }

	    public String getDesc() {
	        return desc;
	    }

	    public void setDesc(String desc) {
	        this.desc = desc;
	    }
	}

}
