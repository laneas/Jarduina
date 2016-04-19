package hardware;

import java.util.ArrayList;

public class Board
{
    private String boardType;
    private String name;
    private String port;
    private ArrayList<Component> components;
    
    public Board(String name, String com)
    {
        setName(name);
        port = com;
        components = new ArrayList<Component>();
    }
    
    public void setBoardType(String theType)
    {
        boardType = theType;
        //boardType = "Arduino Uno"; //default
        //later include logic for setting name based on user selection
    }
    
    public String getBoardType()
    {
        return boardType;
    }
    
    public void setName(String theName)
    {
        if(theName.isEmpty())
        {
            name = "board";
        }
        else
        {
            name = theName;
        }
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setPort(String thePort)
    {
        /*
        Include exception handling later on
        */
        port = thePort;
    }
    
    public String getPort()
    {
        return port;
    }
    
    public void setComponents(ArrayList<Component> theComponents)
    {
        components = theComponents;
    }
    
    public ArrayList<Component> getComponents()
    {
        return components;
    }
    
    public void addComponent(Component theComponent)
    {
        components.add(theComponent);
    }
    
    public void removeComponent(Component theComponent)
    {
        for(int i = 0; i < components.size(); i++)
        {
            if(components.get(i).equals(theComponent))
            {
                components.remove(i);
            }
        }
    }
    
    public void removeComponent(int index)
    {
        components.remove(index);
    }
    
    public void assignComponent(Component theComponent, int thePin)
    {
        int index = thePin - 1;
        theComponent.setPin(thePin);
        components.add(index, theComponent);
    }
    
    public void reset()
    {
        components.clear();
        name = "default";
    }
}
