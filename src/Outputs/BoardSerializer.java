package Outputs;

import hardware.Board;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BoardSerializer extends Thread
{
    protected Board board;
    
    public BoardSerializer(Board theBoard)
    {
        board = theBoard;
        System.out.println("BoardSerialer intl");
    }
    
    @Override
    public void run()
    {
        System.out.println("Begin Serialization");
        try
        {
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Ardjen\\Desktop\\"+board.getName()+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(board);
            out.close();
            fileOut.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        System.out.println("End serialization");
    }
}
