public class AttacAction {
    /**
     * attac opponent avataras
     * @param i_start position of avatar row in matrix
     * @param j_start position of avatar col in matrix
     * @param model curretnt avatar
     * @param team tema of player
     */
    public static  void atack(int i_start , int  j_start , BaseModel model , int team) {
        if (BaceMouseControllerFunction.checkClickedPixel(2, i_start, j_start)) {
            if (Start.shemaArray[MouseController.i_start][MouseController.j_start].attachShema == 1) {
                if (Start.shemaArray[MouseController.i_start][MouseController.j_start].baseModel.helty + Start.shemaArray[MouseController.i_start][MouseController.j_start].baseModel.armor > Start.shemaArray[MouseController.avatar_i][MouseController.avatar_j].baseModel.ataka) {
                    if (Start.shemaArray[MouseController.i_start][MouseController.j_start].baseModel.armor < Start.shemaArray[MouseController.avatar_i][MouseController.avatar_j].baseModel.ataka) {
                        int attacPoint = Start.shemaArray[MouseController.avatar_i][MouseController.avatar_j].baseModel.ataka - Start.shemaArray[MouseController.i_start][MouseController.j_start].baseModel.armor;
                        Start.shemaArray[MouseController.i_start][MouseController.j_start].baseModel.helty -= attacPoint;
                        Start.shemaArray[MouseController.i_start][MouseController.j_start].baseModel.armor = 0;
                        addPoint(team);
                    }
                } else {
                    Start.avatarDamage.add(Start.shemaArray[MouseController.i_start][MouseController.j_start].baseModel );
                    Start.shemaArray[MouseController.i_start][MouseController.j_start].baseModel = new BaseModel();
                    addPoint(team);
                }
            } else {
                BaceMouseControllerFunction.refreshNumberOfMOve();
            }
        }
    }

    /**
     *  mark avatar who can attac
     * @param i_start position of avatar row in matrix
     * @param j_start position of avatar col in matrix
     * @param model curretnt avatar
     * @param team tema of avatar
     */
    public static void atackVisual(int i_start , int  j_start , BaseModel model , int team) {
        int min_i = i_start - model.getAtack_square();
        int max_i = i_start + model.getAtack_square();
        int min_j = j_start - model.getAtack_square();
        int max_j = j_start + model.getAtack_square();
        if (Start.shemaArray[i_start][j_start].baseModel.team == team){
            return;
        }
        if (min_i > 0 ){
            addBAttlePlace(min_i,j_start  ,team);
        }
        if (max_i < 7 ){
            addBAttlePlace(max_i,j_start,team);
        }
        if (min_j > 0 ) {
            addBAttlePlace(i_start,min_j,team);
        }
        if (max_j < 9){
            addBAttlePlace(i_start,max_j,team);
        }
        MouseController.avatar_i = i_start;
        MouseController.avatar_j = j_start;
        Start.addElements(Start.gs, true);
        MouseController.changeTeam();
    }

    /**
     *
     * @param index_i position of avatar row in matrix
     * @param index_j position of avatar col in matrix
     * @param team tema of avatar
     */
    private static  void addBAttlePlace(int index_i , int index_j , int team) {
        if(Start.shemaArray[index_i][index_j].baseModel.getClass().getName() != "BaseModel" &&
                Start.shemaArray[index_i][index_j].baseModel.team == team){
            Start.shemaArray[index_i ][index_j].attachShema = 1;
        }
    }

    /**
     *
     * @param team tema of avatar
     */
    private static void addPoint(int team) {
        switch (team)
        {
            case 1: Start.pointPlayer1++; break;
            case 2 : Start.pointPlayer2++ ; break;
        }
        BaceMouseControllerFunction.refreshNumberOfMOve();
    }
}
