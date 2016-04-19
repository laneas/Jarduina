package hardware;

public abstract class Component
{
    private int pin;
    private String name;
    private boolean state;
    
    public Component(int thePin)
    {
        pin = thePin;
        name = "default";
        state = false;
    }

    public int getPin()
    {
        return pin;
    }

    public void setPin(int pin)
    {
        this.pin = pin;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isState()
    {
        return state;
    }

    public void setState(boolean state)
    {
        this.state = state;
    }
}
