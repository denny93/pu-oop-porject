public class MoveClass {
    /**
     * make move to free position in game area
     * @param i_start current position of avatar in matrix (row)
     * @param j_start current position of avatar in matrix (col)
     */
    public static void move(int i_start, int j_start ) {
        if(BaceMouseControllerFunction.checkClickedPixel(1, i_start, j_start))
        {
            if (Start.shemaArray[MouseController.i_start][MouseController.j_start].danger != 1) {
                BaseModel model_avatar = Start.shemaArray[MouseController.i_start][MouseController.j_start].baseModel;
                Start.shemaArray[MouseController.i_start][MouseController.j_start].baseModel = Start.shemaArray[MouseController.avatar_i][MouseController. avatar_j].baseModel;
                Start.shemaArray[MouseController.avatar_i][MouseController.avatar_j].baseModel = model_avatar;
            }
            else
            {
                return;
            }
        }
        BaceMouseControllerFunction.refreshNumberOfMOve();
        Start.addElements(Start.gs, true);
    }

    /**
     * add visual element in matrix for rendering visual free posiiton for move
     * @param i_start current position of avatar in matrix (row)
     * @param j_start current position of avatar in matrix (col)
     * @param team 1 for team of player one and 2 for second player team
     * @param model current model(avatar)
     */
    public static void moveVisual(int i_start, int j_start , int team , BaseModel model) {
        if (Start.shemaArray[i_start][j_start].baseModel.team == team)
        {
            return;
        }
        changeShemaForMove(i_start,j_start , model);
        Start.addElements(Start.gs, true);
        MouseController.changeTeam();
        MouseController.modePlay++;
        return;
    }

    /**
     * generate area for move for every avatar in 4 section of cordinate system
     * @param i_start current position of avatar in matrix (row)
     * @param j_start current position of avatar in matrix (col)
     * @param model current model(avatar)
     */
    public static void changeShemaForMove(int i_start , int j_start , BaseModel model) {
        int min = i_start - model.getAtack_square();
        MouseController.avatar_i = i_start;
        MouseController.avatar_j = j_start;
        CordinateSystemVisual.getfirstCorginateSystemItems(min,i_start, j_start , model);
        CordinateSystemVisual.getSecondCorginateSystemItems(min,i_start, j_start , model);
        CordinateSystemVisual.getThirdorginateSystemItems(min,i_start, j_start , model);
        CordinateSystemVisual.getfourCorginateSystemItems(min,i_start, j_start , model);
    }
}
