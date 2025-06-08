CREATE DATABASE micro_headlines;
USE micro_headlines;

-- 用户表
CREATE TABLE tb_user (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         username VARCHAR(50) NOT NULL UNIQUE,
                         password VARCHAR(100) NOT NULL,
                         create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         is_deleted TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除标记(0:未删除,1:已删除)'
);

-- 文章表
CREATE TABLE tb_article (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            user_id BIGINT NOT NULL,
                            content TEXT NOT NULL,
                            create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            is_deleted TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除标记(0:未删除,1:已删除)',
                            FOREIGN KEY (user_id) REFERENCES tb_user(id)
);
