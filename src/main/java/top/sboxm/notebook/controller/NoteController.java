package top.sboxm.notebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.sboxm.notebook.pojo.dto.NoteDTO;
import top.sboxm.notebook.pojo.vo.NoteVO;
import top.sboxm.notebook.service.NoteService;
import top.sboxm.notebook.utils.Result;

import java.util.List;

/**
 * 笔记
 */
@RestController
@RequestMapping("/notes")
public class NoteController {
    
    @Autowired
    private NoteService noteService;
    
    /**
     * 获取所有笔记列表
     *
     * @return 笔记列表
     */
    @GetMapping
    public Result<List<NoteVO>> listNotes() {
        return noteService.listNotes();
    }
    
    /**
     * 根据ID获取笔记详情
     *
     * @param id 笔记ID
     * @return 笔记详情
     */
    @GetMapping("/{id}")
    public Result<NoteVO> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }
    
    /**
     * 创建笔记
     *
     * @param noteDTO 笔记信息
     * @return 创建结果
     */
    @PostMapping
    public Result<NoteVO> createNote(@RequestBody @Validated NoteDTO noteDTO) {
        return noteService.createNote(noteDTO);
    }
    
    /**
     * 删除笔记
     *
     * @param id 笔记ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteNote(@PathVariable Long id) {
        return noteService.deleteNote(id);
    }
}
