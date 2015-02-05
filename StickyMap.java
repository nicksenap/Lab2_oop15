import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * The map of coordinates where particles will stick.  This is a binary image.
 */
class StickyMap extends BufferedImage {
   Graphics2D graph;          // Graphics object for us to draw with.
   Ellipse2D.Double stamp;    // A stamp for us to draw sticky blobs.
   Double radius;             // The radius of a sticky blob.

   public static Color NON_STICKY = Color.BLACK;     // The non-sticky colour.
   public static Color STICKY = Color.WHITE;         // The sticky colour.

   /**
    * Creates a non-sticky/sticky colour model.
    */
 /*  private static IndexColorModel stickyColorModel() {
      // We only need two colours: non-sticky and sticky.

      byte[] b = new byte[2];
      b[NON_STICKY] = NON_STICKY;
      b[STICKY] = STICKY;
      return new IndexColorModel( 1, 2, b, b, b );
   }*/

   /**
    * The constructor; creeates a map of the specified size.
    */
   public StickyMap( Integer psize, Integer xsize, Integer ysize ) {
      super( xsize + 1, ysize + 1, BufferedImage.TYPE_BYTE_BINARY );

      this.graph = createGraphics();
      this.graph.setBackground( NON_STICKY );
      this.graph.setColor( STICKY );

      this.stamp = new Ellipse2D.Double( 0.0, 0.0, 2.0 * psize, 2.0 * psize );
      this.radius = psize.doubleValue();
   }

   /**
    * Marks a blob as sticky, centred at the given coordinate.
    */
   void addStickyBlob( Double x, Double y ) {
      this.stamp.x = x - radius;
      this.stamp.y = y - radius;
      this.graph.fill( stamp );
   }

   /**
    * Queries whether the given coordinate is sticky.
    */
   Boolean isSticky( Double x, Double y ) {
      // Round the coordinate to int.
      x += 0.5;
      y += 0.5;
      Integer xint = x.intValue(), yint = y.intValue();

      // Is it sticky?
      return getRGB( xint, yint ) == STICKY.getRGB();
   }
}
