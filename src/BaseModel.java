import java.awt.*;

public class BaseModel {
    Color color ;
    int finalStatus = 0;

    public int ataka;
    public int armor;
    public int helty;
    public int atack_square;
    public int speed;
    public int team;
    public int red;

    public  BaseModel()
    {}
    public BaseModel(Color color)
    {
        this.color = color;
    }
    public BaseModel(Color color, int state )
    {
        this.color = color;
    }
    public BaseModel(int atack , int armor, int helty , int atack_square ,int speed )
    {
        this.ataka =atack;
        this.armor=armor;
        this.helty =helty;
        this.atack_square =atack_square;
        this.speed =speed;
    }
    public BaseModel (int team)
    {
        this.team = team;
    }

    public int getAtaka() {
        return ataka;
    }

    public int getArmor() {
        return armor;
    }

    public int getHelty() {
        return helty;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAtack_square() {
        return atack_square;
    }
}
