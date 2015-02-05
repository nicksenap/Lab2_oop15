/**
 * Simulation class, a thread that advances the simulation.
 */
class Simulation extends Thread {
   private BrownAnimation parent;      // The animation parent object.
   Boolean paused;                     // Whether the simulation is paused.

   /**
    * Creates and starts the simulation.
    */
   Simulation( BrownAnimation p ) {
      this.parent = p;
      this.paused = false;

      this.start();
   }

   /**
    * The thread run.
    */
   public void run() {
      while( true ) {               // Go on forever...
         // As long as we're not paused, we need to move the model's particles,
         // and repaint the view.
         if( !paused ) {
            this.parent.model.moveAll();
            this.parent.view.repaint();
         }

         // Wait according to the model's time step before doing it again.
         try {
            sleep( this.parent.model.timestep );
         }
         catch( InterruptedException e ) {
         }
         finally { }
      }
   }
}
