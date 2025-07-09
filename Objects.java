/**
 * Write a description of class Objekt here.
 *
 * @author Leo 
 * @version 09.07.2025
 */
public class Objects
{
    public int x1;
    public int x2;
    public int collisioncount = 0; 
    public int m1 = 1;            //unchangable mass of unmoving object
    public int m2 = 100;          //changable mass of moving object            
    public float v1 = 0;          //Initial Velocity of m1
    public float v2 = -1;         // Initial Velocity of m2
    public String txt = "A"; 
    
    public Objects()
    {
    }

    /**
     * Velocities after collision
     */
    public void newVelocitiesObject()
    {
        //Calculations for end formula
        float msum=(m1+m2);     
        float a11=(m1-m2)/msum; 
        float a12=2*m2/msum;
        float a21=2*m1/msum;
        float a22=(m2-m1)/msum;
        
        float u1=a11*v1+a12*v2; //New Velocity of m1 after collision
        float u2=a21*v1+a22*v2; //New Velocity of m2 after collision
        v1 = u1;
        v2 = u2;
    }
    
    /**
     * if m1 touches m2, new velocities
     * if m1 touches wall, new velocity
     */
    public void Code()
    {
        if(x1+100 >= x2)
        {
            collisioncount = collisioncount +1;
            newVelocitiesObject();
        }else if(x1==0){
            collisioncount = collisioncount +1;
            v1= -v1;
        }
    }
}