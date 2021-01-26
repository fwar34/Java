package mapper;
import pojo.StudentPojo;

public interface StudentMapper {
    public StudentPojo getStudentPojo(Long id);
    public int insertStudentPojo(StudentPojo studentPojo);
    public int deleteStudentPojo(Long id);
}
