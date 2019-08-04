# 创建表

create table Student (
    id BIGINT PRIMARY KEY auto_increment,
		name varchar(128) DEFAULT "",
		sex VARCHAR(10) DEFAULT "",
		class_id BIGINT DEFAULT 0 COMMENT '班级id，刚注册的新生，id默认为0',
		grade INT DEFAULT 0 COMMENT '年级，刚注册的新生默认为0。注意学生升班之后，grade和class_id都要更新',
		extend_content LONGTEXT DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

insert into Student(name, sex, class_id, grade, extend_content) VALUES("高木", "girl", NULL, 1, "{}");

create table Class (
    id BIGINT PRIMARY KEY auto_increment,
		name varchar(128) DEFAULT "" COMMENT '班级名称，格式为：n年级-m班',
		grade int DEFAULT 0 COMMENT '年级，班级也需要保存一份，但是基本不做修改',
		KEY(name)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

# 后续放到业务整理中

select * from Class c left join Student s on c.id = s.class_id and c.name = 'xxx';