import java.awt.*;

public class LittleMan extends  BaseModel{
    public LittleMan(Color color) {
        super(color);
    }
    public LittleMan(int team)
    {
        super(team);
        ataka = 6;
        armor = 2;
        helty = 12;
        atack_square = 2;
        speed = 2;
    }

}
