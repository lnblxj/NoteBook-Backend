package top.sboxm.notebook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.sboxm.notebook.mapper.UserMapper;
import top.sboxm.notebook.pojo.dto.LoginDTO;
import top.sboxm.notebook.pojo.dto.RegisterDTO;
import top.sboxm.notebook.pojo.po.User;
import top.sboxm.notebook.pojo.vo.UserVO;
import top.sboxm.notebook.service.AuthService;
import top.sboxm.notebook.utils.JwtUtils;
import top.sboxm.notebook.utils.Result;
import top.sboxm.notebook.utils.ResultCode;
import top.sboxm.notebook.utils.ResultFactory;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Result<UserVO> login(LoginDTO loginDTO) {
        try {
            // 创建令牌
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUsername, loginDTO.getUsername());
            User user = userMapper.selectOne(queryWrapper);

            // 生成JWT令牌
            String token = jwtUtils.generateToken(user.getId(), user.getUsername());

            UserVO userVO = new UserVO();
            userVO.setId(user.getId());
            userVO.setUsername(user.getUsername());
            userVO.setEmail(user.getEmail());
            userVO.setToken(token);

            return ResultFactory.success(userVO, "登录成功");
        } catch (Exception e) {
            return ResultFactory.fail(ResultCode.USER_LOGIN_ERROR, "用户名或密码错误");
        }
    }

    @Override
    public Result<UserVO> register(RegisterDTO registerDTO) {

        //前置校验
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            return ResultFactory.fail(ResultCode.PARAM_IS_INVALID, "两次输入的密码不一致");
        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, registerDTO.getUsername());
        if (userMapper.selectCount(queryWrapper) > 0) {
            return ResultFactory.fail(ResultCode.USER_HAS_EXISTED, "用户名已存在");
        }

        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, registerDTO.getEmail());
        if (userMapper.selectCount(queryWrapper) > 0) {
            return ResultFactory.fail(ResultCode.USER_HAS_EXISTED, "邮箱已被注册");
        }

        // 创建账号
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());

        userMapper.insert(user);

        String token = jwtUtils.generateToken(user.getId(), user.getUsername());

        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setEmail(user.getEmail());
        userVO.setToken(token);

        return ResultFactory.success(userVO, "注册成功");
    }
}