package client;

import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import script.StartApp;

/**
 * ����� ������ � �������� �� ������� �������
 * @author Yauhenii
 *
 */
public class Client {
	/**
	 * ������ ������ � �������
	 */
	BufferedReader in = null;
	/**
	 * ������ �� ������
	 */
	PrintWriter out = null;
	/**
	 * ��� ������������
	 */
	String clName = "";
	/**
	 * ������ �������, �������� ������������
	 */
	String addres = "127.0.0.1";

	// ����� ������ ������ � ������� � ��������� ������
	private class ReadMsg extends Thread {
		@Override
		public void run() {

			String str;
			try {
				while (true) {
					str = in.readLine(); // ���� ��������� � �������
					System.out.println(str); // ����� ��������� � ������� �� �������
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
/**
 * ������ �������
 * @throws IOException
 */
	private void Go() throws IOException {
		System.out.println("����� ���������� � �������");

		InetAddress IP = InetAddress.getLocalHost();
		System.out.println("��� IP ����� (�� �������) - " + IP.getHostAddress());

		Socket fromserver = null;
		

		System.out.println("�����������... ");
		try {
			fromserver = new Socket(addres, 8080);
			in = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
			out = new PrintWriter(fromserver.getOutputStream(), true);

			//new WriteMsg().start();
			new ReadMsg().start();
			StartApp.setStatus("����������");
			System.out.println("����������");
		} catch (Exception ex) {
			System.out.println("�������� �����... ");
			fromserver.close();
			in.close();
			out.close();
		}
	}

	public static void main(String[] args) throws IOException {
		Client cl = new Client();
		cl.Go();
	}
}
