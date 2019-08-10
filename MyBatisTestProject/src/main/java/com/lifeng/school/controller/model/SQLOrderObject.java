package com.lifeng.school.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author shuaifeng
 * @Since 2019/8/10
 * 分页参数，后续通过SpringCloud接口文件自动生成
 */
@Data
@AllArgsConstructor
public class SQLOrderObject {

    int start;

    int limit;

}
