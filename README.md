# myblog
使用Springboot+Thymeleaf制作个人博客

# 使用的技术
## 前端
* Jquery
* CSS框架[Semantic UI](https://semantic-ui.com/)
* [Markdown编辑器](https://pandao.github.io/editor.md/)
* 代码高亮[Prism](https://github.com/PrismJS/prism)
* 动画[animate.css](https://animate.style/)
* 目录生成[Tocbot](https://tscanlin.github.io/tocbot/)
* 音乐[Zplayer](https://gitee.com/supperzh/zplayer)
* 照片墙[Lightbox插件](https://github.com/JavaScript-Kit/jkresponsivegallery)

## 后端
* Springboot 2.4.2
* JDK 1.8
* Mybatis持久层框架
* Thymeleaf前端模板
* PageHelper分页插件
* MD5加密
* 阿里云服务器
* Mysql数据库

# 页面展示
## 前端页面
首页页面
![](https://github.com/PALMJJ/myblog/blob/master/images/1.PNG)
![](https://github.com/PALMJJ/myblog/blob/master/images/2.PNG)
![](https://github.com/PALMJJ/myblog/blob/master/images/3.PNG)

文章详情页面
![](https://github.com/PALMJJ/myblog/blob/master/images/19.PNG)

文章分类页面
![](https://github.com/PALMJJ/myblog/blob/master/images/4.PNG)


时间轴页面
![](https://github.com/PALMJJ/myblog/blob/master/images/5.PNG)

音乐盒页面
![](https://github.com/PALMJJ/myblog/blob/master/images/6.PNG)

留言板页面
![](https://github.com/PALMJJ/myblog/blob/master/images/7.PNG)

照片墙页面
![](https://github.com/PALMJJ/myblog/blob/master/images/8.PNG)

关于我页面
![](https://github.com/PALMJJ/myblog/blob/master/images/9.PNG)

搜索结果页面
![](https://github.com/PALMJJ/myblog/blob/master/images/10.PNG)

## 后端页面
后台登录页面
![](https://github.com/PALMJJ/myblog/blob/master/images/11.PNG)

后台主页
![](https://github.com/PALMJJ/myblog/blob/master/images/12.PNG)

文章管理页面
![](https://github.com/PALMJJ/myblog/blob/master/images/13.PNG)

分类管理页面
![](https://github.com/PALMJJ/myblog/blob/master/images/14.PNG)

相册管理页面
![](https://github.com/PALMJJ/myblog/blob/master/images/15.PNG)

新增文章页面
![](https://github.com/PALMJJ/myblog/blob/master/images/16.PNG)

新增分类页面
![](https://github.com/PALMJJ/myblog/blob/master/images/17.PNG)

相册增加页面
![](https://github.com/PALMJJ/myblog/blob/master/images/18.PNG)

# 数据库设计
## 数据库名
myblog

## 构建数据表语句
```sql
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `appreciation` BIT(1) NOT NULL,
  `commentabled` BIT(1) NOT NULL,
  `content` LONGTEXT CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` DATETIME(0) NULL DEFAULT NULL,
  `description` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `first_picture` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flag` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `published` BIT(1) NOT NULL,
  `recommend` BIT(1) NOT NULL,
  `share_statement` BIT(1) NOT NULL,
  `title` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` DATETIME(0) NULL DEFAULT NULL,
  `views` INT(11) NULL DEFAULT NULL,
  `type_id` BIGINT(20) NULL DEFAULT NULL,
  `user_id` BIGINT(20) NULL DEFAULT NULL,
  `comment_count` INT(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK292449gwg5yf7ocdlmswv9w4j`(`type_id`) USING BTREE,
  INDEX `FK8ky5rrsxh01nkhctmo7d48p82`(`user_id`) USING BTREE,
  CONSTRAINT `FK292449gwg5yf7ocdlmswv9w4j` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK8ky5rrsxh01nkhctmo7d48p82` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=62 CHARACTER SET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Dynamic;

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=58 CHARACTER SET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `avatar` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` DATETIME(0) NULL DEFAULT NULL,
  `email` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` INT(11) NULL DEFAULT NULL,
  `update_time` DATETIME(0) NULL DEFAULT NULL,
  `username` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 CHARACTER SET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Dynamic;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` DATETIME(0) NULL DEFAULT NULL,
  `blog_id` BIGINT(20) NULL DEFAULT NULL,
  `parent_comment_id` BIGINT(20) NULL DEFAULT NULL,
  `admin_comment` BIT(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 CHARACTER SET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Dynamic;

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` DATETIME(0) NULL DEFAULT NULL,
  `parent_message_id` BIGINT(20) NULL DEFAULT NULL,
  `admin_message` BIT(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 CHARACTER SET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Dynamic;

-- ----------------------------
-- Table structure for t_picture
-- ----------------------------
DROP TABLE IF EXISTS `t_picture`;
CREATE TABLE `t_picture` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `pictureaddress` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `picturedescription` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `picturename` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `picturetime` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 CHARACTER SET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Dynamic;

SET FOREIGN_KEY_CHECKS=1;
```

## 数据表存放数据示例
用户数据表：avatar表示头像，路径为resources资源路径下images文件夹中的文件，password存放使用MD5加密后的密码，比如密码为123456.加密后为图中所示
![](https://github.com/PALMJJ/myblog/blob/master/images/20.PNG)

相册数据表：这里只是样例，所用的图片也是资源路径下pictures文件夹中的文件，但通过后台上传的图片肯定不能通过已经压缩好的jar包上传，所以这时候一般使用图床，生成资源地址
![](https://github.com/PALMJJ/myblog/blob/master/images/21.PNG)
