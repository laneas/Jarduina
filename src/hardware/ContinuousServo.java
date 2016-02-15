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
public class ContinuousServo extends Servo implements Incrementable
{
    
    public ContinuousServo(int thePin)
    {
        super(thePin);
        upperLimit = 180;
        lowerLimit = 0;
        defaultAngle = 90;
    }
    
}
