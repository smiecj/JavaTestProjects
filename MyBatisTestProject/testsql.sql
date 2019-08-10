-- create database school;
# 基本表设计 -- 学校

# 创建表
## Student
create table Student (
    id BIGINT PRIMARY KEY auto_increment,
		name varchar(128) DEFAULT "",
		sex VARCHAR(10) DEFAULT "",
		class_id BIGINT DEFAULT 0 COMMENT '班级id，刚注册的新生，id默认为0',
		grade INT DEFAULT 0 COMMENT '年级，刚注册的新生默认为0。注意学生升班之后，grade和class_id都要更新',
		extend_content LONGTEXT DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1;

insert into Student(name, sex, class_id, grade, extend_content) VALUES("高木", "girl", 1, 1, "{}");

create table Class (
    id BIGINT PRIMARY KEY auto_increment,
		name varchar(128) DEFAULT "" COMMENT '班级名称，格式为：n年级-m班',
		grade int DEFAULT 0 COMMENT '年级，班级也需要保存一份，但是基本不做修改',
		KEY(name)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1;

insert into Class(name, grade) VALUES("一年级一班", 1);

## 后续放到业务整理中

select * from Class c left join Student s on c.id = s.class_id and c.name = '一年级一班';

### 分表
create table Student_1 (
    id BIGINT PRIMARY KEY auto_increment,
		name varchar(128) DEFAULT "",
		sex VARCHAR(10) DEFAULT "",
		class_id BIGINT DEFAULT 0 COMMENT '班级id，刚注册的新生，id默认为0',
		grade INT DEFAULT 0 COMMENT '年级，刚注册的新生默认为0。注意学生升班之后，grade和class_id都要更新',
		extend_content LONGTEXT DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

create table Student_2 (
    id BIGINT PRIMARY KEY auto_increment,
		name varchar(128) DEFAULT "",
		sex VARCHAR(10) DEFAULT "",
		class_id BIGINT DEFAULT 0 COMMENT '班级id，刚注册的新生，id默认为0',
		grade INT DEFAULT 0 COMMENT '年级，刚注册的新生默认为0。注意学生升班之后，grade和class_id都要更新',
		extend_content LONGTEXT DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

create table Student_3 (
    id BIGINT PRIMARY KEY auto_increment,
		name varchar(128) DEFAULT "",
		sex VARCHAR(10) DEFAULT "",
		class_id BIGINT DEFAULT 0 COMMENT '班级id，刚注册的新生，id默认为0',
		grade INT DEFAULT 0 COMMENT '年级，刚注册的新生默认为0。注意学生升班之后，grade和class_id都要更新',
		extend_content LONGTEXT DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

## teacher

## class

## subject

## test
### 这个肯定要进行分表，按照grade
### subject id, grade, student id, result

## 关联方式: class id
-- class name 必须是非空唯一的

# 后续分表设计
## 学生：按照grade 进行分表

# 非关系型数据
## 饭堂每天的消费记录

# 小型查询
## 按照班级名字查询所有学生

# 经常涉及的大查询
## 查询整个年级所有的男生

## 查询整个年级所有高三学生

## 查询某个学生近一周的消费记录

## 查询某个学生所有考试的成绩记录