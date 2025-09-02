package top.sboxm.notebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.sboxm.notebook.pojo.dto.LoginDTO;
import top.sboxm.notebook.pojo.dto.RegisterDTO;
import top.sboxm.notebook.pojo.vo.UserVO;
import top.sboxm.notebook.service.AuthService;
import top.sboxm.notebook.utils.Result;

/**
 * 认证
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 用户登录
     *
     * @param loginDTO 登录信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody @Validated LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    /**
     * 用户注册
     *
     * @param registerDTO 注册信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<UserVO> register(@RequestBody @Validated RegisterDTO registerDTO) {
        return authService.register(registerDTO);
    }
}