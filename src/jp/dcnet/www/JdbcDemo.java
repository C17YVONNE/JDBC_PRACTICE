package jp.dcnet.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JdbcDemo {

	public static void main(String[] args) {

		// 指定要读取的文本文件路径
		String filePath = "C:\\Users\\LqyCarl\\Desktop\\JavaLesson\\person.txt";

		BufferedReader reader = null;

		List<PersonObj> list = new ArrayList<>();

		// 创建一个 BufferedReader 实例以读取文件
		try {
			reader = new BufferedReader(new FileReader(filePath));
			// 用于保存每行的字符串
			String line;

			while ((line = reader.readLine()) != null) {
				// 确保行不为空
				if (!line.trim().isEmpty()) {
					// 使用逗号分隔行中的各部分
					String[] parts = line.split(",");
					// 确保分割后的数组长度足够
					if (parts.length >= 3) {
						PersonObj obj = new PersonObj();

						obj.setName(parts[0].trim());
						obj.setAge(Integer.parseInt(parts[1].trim()));
						obj.setSkill(parts[2].trim());
						list.add(obj);
					} else {
						System.err.println("Line format incorrect: " + line);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.err.println("Number format exception: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected error: " + e.getMessage());
		} finally {
			// 确保 BufferedReader 被关闭以释放资源
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		PersonService personService = new PersonService();
		personService.addPerson(list);
	}
}
