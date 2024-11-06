package Templates;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
public class Label extends JLabel {
	private static final long serialVersionUID = 1L;
	public Label(int x,int y,int w,int h,String texto,Color colorF,int fuente,boolean center) {
		this.setText(texto);
		this.setForeground(colorF);
		this.setBounds(x,y,w,h);
		this.setFont(new Font("Tahoma",Font.BOLD,fuente));
		if(center) {
			this.setHorizontalAlignment(JLabel.CENTER);
		}
		this.setVerticalAlignment(JLabel.CENTER);
	}
	public Label(int x,int y,int w,int h,String texto,Color colorF,int fuente) {
		this.setText(texto);
		this.setForeground(colorF);
		this.setBounds(x,y,w,h);
		this.setFont(new Font("Tahoma",Font.BOLD,fuente));
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);
	}
	public Label(int x,int y,int w,int h,String texto,Color colorF) {
		this.setText(texto);
		this.setForeground(colorF);
		this.setBounds(x,y,w,h);
		this.setFont(new Font("Tahoma",Font.BOLD,16));
		this.setHorizontalAlignment(JLabel.LEFT);
		this.setVerticalAlignment(JLabel.CENTER);
	}
	public Label(int x,int y,int w,int h,String texto,int fuente) {
		this.setText(texto);
		this.setForeground(Colors.WHITE);
		this.setBounds(x,y,w,h);
		this.setFont(new Font("Tahoma",Font.PLAIN,fuente));
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);
	}
}