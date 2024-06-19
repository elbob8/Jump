package jump;


import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Elbob
 */
public class Game_Panel extends JPanel implements KeyListener {
    
    private int xplayer = 50,yplayer = 450;
    private float plyerYdir = 1f;
    private int xenemy = 800;
    private int xboss = 900;
    private float enemyYdir = 2f;
    private boolean play = true;
    private boolean gameover = false;
    private int Score = 0;
    private boolean boss = false;
    private Color color = new Color(150,20,90);
    private Random random;
    
    public Game_Panel(){
        random = new Random();
        addKeyListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);
        //startGame
        start();
        

        //Player
        g2.setColor(Color.BLUE);
        g2.fillRect(xplayer, yplayer, 25, 25);
        g2.fillRect(xplayer-12, yplayer, 10, 25);
        g2.fillRect(xplayer+27, yplayer, 10, 25);
        g2.fillOval(xplayer, yplayer-25, 25, 25);
        g2.setColor(Color.WHITE);
        g2.fillOval(xplayer+5, yplayer-20, 5, 5);
        g2.fillOval(xplayer+15, yplayer-20, 5, 5);
        g2.drawLine(xplayer+20, yplayer-10, xplayer+5, yplayer-10);
        
        //enemy
        g2.setColor(Color.red);
        g2.fillRect(xenemy, 480, 25, 25);
        g2.drawLine(xenemy, 480, xenemy-3, 470);
        g2.drawLine(xenemy+24, 480, xenemy+27, 471);
        g2.setColor(Color.WHITE);
        g2.fillOval(xenemy+5, 485, 5, 5);
        g2.fillOval(xenemy+15, 485, 5, 5);
        g2.drawLine(xenemy+20, 495, xenemy+5, 495);
        
        //boss
        color = getRndColor();
        g2.setColor(color);
        g2.fillRect(xboss, 460, 25, 60);
        g2.fillRect(xboss-12, 480, 10, 20);
        g2.fillRect(xboss+27, 480, 10, 20);
        g2.setColor(Color.WHITE);
        g2.fillOval(xboss+5, 470, 5, 5);
        g2.fillOval(xboss+15, 470, 5, 5);
        g2.drawLine(xboss+5, 480, xboss+20,480);
        
        //player
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 500, getWidth(), 25);
        
        //play
        if(play){
            yplayer += plyerYdir*2;
            
            if(Score == 200){
               boss = true;
               xboss -= enemyYdir;
            }else{
                
                if(boss){
                    if(Score >= 350){
                            xboss -= enemyYdir*2;
                        if(Score >=500){
                            xenemy -= enemyYdir*2;
                        }
                    }else{
                        xboss -= enemyYdir;
                    }
                }else{
                    xenemy -= enemyYdir;
                }
            }
        }
        if(gameover){
           g2.setColor(Color.red);
           g2.setFont(new Font("Ink Free",Font.BOLD,50));
           g2.drawString("GameOver", 200, 200);
           
           g2.setColor(Color.RED);
           g2.setFont(new Font("Ink Free",Font.BOLD,20));
           g2.drawString("Press Enter To Restart", 250, 250);
           
           yplayer = yplayer;
           xenemy = xenemy;
           play = false;
        }
        
        //Score
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Ink Free",Font.BOLD,20));
        g2.drawString("Score : "+Score, 10, 50);
        
       
    }
    public void start(){
        
        if(new Rectangle(xplayer,yplayer,25,25).intersects(new Rectangle(0, 500, getWidth(), 25))){
            xplayer=50; yplayer=480 ;
        }
        
        if(xenemy < 0){
            xenemy = 800;
            if(Score >= 500){
                Score+=50;
            }else{
                Score+=25;
            }
        }
        
        if(xboss < 0){
            xboss = 900;
            if(Score >= 500){
                Score+=50;
            }else{
                Score+=25;
            }
        }
        
        
        if(new Rectangle(xplayer,yplayer,25,25).intersects(new Rectangle(xenemy,480,25,25))){
            gameover = true;
        }
        
        if(new Rectangle(xplayer,yplayer,25,25).intersects(new Rectangle(xboss, 460, 25, 60))){
            gameover = true;
        }
        
    }
    
    private Color getRndColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r,g,b);
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
              if(yplayer >= 600){
                    yplayer = 600;
                }else{
                  if(play){
                      if(yplayer < 450){
                           xplayer = xplayer;
                           yplayer = yplayer;
                           yplayer += plyerYdir*20;
                        }else{
                          yplayer -= 150;
                        }
                  }
                }
                break;
            case KeyEvent.VK_ENTER:
                if(!play){
                    play = true;
                    gameover = false;
                    boss = false;
                    xenemy = 800;
                    xboss = 900;
                    Score = 0;
                }
                break;
        }
    }
    
   
    @Override
    public void keyReleased(KeyEvent e){
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}

    
}
