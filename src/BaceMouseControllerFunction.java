public class BaceMouseControllerFunction {
    /**
     * refresh move maked from anotherr class and controls in start position for move
     */
    public  static  void refreshNumberOfMOve() {
        for (int i = 0 ; i < 7 ; i++){
            for (int j = 0 ; j < 9 ; j++)
            {
                Start.shemaArray[i][j].number_of_move = 0 ;
                Start.shemaArray[i][j].posibletomove = 0 ;
                Start.shemaArray[i][j].attachShema = 0 ;
            }
        }
        MouseController.changeTeam();
        Start.addElements(Start.gs , true);
    }

    /**
     *
     * @param modes
     * @param i_start position of avatar row in matrix
     * @param j_start position of avatar col in matrix
     * @return
     */
    public static  boolean checkClickedPixel(int modes,int i_start , int j_start ) {
        for (int i = 0 ; i < 7 ; i++){
            for (int j = 0 ; j < 9 ; j++){
                if (Start.shemaArray[i][j].startX <= MouseController.x && Start.shemaArray[i][j].startY <= MouseController.y
                        && Start.shemaArray[i][j].endX >= MouseController.x && Start.shemaArray[i][j].endY >= MouseController.y) {
                    if (modes == 1){
                        if (i == MouseController.avatar_i || j == j_start) {
                            return false;
                        }
                        if (Start.shemaArray[i][j].number_of_move != 0) {
                            MouseController.i_start = i;
                            MouseController.j_start = j;
                            return true;
                        }
                    }
                    else{
                        if (Start.shemaArray[i][j].attachShema != 0) {
                            MouseController.i_start = i;
                            MouseController.j_start = j;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
