
package jarduina;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

public class SerialComm
{
    public InputStream in;
    public OutputStream out;
    public SerialReader sr;
    
    public SerialComm()
    {
        super();
    }
    
    public void connect ( String portName ) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use");
        }
        else
        {
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
            
            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                
                in = serialPort.getInputStream();
                out = serialPort.getOutputStream();
                
                sr = new SerialReader(in);
                (new Thread(sr)).start();
                (new Thread(new SerialWriter(out))).start();

            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }     
    }
    
    public void listPorts()
    {
        System.out.println("Ports:");
        Enumeration list = CommPortIdentifier.getPortIdentifiers();
        
        while(list.hasMoreElements())
        {
            CommPortIdentifier cpi = (CommPortIdentifier)list.nextElement();
            System.out.print("Port"+cpi.getName()+" ");
            if(cpi.getPortType() == CommPortIdentifier.PORT_SERIAL){System.out.println(" is a Serial Port - "+cpi);}
            else if(cpi.getPortType() == CommPortIdentifier.PORT_PARALLEL){System.out.println(" is a Parallel Port - "+cpi);}
            else{System.out.println("is an Unknown Port - "+cpi);}
        }
    }
}
