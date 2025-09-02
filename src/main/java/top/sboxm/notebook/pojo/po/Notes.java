package top.sboxm.notebook.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 笔记po
 */
@Data
@Accessors(chain = true)
@TableName("notes")
@EqualsAndHashCode(callSuper = false)
public class Notes implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID，外键
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 笔记标题
     */
    @TableField("title")
    private String title;

    /**
     * 笔记内容
     */
    @TableField("content")
    private String content;

    /**
     * 状态：draft-草稿, published-已发布
     */
    @TableField("status")
    private String status;

    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Timestamp createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private Timestamp updatedAt;

    /**
     * 软删除时间
     */
    @TableField("deleted_at")
    private Timestamp deletedAt;

}