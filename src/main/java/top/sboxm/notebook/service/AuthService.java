package top.sboxm.notebook.service;

import top.sboxm.notebook.pojo.dto.LoginDTO;
import top.sboxm.notebook.pojo.dto.RegisterDTO;
import top.sboxm.notebook.pojo.vo.UserVO;
import top.sboxm.notebook.utils.Result;

/**
 * 认证服务
 */
public interface AuthService {

    /**
     * 用户登录
     *
     * @param loginDTO 登录信息
     * @return 登录结果
     */
    Result<UserVO> login(LoginDTO loginDTO);

    /**
     * 用户注册
     *
     * @param registerDTO 注册信息
     * @return 注册结果
     */
    Result<UserVO> register(RegisterDTO registerDTO);
}