package dao.mapper;

import dao.intercepter.TableSharder;
import dao.model.Student;

import java.util.List;

@TableSharder(shardBy = "id", shardType = "123", tableName = "Student")
public interface StudentMapper {

    List<Student> getStudentsByPrimaryKey(Long id);

}
