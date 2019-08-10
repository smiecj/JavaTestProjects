package com.lifeng.school.controller;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import com.alibaba.druid.pool.DruidDataSource;
import com.lifeng.school.dao.model.Student;
import com.lifeng.school.dao.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mybatis")
public class HelloWorldController {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private DruidDataSource dataSource;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

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
    public String testChangeDataSource() throws SQLException {
        logger.info("[testChangeDataSource] 开始切换数据源！");
        dataSource.restart();
        if (dataSource.getUrl().contains("localhost")) {
            dataSource.setUrl("jdbc:mysql://192.168.99.100:30026/school?allowMultiQueries=TRUE&serverTimezone=UTC&characterEncoding=UTF-8");
            dataSource.setUsername("root");
            dataSource.setPassword("lifeng_mysql%123");
        }
        else {
            dataSource.setUrl("jdbc:mysql://localhost:3307/school?allowMultiQueries=TRUE&serverTimezone=UTC&characterEncoding=UTF-8");
            dataSource.setUsername("root");
            dataSource.setPassword("shuaifeng123");
        }
        dataSource.init();
        logger.info("[testChangeDataSource] 切换数据源完成！");
        return "切换数据源成功！现在是：" + (dataSource.getUrl().contains("localhost") ? "本地" : "远程");
    }
}
