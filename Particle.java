import java.util.ArrayList;
import java.util.Random;

/**
 * A particle; keeps track of where a particle is and if it's moving.
 */
public class Particle {
   Double x, y;         // The coordinates of the particle.
   Boolean isMoving;    // Whether the particle is currently moving.

   /**
    * Creates a particle randomly within a 1*1 box.
    */
   public Particle() {
      this( Math.random(), Math.random() );
   }

   /**
    * Creates a particle with a specific coordinate.
    */
   public Particle( Double x, Double y ) {
      this.x = x;
      this.y = y;
      this.isMoving = true;
   }

   /**
    * Creates a particle with a coordinate at random within a xsize*ysize box.
    */
   public Particle( Integer xsize, Integer ysize ) {
      this();
      this.x *= xsize;
      this.y *= ysize;
   }

   /**
    * Sets the particle's position to the middle of a xsize*ysize box.
    */
   public void centre( Integer xsize, Integer ysize ) {
      this.x = xsize / 2.0;
      this.y = ysize / 2.0;
      this.isMoving = true;
   }

   /**
    * Sets the particle's position to a random coord within a xsize*ysize box.
    */
   public void scatter( Integer xsize, Integer ysize ) {
      this.x = Math.random() * xsize;
      this.y = Math.random() * ysize;
      this.isMoving = true;
   }

   /**
    * Randomly moves the particle by length L within the maxx*maxy box.
    */
   public void randomMove( Double L, Integer maxx, Integer maxy ) {
      if( !this.isMoving )    // Don't move if it's stuck.
         return;

      Double angle = Math.random() * 2 * Math.PI;     // Choose an angle.
      this.x += L * Math.cos( angle );
      this.y += L * Math.sin( angle );                // And advance coords.

      // If it went outside the box, make it stick.
      if( this.x <= 0.0 ) {
         this.isMoving = false;
         this.x = 0.0;
      }
      else if( this.x >= maxx ) {
         this.isMoving = false;
         this.x = maxx.doubleValue();
      }

      if( this.y <= 0.0 ) {
         this.isMoving = false;
         this.y = 0.0;
      }
      else if( this.y >= maxy ) {
         this.isMoving = false;
         this.y = maxy.doubleValue();
      }
   }
}
