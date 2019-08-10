package com.lifeng.school.dao.mapper;

import com.lifeng.school.dao.intercepter.TableSharder;
import com.lifeng.school.dao.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@TableSharder(shardBy = "id", shardType = "hash", tableName = "Student")
public interface StudentMapper {

    List<Student> getStudentsByPrimaryKey(Long id);

    List<Student> getAllStudentsByClassInfo(@Param("params") Map<String, Object> queryCondition);

}
