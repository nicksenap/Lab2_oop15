import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * The view.  This is a JPanel displaying the simulation state.
 */
public class View extends JPanel {
   private Model model;       // The model we should display.

   public static Color BG_COLOUR = Color.black;       // The background colour.
   public static Color MOVING_COLOUR = Color.green;   // The colour of moving particles.
   public static Color STUCK_COLOUR = Color.yellow;   // The colour of stuck particles.

   /**
    * All we need for construction is the model to use.
    */
   public View( Model m ) {
      super();
      setBackground( BG_COLOUR );
      this.model = m;
   }

   /**
    * Paints the view!
    */
   public void paintComponent( Graphics g ) {
      super.paintComponent( g );       // Do the inherited stuff first.

      // Need to do our circle-filling on a Graphics2D object, so cast.
      Graphics2D g2d = (Graphics2D)g;

      // Create an ellipse to draw at various points.
      Double size = this.model.particleSize.doubleValue();
      Ellipse2D.Double stamp = new Ellipse2D.Double( 0.0, 0.0, size, size );
      size /= 2;

      // Iterate through the model's particles.
      for( Particle p : this.model.particles ) {
         if( p == null )      // Quit when we hit the size of the model.
            return;

         // Different colour depending on stuck status.
         if( p.isMoving )
            g2d.setColor( MOVING_COLOUR );
         else
            g2d.setColor( STUCK_COLOUR );

         // Origo is at p.x, p.y, so the upper left corner is at p.(x|y) - size / 2.
         stamp.x = p.x - size;
         stamp.y = p.y - size;
         g2d.fill( stamp );
      }
   }
}
