package top.sboxm.notebook.utils;

/**
 * 响应工厂
 */
public class ResultFactory {

    /**
     * 创建成功响应
     *
     * @param <T> 数据类型
     * @return 成功响应
     */
    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null, true);
    }

    /**
     * 创建带数据的成功响应
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return 成功响应
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data, true);
    }

    /**
     * 创建带消息的成功响应
     *
     * @param message 成功消息
     * @param <T>     数据类型
     * @return 成功响应
     */
    public static <T> Result<T> success(String message) {
        return new Result<>(ResultCode.SUCCESS.getCode(), message, null, true);
    }

    /**
     * 创建带数据和消息的成功响应
     *
     * @param data    响应数据
     * @param message 成功消息
     * @param <T>     数据类型
     * @return 成功响应
     */
    public static <T> Result<T> success(T data, String message) {
        return new Result<>(ResultCode.SUCCESS.getCode(), message, data, true);
    }

    /**
     * 创建失败响应
     *
     * @param resultCode 错误码枚举
     * @param <T>        数据类型
     * @return 失败响应
     */
    public static <T> Result<T> fail(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), null, false);
    }

    /**
     * 创建带自定义消息的失败响应
     *
     * @param resultCode 错误码枚举
     * @param message    错误消息
     * @param <T>        数据类型
     * @return 失败响应
     */
    public static <T> Result<T> fail(ResultCode resultCode, String message) {
        return new Result<>(resultCode.getCode(), message, null, false);
    }

    /**
     * 创建带数据的失败响应
     *
     * @param resultCode 错误码枚举
     * @param data       响应数据
     * @param <T>        数据类型
     * @return 失败响应
     */
    public static <T> Result<T> fail(ResultCode resultCode, T data) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), data, false);
    }

    /**
     * 创建带数据和自定义消息的失败响应
     *
     * @param resultCode 错误码枚举
     * @param data       响应数据
     * @param message    错误消息
     * @param <T>        数据类型
     * @return 失败响应
     */
    public static <T> Result<T> fail(ResultCode resultCode, T data, String message) {
        return new Result<>(resultCode.getCode(), message, data, false);
    }

    /**
     * 创建自定义响应
     *
     * @param code    状态码
     * @param message 响应消息
     * @param data    响应数据
     * @param success 是否成功
     * @param <T>     数据类型
     * @return 自定义响应
     */
    public static <T> Result<T> custom(Integer code, String message, T data, Boolean success) {
        return new Result<>(code, message, data, success);
    }
}