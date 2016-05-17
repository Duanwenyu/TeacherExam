package cn.edu.qdu.exam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Teacher {
	static Teacher t = new Teacher();

	public static void main(String[] args) {
		try {
			t.outputResult();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 读取文档的方法
	public ArrayList<String> readTxt(String fileName) throws IOException {
		ArrayList<String> arraylist = new ArrayList<String>();
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String s = null;
		while ((s = in.readLine()) != null) { // *****
			arraylist.add(s);
		}
		in.close();// ****
		return arraylist;
	}

	// 选择文件的方法
	public File[] selectStuTxt() {
		String path = "F:\\Exam";
		File file = new File(path);
		File[] stuList = file.listFiles();
		return stuList;
	}

	// 比较答案和学生答案的方法
	public String compare(String string) throws IOException {
		String answer = "F:\\answer.txt";
		ArrayList<String> answerList = new ArrayList<String>();
		ArrayList<String> stuList = new ArrayList<String>();
		answerList = t.readTxt(answer);
		stuList = t.readTxt(string);
		char[] answerChar = null;
		char[] studentChar = null;
		int scoreNum = 0;
		// String name = t.getName(string);
		for (int i = 0; i < answerList.size(); i++) {
			answerChar = answerList.get(i).toString().toCharArray();
			studentChar = stuList.get(i).toString().toCharArray();
			for (int j = 0; j < answerChar.length; j++) {
				if (answerChar[j] == studentChar[j]) {
					scoreNum++;
				}
			}
		}
		String student ="    " + scoreNum * 4;
		return student;
	}

	// 得到文件名字的方法
	public String getName(String s) {
		int a = s.indexOf('.');
		int b = s.lastIndexOf('\\');
		StringBuffer sa = new StringBuffer(s);
		return sa.substring(b + 1, a);
	}

	// /////借鉴的方法
	// 输出到result文件的方法
	public void outputResult() throws IOException {
		BufferedWriter out = new BufferedWriter(
				new FileWriter("F:\\result.txt"));
		ArrayList<String> result = new ArrayList<String>();
		System.out.println(t.selectStuTxt().length);
		for (int j = 0; j < t.selectStuTxt().length; j++) {
			result.add(t.compare(t.selectStuTxt()[j].toString()));
		}
		System.out.println(result);
		for (String string : result) {
			out.write(string);
			out.newLine();
			out.flush();
		}
		out.close();
	}
}
