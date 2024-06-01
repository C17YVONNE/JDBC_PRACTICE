package jp.dcnet.www;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PersonDao {

	// PostgreSQL数据库连接信息
	static final String DB_URL = "jdbc:postgresql://localhost:5432/HospitalSys";
	static final String USER = "postgres";
	static final String PASS = "postgres";

	public void addPerson(List<PersonObj> list) {

		Connection conn = null;
		PreparedStatement stmt = null;

		// 注册 JDBC 驱动
		try {
			Class.forName("org.postgresql.Driver");

			// 打开一个连接
			System.out.println("连接数据库...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			for (PersonObj obj : list) {
				// SQL 插入语句
				String sql = "INSERT INTO person (name, age, skill) VALUES (?, ?, ?)";

				// 创建 PreparedStatement 对象
				stmt = conn.prepareStatement(sql);

				// 设置插入的值
				stmt.setString(1, obj.getName());
				stmt.setInt(2, obj.getAge());
				stmt.setString(3, obj.getSkill());
				
				// 执行插入操作
				int rowsAffected = stmt.executeUpdate();

				if (rowsAffected > 0) {
					System.out.println("数据插入成功！");
				} else {
					System.out.println("数据插入失败！");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}