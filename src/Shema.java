public class Shema {
    int startX;
    int startY;
    int endX;
    int endY;
    BaseModel baseModel;
    int player;
    int status  = 0;
    int number_of_move = 0 ;
    int posibletomove = 0 ;
    int attachShema;
    int danger = 0 ;

    public Shema(int startX , int start_y , int endX , int endY , BaseModel baseModel, int player)
    {
        this.startX = startX;
        this.startY = start_y;
        this.endX = endX;
        this.endY = endY;
        this.baseModel = baseModel;
        this.player = player;
    }
    public Shema(int startX , int start_y , int endX , int endY , BaseModel baseModel)
    {
        this.startX = startX;
        this.startY = start_y;
        this.endX = endX;
        this.endY = endY;
        this.baseModel = baseModel;
        this.player = 0;
        this.status = 0 ;
    }
}