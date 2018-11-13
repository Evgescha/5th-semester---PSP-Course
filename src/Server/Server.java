package Server;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * �����, ����������� ������ �������
 * 
 * @author Yauhenii
 *
 */
public class Server {
	/**
	 * ������ ������������ ��������
	 */
	static LinkedList<Serverss> serverList = new LinkedList<>();
	/**
	 * ������� ��, ��������� ����� ��� ������� �������
	 */
	static int id = 0;
	/**
	 * ������� ������
	 */
	Scanner sc = new Scanner(System.in);
	/**
	 * ���� ��� ������ � ��������
	 */
	static int portServ = 8080;
	/**
	 * ����� ��� ������ � ��������
	 */
	static ServerSocket server;

	/**
	 * ����� ������� ������� ����� *
	 * 
	 * @param args
	 *            ������� ������
	 */
	public static void main(String[] args) {
		System.out.println("����� ���������� �� ������.");
		// ��������� �������� IP
		InetAddress IP;
		try {
			IP = InetAddress.getLocalHost();
			System.out.println("IP ����� ������� - " + IP.getHostAddress());
		} catch (UnknownHostException e1) {
			System.out.println("������ ����������� IP");
			e1.printStackTrace();
		}

		// ����� �������
		try {
			server = new ServerSocket(portServ);
		} catch (IOException e1) {
			System.out.println("������ �������� ������������");
			e1.printStackTrace();
			return;
		}

		System.out.println("������ ����������");

		try {
			// ���� �������� ��������
			while (true) {
				// ����������� �� ������������� ������ ����������:
				Socket socket = server.accept();
				// ��������� ������� � ���� � ���� ��� ���� �������������� ��
				serverList.add(new Serverss(socket, id)); // �������� ����� ����������� � ������
				id++;
				System.out.println("����������� ����� ������. ��� �� - " + (id-1));
			}

		} catch (IOException e1) {
			System.out.println("������ ����������� ������� � ��������� ��� � ����");
			e1.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				System.out.println("������ �������� ������ �������");
				e.printStackTrace();
			}
		}

	}
}

/**
 * �����, ����������� ������� � ���������
 * 
 * @author Yauhenii
 *
 */
class Serverss extends Thread {
	/**
	 * �������� � ��������� ������
	 */
	String input, output;
	/**
	 * ������ �� ������
	 */
	BufferedReader in = null;
	/**
	 * �������� ������ ��������
	 */
	PrintWriter out = null;
	/**
	 * ����� �������
	 */
	Socket fromclient = null;
	/**
	 * ������ ����� �������
	 */
	int ID = 0;
	/**
	 * ����� �� ������ ������
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
	 * ���� �� ������ ����
	 */
	boolean isSearch = false;
	/**
	 * ������ �� ������ � ������ ������?
	 */
	boolean isGame = false;

	/**
	 * ����������� ������ �������
	 * 
	 * @param fromclient
	 *            ����� �������
	 * @param ID
	 *            ������ ����� �������
	 * @throws IOException
	 *             ������ ��� ��������� ������ � ������ ��� �������
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
	 * ����� ������ ������ �� ������� �������
	 */
	@Override
	public void run() {

		try {
			while (true) {
				input = in.readLine();
				System.out.println("� ���� �����: " + input);
			}
		} catch (IOException e) {
			System.out.println("������� ����������(((");
			// e.printStackTrace();
		}

	}

}
