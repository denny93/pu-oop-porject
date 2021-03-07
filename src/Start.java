import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Start extends JPanel {

    public static Shema[][] shemaArray = new Shema[7][9];
    public static AvatarPOsition[] avatar = new AvatarPOsition[3];
    static JFrame frame;
    public static Graphics gs;
    public static int pointPlayer1 = 0;
    public static int pointPlayer2 = 0;
    public static ArrayList<BaseModel> avatarDamage = new ArrayList<BaseModel>();

    public  static void main(String[] args)
    {
        shemaArray= new Shema[7][9];
        createJframe();
        refreshPage();
    }

    public  void paint(Graphics g){
        gs = g;
        addElements(g , false );
    }

    /**
     * generate and visualize element in game area
     * @param g grafic parameter to render elements in form
     * @param modes mode of paint true = repaint false no repaint
     */
    public  static void addElements(Graphics g, boolean modes) {
        if (modes) {
            frame.repaint();
        }
        int x = 0 ;
        int y = 0;
        for(int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                addAvatars(x , y , i , j , g );
                addMovePixels(x, y , i , j , g);
                if (shemaArray[i][j].attachShema == 1)
                {
                    g.setColor(Color.red);
                    g.fillRect(x, y, 70, 70);
                    g.setColor(Color.black);
                    g.drawRect(x, y, 70, 70);
                }
                shemaArray[i][j].startX = x;
                shemaArray[i][j].startY = y;
                shemaArray[i][j].endX = x+70;
                shemaArray[i][j].endY = y+70;

                x+=70;
            }
            y+= 70;
            x = 0 ;
        }
        addActions(g);
        addPlayerOneInfoPoint(700,  0 ,g );
        addPlayerTwoInfoPoiunt(800,0,g);
        checkForWinner();
    }

    /**
     * add action of avatar in game
     * @param g grafic parameter to render elements in form
     */
    private static void addActions(Graphics g) {
        int y = 100;
        int x = 700;
        String[] arr = {"R", "D" , "E"};
        String[] arr_actiuon = {"Atack", "Move" , "Heat"};

        if (checkAvatarCount()) {
            addActionWindows(g, x, y,arr);
        }
        else
        {
            addActionWindows(g, x, y,arr_actiuon);
        }
    }

    /**
     * add move pixels green for free to move and red to danger zone
     * @param x position for render element
     * @param y position for render element
     * @param i row of matrix
     * @param j col of matrix
     * @param g grafic parameter to render elements in form
     */
    private static void addMovePixels(int x, int y, int i, int j, Graphics g) {
        if(shemaArray[i][j].number_of_move != 0 )
        {
            if (shemaArray[i][j].danger == 1)
            {
                g.setColor(Color.red);
                g.fillRect(x, y, 70, 70);
                g.setColor(Color.black);
                g.drawRect(x, y, 70, 70);
            }
            else
            {
                g.setColor(Color.green);
                g.fillRect(x, y, 70, 70);
                g.setColor(Color.black);
                g.drawRect(x, y, 70, 70);
            }
        }
    }

    /**
     * model for auto add avatars in game
     * @param x position for render element
     * @param y position for render element
     * @param i row of matrix
     * @param j col of matrix
     * @param g grafic parameter to render elements in form
     */
    private static void addAvatars(int x, int y, int i, int j, Graphics g) {
        if (shemaArray[i][j].baseModel.getClass().getName() == "Elf"){
            addElf( i , j ,x , y , g);
        }
        else if(shemaArray[i][j].baseModel.getClass().getName() == "LittleMan"){
            addLittleMAn( i , j ,x , y , g);

        }
        else if(shemaArray[i][j].baseModel.getClass().getName() == "Ricar"){
            addRicar( i , j ,x , y , g);
        }
        else if (shemaArray[i][j].status != 0 )
        {
            AddStatus(x , y , g);
        }
        else {
            g.setColor(shemaArray[i][j].baseModel.color);
            g.fillRect(x, y, 70, 70);
            g.setColor(Color.black);
            g.drawRect(x, y, 70, 70);
        }
    }

    /**
     * visual danger zone on move event of avatar
     * @param x position for render element
     * @param y position for render element
     * @param g grafic parameter to render elements in form
     */
    private static void AddStatus( int x, int y, Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawString("X", x + 30, y + 40);
    }

    /**
     * add ricar avatar inmatrix (game)
     * @param x position for render element
     * @param y position for render element
     * @param i row of matrix
     * @param j col of matrix
     * @param g grafic parameter to render elements in form
     */
    private static void addRicar( int i , int j ,int x, int y, Graphics g) {
        if (shemaArray[i][j].baseModel.red == 1) {
            g.setColor(Color.yellow);
        }else{
            g.setColor(Color.white);
        }
        g.fillRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawString("R", x + 30, y + 40);
    }

    /**
     * add djudje avatar inmatrix (game)
     * @param x position for render element
     * @param y position for render element
     * @param i row of matrix
     * @param j col of matrix
     * @param g grafic parameter to render elements in form
     */
    private static void addLittleMAn(int i , int j ,int x, int y, Graphics g) {
        if (shemaArray[i][j].baseModel.red == 1) {
            g.setColor(Color.yellow);
        }else{
            g.setColor(Color.white);
        }
        g.fillRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawString("D", x + 30, y + 40);

    }

    /**
     * add elf avatar inmatrix (game)
     * @param x position for render element
     * @param y position for render element
     * @param i row of matrix
     * @param j col of matrix
     * @param g grafic parameter to render elements in form
     */
    private static void addElf(int i , int j ,int x , int y, Graphics g) {
        if (shemaArray[i][j].baseModel.red == 1) {
            g.setColor(Color.yellow);
        }else{
            g.setColor(Color.white);
        }
        g.fillRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawString("E", x + 30, y + 40);
    }

    /**
     *generate box of score for player two
     * @param x position for render element
     * @param y position for render element
     * @param g grafic parameter to render elements in form
     */
    private static void addPlayerTwoInfoPoiunt(int x , int y , Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawString(String.valueOf(pointPlayer2), x + 30, y + 40);
    }

    /**
     * generate box of score for player one
     * @param x position for render element
     * @param y position for render element
     * @param g grafic parameter to render elements in form
     */
    private static void addPlayerOneInfoPoint(int x , int y , Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawString(String.valueOf(pointPlayer1), x + 30, y + 40);
    }

    /**
     * chek element in matrix for winner
     */
    private static void checkForWinner() {
        int player1 = 0 ;
        int player2 = 0;
        int totalCount = 0 ;
        for (int i = 0 ; i <  7; i++){
            for (int j = 0 ; j < 9 ; j++ ){
                if (shemaArray[i][j].baseModel.team == 1){
                    player1 ++;
                }
                else{
                    player2++;
                }
                if(shemaArray[i][j].baseModel.getClass().getName() != "BaseModel"){
                    totalCount++;
                }
            }
        }
        if (totalCount == 0 ){
            return;
        }
        GenerateMessage(player1 , player2);

    }

    /**
     * display modal form for game over
     * @param player1 player one score
     * @param player2 player two score
     */
    private static void GenerateMessage(int player1, int player2) {
        if (player1 == 0 ){
            JOptionPane.showMessageDialog(null, getStrinForPlayer(), "Winner: ", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            if (player2 == 0){
                JOptionPane.showMessageDialog(null, getStrinForPlayer(), "Winner: ", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * get all string for message
     * @return infomation of damages of player
     */
    private static String getStrinForPlayer() {
        String winner = "player 1 is winner\n";
        String plyer1Damage = getAvatarDamage(1);
        String plyer2Damage = getAvatarDamage(2);
        return winner + plyer1Damage + plyer2Damage;
    }

    /**
     * get avatar damage of attac
     * @param modes select team
     * @return
     */
    private static String getAvatarDamage(int modes) {
        String avatars = "";
        for (int i = 0 ; i < avatarDamage.size(); i++)
        {
            if (avatarDamage.get(i).team == modes) {
                avatars += avatarDamage.get(i).getClass().getName();
            }
        }
        return avatars;
    }


    /**
     * crate element for actions right of game area
     * @param x position for render element
     * @param y position for render element
     * @param g grafic parameter to render elements in form
     * @param arr infomation of difrent actions
     */
    private static void addActionWindows(Graphics g , int x , int y ,String[] arr) {
        g.setColor(Color.red);
        g.drawString("Actions", x, y -100);
        for (int index = 0; index < 3; index++) {
            avatar[index] = new AvatarPOsition(x, y, x + 70, y + 70, null, arr[index]);
            g.setColor(Color.white);
            g.fillRect(x, y, 70, 70);
            g.setColor(Color.black);
            g.drawRect(x, y, 70, 70);
            g.setColor(Color.red);
            g.drawString(arr[index], x + 30, y + 40);
            x += 80;
        }
    }

    /**
     *
     * @return check count of live avatars
     */
    public static boolean checkAvatarCount() {
        int count = 0 ;
        for(int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                if (shemaArray[i][j].baseModel.getClass().getName() != "BaseModel") {
                    count++;
                }
            }
        }
        if (count == 12)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * create frame for rendering (window)
     */
    private static void createJframe() {
        frame = new JFrame();
        frame.setSize(1000,600);
        frame.getContentPane().add(new Start());
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Component mouseClick = new MouseController()  ;
        frame.addMouseListener((MouseListener) mouseClick);
    }

    /**
     * refresh page with current infomation for danger zone ,  models (Shema) and color shema of game area
     */
    private static void refreshPage() {
        createModels();
        createColorShema();
        CreaateDangerZone();
        addElements(gs, true);
    }

    /**
     * generate ganger zone area in matrix
     */
    private static void CreaateDangerZone() {
        int max = 6 ;
        Random random  = new Random();
        for (int index = 0 ; index < max ; index ++)
        {
            int x = ThreadLocalRandom.current().nextInt(2, 5);
            int y = random.nextInt(7) ;
            if (shemaArray[x][y].danger == 1)
            {
                max ++;
                continue;
            }
            else
            {
                shemaArray[x][y].danger = 1;
            }
        }
    }

    /**
     * creae all models in game area
     */
    private static void createModels() {
        for (int i  = 0 ; i < 7 ; i++)
        {
            for (int j = 0 ; j < 9 ; j++)
            {
                shemaArray[i][j] = new Shema(0,0,0,0,new BaseModel(null));
            }
        }
    }

    /**
     * generate color of game area
     */
    private static void createColorShema() {
        boolean status = true;
        for (int index = 0 ; index < 2 ; index++)
        {
            createBattleElement(status, index , 1);
            status = false;
        }
        for (int index = 2 ; index < 5 ; index++)
        {
            for (int index_x = 0 ; index_x < 9 ; index_x++) {
                shemaArray[index][index_x].baseModel.color = Color.lightGray;
            }
        }
        status = true;
        for (int index = 5 ; index < 7 ; index++)
        {
            createBattleElement(status, index,2);
            status = false;
        }
    }

    /**
     *
     * @param status
     * @param index
     * @param playerMode
     */
    private static void createBattleElement(boolean status, int index, int playerMode) {
        for (int index_x = 0 ; index_x < 9; index_x++)
        {
            if (status)
            {
                shemaArray[index][index_x].player = playerMode;
                shemaArray[index][index_x].baseModel.color = Color.BLACK;
                status = false;
            }
            else
            {
                shemaArray[index][index_x].player = playerMode;
                shemaArray[index][index_x].baseModel.color = Color.gray;
                status = true;
            }
        }
    }
}
