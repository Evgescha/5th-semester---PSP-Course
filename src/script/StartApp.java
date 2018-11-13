package script;
import form.*;

/**
 * Класс управления формами на стороне клиента
 * @author Yauhenii
 *
 */
public final class StartApp {
	/**
	 * Тип игры пользователя
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
	 * Имя пользователя
	 */
	static String name = "";
	/**
	 * Главная форма
	 */
	static Main mainForm = new Main();
	/**
	 * Форма игры с пользователем\пк
	 */
	static Game gameForm;
	/**
	 * Строка состояния подключения к серверу
	 */
	static String Status = "Не подключено";
	
	/**
	 * Метод получения текущего статуса
	 * @return статус
	 */
	public static String getStatus() {
		return Status;
	}
	/**
	 * Метод изменения текущего статуса
	 * @param status новый текущий статус
	 */
	public static void setStatus(String status) {
		Status = status;
	}
	/**
	 * Метод получения имени пользователя
	 * @return имя пользователя
	 */
	public static String getName() {
		return name;
	}
	/**
	 * Метод обновления имени пользователя
	 * @param name новое имя пользователя
	 */
	public static void setName(String name) {
		StartApp.name = name;
	}
	
	
	/**
	 * Метод вывода главной формы на экран
	 */
	public static void showMainForm() {mainForm.show();}
	/**
	 * Метод скрытия главной формы 
	 */
	public static void hideMainForm() {mainForm.hide();}
	/**
	 * Метод вывода формы с игрой на экран
	 */
	public static void showGameForm() {gameForm.show();}
	/**
	 * Метод скрытия игровой формы
	 */
	public static void hideGameForm() {gameForm.hide();}
	/**
	 * Метод, реализующий создание новой игровой формы
	 */
	public static void newGameForm() {gameForm = new Game(getTypeGame());}

}
