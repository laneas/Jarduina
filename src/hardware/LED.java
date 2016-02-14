/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hardware;

import java.awt.Color;

/**
 *
 * @author Ardjen
 */
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
