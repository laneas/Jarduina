package hardware;

public class Servo extends Component implements Incrementable
{
    protected double angle;
    protected double defaultAngle;
    protected double upperLimit, lowerLimit;
    
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
