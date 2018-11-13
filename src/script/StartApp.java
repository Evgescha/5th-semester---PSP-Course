package script;
import form.*;

/**
 * ����� ���������� ������� �� ������� �������
 * @author Yauhenii
 *
 */
public final class StartApp {
	/**
	 * ��� ���� ������������
	 */
	static int typeGame;
	public static int getTypeGame() {
		return typeGame;
	}
	public static void setTypeGame(int typeGame) {
		StartApp.typeGame = typeGame;
	}
	
	public static final int PC = 1;
	public static final int USER = 2;
	/**
	 * ��� ������������
	 */
	static String name = "";
	/**
	 * ������� �����
	 */
	static Main mainForm = new Main();
	/**
	 * ����� ���� � �������������\��
	 */
	static Game gameForm;
	/**
	 * ������ ��������� ����������� � �������
	 */
	static String Status = "�� ����������";
	
	/**
	 * ����� ��������� �������� �������
	 * @return ������
	 */
	public static String getStatus() {
		return Status;
	}
	/**
	 * ����� ��������� �������� �������
	 * @param status ����� ������� ������
	 */
	public static void setStatus(String status) {
		Status = status;
	}
	/**
	 * ����� ��������� ����� ������������
	 * @return ��� ������������
	 */
	public static String getName() {
		return name;
	}
	/**
	 * ����� ���������� ����� ������������
	 * @param name ����� ��� ������������
	 */
	public static void setName(String name) {
		StartApp.name = name;
	}
	
	
	/**
	 * ����� ������ ������� ����� �� �����
	 */
	public static void showMainForm() {mainForm.show();}
	/**
	 * ����� ������� ������� ����� 
	 */
	public static void hideMainForm() {mainForm.hide();}
	/**
	 * ����� ������ ����� � ����� �� �����
	 */
	public static void showGameForm() {gameForm.show();}
	/**
	 * ����� ������� ������� �����
	 */
	public static void hideGameForm() {gameForm.hide();}
	/**
	 * �����, ����������� �������� ����� ������� �����
	 */
	public static void newGameForm() {gameForm = new Game(getTypeGame());}

}
