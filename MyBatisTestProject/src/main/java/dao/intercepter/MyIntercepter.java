package dao.intercepter;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.util.Properties;

import static com.github.pagehelper.util.MetaObjectWithReflectCache.DEFAULT_OBJECT_WRAPPER_FACTORY;
import static com.github.pagehelper.util.MetaObjectWithReflectCache.DEFAULT_REFLECTOR_FACTORY;
import static org.apache.ibatis.reflection.SystemMetaObject.DEFAULT_OBJECT_FACTORY;

/**
 * mybatis 注入器，可用于分表
 * 参考文档: http://www.mybatis.org/mybatis-3/configuration.html; http://www.voidcn.com/article/p-vnsktyps-es.html
 */
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
)
})
public class MyIntercepter implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(MyIntercepter.class);


    private Properties myProperties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();
        // 获取真正进行执行的SQL
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);

        Object parameterObject = metaStatementHandler.getValue("delegate.boundSql.parameterObject");
        BoundSql boundSQL = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        String originSQL = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

        if (!StringUtils.isEmpty(originSQL)) {
            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
            String id = mappedStatement.getId();
            String className = id.substring(0, id.lastIndexOf("."));
            Class classObj = Class.forName(className);
            TableSharder sharder = (TableSharder) classObj.getAnnotation(TableSharder.class);

            if (sharder != null) {
                // 这里进行分表的相关操作
                logger.info("[MyIntercepter] 分表完成！");
            }
        }

        return result;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        this.myProperties = properties;
    }
}
