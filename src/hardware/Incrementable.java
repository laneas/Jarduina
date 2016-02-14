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
public interface Incrementable
{
    public abstract void setUpperLimit(int upperLimit);
    public abstract void setLowerLimit(int lowerLimit);
    public abstract void writeAngle(int theAngle);
    public abstract void setToDefault();
    
}
