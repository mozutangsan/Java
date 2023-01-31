package study;

import java.sql.SQLException;

public interface mySQL {
    public void insertValue();
    public String getType(String column_name1,String column_type,String column_name2) throws SQLException;

    public void detectValue();
}
