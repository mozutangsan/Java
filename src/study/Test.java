package study;

public class Test extends ConnectSQL{
    static{
        JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
        DB_URL="jdbc:mysql://localhost:3306/student?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        USER="root";
        PASS="";
        TABLE="websites";
    }
    public static void main(String[] args){
        Test a=new Test();
        a.main();
    }
}
