/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarduina;

import hardware.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Jarduina
{
    static SerialComm comm;
    private static boolean connected;
    
    public static void main(String[] args)
    {
        String port = JOptionPane.showInputDialog("Enter Port Name: ");
        try
        {
            comm = new SerialComm();
            //(new SerialComm()).connect("COM3");
            comm.connect(port);
            connected = true;
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e);
            connected = false;
            JOptionPane.showMessageDialog(new JFrame(), "Could not connect to: "+port, "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        if(connected)
        {
            Board b = new Board("Test Board", port);
            b.addComponent(new Ultrasonic(3));
            b.addComponent(new LED(6));
            
            Interface i = new Interface(b);
        }
    }
    
}
