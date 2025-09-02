-- 创建用户表
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
                       password VARCHAR(255) NOT NULL COMMENT '密码（加密后）',
                       email VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
                       created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       INDEX idx_username (username),
                       INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 创建笔记表
CREATE TABLE notes (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_id BIGINT NOT NULL COMMENT '用户ID，外键',
                       title VARCHAR(200) NOT NULL COMMENT '笔记标题',
                       content TEXT COMMENT '笔记内容',
                       status ENUM('draft', 'published') NOT NULL DEFAULT 'draft' COMMENT '状态',
                       created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       deleted_at DATETIME DEFAULT NULL COMMENT '软删除时间',
                       FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                       INDEX idx_user_id (user_id),
                       INDEX idx_deleted_at (deleted_at),
                       INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='笔记表';