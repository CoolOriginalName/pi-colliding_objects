import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.GraphicsConfiguration;
/**
 * Write a description of class MyCanvas here.
 *
 * @author Bbtheboulder
 * @version 09.07.25
 */
class MyCanvas extends Canvas {
    public static final int heightAll = 850;
    public static final int size = 100;
    public static final int initial_position1 = 400;
    public static final int initial_position2 = 1920-size-10;

    private static final int TIMER_DELAY_MILLIS = 1000 / 60; // 30 FPS

    private int mass1 = 1;
    private int mass2 = 1;
    private float v1 = 0;
    private float v2 = -10;
    private int collisioncount = 0;
    private float x1 = initial_position1;
    private float x2 = initial_position2;
    private String txt = "A";
    private int canvasSize;
    BufferStrategy bs;
    private final Timer timer;
    private boolean buffer = false;
    

    public MyCanvas(int pcanvasSize) {
        setVisible(true);
        canvasSize = pcanvasSize;
        setBackground(Color.BLACK);
        setSize(canvasSize, canvasSize);

        timer = new Timer(TIMER_DELAY_MILLIS, (event) -> {
            collision();
            System.out.println(collisioncount);
            movement();
            repaint();
        });
        timer.start();
    }
    
    public void newVelocitiesObject(){
        //Calculations for end formula
        float msum=(mass1+mass2);     
        float a11=(mass1-mass2)/msum; 
        float a12=2*mass2/msum;
        float a21=2*mass1/msum;
        float a22=(mass2-mass1)/msum;

        float u1=a11*v1+a12*v2; //New Velocity of m1 after collision
        float u2=a21*v1+a22*v2; //New Velocity of m2 after collision
        v1 = u1;
        v2 = u2;
    }

    /**
     * if m1 touches m2, new velocities
     * if m1 touches wall, new velocity
     */
    public void collision(){
        if (buffer == false){
            createBufferStrategy(2);
            bs = this.getBufferStrategy();
            buffer = false;
        }
        if(x1+100 >= x2){
            collisioncount = collisioncount +1;
            newVelocitiesObject();
        }else if(x1==0){
            collisioncount = collisioncount +1;
            v1= -v1;
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2=null;
        do{
            g2 = (Graphics2D) bs.getDrawGraphics();
            draw(g2);
            g2.dispose();
        } while (bs.contentsRestored()) ;
        bs.show();
    }
        
    public void draw(Graphics2D g2){
        g2.setColor(Color.BLUE);
        g2.fillRect((int)x1, (heightAll-100), 100, 100);
        g2.fillRect((int)x2, (heightAll-size), size, size);
        g2.drawLine(0, heightAll+3, canvasSize, heightAll+3);

        int stringY1 = heightAll - 105;
        g2.drawString("masse: " + mass1 + "kg", x1, stringY1);
        int stringY2 = heightAll - 105;
        g2.drawString("masse: " + mass2 + "kg", x2, stringY2);
    }
    
    public void movement(){
        x1 = x1 + v1;
        x2 = x2 + v2;
    }

    private void stopAnimation() {
        timer.stop();
    }
}