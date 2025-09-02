package top.sboxm.notebook.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 笔记dto
 */
@Data
public class NoteDTO {

    /**
     * 笔记标题
     */
    @NotBlank(message = "标题不能为空")
    @Size(max = 100, message = "标题长度不能超过100个字符")
    private String title;

    /**
     * 笔记内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 状态：draft-草稿, published-已发布
     */
    private String status = "published";
}