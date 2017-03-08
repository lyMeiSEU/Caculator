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
        setTitle("计算器");
        setBounds(400, 400, 300, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
    }

    private void init() {//初始化
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
        JButton equ = new JButton("=");
        panel.add(add);
        panel.add(min);
        panel.add(plu);
        panel.add(div);
        panel.add(cle);
        panel.add(equ);
        add(BorderLayout.CENTER, panel);
        cle.addActionListener(new ActionListener() {//清空
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
        equ.addActionListener(new ActionListener() {//计算

            @Override
            public void actionPerformed(ActionEvent arg0) {
                string = textField.getText();
                if (!string.matches("[0-9]+[+-/*/][0-9]+")) {//检查是否为正确的表达式
                    textField.setText("error");
                    flag = true;
                    return;
                }
                String regex = "[^+-/*/]";
                String c = string.replaceAll(regex, "");//找到运算符
                int index = string.indexOf(c);
                number1 = Double.parseDouble(string.substring(0, index));
                number2 = Double.parseDouble(string.substring(index + 1, string.length()));
                char operator = string.charAt(index);
                switch (operator) {//运算
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

    class NumberActionListener implements ActionListener {//判断输入

        @Override
        public void actionPerformed(ActionEvent arg0) {
            String num = arg0.getActionCommand();//获取按钮内容
            if (!flag) {//如果未计算
                textField.setText(textField.getText() + num);//???
            } else {//如果计算完毕，重新开始
                textField.setText("");
                textField.setText(textField.getText() + num);
                flag = false;
            }

        }
    }
}


