package jp.dcnet.www;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTest {

	public static void main(String[] args) {

		
		
		Connection connection = null;
        try {
            // 注册驱动程序
            Class.forName("org.postgresql.Driver");
            
            // 建立连接
            String url = "jdbc:postgresql://localhost:5432/HospitalSys";
            String username = "postgres";
            String password = "postgres";
            connection = DriverManager.getConnection(url, username, password);
            
            // 连接成功时执行的代码
            System.out.println("连接成功！");
            
        } catch (SQLException e) {
            // 处理SQL异常
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // 处理类未找到异常
            e.printStackTrace();
        } finally {
            // 关闭连接
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
