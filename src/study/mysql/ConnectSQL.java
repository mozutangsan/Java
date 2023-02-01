package study.mysql;

import java.sql.*;
import java.util.logging.Logger;

/**
 * 该类用于实现数据库操作。
 * @author 模组唐三
 * @version jdk1.8.0
 */
public abstract class ConnectSQL implements mySQL {
    //在子类中必须给这些变量赋值
    static String JDBC_DRIVER;
    static String DB_URL;
    static String USER;
    static String PASS;
    static String TABLE;
    private static Connection conn=null;
    private static Statement stmt=null;
    private static final Logger logger=Logger.getLogger(String.valueOf(ConnectSQL.class));

    @Override
    public void start() {
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            logger.info("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            logger.info("实例化Statement对象...");
            stmt = conn.createStatement();
        }catch (SQLException es){
            //处理SQL错误
            es.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        JDBC_DRIVER=DB_URL=USER=PASS=TABLE=null;
        logger.info("数据已清除");
        //关闭资源
        try{
            if(stmt!=null) stmt.close();
        }catch(SQLException se2){
        }
        try{
            if(conn!=null) conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
        logger.info("数据库资源已关闭");
    }

    @Override
    public String getType(String column_name1,String column_type,String column_name2) throws SQLException {
        String sql;
        sql = "SELECT "+column_name1+","+column_name2+" FROM "+TABLE;
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            if(rs.getString(column_name1).equals(column_type)){
                return rs.getString(column_name2);
            }
        }
        return null;
    }

    @Override
    public void updateValue(String column_name1, int column_type1, String column_name2, String column_type2) throws SQLException {
        String sql;
        sql="UPDATE "+TABLE+" SET "+column_name2+"='"+column_type2+"' where "+column_name1+"="+column_type1;
        stmt.execute(sql);
        String msg=column_name2+"已被更改为"+column_type2;
        logger.info(msg);
    }
    @Override
    public void insertValue(String column_name,int column_type) throws SQLException {
        String sql;
        sql="INSERT INTO "+TABLE+"("+column_name+")"+"\n"+"VALUE"+"\n"+"("+column_type+")";
        stmt.execute(sql);
        String msg=column_name+"已被创建并被赋值为"+column_type;
        logger.info(msg);
    }
}
