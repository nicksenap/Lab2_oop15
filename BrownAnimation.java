import javax.swing.*;
import java.awt.*;

/**
 * The main Brownian animation class.  Creates the main window and controllers.
 */
public class BrownAnimation extends JFrame {
   Model model;       // The simulation model used.
   View view;         // The display area of the simulation.
   Simulation simul;  // The simulation running the model.

   /**
    * Creates the main Brownian animation window, and sets everything up.
    */
   public BrownAnimation() {
      super();    // Super-duper!

      // Create a new model, with a 300-by-300 area, and initially the default number of particles.
      model = new Model( 300, 300 );
      model.setSize( Model.DEF_PARTICLE_AMT );

      // Create a view to go with the model, with nice colours.
      view = new View( model );
      view.setPreferredSize( new Dimension( 300, 300 ) );
      view.setOpaque( true );

      // Create some manipulation controls.
      Manipulation man = new Manipulation( this );

      // Add them all nicely in the window.
      setLayout( new GridBagLayout() );
      GridBagConstraints c = new GridBagConstraints();
       setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

      setTitle( "Brownian Motion" );
      setSize( 400, 350 );
      setBackground( Color.DARK_GRAY );

      c.gridx = 0;
      c.gridy = 0;
      c.ipadx = 5;
      c.ipady = 5;
      add( man, c );

      c.gridx = 0;
      c.gridy = 1;
      add( view, c );

      pack();
      setVisible( true );

      // Start the simulation by creating a Simulation object.
      this.simul = new Simulation( this );
   }

   /**
    * This method sets the paused status of the simulation.
    */
   public void setPaused( Boolean paused ) {
      simul.paused = paused;
   }

   /**
    * The main entry point of the program.  Creates a BrownAnimation.
    */
   public static void main( String[] arg ) {
      new BrownAnimation();
   }
}
