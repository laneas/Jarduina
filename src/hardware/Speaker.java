package hardware;

public class Speaker extends Component
{
    private int volume;
    private String fileLocation;
    
    public Speaker(int thePin)
    {
        super(thePin);
    }
    
    public void play()
    {
        //find default file and play it
    }
    
    public void play(String theLocation)
    {
        //play a specific file.
    }

    public int getVolume()
    {
        return volume;
    }

    public void setVolume(int volume)
    {
        this.volume = volume;
    }

    public String getFileLocation()
    {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation)
    {
        this.fileLocation = fileLocation;
    }
}
