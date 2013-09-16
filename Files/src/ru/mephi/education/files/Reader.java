package ru.mephi.education.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Reader {

	private static File inFile;
	private static File outFile;
	private static FileInputStream fis;
	private static FileOutputStream fos;
	private static BufferedReader reader;
	private static BufferedWriter writer;
	private static Scanner scanner;

	public static void main(String[] args) {
		inFile = new File("group.csv");
		try {
			StdOutErrLog.tieSystemOutAndErrToLog();
			System.out.println("Read via BufferedReader");
			fis = new FileInputStream(inFile);
			reader = new BufferedReader(new InputStreamReader(fis));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}

			System.out.println("-------");
			System.out.println("Read via Scanner");
			scanner = new Scanner(inFile);
			while (scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
			scanner.close();

			System.out.println("-------");
			System.out.println("Read via Files utility");
			List<String> list = Files.readAllLines(inFile.toPath(),
					Charset.defaultCharset());
			Map<Integer, String> students = new TreeMap<>();
			for (String row : list) {
				System.out.println(row);
				String[] student = row.split(";");
				students.put(Integer.valueOf(student[1]), student[0]);
			}
			System.out.println("write to file");
			outFile = new File("out.txt");
			fos = new FileOutputStream(outFile);
			writer = new BufferedWriter(new OutputStreamWriter(fos));
			for (Entry<Integer, String> entry : students.entrySet()) {
				writer.append(entry.getKey().toString()).append("-")
						.append(entry.getValue());
				writer.newLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
				scanner.close();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}
}
