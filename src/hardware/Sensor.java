package hardware;

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
