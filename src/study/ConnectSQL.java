package study;

import java.sql.*;

public abstract class ConnectSQL implements mySQL{
    //在子类中必须给这些变量赋值
    static String JDBC_DRIVER;
    static String DB_URL;
    static String USER;
    static String PASS;
    static String TABLE;
    private static Connection conn=null;
    private static Statement stmt=null;
    //初始化数据库
    public ConnectSQL() {
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
        }catch (SQLException es){
            //处理SQL错误
            es.printStackTrace();
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
        }catch (Exception e){
            e.printStackTrace();
        }
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
    public void detectValue() {

    }

    @Override
    public void insertValue() {

    }

    public void ma(){
        try {
            // 执行查询
            String sql;
            sql = "SELECT id, name, url FROM "+TABLE;
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch (SQLException es){
            es.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
