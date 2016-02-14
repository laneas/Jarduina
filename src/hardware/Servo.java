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
public class Servo extends Component implements Incrementable
{
    private double angle;
    private double defaultAngle;
    private double upperLimit, lowerLimit;
    
    public Servo(int thePin)
    {
        super(thePin);
        angle = 0;
    }

    @Override
    public void setUpperLimit(int theUpperLimit)
    {
        upperLimit = theUpperLimit;
    }

    @Override
    public void setLowerLimit(int theLowerLimit)
    {
        lowerLimit = theLowerLimit;
    }

    @Override
    public void writeAngle(int theAngle)
    {
        angle = theAngle;
    }

    @Override
    public void setToDefault()
    {
        angle = defaultAngle;
    }

    public double getAngle()
    {
        return angle;
    }

    public void setAngle(double angle)
    {
        this.angle = angle;
    }

    public double getDefaultAngle()
    {
        return defaultAngle;
    }

    public void setDefaultAngle(double defaultAngle)
    {
        this.defaultAngle = defaultAngle;
    }
    
}
