package Templates;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import javax.swing.JPanel;
public class Button extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	boolean activado = false;
	Color backgroundColor,hoverColor,tmpColor;
	String texto;
	public Button() {}
	public Button(String texto) {
		this.texto = texto;
	}
	public void locationSize(int x,int y,int w,int h) {
		this.setBounds(x,y,w,h);
		this.setLayout(null);
		this.setVisible(true);
	}
	public void text(Color color,int tamano) {
		this.add(new Label(0,0,this.getWidth(),this.getHeight(),texto,tamano));
	}
	public void Icon(String icon) {
		this.add(new IconImage(icon,6,6,this.getWidth() - 12,this.getHeight() - 12));
	}
	public void setDesign(Color colorFondo) {
		this.setOpaque(false);
        this.setPreferredSize(new Dimension(100, 50));
        backgroundColor = colorFondo;
		tmpColor = backgroundColor;
	}
	public void setHoverColor(Color color) {
		hoverColor = color;
		this.addMouseListener(this);
	}
	protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape shape = new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),getHeight(),getHeight());
        g2d.setColor(backgroundColor);
        g2d.fill(shape);
        super.paintComponent(g);
    }
	public void mouseEntered(MouseEvent e) {
        backgroundColor = hoverColor;
		this.repaint();
	}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {
		backgroundColor = tmpColor;
		this.repaint();
	}
}