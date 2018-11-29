package pacakge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {
    private JPanel jPane;
    private JButton ButtonReset;
    private JTextField textField1;
    private JButton ButtonOK;
    private JButton ButtonCancel;
    private JTextArea textArea1;
    private JPanel jPane2;
    private JPanel jPane3;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a5Button;
    private JButton a4Button;
    private JCheckBox checkBox1;
    private MyActionListener listener = new MyActionListener();
    GUI() {
        ButtonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDialog dialog = new MyDialog();
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        ButtonCancel.addActionListener(listener);
        ButtonReset.addActionListener(listener);
        a1Button.addActionListener(listener);
        a2Button.addActionListener(listener);
        a3Button.addActionListener(listener);
        a4Button.addActionListener(listener);
        a5Button.addActionListener(listener);
    }
    JPanel getJPane(){
        return this.jPane;
    }
    class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            textArea1.append(e.getActionCommand()+"\n");
        }
    }

}
