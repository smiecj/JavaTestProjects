package com.lifeng.school.dao.intercepter;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author shuaifeng
 * @Since 2019/8/10
 */
@Component
public class PrimaryKeySharder {

    // 这里保存所有分表的配置，后续可以放到zk里面去
    private final Map<String, Integer> tableNameToShardNumMap = new HashMap<>();

    public PrimaryKeySharder() {
        tableNameToShardNumMap.put("Student", 3);
    }

    public String getHashKey(String tableName, String primaryKey) {
        String shardTableName = tableName;
        int hashNum = primaryKey.hashCode() % tableNameToShardNumMap.get(tableName) + 1;
        shardTableName = shardTableName + "_" + hashNum;
        return shardTableName;
    }

}
