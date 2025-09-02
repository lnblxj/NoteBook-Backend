package top.sboxm.notebook.pojo.vo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 笔记vo
 */
@Data
public class NoteVO {

    /**
     * 笔记ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 笔记标题
     */
    private String title;

    /**
     * 笔记内容
     */
    private String content;

    /**
     * 状态：draft-草稿, published-已发布
     */
    private String status;

    /**
     * 创建时间
     */
    private Timestamp createdAt;

    /**
     * 更新时间
     */
    private Timestamp updatedAt;
}