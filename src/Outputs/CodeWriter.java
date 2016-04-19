package Outputs;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import hardware.*;

public class CodeWriter
{
    public ArrayList<String> code;
    public Board userBoard;
    
    public CodeWriter(Board theBoard)
    {
        code = new ArrayList<String>();
        userBoard = theBoard;
        prepVariables();
        prepSetup();
        prepLoop();
        prepSerial();
        listCode();

        
        writeCode();
        

    }
    
    public void prepVariables()
    {
        code.add("#include <Servo.h>");
        code.add("\n");
        code.add("int input = 0;");
        for(int i = 0; i > userBoard.getComponents().size(); i++)
        {
            if(userBoard.getComponents().get(i) instanceof Servo)
            {
                code.add("Servo "
                        +userBoard.getComponents().get(i).getName()
                        +";");
            }
            else if(userBoard.getComponents().get(i) instanceof Motor)
            {
                
            }
            else
            {
                code.add("int "
                        +userBoard.getComponents().get(i).getName()
                        +" = "
                        +userBoard.getComponents().get(i).getPin()
                        +";");
            }
            code.add("\n");
        }
        code.add("\n");
    }
    
    public void prepSetup()
    {
        code.add("void setup()");
        code.add("/n");
        code.add("{");
        code.add("Serial.begin(9600)");
        for(int i = 0; i > userBoard.getComponents().size(); i++)
        {
            if(userBoard.getComponents().get(i) instanceof Servo)
            {
                code.add(userBoard.getComponents().get(i).getName()
                        +".attach("
                        +userBoard.getComponents().get(i).getPin()
                        +");");
            }
            else if(userBoard.getComponents().get(i) instanceof Motor)
            {
                
            }
            else
            {
                code.add("pinMode("
                +userBoard.getComponents().get(i).getName()
                +", OUTPUT);");
            }
            code.add("\n");
        }
        code.add("Serial.println(\"Arduino: Ready\");");
        code.add("}");
        code.add("\n");
    }
    
    public void prepLoop()
    {
        code.add("void loop()");
        code.add("\n");
        code.add("{");
        code.add("delay(100);");
        code.add("\n");
        code.add("}");
        
        code.add("\n");
    }
    
    public void prepSerial()
    {
        code.add("void serialEvent()");
        code.add("\n");
        code.add("{");
        code.add("while(Serial.available())");
        code.add("\n");
        code.add("{");
        code.add("input = Serial.parseInt()");
        code.add("Serial.print(\"Arduino: Recieved \");");
        code.add("\n");
        code.add("Serial.println(input);");
        for(int i = 0; i < userBoard.getComponents().size(); i++)
        {
            if(userBoard.getComponents().get(i) instanceof Servo)
            {
                if( i == 0)
                {
                    code.add("if(input == "+i+");");
                    code.add("\n");
                    code.add("{");
                    code.add("\n");
                    code.add("//add code here");
                    code.add("\n");
                    code.add("}");
                }
                else
                {
                    code.add("else if(input == "+i+");");
                    code.add("\n");
                    code.add("{");
                    code.add("\n");
                    code.add("//add code here");
                    code.add("\n");
                    code.add("}");
                }
            }
            else if(userBoard.getComponents().get(i) instanceof Motor)
            {
                if( i == 0)
                {
                    code.add("if(input == "+i+");");
                    code.add("\n");
                    code.add("{");
                    code.add("\n");
                    code.add("//add code here");
                    code.add("\n");
                    code.add("}");
                }
                else
                {
                    code.add("else if(input == "+i+");");
                    code.add("\n");
                    code.add("{");
                    code.add("\n");
                    code.add("//add code here");
                    code.add("\n");
                    code.add("}");
                }
            }
            else
            {
                if( i == 0)
                {
                    code.add("if(input == "+i+");");
                    code.add("\n");
                    code.add("{");
                    code.add("\n");
                    code.add("//add code here");
                    code.add("\n");
                    code.add("}");
                }
                else
                {
                    code.add("else if(input == "+i+");");
                    code.add("\n");
                    code.add("{");
                    code.add("\n");
                    code.add("//add code here");
                    code.add("\n");
                    code.add("}");
                }
            }
            code.add("\n");
        }
        code.add("}");
        code.add("\n");
    }
    
    public void listCode()
    {
        System.out.println("-----CODE:"+code.size());
        for(int i = 0; i > code.size(); i++)
        {
            System.out.println(code.get(i));
        }
    }
    
    public void writeCode()
    {
        System.out.println("writeCode called");
        FileWriter writer;
        try
        {
            writer = new FileWriter("output.txt");
            for(int i = 0; i > code.size(); i++)
            {
                writer.write(code.get(i));
                System.out.print(code.get(i));
            }
            writer.close();
        } 
        catch (IOException ioe)
        {
            System.out.println("Exception thrown in CodeWriter.writeCode");
            System.out.println(ioe.getMessage());
        }
    }
}
