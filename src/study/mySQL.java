package study;

import java.sql.SQLException;

/**
 * 该接口用于实现java操作mySQL
 */
public interface mySQL {
    /**
     * 初始化数据库链接并导入数据
     */
    void start();

    /**
     * 关闭数据库并清楚数据
     */
    void stop();
    /**
     * 为数据库表格插入指定名称和内容的数据
     * @param column_name 要加入数据的名称
     * @param column_type 要加入数据的内容
     * @throws SQLException SQL错误子类中统一处理
     */
    void insertValue(String column_name,int column_type) throws SQLException;
    /**
     * 传入指定数据的名称和内容，返回同一个数据的另一个指定数据名称的内容。
     * @param column_name1 数据名称
     * @param column_type 数据内容
     * @param column_name2 要查询的数据名称
     * @return 查询数据结果
     * @throws SQLException SQL错误子类中统一处理
     */
    String getType(String column_name1,String column_type,String column_name2) throws SQLException;
    /**
     * 传入指定数据的名称和int型内容，将指定名称的内容改为指定内容
     * @param column_name1 数据名称
     * @param column_type1 数据内容
     * @param column_name2 要更改的数据名称
     * @param column_type2 要更改的数据内容
     * @throws SQLException SQL错误子类中统一处理
     */
    void updateValue(String column_name1,int column_type1,String column_name2,String column_type2) throws SQLException;
}
