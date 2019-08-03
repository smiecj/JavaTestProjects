package dao.intercepter;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * mybatis 注入器，可用于分表
 * 参考文档: http://www.mybatis.org/mybatis-3/configuration.html
 *
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "prepare",
        args = {MappedStatement.class,Object.class}
)
})
public class MyIntercepter implements Interceptor {
    private Properties myProperties;
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();
        return result;
    }

    @Override
    public Object plugin(Object o) {
        return o;
    }

    @Override
    public void setProperties(Properties properties) {
        this.myProperties = properties;
    }
}
