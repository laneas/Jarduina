package GUI;

import hardware.*;
import jarduina.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Application
{
    private JFrame window;
    private JTabbedPane tabs;
    private JPanel boardSetup;
      private JTextField boardNameInput, portInput;
      private JComboBox boardTypeInput;
      private JButton boardSubmit;
    private JPanel boardEdit;
      private JButton addConServo, addLed, addServo, addUltrasonic;
      private JComboBox pinInput;
      private JTextField componentNameInput;
    private JPanel boardInterface;
    
    private Board userBoard;
    private SerialComm comm;
    
    public Application()
    {
        userBoard = null;
        comm = null;
        setup();
    }
    
    public void setup()
    {
        windowSetup();
    }
    
    public void windowSetup()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        window = new JFrame("Arduino Interface");
          window.setBounds(0, 0, screenSize.width, screenSize.height);
          window.setVisible(true);
          window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
        boardSetupSetup();
        boardEditSetup();
        boardInterfaceSetup();
          
        tabs = new JTabbedPane(JTabbedPane.LEFT);
          tabs.addTab("Board Setup", null, boardSetup, "Start Here");
          tabs.addTab("Board Edit", null, boardEdit, "Add Components Here");
          tabs.addTab("Board Interface", null, boardInterface, "Your Interface");
        
        window.add(tabs);
    }
    
    public void boardSetupSetup()
    {
        String[] boardTypeList = {"Uno", "Nano", "Mega", "Nano", "Leonardo", "Yun", "Mini", "Duemilanove", "Other"};
        boardSetup = new JPanel();
        
        boardNameInput = new JTextField("Enter Board Name Here");
        portInput = new JTextField("Enter Port Here");
        boardTypeInput = new JComboBox(boardTypeList);
        boardSubmit = new JButton("Submit");
          boardSubmit.addActionListener(new boardSubmitListener());
        
        boardSetup.add(boardNameInput);
        boardSetup.add(portInput);
        boardSetup.add(boardTypeInput);
        boardSetup.add(boardSubmit);
    }
    
    public void boardEditSetup()
    {
        int widthScale = 150;
        int heightScale = 150;
        Toolkit t = Toolkit.getDefaultToolkit();
        ImageIcon conServo = new ImageIcon("src//res//ConServo.png");
          conServo = new ImageIcon(conServo.getImage().getScaledInstance(widthScale, heightScale, java.awt.Image.SCALE_SMOOTH));
        ImageIcon led = new ImageIcon("src//res//LED.png");
          led = new ImageIcon(led.getImage().getScaledInstance(widthScale, heightScale, java.awt.Image.SCALE_SMOOTH));
        ImageIcon servo = new ImageIcon("src//res//Servo.png");
          servo = new ImageIcon(servo.getImage().getScaledInstance(widthScale, heightScale, java.awt.Image.SCALE_SMOOTH));
        ImageIcon ultrasonic = new ImageIcon("src//res//Ultrasonic.png");
          ultrasonic = new ImageIcon(ultrasonic.getImage().getScaledInstance(widthScale, heightScale, java.awt.Image.SCALE_SMOOTH));
        String[] pins = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
        
        boardEdit = new JPanel();
        
        addConServo = new JButton("Continuous Servo", conServo);
          addConServo.addActionListener(new editListener());
        addLed = new JButton("LED", led);
          addLed.addActionListener(new editListener());
        addServo = new JButton("Servo", servo);
          addServo.addActionListener(new editListener());
        addUltrasonic = new JButton("Ultrasonic Sensor", ultrasonic);
          addUltrasonic.addActionListener(new editListener());
          
        pinInput = new JComboBox(pins);
        JLabel enterName = new JLabel("Enter Compontent Name:");
        componentNameInput = new JTextField("default");
        
        boardEdit.add(addConServo);
        boardEdit.add(addLed);
        boardEdit.add(addServo);
        boardEdit.add(addUltrasonic);
        boardEdit.add(pinInput);
        boardEdit.add(enterName);
        boardEdit.add(componentNameInput);
    }
    
    public void boardInterfaceSetup()
    {
        boardInterface = new JPanel();
        JButton connectButton = new JButton("Connect");
          connectButton.addActionListener(new connectListener());
          
        boardInterface.add(connectButton);
    }
    
    private class boardSubmitListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if(userBoard == null)
            {
                String bType = String.valueOf(boardTypeInput.getSelectedItem());
                String bName = boardNameInput.getText();
                String bPort = portInput.getText();
                
                userBoard = new Board(bName, bPort);
                  userBoard.setBoardType(bType);
            }
            else
            {
                //notify of board override
            }
        }
    }
    
    private class connectListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            Object o = ae.getSource();
            JButton b = (JButton)o;
            
            if(userBoard != null)
            {
                if(b.getText().equals("Connect"))
                {
                    comm = new SerialComm();
                    try
                    {
                        comm.connect(userBoard.getPort());
                    }
                    catch (Exception ex)
                    {
                        System.out.println("Exception Thrown at Application.connectListener");
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
    }
    
    private class editListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            Object o = ae.getSource();
            int thePin = Integer.parseInt(String.valueOf(boardTypeInput.getSelectedItem()));
            String theName = componentNameInput.getText();
            
            if(userBoard != null)
            {
                if(o == addConServo)
                {
                   ContinuousServo cs = new ContinuousServo(thePin);
                     cs.setName(theName);
                   userBoard.addComponent(cs);
                }
                else if(o == addLed)
                {
                    LED l = new LED(thePin);
                      l.setName(theName);
                    userBoard.addComponent(l);
                }
                else if(o == addServo)
                {
                    Servo s = new Servo(thePin);
                      s.setName(theName);
                    userBoard.addComponent(s);
                }
                else if(o == addUltrasonic)
                {
                    Ultrasonic u = new Ultrasonic(thePin);
                     u.setName(theName);
                   userBoard.addComponent(u);
                }
                else
                {
                    System.out.println("random object clicked");
                }
            }
            else
            {
                System.out.println("Board is null, ya dingus");
                //notify that board is null
            }
        }
    }
}
