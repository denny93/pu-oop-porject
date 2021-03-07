import java.awt.*;

public class Elf extends  BaseModel {
    public Elf(Color color) {
        super(color);
    }
    public Elf(int team)
    {
        super(team);
        ataka = 5;
        armor = 1;
        helty = 10;
        atack_square = 3;
        speed = 3;
    }
    public Elf()
    {
    }
}
