package top.sboxm.notebook.service;

import top.sboxm.notebook.pojo.dto.NoteDTO;
import top.sboxm.notebook.pojo.vo.NoteVO;
import top.sboxm.notebook.utils.Result;

import java.util.List;

/**
 * 笔记服务
 */
public interface NoteService {

    /**
     * 获取所有笔记列表
     *
     * @return 笔记列表
     */
    Result<List<NoteVO>> listNotes();

    /**
     * 根据ID获取笔记详情
     *
     * @param id 笔记ID
     * @return 笔记详情
     */
    Result<NoteVO> getNoteById(Long id);

    /**
     * 创建笔记
     *
     * @param noteDTO 笔记信息
     * @return 创建结果
     */
    Result<NoteVO> createNote(NoteDTO noteDTO);

    /**
     * 删除笔记
     *
     * @param id 笔记ID
     * @return 删除结果
     */
    Result<Void> deleteNote(Long id);
}