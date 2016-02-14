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
public class Ultrasonic extends Sensor implements Readable
{
    private double distance;
    private double microseconds;
    
    public Ultrasonic(int thePin)
    {
        super(thePin);
    }

    @Override
    public void read()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void readFor(double milliseconds)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    public double getMicroseconds()
    {
        return microseconds;
    }

    public void setMicroseconds(double microseconds)
    {
        this.microseconds = microseconds;
    }
    
    public void toInches(double microseconds)
    {
        distance = microseconds / 74 / 2;
    }
    
    public void toCentimeters(double microseconds)
    {
        distance = microseconds / 29 / 2;
    }
}
