package dao.model;

import lombok.Data;

@Data
public class Student {

    private long id;

    private String name;

    private int grade;

    private long classId;

    private String extendContent;

}
