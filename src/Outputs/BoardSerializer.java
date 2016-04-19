/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Outputs;

import hardware.Board;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Ardjen
 */
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
