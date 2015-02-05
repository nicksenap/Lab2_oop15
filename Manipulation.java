import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Object to store the simulation controls.
 * It's a JPanel, containing the various controls, and implementing various
 * Listeners in order to react to changes.
 */
public class Manipulation extends JPanel implements ActionListener, ItemListener, AdjustmentListener {
   BrownAnimation parent;                    // The parent window.
   JScrollBar speedbar,                      // The different scrollbars.
            stepbar, sizebar, particlesbar;

   /**
    * The one and only constructor.  Takes the parent animation class that
    * creates it as argument.
    */
   public Manipulation( BrownAnimation p ) {
      super();          // Do the inherited constructiony stuff.

      this.parent = p;  // Store the parent for the future.

      // This is a JPanel, and thus needs to be laid out.
      setLayout( new GridBagLayout() );
      GridBagConstraints c = new GridBagConstraints();
      setSize( 350, 450 );

      // Create the scrollbar to control the speed of the simulation.
      speedbar = new JScrollBar( JScrollBar.HORIZONTAL, 400, 35, 0, 500 );
      speedbar.addAdjustmentListener( this );
      speedbar.setPreferredSize( new Dimension( 350, 19 ) );
      c.gridx = 0;
      c.gridy = 0;
      c.fill = GridBagConstraints.HORIZONTAL;
      add( speedbar, c );

      c.gridx = 1;
      c.gridy = 0;
      add( new JLabel( "Speed" ), c );


      // Create the scrollbar to control the step length of the simulation.
      stepbar = new JScrollBar( JScrollBar.HORIZONTAL, 10, 10, 0, 100 );
      stepbar.addAdjustmentListener( this );
      stepbar.setPreferredSize( new Dimension( 350, 19 ) );
      c.gridx = 0;
      c.gridy = 1;
      c.fill = GridBagConstraints.HORIZONTAL;
      add( stepbar, c );

      c.gridx = 1;
      c.gridy = 1;
      add( new JLabel( "Step length" ), c );


      // Create the scrollbar to control the particle size of the simulation.
      sizebar = new JScrollBar( JScrollBar.HORIZONTAL, Model.DEF_PARTICLE_SIZE, 10, 1, Model.DEF_PARTICLE_SIZE * 6 );
      sizebar.addAdjustmentListener( this );
      sizebar.setPreferredSize( new Dimension( 350, 19 ) );
      c.gridx = 0;
      c.gridy = 2;
      c.fill = GridBagConstraints.HORIZONTAL;
      add( sizebar, c );

      c.gridx = 1;
      c.gridy = 2;
      add( new JLabel( "Particle size" ), c );


      // Create the scrollbar to control the number of particles of the simulation.
      particlesbar = new JScrollBar( JScrollBar.HORIZONTAL, Model.DEF_PARTICLE_AMT, 10, 1, Model.MAX_SIZE );
      particlesbar.addAdjustmentListener( this );
      particlesbar.setPreferredSize( new Dimension( 350, 19 ) );
      c.gridx = 0;
      c.gridy = 3;
      c.fill = GridBagConstraints.HORIZONTAL;
      add( particlesbar, c );

      c.gridx = 1;
      c.gridy = 3;
      add( new JLabel( "Particle amount" ), c );


      // Create a checkbox to pause the simulation.
      JCheckBox cb = new JCheckBox( "Pause", false );
      cb.addItemListener( this );
      c.gridx = 0;
      c.gridy = 4;
      add( cb, c );


      // Create a button to centre all the particles.
      JButton button = new JButton( "Centre" );
      button.setActionCommand( "centre" );
      button.addActionListener( this );
      c.gridx = 1;
      c.gridy = 4;
      add( button, c );


      // Create a button to scatter all the particles.
      button = new JButton( "Scatter" );
      button.setActionCommand( "scatter" );
      button.addActionListener( this );
      c.gridx = 2;
      c.gridy = 4;
      add( button, c );


      setVisible( true );     // Show it all.
   }

   /**
    * Listener function for the scrollbars.
    */
   public void adjustmentValueChanged( AdjustmentEvent e ) {
      Object source = e.getSource();

      // Do various things depending on which scrollbar changed.
      if( source == this.speedbar )
         this.parent.model.timestep = 500 - e.getValue();
      else if( source == this.stepbar )
         this.parent.model.L = e.getValue() / 10.0;
      else if( source == this.sizebar ) {
         this.parent.model.particleSize = e.getValue();
         this.parent.model.unstickAll();
      }
      else if( source == this.particlesbar ) {
         this.parent.model.setSize( e.getValue() );
         this.parent.model.unstickAll();
      }
   }

   /**
    * Listener function for the buttons.
    */
   public void actionPerformed( ActionEvent e ) {
      if( "centre".equals( e.getActionCommand() ) )
         this.parent.model.centreAll();
      else if( "scatter".equals( e.getActionCommand() ) )
         this.parent.model.scatterAll();
   }

   /**
    * Listener function for the pause checkbox.
    */
   public void itemStateChanged( ItemEvent e ) {
      if( e.getStateChange() == ItemEvent.DESELECTED )
         this.parent.setPaused( false );
      else if( e.getStateChange() == ItemEvent.SELECTED )
         this.parent.setPaused( true );
   }
}
