package study;

import java.sql.SQLException;

public class Test extends ConnectSQL{
    static{
        JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
        DB_URL="jdbc:mysql://localhost:3306/student?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        USER="root";
        PASS="";
        TABLE="websites";
        //使用构造方法
        new Test();
    }
}
