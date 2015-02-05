import java.util.ArrayList;

/**
 * The simulation state.  Handles doing things to all particles.
 */
public class Model {
   Particle[] particles;   // The particle objects
   Integer size;           // Current size of simulation: the number of particles.
   Integer particleSize;   // The size of the particles.
   Double L;               // The distance to move a particle each step.
   Integer timestep;       // The time between redrawings.
   Integer xsize, ysize;   // The size of the drawing area.
   StickyMap stickyMap;    // A map of where particles will stick.

   public static Integer MAX_SIZE = 50000;         // Maximum number of particles we can handle.
   public static Integer DEF_PARTICLE_SIZE = 5;    // The default particle size.
   public static Integer DEF_PARTICLE_AMT = 500;   // The default number of particles.

   /**
    * Creates an empty (no particles) model with the specified particle size and
    * drawing area size.
    */
   public Model( Integer psize, Integer xsize, Integer ysize ) {
      this.particleSize = psize;
      this.size = 0;
      this.L = 1.0;
      this.timestep = 15;
      this.particles = new Particle[MAX_SIZE];
      this.xsize = xsize;
      this.ysize = ysize;
      this.stickyMap = new StickyMap( psize, xsize, ysize );
   }

   /**
    * Creates an empty (no particles) model with the default particle size and
    * drawing area size.
    */
   public Model( int xsize, int ysize ) {
      this( DEF_PARTICLE_SIZE, xsize, ysize );
   }

   /**
    * Changes the size (number of particles) of the current simulation.
    * Returns the new size of the simulation.
    */
   public Integer adjustSize( Integer n ) {
      if( n < 0 ) {     // Negative number, we need to remove particles.
         for( Integer i = 0; i > n && this.size > 0; i-- )
            this.particles[this.size--] = null;
      }
      else {            // Positive number, need to add new particles.
         for( Integer i = 0; i < n && this.size < MAX_SIZE; i++ )
            this.particles[this.size++] = new Particle( this.xsize, this.ysize );
      }

      return this.size;
   }

   /**
    * Sets the size (number of particles) of the current simulation.
    * Returns the new size of the simulation.
    */
   public Integer setSize( Integer n ) {
      return adjustSize( n - this.size );
   }

   /**
    * Randomly moves all the particles according to the step length.
    */
   public void moveAll() {
      ArrayList<Particle> toStick = new ArrayList<Particle>();    // These need to be marked as stuck after moving them all.

      for( Particle p : this.particles ) {
         if( p == null )
            break;

         // If it already is stuck, do nothing.
         if( !p.isMoving )
            continue;

         // Move it.
         p.randomMove( this.L, this.xsize, this.ysize );

         // If it stuck now (and hence hit a wall) or it is in a sticky area,
         // mark it for later.
         if( !p.isMoving || this.stickyMap.isSticky( p.x, p.y ) )
            toStick.add( p );
      }

      // Update the sticky map for the newly stuck particles.
      for( Particle p : toStick ) {
         p.isMoving = false;
         this.stickyMap.addStickyBlob( p.x, p.y );
      }
   }

   /**
    * Centres all the particles in the drawing area.
    */
   public void centreAll() {
      this.stickyMap = new StickyMap( this.particleSize, this.xsize, this.ysize );

      for( Particle p : this.particles ) {
         if( p == null )
            return;
         p.centre( this.xsize, this.ysize );
      }
   }

   /**
    * Unsticks all the particles.
    */
   public void unstickAll() {
      this.stickyMap = new StickyMap( this.particleSize, this.xsize, this.ysize );

      for( Particle p : this.particles ) {
         if( p == null )
            return;
         p.isMoving = true;
      }
   }

   /**
    * Randomly spreads all the particles over the drawing area.
    */
   public void scatterAll() {
      this.stickyMap = new StickyMap( this.particleSize, this.xsize, this.ysize );

      for( Particle p : this.particles ) {
         if( p == null )
             return;
         p.scatter( this.xsize, this.ysize );
      }
   }
}
