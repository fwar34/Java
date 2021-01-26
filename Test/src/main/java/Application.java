import org.apache.ibatis.session.SqlSession;
import mapper.StudentMapper;
import pojo.StudentPojo;
import utils.SqlSessionFactoryUtils;

public class Application {
    public static void main(String[] args) {
        System.out.println("main run...");
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        //delete
        studentMapper.deleteStudentPojo((long)1);
        studentMapper.deleteStudentPojo((long)2);

        // insert
        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setId((long)1);
        studentPojo.setName("feng");
        studentPojo.setSex("man");
        studentMapper.insertStudentPojo(studentPojo);

        //query & update
        StudentPojo studentPojo1 = studentMapper.getStudentPojo((long)1);
        System.out.println(studentPojo1.getId() + " " + studentPojo1.getName());
        studentPojo1.setId((long)2);
        studentPojo1.setName("jing");
        studentPojo1.setSex("woman");
        studentMapper.insertStudentPojo(studentPojo1);

        StudentPojo studentPojo2 = studentMapper.getStudentPojo((long)2);
        System.out.println(studentPojo2.getId() + " " + studentPojo2.getName());

        sqlSession.commit();
        System.out.println("main stop...");
    }
}