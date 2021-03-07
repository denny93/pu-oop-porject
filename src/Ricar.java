import java.awt.*;

public class Ricar extends BaseModel {
    public Ricar(Color color) {
        super(color);
    }
    public Ricar(int team)
    {
        super(team);
        ataka = 8;
        armor = 3;
        helty = 15;
        atack_square = 1;
        speed = 1;
    }
}
