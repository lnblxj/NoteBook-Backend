package top.sboxm.notebook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import top.sboxm.notebook.mapper.NoteMapper;
import top.sboxm.notebook.mapper.UserMapper;
import top.sboxm.notebook.pojo.dto.NoteDTO;
import top.sboxm.notebook.pojo.po.Notes;
import top.sboxm.notebook.pojo.po.User;
import top.sboxm.notebook.pojo.vo.NoteVO;
import top.sboxm.notebook.service.NoteService;
import top.sboxm.notebook.utils.Result;
import top.sboxm.notebook.utils.ResultCode;
import top.sboxm.notebook.utils.ResultFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取当前登录用户ID
     *
     * @return 用户ID
     */
    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        String username = authentication.getName();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        return user != null ? user.getId() : null;
    }

    @Override
    public Result<List<NoteVO>> listNotes() {
        Long userId = getCurrentUserId();
        if (userId == null) {
            return ResultFactory.fail(ResultCode.USER_NOT_LOGIN, "用户未登录");
        }

        LambdaQueryWrapper<Notes> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Notes::getUserId, userId)
                .isNull(Notes::getDeletedAt)
                .orderByDesc(Notes::getUpdatedAt);
        List<Notes> notesList = noteMapper.selectList(queryWrapper);

        List<NoteVO> noteVOList = new ArrayList<>();
        for (Notes note : notesList) {
            NoteVO noteVO = new NoteVO();
            BeanUtils.copyProperties(note, noteVO);
            noteVOList.add(noteVO);
        }

        return ResultFactory.success(noteVOList);
    }

    @Override
    public Result<NoteVO> getNoteById(Long id) {
        Long userId = getCurrentUserId();
        if (userId == null) {
            return ResultFactory.fail(ResultCode.USER_NOT_LOGIN, "用户未登录");
        }

        Notes note = noteMapper.selectById(id);
        if (note == null || note.getDeletedAt() != null) {
            return ResultFactory.fail(ResultCode.DATA_NOT_FOUND, "笔记不存在");
        }

        // 验证所有权
        if (!note.getUserId().equals(userId)) {
            return ResultFactory.fail(ResultCode.PERMISSION_DENIED, "无权访问该笔记");
        }

        NoteVO noteVO = new NoteVO();
        BeanUtils.copyProperties(note, noteVO);

        return ResultFactory.success(noteVO);
    }

    @Override
    public Result<NoteVO> createNote(NoteDTO noteDTO) {
        Long userId = getCurrentUserId();
        if (userId == null) {
            return ResultFactory.fail(ResultCode.USER_NOT_LOGIN, "用户未登录");
        }

        Notes note = new Notes();
        BeanUtils.copyProperties(noteDTO, note);
        note.setUserId(userId);
        noteMapper.insert(note);

        NoteVO noteVO = new NoteVO();
        BeanUtils.copyProperties(note, noteVO);

        return ResultFactory.success(noteVO, "创建成功");
    }

    @Override
    public Result<Void> deleteNote(Long id) {
        Long userId = getCurrentUserId();
        if (userId == null) {
            return ResultFactory.fail(ResultCode.USER_NOT_LOGIN, "用户未登录");
        }

        Notes note = noteMapper.selectById(id);
        if (note == null || note.getDeletedAt() != null) {
            return ResultFactory.fail(ResultCode.DATA_NOT_FOUND, "笔记不存在");
        }

        // 验证所有权
        if (!note.getUserId().equals(userId)) {
            return ResultFactory.fail(ResultCode.PERMISSION_DENIED, "无权删除该笔记");
        }

        noteMapper.deleteById(id);

        return ResultFactory.success("删除成功");
    }
}