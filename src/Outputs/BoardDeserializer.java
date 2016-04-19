/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Outputs;

import hardware.Board;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;


/**
 *
 * @author Ardjen
 */
public class BoardDeserializer extends Thread
{
    protected Board board;
    protected String filePath;
    
    public BoardDeserializer(String theFilePath)
    {
        filePath = theFilePath;
        board = null;
        System.out.println("BoardDeserializer intl");
    }
    
    @Override
    public void run()
    {
        System.out.println("Begin Deserialization");
        try
        {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            board = (Board)in.readObject();
            in.close();
            fileIn.close();
        } 
        catch(FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch(ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
        
        System.out.println("End Deserialization");
        
    }
    
    public Board getBoard()
    {
        return board;
    }
}
