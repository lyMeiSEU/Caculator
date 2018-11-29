package pacakge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
	private JButton[] buttonOfNumber = new JButton[10];
	private JTextField textField;
	private String string;
	private double number1, number2, result;
	private NumberActionListener listener = new NumberActionListener();
	private Boolean flag = false;

	Calculator() {
		setTitle("Calculator");
		setBounds(400, 400, 300, 400);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		init();
	}

	private void init() {// ��ʼ��
		textField = new JTextField(20);
		textField.setEditable(false);
		setLayout(new BorderLayout());
		add(BorderLayout.NORTH, textField);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 4));
		for (int i = 0; i < 10; i++) {
			buttonOfNumber[i] = new JButton(String.valueOf(i));
			buttonOfNumber[i].addActionListener(listener);
			panel.add(buttonOfNumber[i]);
		}
		JButton add;
		add = new JButton("+");
		add.addActionListener(listener);
		JButton min = new JButton("-");
		min.addActionListener(listener);
		JButton plu = new JButton("*");
		plu.addActionListener(listener);
		JButton div = new JButton("/");
		div.addActionListener(listener);
		JButton cle = new JButton("C");
		JButton cel = new JButton("del");
		JButton equ = new JButton("=");
		panel.add(add);
		panel.add(min);
		panel.add(plu);
		panel.add(div);
		panel.add(cle);
		panel.add(cel);
		panel.add(equ);
		add(BorderLayout.CENTER, panel);
		cle.addActionListener(new ActionListener() {// ���
			@Override
			public void actionPerformed(ActionEvent arg0) {
				string = null;
				number1 = 0;
				number2 = 0;
				result = 0;
				textField.setText("");
				flag = false;
			}
		});
		cel.addActionListener(new ActionListener() {// ���
			@Override
			public void actionPerformed(ActionEvent arg0) {
				string = textField.getText();
				String regex = "[^+-/*/]";
				String c = string.replaceAll(regex, "");// �ҵ������
				int index = string.indexOf(c);

				if (string == String.valueOf(result) || result != 0) {
					string = null;
					number1 = 0;
					number2 = 0;
					result = 0;
					textField.setText("");
					return;
				} else {
					if (index == 0) {
						number1 = 0;
						number2 = 0;
						result = 0;
						textField.setText("");
					}
					if (index == string.length()) {
						number1 = 0;
						number2 = 0;
						textField.setText("");
					}
					if (index == string.length() - 1) {
						number1 = Double.parseDouble(string.substring(0, index));
						textField.setText(String.valueOf((int) number1));
					}
					if (index < string.length() - 1) {
						number1 = Double.parseDouble(string.substring(0, index));
						number2 = Double.parseDouble(string.substring(index + 1, string.length()));
						char operator = string.charAt(index);
						textField.setText(String.valueOf((int) number1) + operator);
					} else {
						string = null;
						number1 = 0;
						number2 = 0;
						result = 0;
						textField.setText("");
						flag = false;
					}
					string = null;
					flag = false;
				}
			}
		});
		equ.addActionListener(new ActionListener() {// ����

			@Override
			public void actionPerformed(ActionEvent arg0) {
				string = textField.getText();
				if (!string.matches("[0-9]+[+-/*/][0-9]+")) {// ����Ƿ�Ϊ��ȷ�ı��ʽ
					textField.setText("error");
					flag = true;
					return;
				}
				String regex = "[^+-/*/]";
				String c = string.replaceAll(regex, "");// �ҵ������
				int index = string.indexOf(c);
				number1 = Double.parseDouble(string.substring(0, index));
				number2 = Double.parseDouble(string.substring(index + 1, string.length()));
				char operator = string.charAt(index);
				switch (operator) {// ����
				case '+':
					result = number1 + number2;
					break;
				case '-':
					result = number1 - number2;
					break;
				case '*':
					result = number1 * number2;
					break;
				case '/':
					if (number2 == 0) {
						textField.setText("error");
						flag = true;
						return;
					}
					result = number1 / number2;
					break;
				default:
					break;
				}
				textField.setText(String.valueOf(result));
				flag = true;
			}
		});
	}

	class NumberActionListener implements ActionListener {// �ж�����

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String num = arg0.getActionCommand();// ��ȡ��ť����
			if (!flag) {// ���δ����
				textField.setText(textField.getText() + num);// ???
			} else {// ���������ϣ����¿�ʼ
				textField.setText("");
				textField.setText(textField.getText() + num);
				flag = false;
			}

		}
	}
}
