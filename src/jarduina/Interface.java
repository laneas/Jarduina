package jarduina;

import hardware.Board;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Interface
{
    JFrame frame;
    JPanel panel;
    JTextField label;
    
    public Interface(Board theBoard)
    {
        setup();
    }
    
    public void setup()
    {
        frame = new JFrame();
          frame.setPreferredSize(new Dimension(100, 100));
          frame.setVisible(true);
          frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
          frame.pack();
        panel = new JPanel();
          panel.setVisible(true);
          panel.setPreferredSize(new Dimension(100, 100));
        label = new JTextField("--------");
        label.setSize(100, 100);
        for(int i = 0; i < 10; i++)
        {
            JButton b = new JButton(String.valueOf(i));
            b.addActionListener(new ButtonListener());
            panel.add(b);
        }
        panel.add(label);
        frame.add(panel);
    }
    
    private class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            Object o = ae.getSource();
            JButton temp = (JButton)o;
            int buttonNum = Integer.parseInt(temp.getText());
            buttonNum = buttonNum + 48;
            try
            {
                Jarduina.comm.out.write((buttonNum));
            }
            catch(IOException ioe)
            {
                System.out.println("Java: "+ioe);
            }
            
            label.replaceSelection(Jarduina.comm.sr.str);
        }
    }
    
}
