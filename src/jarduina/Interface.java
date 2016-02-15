package jarduina;

import hardware.Board;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Interface
{
    JFrame frame;
    JPanel panel;
    JTextField label;
    JTextField angleWrite;
    JButton angleButton;
    
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
        label = new JTextField("");
        label.setPreferredSize(new Dimension(100, 25));
        angleWrite = new JTextField("90");
        angleWrite.setPreferredSize(new Dimension(50, 25));
        angleButton  = new JButton("Angle Write");
          angleButton.addActionListener(new ButtonListener());
        for(int i = 0; i < 10; i++)
        {
            JButton b = new JButton(String.valueOf(i));
            b.addActionListener(new ButtonListener());
            panel.add(b);
        }
        panel.add(label);
        panel.add(angleWrite);
        panel.add(angleButton);
        frame.add(panel);
    }
    
    private class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            Object o = ae.getSource();
            JButton temp = (JButton)o;
            
            
            if(o == angleButton)
            {
                try
                {
                    String angle = angleWrite.getText();
                    for(int i = 0; i < angle.length(); i++)
                    {
                        char index = angle.charAt(i);
                        int value = Character.getNumericValue(index);
                        value = value + 48;
                        Jarduina.comm.out.write(value);
                    }
                }
                catch(IOException ioe)
                {
                    System.out.println("Java: "+ioe);
                } 
            }
            else
            {
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
            
                label.setText(Jarduina.comm.sr.str.substring(Jarduina.comm.sr.str.length() - 7));
            }
        }
    }
    
    public byte[] intToByteArray(int value) 
    {
    return new byte[] {
            (byte)(value >>> 24),
            (byte)(value >>> 16),
            (byte)(value >>> 8),
            (byte)value};
    }
    
}
