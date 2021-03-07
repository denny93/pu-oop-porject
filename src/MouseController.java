import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 *This is class for control all game by clicking over one of the graphics buckets
 * in front end
 * we use mouse press to handle the event from clicking over jframe
 */

public class MouseController extends JComponent implements MouseListener {
    static int x = 0;
    static int y = 0;
    boolean addAvatars = false;
    int mode = 0 ;
    int player_mode = 1;
    int state_action = 0;
    static int modePlay = 0 ;
    BaseModel model = null;
    int action = 0;
    static int i_start = 0 ;
    static int j_start = 0 ;

    static int team = 1;
    static int avatar_i = 0 ;
    static int avatar_j = 0 ;
    String avatar = "";

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX()  -20;
        y = e.getY()  - 20;
        if(checkAvatarCout()) {
            if (x > 650) {
                if (checkCklickAvatar()) {
                    addAvatars = true;
                    changeShema(player_mode);
                }
            }
            if (addAvatars) {
                mode++;
            }
            if (mode > 0) {
                if (addElements()) {
                    mode = 0;
                    addAvatars = false;
                }
            }
        }
        else{
               actionsInGAme();
        }

    }

    /***
     * active when all avarat is in game area  and make visaul and current action by  position of click of pointer
     */
    private void actionsInGAme() {
        if (x > 650) {
            int isnotElement = 0;
            checkCklickAction();
            switch (state_action) {
                case 1:
                    action = 1;
                    break;
                case 2:
                    action = 2;
                    break;
                case 3:
                    action = 3;
                    break;
                default:isnotElement = 1 ; break;
            }
            if (isnotElement == 0 ) {
                modePlay = 1;
            }
            return;
        }
        if (modePlay > 0){
            if (modePlay == 1) {
                checkSeelctedEelement();

                switch(action){
                    case  1 :AttacAction.atackVisual(i_start ,  j_start , model , team);modePlay++; break;
                    case  2 : MoveClass.moveVisual(i_start, j_start , team , model);modePlay++; break;
                    case  3 : HealtClass.heal(x, y );modePlay = 0 ; break;
                }

            }
            else if (modePlay > 1) {
                switch(action){
                    case  1 :AttacAction.atack(i_start ,  j_start , model , team); break;
                    case  2 : MoveClass.move(i_start, j_start); break;
                }
                BaceMouseControllerFunction.refreshNumberOfMOve();
                modePlay = 0;
            }
        }
    }

    /**
     * check if element id avatar or not
     */
    private void checkSeelctedEelement() {
        for (int i = 0 ; i < 7 ; i++)
        {
            for(int j = 0 ; j < 9 ; j++)
            {
                if (Start.shemaArray[i][j].startX<= x &&Start.shemaArray[i][j].startY<= y
                        && Start.shemaArray[i][j].endX >=  x &&Start.shemaArray[i][j].endY >= y ) {
                    if (Start.shemaArray[i][j].baseModel.getClass().getName()!= "BaseModel")
                    {
                        i_start = i ;
                        j_start = j;
                        model = Start.shemaArray[i][j].baseModel;
                        return;
                    }
                }
            }
        }
        model = null;
    }

    /**
     * change team for action
     */
    public  static void changeTeam() {
        if (team == 1)
        {
            team = 2;
            changeColorRed(1);
        }
        else
        {
            team = 1;
            changeColorRed(2);
        }
    }

    /**
     *  generate nex avatar for move in matrix
     * @param index this is add nex avatar for action by team id
     */
    private static void changeColorRed(int index) {
        for (int i = 0 ; i < 7 ; i++)
        {
            for (int j = 0 ; j < 9 ; j++)
            {
                if (Start.shemaArray[i][j].baseModel.team == index)
                {
                    Start.shemaArray[i][j].baseModel.red = 1;
                }
                else
                {
                    Start.shemaArray[i][j].baseModel.red = 0;
                }
            }
        }
    }

    /**
     * get cklicked action from players
     */
    private void checkCklickAction() {
        for (int index = 0 ; index < 3 ; index++) {
            if (Start.avatar[index].startX <= x && Start.avatar[index].startY <= y
                    && Start.avatar[index].endX >= x && Start.avatar[index].endY >= y) {
                state_action = index + 1;
            }
        }
    }

    /**
     *
     * @return retunr avaable avatars adding in game
     */
    private boolean checkAvatarCout() { return Start.checkAvatarCount();}

    /**
     * add element in matrix
     * @return return true if avatar is enter in matrix and false is is't
     */
    private Boolean addElements()
    {
        boolean isAdded = false;
        for (int i = 0 ; i < 7 ; i++) {
            for (int j = 0; j < 9; j++) {
                if (Start.shemaArray[i][j].startX<= x &&Start.shemaArray[i][j].startY<= y
                        && Start.shemaArray[i][j].endX >=  x &&Start.shemaArray[i][j].endY >= y ) {
                    if (player_mode == 1){
                        if (i > 1){
                            return  false;
                        }
                    }
                    else{
                        if (i< 5){
                            return false ;
                        }
                    }
                    if (Start.shemaArray[i][j].baseModel.getClass().getName() == "BaseModel") {
                       addAvatar(i , j);
                        isAdded = true;
                        break;
                    }
                }
            }
            if (isAdded)
            {
                refreshShema();
                break;
            }
        }
        return isAdded;
    }

    /**
     * add avatar in matrix
     * @param i position of pixel in maxtrix (row)
     * @param j position of pixel in maxtrix (col)
     */
    private void addAvatar(int i , int j) {
        switch (avatar) {
            case "D":
                Start.shemaArray[i][j].baseModel = new LittleMan(team);
                break;
            case "R":
                Start.shemaArray[i][j].baseModel = new Ricar(team);
                break;
            case "E":
                Start.shemaArray[i][j].baseModel = new Elf(team);
                break;
        }
        if(team ==  1){
            team = 2;
        }
        else
        {
            team = 1;
        }
    }

    /**
     * refresh element when avatar is enter
     */
    private void refreshShema() {
        for (int i = 0 ; i < 7 ; i++) {
            for (int j = 0; j < 9; j++) {
                Start.shemaArray[i][j].status = 0 ;
            }
        }
        if(player_mode == 1)
        {
            player_mode = 2;
        }
        else
        {
            player_mode = 1;
        }
        Start.addElements(Start.gs , true);
    }

    /**
     * check if user select avatara from action area or not
     * @return true if is cklicked avarat or false if is't
     */
    private boolean checkCklickAvatar() {
        boolean isCklicked = false;
        for (int index = 0 ; index < 3 ; index++)
        {
            if (Start.avatar[index].startX<= x &&Start.avatar[index].startY<= y
                    && Start.avatar[index].endX >=  x &&Start.avatar[index].endY >= y )
            {
                if(!checkCount(Start.avatar[index].type))
                {
                    isCklicked = false;
                    break;
                }
                avatar = Start.avatar[index].type;
                isCklicked = true;
                break;
            }
        }
        return  isCklicked ;
    }

    /**
     * check count of avatars gor adding two from every type
     * @param type
     * @return true is is not two avatar of same type or false if is't
     */
    private boolean checkCount(String type) {
        String className = "";
         switch(type){
            case "R" :className = "Ricar";
                break;
            case "D" :className = "LittleMan";
                break;
            case "E" : className = "Elf";
                break;
        }
        int count = 0 ;
        for(int i = 0 ; i < 7; i++){
            for(int j = 0 ; j < 9 ; j++){
                if (Start.shemaArray[i][j].player == player_mode
                        && Start.shemaArray[i][j].baseModel.getClass().getName() == className){
                    count++;
                }
                if (count == 2){
                    return false;
                }
            }
        }
        return  true;
    }

    private void changeShema(int player_mode) {
        for (int i = 0 ; i < 7 ; i++){
            for (int j = 0 ; j < 9 ; j++){
                if (Start.shemaArray[i][j].player != player_mode){
                    Start.shemaArray[i][j].status = 1;
                }
            }
        }
        Start.addElements(Start.gs, true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
