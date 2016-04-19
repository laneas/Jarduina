package hardware;

import java.awt.Color;

public class LED extends Component
{
    private Color ledColor;
    
    public LED(int thePin)
    {
        super(thePin);
        ledColor = new Color(0, 0, 0);
    }
    
    public void fadeFor(int millisconds, int loops)
    {
        for(int i = 0; i < 5; i++)
        {
            
        }
    }
    
    public void fade(int milliseconds)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Color getLedColor()
    {
        return ledColor;
    }

    public void setLedColor(Color ledColor)
    {
        this.ledColor = ledColor;
    }
}
