package client;

import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import script.StartApp;

/**
 * Класс рабоыт с клиентом на стороне клиента
 * @author Yauhenii
 *
 */
public class Client {
	/**
	 * Чтение буфера с сервера
	 */
	BufferedReader in = null;
	/**
	 * Запись на сервер
	 */
	PrintWriter out = null;
	/**
	 * Имя пользователя
	 */
	String clName = "";
	/**
	 * Адресс сервера, которому подключаемся
	 */
	String addres = "127.0.0.1";

	// Метод чтения данных с сервера в отдельном потоке
	private class ReadMsg extends Thread {
		@Override
		public void run() {

			String str;
			try {
				while (true) {
					str = in.readLine(); // ждем сообщения с сервера
					System.out.println(str); // пишем сообщение с сервера на консоль
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
/**
 * Запуск Клиента
 * @throws IOException
 */
	private void Go() throws IOException {
		System.out.println("Добро подаловать в клиенты");

		InetAddress IP = InetAddress.getLocalHost();
		System.out.println("Ваш IP адрес (не сервера) - " + IP.getHostAddress());

		Socket fromserver = null;
		

		System.out.println("Подключение... ");
		try {
			fromserver = new Socket(addres, 8080);
			in = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
			out = new PrintWriter(fromserver.getOutputStream(), true);

			//new WriteMsg().start();
			new ReadMsg().start();
			StartApp.setStatus("Подключено");
			System.out.println("Подключено");
		} catch (Exception ex) {
			System.out.println("Ошибочка вышла... ");
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
