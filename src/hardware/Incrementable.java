package hardware;

public interface Incrementable
{
    public abstract void setUpperLimit(int upperLimit);
    public abstract void setLowerLimit(int lowerLimit);
    public abstract void writeAngle(int theAngle);
    public abstract void setToDefault();
    
}
