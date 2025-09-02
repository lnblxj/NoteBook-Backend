package top.sboxm.notebook.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户vo
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 认证令牌
     */
    private String token;
}