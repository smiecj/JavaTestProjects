package com.lifeng.school.dao.intercepter;

import org.apache.ibatis.mapping.MappedStatement;

/**
 * @Author shuaifeng
 * @Since 2019/8/10
 * 对不同类型的查询条件解析器
 */
public class MyStatementResolver {

    /**
     * 通过传参返回查询的主键条件
     * @param mappedStatement
     * @param parameter
     * @param originSQL
     * @return
     */
    public static String getPrimaryKeyByStatement(MappedStatement mappedStatement, Object parameter, String originSQL) {
        return "";
    }

}
