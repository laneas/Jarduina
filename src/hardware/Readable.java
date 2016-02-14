/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hardware;

/**
 *
 * @author Ardjen
 */
public interface Readable
{
    public abstract void read();
    public abstract void readFor(double milliseconds);
    
}
