package GUI;

import hardware.*;
import jarduina.SerialComm;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Widget extends JPanel
{
    private SerialComm comm;
    private JTextField sInput;
    private JButton switchButton;
    private Component component;
    private JLabel feedback;
    
    public Widget(Component theComponent, SerialComm theComm)
    {
        comm = theComm;
        component = theComponent;
        if(theComponent instanceof LED)
        {
            makeLEDScreen();
        }
        else if(theComponent instanceof ContinuousServo)
        {
            makeContinuousServoScreen();
        }
        else if(theComponent instanceof Servo)
        {
            makeServoScreen();
        }
        else if(theComponent instanceof Ultrasonic)
        {
            makeUltrasonicScreen();
        }
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);
    }
    
    public void makeContinuousServoScreen()
    {
        JLabel name = new JLabel(component.getName());
        JButton csStart = new JButton("Start");
          csStart.addActionListener(new csListener());
        JButton csStop = new JButton("Stop");
          csStart.addActionListener(new csListener());
        sInput = new JTextField("90");
        this.add(name);
        this.add(csStart);
        this.add(csStop);
        this.add(sInput);
    }
    
    public void makeServoScreen()
    {
        JLabel name = new JLabel(component.getName());
        JButton writeAngle = new JButton("Write");
          writeAngle.addActionListener(new csListener());
        sInput = new JTextField("0");
        this.add(name);
        this.add(writeAngle);
        this.add(sInput);
    }
    
    public void makeLEDScreen()
    {
        JLabel name = new JLabel(component.getName());
        switchButton = new JButton("OFF");
          switchButton.setBackground(Color.gray);
          switchButton.addActionListener(new switchListener());
        this.add(name);
        this.add(switchButton);
    }
    
    public void makeUltrasonicScreen()
    {
        JLabel name = new JLabel(component.getName());
        switchButton = new JButton("Logging Off");
          switchButton.addActionListener(new switchListener());
        JButton read = new JButton("Read");
          read.addActionListener(new readListener());
        feedback = new JLabel("0000");
        
        this.add(name);
        this.add(switchButton);
        this.add(feedback);
        this.add(read);
    }
    
    public void writeIntToDevice(String stringToWrite)
        {
            try
            {
                for(int i = 0; i < stringToWrite.length(); i++)
                {
                    char index = stringToWrite.charAt(i);
                    int value = Character.getNumericValue(index);
                    value = value + 48;
                    comm.out.write(value);
                }
            }
            catch(IOException ioe)
            {
                System.out.println("Exception Thrown in Widget.writeToDevice(String)");
                System.out.println(ioe.getMessage());
            }
        }
    
    private class readListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            feedback.setText(comm.sr.str.substring(comm.sr.str.length() - 7));
        }
    }
    
    private class switchListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            Object o = ae.getSource();
            JButton b = (JButton)o;
            if(b.getText().equals("ON"))
            {
                writeIntToDevice("0");
                switchButton.setText("OFF");
                switchButton.setBackground(Color.GREEN);
            }
            else if(b.getText().equals("OFF"))
            {
                writeIntToDevice("1");
                switchButton.setText("ON");
                switchButton.setBackground(Color.GRAY);
            }
            else if(b.getText().equals("Logging Off"))
            {
                //log to file
                switchButton.setText("Logging On");
                switchButton.setBackground(Color.GRAY);
            }
            else if(b.getText().equals("Logging On"))
            {
                //stop logging to file
                switchButton.setText("Logging Off");
                switchButton.setBackground(Color.GREEN);
            }
            
        }
    }
    
    private class csListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            JButton b = (JButton)ae.getSource();
            int angleWrite = 90;
            try
            {
                angleWrite = Integer.parseInt(sInput.getText());
            }
            catch(NumberFormatException nfe)
            {
                angleWrite = 90;
                sInput.setText("90");
            }
            String angle = Integer.toString(angleWrite);
            
            if(b.getText().equals("Start"))
            {
                writeIntToDevice(angle);
                
            }
            if(b.getText().equals("Stop"))
            {
                String stopInt = Integer.toString(90);
                writeIntToDevice(stopInt);
            }
            if(b.getText().equals("Write"))
            {
                writeIntToDevice(angle);
            }
        }
    }
    
}
