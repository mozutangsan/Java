package study;

import java.sql.SQLException;

/**
 * 该接口用于实现java操作mySQL
 */
public interface mySQL {
    /**
     * 为数据库表格插入指定名称和内容的数据
     * @param column_name
     * @param column_type
     * @throws SQLException
     */
    public void insertValue(String column_name,int column_type) throws SQLException;
    /**
     * 传入指定数据的名称和内容，返回同一个数据的另一个指定数据名称的内容。
     * @param column_name1
     * @param column_type
     * @param column_name2
     * @return 查询数据结果
     * @throws SQLException
     */
    public String getType(String column_name1,String column_type,String column_name2) throws SQLException;
    /**
     * 传入指定数据的名称和int型内容，将指定名称的内容改为指定内容
     * @param column_name1
     * @param column_type1
     * @param column_name2
     * @param column_type2
     * @throws SQLException
     */
    public void updateValue(String column_name1,int column_type1,String column_name2,String column_type2) throws SQLException;
}
