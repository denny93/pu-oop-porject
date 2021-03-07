
public class AvatarPOsition extends  Shema{
    String type ;
    public AvatarPOsition(int startX, int start_y, int endX, int endY, BaseModel baseModel, String typeIn) {
        super(startX, start_y, endX, endY, baseModel);
        type =typeIn;
    }
}
