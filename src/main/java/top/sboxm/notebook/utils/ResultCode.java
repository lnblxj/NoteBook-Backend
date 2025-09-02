package top.sboxm.notebook.utils;

/**
 * 响应状态码枚举类
 */
public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(200, "操作成功"),

    /* 参数错误：1000～1999 */
    PARAM_ERROR(1000, "参数错误"),
    PARAM_IS_INVALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    /* 用户错误：2000～2999 */
    USER_NOT_LOGIN(2000, "用户未登录"),
    USER_LOGIN_ERROR(2001, "账号或密码错误"),
    USER_ACCOUNT_FORBIDDEN(2002, "账号已被禁用"),
    USER_NOT_EXIST(2003, "用户不存在"),
    USER_HAS_EXISTED(2004, "用户已存在"),
    USER_REGISTER_ERROR(2005, "用户注册错误"),

    /* 业务错误：3000～3999 */
    BUSINESS_ERROR(3000, "业务异常"),
    SPECIFIED_RESOURCE_NOT_FOUND(3001, "指定资源不存在"),
    SPECIFIED_RESOURCE_ALREADY_EXIST(3002, "指定资源已存在"),
    OPERATION_NOT_ALLOWED(3003, "操作不允许"),

    /* 系统错误：4000～4999 */
    SYSTEM_INNER_ERROR(4000, "系统内部错误"),
    SYSTEM_BUSY(4001, "系统繁忙，请稍后重试"),

    /* 数据错误：5000～5999 */
    DATA_NOT_FOUND(5000, "数据不存在"),
    DATA_IS_WRONG(5001, "数据有误"),
    DATA_ALREADY_EXISTED(5002, "数据已存在"),
    DATA_TOO_MUCH(5003, "数据量过大"),

    /* 接口错误：6000～6999 */
    INTERFACE_INNER_INVOKE_ERROR(6000, "内部系统接口调用异常"),
    INTERFACE_OUTER_INVOKE_ERROR(6001, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(6002, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(6003, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(6004, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(6005, "接口负载过高"),

    /* 权限错误：7000～7999 */
    PERMISSION_NO_ACCESS(7000, "无访问权限"),
    PERMISSION_UNAUTHORIZED(7001, "未授权"),
    PERMISSION_EXPIRED(7002, "授权过期"),
    PERMISSION_DENIED(7003, "权限不足"),

    /* HTTP状态码 */
    HTTP_BAD_REQUEST(400, "错误的请求"),
    HTTP_UNAUTHORIZED(401, "未授权"),
    HTTP_FORBIDDEN(403, "禁止访问"),
    HTTP_NOT_FOUND(404, "资源不存在"),
    HTTP_METHOD_NOT_ALLOWED(405, "方法不允许"),
    HTTP_REQUEST_TIMEOUT(408, "请求超时"),
    HTTP_INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    HTTP_BAD_GATEWAY(502, "网关错误"),
    HTTP_SERVICE_UNAVAILABLE(503, "服务不可用"),
    HTTP_GATEWAY_TIMEOUT(504, "网关超时");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}