public class CordinateSystemVisual {

    /**
     *  select area for move in forurt section of cordinate system
     * @param min area of avatar
     * @param i_start position (row) of avatar
     * @param j_start position (col) of avatar
     * @param model avatar
     */
    public static  void getfourCorginateSystemItems(int min, int i_start, int j_start, BaseModel model ) {
        min = i_start;
        for (int index = model.getAtack_square() ; index > 0 ; index--) {
            if(min > 8){
                break;
            }
            for (int i  = 0 ; i <= index; i++){
                if(j_start -i < 0){
                    continue;
                }
                if (Start.shemaArray[min][j_start - i].baseModel.getClass().getName() != "BaseModel"){
                    continue;
                }
                else{
                    Start.shemaArray[min][j_start - i].posibletomove = 1;
                }
                Start.shemaArray[min][j_start - i].number_of_move =1;
            }
            min++;
            if (min > 6){
                break;
            }
        }
    }

    /**
     *  select area for move in Third section of cordinate system
     * @param min area of avatar
     * @param i_start position (row) of avatar
     * @param j_start position (col) of avatar
     * @param model avatar
     */
    public static void getThirdorginateSystemItems(int min, int i_start, int j_start, BaseModel model ) {
        min = i_start;
        for (int index = model.getAtack_square() +1; index > 0 ; index--) {
            if(min > 8){
                break;
            }
            for (int i  = 0 ; i < index; i++) {
                if (j_start+ i  > 8 ){
                    continue;
                }
                if (Start.shemaArray[min][j_start + i].baseModel.getClass().getName() != "BaseModel"){
                    continue;
                }
                else{
                    Start.shemaArray[min][j_start + i].posibletomove = 1;
                }
                Start.shemaArray[min][j_start + i].number_of_move =1;
            }
            min++;
            if (min > 6){
                break;
            }
        }
    }

    /**
     *  select area for move in sercond section of cordinate system
     * @param min area of avatar
     * @param i_start position (row) of avatar
     * @param j_start position (col) of avatar
     * @param model avatar
     */
    public static void getSecondCorginateSystemItems(int min, int i_start, int j_start, BaseModel model ) {
        min = i_start - model.getAtack_square();
        for (int index = 0  ;index < model.getAtack_square() + 1 ;index ++){
            if (min < 0){
                min++;
                continue;
            }
            if(min > 8){
                break;
            }
            for (int i  = 0 ; i <= index; i++){
                if(j_start -i < 0){
                    continue;
                }
                if (Start.shemaArray[min][j_start - i].baseModel.getClass().getName() != "BaseModel"){
                    continue;
                }else{
                    Start.shemaArray[min][j_start - i].posibletomove = 1;
                }
                Start.shemaArray[min][j_start - i].number_of_move =1;
            }
            min++;
        }
    }

    /**
     *  select area for move in first section of cordinate system
     * @param min area of avatar
     * @param i_start position (row) of avatar
     * @param j_start position (col) of avatar
     * @param model avatar
     */
    public static void getfirstCorginateSystemItems(int min, int i_start, int j_start, BaseModel model ) {
        for (int index = 0  ;index < model.getAtack_square() + 1 ;index ++) {
            if (min < 0){
                min++;
                continue;
            }
            if(min > 8){
                break;
            }
            for (int i  = 0 ; i <= index; i++){
                if (j_start+ i  > 8 ){
                    continue;
                }
                if (Start.shemaArray[min][j_start + i].baseModel.getClass().getName() != "BaseModel"){
                    continue;
                }
                else{
                    Start.shemaArray[min][j_start + i].posibletomove = 1;
                }
                Start.shemaArray[min][j_start + i].number_of_move =1;
            }
            min++;
        }
    }
}
