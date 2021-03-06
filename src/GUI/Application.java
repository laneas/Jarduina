package GUI;

import Outputs.BoardDeserializer;
import Outputs.BoardSerializer;
import Outputs.CodeWriter;
import hardware.*;
import jarduina.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
      private JLabel boardIcon, logoIcon;
    private JPanel boardEdit;
      private JButton addConServo, addLed, addServo, addUltrasonic;
      private JComboBox pinInput;
      private JTextField componentNameInput;
    private JPanel boardInterface;
    private JPanel boardEditFeedback;
        
    private Board userBoard;
    private SerialComm comm;
    
    private int widgetCounter = 10;
    
    private static final int BUTTONWIDTH = 100;
    private static final int BUTTONHEIGHT = 25;
    private static final int TEXTWIDTH = 175;
    private static final int TEXTHEIGHT = 25;
    
    private static final Color arduinoBlue = new Color(0, 151, 156);
    private static final Color arduinoRed = new Color(156, 0, 0);
    private static final Color arduinoBrown = new Color(156, 78, 0);
    private static final Font arduinoFont  = new Font("Serif", Font.BOLD, 18);
    
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
        String[] boardTypeList = {"Uno", "Nano", "Mega", "Leonardo", "Yun", "Mini", "Duemilanove", "Other"};
        boardSetup = new JPanel();
          boardSetup.setBackground(arduinoBlue);
          boardSetup.setLayout(null);
          
        ImageIcon board = new ImageIcon("src//res//boards//uno.jpg");
        boardIcon = new JLabel();
        boardIcon.setIcon(board);
        boardIcon.setBounds(500, 25, 500, 800);
        
        ImageIcon logo = new ImageIcon("src//res//Logo.png");
        logoIcon = new JLabel();
        logoIcon.setIcon(logo);
        logoIcon.setBounds(100, 25, 300, 400);
        
        boardNameInput = new JTextField("Enter Board Name Here");
          boardNameInput.setPreferredSize(new Dimension(TEXTWIDTH, TEXTHEIGHT));
          boardNameInput.setBounds(10, 25, TEXTWIDTH, TEXTHEIGHT);
        portInput = new JTextField("Enter Port Here");
          portInput.setPreferredSize(new Dimension(TEXTWIDTH, TEXTHEIGHT));
          portInput.setBounds(10, 60, TEXTWIDTH, TEXTHEIGHT);
        boardTypeInput = new JComboBox(boardTypeList);
          boardTypeInput.setPreferredSize(new Dimension(TEXTWIDTH, TEXTHEIGHT));
          boardTypeInput.addActionListener(new typeListener());
          boardTypeInput.setBounds(10, 100, TEXTWIDTH, TEXTHEIGHT);
        boardSubmit = new JButton("Submit");
          boardSubmit.addActionListener(new boardSubmitListener());
          boardSubmit.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
          boardSubmit.setBounds(10, 150, BUTTONWIDTH, BUTTONHEIGHT);
        boardSetup.add(boardNameInput);
        boardSetup.add(portInput);
        boardSetup.add(boardTypeInput);
        boardSetup.add(boardSubmit);
        boardSetup.add(boardIcon);
        boardSetup.add(logoIcon);
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
        
        GridLayout gl = new GridLayout(3, 4);
        
        boardEdit = new JPanel();
          boardEdit.setBackground(arduinoBlue);
          boardEdit.setLayout(gl);
        JPanel dash = new JPanel();
          dash.setBackground(arduinoBlue);
          dash.setLayout(gl);
          
        boardEditFeedback = new JPanel();
          boardEditFeedback.setLayout(new GridLayout(5, 1));
        JLabel boardName = new JLabel("Board Components");
          boardEditFeedback.add(boardName);
          boardEditFeedback.setBackground(arduinoBrown);
          boardName.setForeground(Color.WHITE);
          boardName.setFont(arduinoFont);
        
        addConServo = new JButton("Continuous Servo", conServo);
          addConServo.addActionListener(new editListener());
        addLed = new JButton("LED", led);
          addLed.addActionListener(new editListener());
        addServo = new JButton("Servo", servo);
          addServo.addActionListener(new editListener());
        addUltrasonic = new JButton("Ultrasonic Sensor", ultrasonic);
          addUltrasonic.addActionListener(new editListener());
          
        pinInput = new JComboBox(pins);
          pinInput.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
        JLabel enterName = new JLabel("Enter Compontent Name:");
          enterName.setForeground(arduinoRed);
          enterName.setFont(arduinoFont);
          componentNameInput = new JTextField("default");
          componentNameInput.setPreferredSize(new Dimension(TEXTWIDTH, TEXTHEIGHT));
          componentNameInput.setFont(arduinoFont);
        JLabel enterpin = new JLabel("Enter Pin: ");
          enterpin.setForeground(arduinoRed);
          enterpin.setFont(arduinoFont);
        JButton save = new JButton("Save");
          save.addActionListener(new serialListener());
          save.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
        JButton load = new JButton("Load");
          load.addActionListener(new serialListener());
          load.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
          
        boardEdit.add(addConServo);
        boardEdit.add(addLed);
        boardEdit.add(addServo);
        boardEdit.add(addUltrasonic);
        dash.add(enterName);
        dash.add(componentNameInput);
        dash.add(enterpin);
        dash.add(pinInput);
        dash.add(save);
        dash.add(load);
        boardEdit.add(dash);
        boardEdit.add(boardEditFeedback);
    }
    
    public void boardInterfaceSetup()
    {
        GridLayout gl = new GridLayout(3, 2);
        boardInterface = new JPanel();
          boardInterface.setLayout(gl);
          boardInterface.setBackground(arduinoBlue);
        JButton connectButton = new JButton("Connect");
          connectButton.addActionListener(new connectListener());
          connectButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
        JButton createInterfaceButton = new JButton("Create Interface");
          createInterfaceButton.addActionListener(new connectListener());
          createInterfaceButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
          
        boardInterface.add(connectButton);
        boardInterface.add(createInterfaceButton);
    }
    
    private class typeListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ce)
        {
           if(ce.getSource() == boardTypeInput)
           {
           String type = String.valueOf(boardTypeInput.getSelectedItem());
           if(type.equals("Uno"))
           {
               ImageIcon board = new ImageIcon("src//res//boards//uno.jpg");
               boardIcon.setIcon(board);
           }
           else if(type.equals("Nano"))
           {
            ImageIcon board = new ImageIcon("src//res//boards//nano.jpg");
            boardIcon.setIcon(board);
           }
           else if(type.equals("Mega"))
           {
            ImageIcon board = new ImageIcon("src//res//boards//mega.jpg");
            boardIcon.setIcon(board);
           }
           else if(type.equals("Leonardo"))
           {
            ImageIcon board = new ImageIcon("src//res//boards//leonardo.jpg");
            boardIcon.setIcon(board);
           }
           else if(type.equals("Yun"))
           {
            ImageIcon board = new ImageIcon("src//res//boards//yun.jpg");
            boardIcon.setIcon(board);
           }
           else if(type.equals("Mini"))
           {
            ImageIcon board = new ImageIcon("src//res//boards//mini.jpg");
            boardIcon.setIcon(board);
           }
           else if(type.equals("Duemilanove"))
           {
            ImageIcon board = new ImageIcon("src//res//boards//due.jpg");
            boardIcon.setIcon(board);
           }
           else
           {
            ImageIcon board = new ImageIcon("src//res//boards//lillypad.jpg");
            boardIcon.setIcon(board);
           }
           }
        }
                
    }
        
    private class serialListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            Object o = ae.getSource();
            JButton b = (JButton)o;
            
            if(b.getText().equals("Save"))
            {
                BoardSerializer bs = new BoardSerializer(userBoard);
            }
            else if(b.getText().equals("Load"))
            {
                BoardDeserializer bd = new BoardDeserializer("C:\\Users\\Ardjen\\Desktop\\"+userBoard.getName()+".ser");
            }
        }
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
                if(b.getText().equals("Create Interface"))
                {
                    for(int i = 0; i < userBoard.getComponents().size(); i++)
                    {
                        Widget w = new Widget(userBoard.getComponents().get(i), comm, widgetCounter);
                        boardInterface.add(w);
                        widgetCounter++;
                    }
                    CodeWriter cw = new CodeWriter(userBoard);
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
            int thePin = Integer.parseInt(String.valueOf(pinInput.getSelectedItem()));
            String theName = componentNameInput.getText();
            
            if(userBoard != null)
            {
                if(o == addConServo)
                {
                   ContinuousServo cs = new ContinuousServo(thePin);
                     cs.setName(theName);
                   userBoard.addComponent(cs);
                   JLabel temp = new JLabel(theName+": "+"Continuous Servo Attached to Pin: "+thePin);
                     temp.setFont(arduinoFont);
                     temp.setForeground(Color.WHITE);
                   boardEditFeedback.add(temp);
                }
                else if(o == addLed)
                {
                    LED l = new LED(thePin);
                      l.setName(theName);
                    userBoard.addComponent(l);
                    
                    JLabel temp = new JLabel(theName+": "+"LED Attached to Pin: "+thePin);
                     temp.setFont(arduinoFont);
                     temp.setForeground(Color.WHITE);
                   boardEditFeedback.add(temp);
                }
                else if(o == addServo)
                {
                    Servo s = new Servo(thePin);
                      s.setName(theName);
                    userBoard.addComponent(s);
                    
                    JLabel temp = new JLabel(theName+": "+"Servo Attached to Pin: "+thePin);
                     temp.setFont(arduinoFont);
                     temp.setForeground(Color.WHITE);
                   boardEditFeedback.add(temp);
                }
                else if(o == addUltrasonic)
                {
                    Ultrasonic u = new Ultrasonic(thePin);
                     u.setName(theName);
                   userBoard.addComponent(u);
                   
                   JLabel temp = new JLabel(theName+": "+"Ultrasonic Attached to Pin: "+thePin);
                     temp.setFont(arduinoFont);
                     temp.setForeground(Color.WHITE);
                   boardEditFeedback.add(temp);
                }
                else
                {
                    System.out.println("random object clicked");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(new JFrame(), "You must first make a board!");
            }
        }
    }
}
