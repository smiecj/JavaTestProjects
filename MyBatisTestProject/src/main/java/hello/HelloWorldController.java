package hello;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import com.alibaba.druid.pool.DruidDataSource;
import dao.mapper.StudentMapper;
import dao.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;

@Controller
public class HelloWorldController {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private DruidDataSource dataSource;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/hello-world")
    @ResponseBody
    public Greeting sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    // 测试查询数据
    @GetMapping(value = "/testselect")
    @ResponseBody
    public Student testSelect(@RequestParam(name = "id", required = false, defaultValue = "1") String id) {
        return studentMapper.getStudentsByPrimaryKey(Long.valueOf(id)).get(0);
    }

    // 测试重置数据源
    @GetMapping(value = "/datasourcechange")
    @ResponseBody
    public Student testChangeDataSource() throws SQLException {
        if (dataSource.getUrl().contains("localhost")) {
            dataSource.setUrl("jdbc:mysql://192.168.99.100:30026/school?allowMultiQueries=true");
        }
        else {
            dataSource.setUrl("jdbc:mysql://192.168.99.100:30026/school?allowMultiQueries=true");
        }
        dataSource.restart();
    }
}
