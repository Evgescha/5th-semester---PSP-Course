package form;

import java.awt.Button;
import java.awt.EventQueue;
import client.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import script.StartApp;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

/**
 * Класс главной формы
 * 
 * @author Yauhenii
 *
 */
public class Main extends JFrame {

	private Client client;
	private JTextField textField;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JButton button;
	private JButton button_1;
	private JButton button_2;

	/**
	 * Метод обновления статуса подлючения
	 * 
	 * @author Yauhenii
	 *
	 */
	private class ReadStatus extends Thread {
		@Override
		public void run() {
			int temp = 0;
			try {
				while (true) {
					label_1.setText(StartApp.getStatus());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Main() {
		initialize();
		new ReadStatus().start();
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		// установка панели, размеров, действия при закрытии
		this.setBounds(100, 100, 311, 249);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		// кнопка и слушатель
		button = new JButton("Подключится к серверу");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// работать лишь при условии что имя введено
				if (!textField.getText().isEmpty()) {
					// Сохранении имени и запуск подключения
					StartApp.setName(textField.getText());
					textField.setEnabled(false);
					button.setEnabled(false);
					client = new Client();
					try {
						client.main(null);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Ошибка подключения к серверу");
						e.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(null, "Введите ваше имя для подключения");
			}
		});
		button.setBounds(10, 11, 293, 23);
		this.getContentPane().add(button);

		label = new JLabel("Статус: ");
		label.setBounds(20, 42, 65, 34);
		this.getContentPane().add(label);

		label_1 = new JLabel("Не подключено");
		label_1.setBounds(172, 45, 131, 34);
		this.getContentPane().add(label_1);

		label_2 = new JLabel("Ваш ник");
		label_2.setBounds(10, 87, 107, 23);
		this.getContentPane().add(label_2);

		textField = new JTextField();
		textField.setBounds(127, 88, 176, 20);
		this.getContentPane().add(textField);
		textField.setColumns(10);

		label_3 = new JLabel("Выберите вариант игры");
		label_3.setBounds(10, 119, 278, 23);
		this.getContentPane().add(label_3);

		button_1 = new JButton("Игра с компьютером");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (label_1.getText() == "Подключено") {
					StartApp.setTypeGame(StartApp.PC);
					StartApp.newGameForm();
					StartApp.showGameForm();
					StartApp.hideMainForm();
				}
			}
		});
		button_1.setBounds(10, 153, 293, 23);
		this.getContentPane().add(button_1);

		button_2 = new JButton("Игра с человеком");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartApp.setTypeGame(StartApp.USER);
				StartApp.newGameForm();
				StartApp.showGameForm();
				StartApp.hideMainForm();
			}
		});
		button_2.setBounds(10, 187, 293, 23);
		this.getContentPane().add(button_2);

		// button.setVisible(false);

	}
}
