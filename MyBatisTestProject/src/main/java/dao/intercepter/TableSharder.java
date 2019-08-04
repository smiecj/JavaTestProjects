package dao.intercepter;

/**
 * @Author shuaifeng
 * @Since 2019/8/4
 * 分表相关操作，以下的参数主要是为了进行分表的时候方便查看参数
 */
public @interface TableSharder {

    public String tableName();

    public String shardType();

    public String shardBy();
}
