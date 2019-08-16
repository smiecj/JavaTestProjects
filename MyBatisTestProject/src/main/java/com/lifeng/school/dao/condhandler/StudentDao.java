package com.lifeng.school.dao.condhandler;

import com.lifeng.school.dao.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author shuaifeng
 * @Since 2019/8/10
 * 在查询SQL之前对查询条件进行处理者
 */
@Component
public class StudentDao {

    public List<Student> getStudentByMapParam(Map<String, Object> param) {
        // 组装分页参数
        return null;
    }

}
