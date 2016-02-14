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
public abstract class Sensor extends Component
{
    protected double voltage;
    protected String feedback;
    
    public Sensor(int thePin)
    {
        super(thePin);
        voltage = 0.0;
        feedback = "";
    }
    
    public void setVoltage(double theVoltage)
    {
        voltage = theVoltage;
    }
    
    public double getVoltage()
    {
        return voltage;
    }
    
    public void setFeedback(String theFeedback)
    {
        feedback = theFeedback;
    }
    
    public String getFeedback()
    {
        return feedback;
    }
}
