import java.util.Random;

public class HealtClass {
    /**
     *  heaLT POINTS OF AVATAR WHEN PLAYER SELECT ACTION HEALT AND CLICK OVER AVATAR
     * @param x position of pointer x
     * @param y position of pointer y
     */
    public static  void heal(int x , int y) {
        Random rand = new Random();

        for (int i = 0 ; i < 7 ; i++){
            for (int j = 0 ; j < 9 ; j++) {
                if (Start.shemaArray[i][j].startX <= x && Start.shemaArray[i][j].startY <= y
                        && Start.shemaArray[i][j].endX >= x && Start.shemaArray[i][j].endY >= y) {
                    if (Start.shemaArray[i][j].baseModel.getClass().getName() != "BaseModel"){
                        Start.shemaArray[i][j].baseModel.helty += rand.nextInt(7);
                    }
                }
            }
        }
        if (rand.nextInt(7) % 2 != 0)
        {
            return;
        }
        BaceMouseControllerFunction.refreshNumberOfMOve();
        Start.addElements(Start.gs, true);
    }
}
