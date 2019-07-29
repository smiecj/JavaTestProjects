package dao.mapper;

import dao.model.Student;

import java.util.List;

public interface StudentMapper {

    List<Student> getStudentsByPrimaryKey(Long id);

}
