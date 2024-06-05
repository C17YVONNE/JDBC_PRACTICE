package jp.dcnet.www;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 指定されたログファイルに、不正ログイン情報(ログID=CAB001)のレコードカウントを集計する
 */
public class ErrorOutput {

	public static void main(String[] args) {
		String inputFilePath = "D:\\CYQJAVA\\Pleiades\\eclipse\\JavaIoInput.log";

		try {
			// ログファイルを読み込む
			List<String> logLines = Files.readAllLines(Paths.get(inputFilePath));
			// CAB001のレコードをフィルタリング
			List<String> CAB001Logs = logLines.stream()
					.filter(line -> line.contains("CAB001"))
					.collect(Collectors.toList());

			// 結果ファイルに書き込む
			String resultFileName = "JavaIoOutput";
			String outputFilePath = "D:\\CYQJAVA\\Pleiades\\eclipse\\JavaIoOutput.log";
			try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath))) {
				for (String log : CAB001Logs) {
					// タイムスタンプとloginIdを抽出
					String[] parts = log.split("\\t");
					String timestamp = parts[0];
					String loginId = log.split("loginId=")[1].trim();
					writer.write(timestamp + "," + loginId);
					writer.newLine();
				}
			}

			System.out.println("結果が " + resultFileName + " に書き込まれました。");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
