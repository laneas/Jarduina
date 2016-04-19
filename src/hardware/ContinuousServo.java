package hardware;

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
