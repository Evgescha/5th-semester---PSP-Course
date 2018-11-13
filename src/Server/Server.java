package Server;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Класс, реализующий логику сервера
 * 
 * @author Yauhenii
 *
 */
public class Server {
	/**
	 * Спислк подключенных клиентов
	 */
	static LinkedList<Serverss> serverList = new LinkedList<>();
	/**
	 * Счетчик ИД, постоянно новый для каждого клиента
	 */
	static int id = 0;
	/**
	 * Сканнер чтения
	 */
	Scanner sc = new Scanner(System.in);
	/**
	 * Порт для работы с Сервером
	 */
	static int portServ = 8080;
	/**
	 * Сокет для работы с сервером
	 */
	static ServerSocket server;

	/**
	 * Метод запуска главной формы *
	 * 
	 * @param args
	 *            входная строка
	 */
	public static void main(String[] args) {
		System.out.println("Добро пожаловать на сервер.");
		// Получение текущего IP
		InetAddress IP;
		try {
			IP = InetAddress.getLocalHost();
			System.out.println("IP адрес системы - " + IP.getHostAddress());
		} catch (UnknownHostException e1) {
			System.out.println("Ошибка определения IP");
			e1.printStackTrace();
		}

		// старт сервера
		try {
			server = new ServerSocket(portServ);
		} catch (IOException e1) {
			System.out.println("Ошибка создания СокетСервера");
			e1.printStackTrace();
			return;
		}

		System.out.println("Сервер запустился");

		try {
			// цикл ожидания клиентов
			while (true) {
				// Блокируется до возникновения нового соединения:
				Socket socket = server.accept();
				// добавляем клиента в базу и даем ему свой индивидуальный ид
				serverList.add(new Serverss(socket, id)); // добавить новое соединенние в список
				id++;
				System.out.println("Подключился новый клиент. Его ид - " + (id-1));
			}

		} catch (IOException e1) {
			System.out.println("Ошибка Подключения клиента и занесения его в базу");
			e1.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				System.out.println("Ошибка Закрытия сокета сервера");
				e.printStackTrace();
			}
		}

	}
}

/**
 * Класс, реализующий общение с клиентами
 * 
 * @author Yauhenii
 *
 */
class Serverss extends Thread {
	/**
	 * Входящие и исходящие строки
	 */
	String input, output;
	/**
	 * Чтение из буфера
	 */
	BufferedReader in = null;
	/**
	 * Отправка данных клиентам
	 */
	PrintWriter out = null;
	/**
	 * Сокет клиента
	 */
	Socket fromclient = null;
	/**
	 * Личный номер клиента
	 */
	int ID = 0;
	/**
	 * Готов ли клиент играть
	 */
	boolean isReady = false;
	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public boolean isSearch() {
		return isSearch;
	}

	public void setSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}

	public boolean isGame() {
		return isGame;
	}

	public void setGame(boolean isGame) {
		this.isGame = isGame;
	}

	/**
	 * Ищет ли клиент игру
	 */
	boolean isSearch = false;
	/**
	 * Играет ли клиент в данный момент?
	 */
	boolean isGame = false;

	/**
	 * Конструктор класса клиента
	 * 
	 * @param fromclient
	 *            Сокет клиента
	 * @param ID
	 *            личный номер клиента
	 * @throws IOException
	 *             ошибка при чсоздании чтения и записи для клиента
	 */
	public Serverss(Socket fromclient, int ID) throws IOException {
		this.fromclient = fromclient;
		this.ID = ID;
		in = new BufferedReader(new InputStreamReader(fromclient.getInputStream()));
		out = new PrintWriter(fromclient.getOutputStream(), true);
		// BufferedReader inu = new BufferedReader(new InputStreamReader(System.in));
		start();
	}

	/**
	 * Метод чтения занных со стороны клиента
	 */
	@Override
	public void run() {

		try {
			while (true) {
				input = in.readLine();
				System.out.println("В чате пишут: " + input);
			}
		} catch (IOException e) {
			System.out.println("Общение скончалось(((");
			// e.printStackTrace();
		}

	}

}
