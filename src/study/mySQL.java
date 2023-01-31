package study;

import java.sql.SQLException;

public interface mySQL {
    public void insertValue(String column_name,int column_type) throws SQLException;
    public String getType(String column_name1,String column_type,String column_name2) throws SQLException;

    public void updateValue(String column_name1,int column_type1,String column_name2,String column_type2) throws SQLException;
}
