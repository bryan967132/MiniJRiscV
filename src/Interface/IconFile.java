package Interface;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Controller.Controller;
import Templates.Colors;
import Templates.IconImage;
import Templates.Icons;
public class IconFile extends JPanel implements MouseListener, Comparable<IconFile> {
    private static final long serialVersionUID = 1L;
	boolean activado = false;
	Color backgroundColor,hoverColor,tmpColor;
	Controller controller;
	File file;
	IDE ide;
	public int id;
	public String json;
	String texto;
	public String name,path;
	public IconFile() {
        this.setBackground(null);
    }
	public IconFile(int id,File file,IDE ide,Controller controller) {
		this.id = id;
		this.file = file;
		this.ide = ide;
		this.controller = controller;
		this.path = file.getAbsolutePath();
		this.name = file.getName();
        this.setBackground(null);
    }
	public IconFile(String texto) {
		this.texto = texto;
        this.setBackground(null);
	}
	public void locationSize(int x,int y,int w,int h) {
		this.setBounds(x,y,w,h);
		this.setLayout(null);
		this.setVisible(true);
		this.text(name,Colors.WHITE,12);
	}
	public void text(Color color,int tamano) {
        Icon();
		this.add(new PJName(40,0,this.getWidth() - 30,this.getHeight(),texto,tamano));
	}
    public void text(String texto,Color color,int tamano) {
        Icon();
		this.add(new PJName(40,0,this.getWidth() - 30,this.getHeight(),texto,tamano));
	}
	private void Icon() {
		this.add(new IconImage(Icons.FILE1,12,3,this.getHeight() - 6,this.getHeight() - 6));
	}
	public void setHoverColor(Color color) {
		hoverColor = color;
		this.addMouseListener(this);
	}
	public void lookCode() {
		String text = controller.readInput(path);
		if(!text.equals("java.io.FileNotFoundException")) {
			ide.editorArea.editor.setText(controller.readInput(path));
			ide.setFormat();
			ide.indexFilePJ = id;
			ide.updateTag();
			return;
		}
		JOptionPane.showMessageDialog(null,"No se encontró el archivo.","Archivo no encontrado.",JOptionPane.ERROR_MESSAGE);
		controller.pjs.remove(id);
		controller.serialize();
		controller.deserialize(ide);
		ide.lookPJFiles();
	}
	@Override
	public int compareTo(IconFile iconFile) {
        return this.name.compareTo(iconFile.name);
    }
	public String toString() {
		return "FILENAME: " + name + "\nPATH: " + path;
	}
	public void mouseEntered(MouseEvent e) {
        this.setBackground(hoverColor);
	}
	public void mouseClicked(MouseEvent e) {
        //if(e.getClickCount() == 2) {
            lookCode();
        //}
    }
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {
        this.setBackground(null);
	}
}
class PJName extends JLabel {
	private static final long serialVersionUID = 1L;
	public PJName(int x,int y,int w,int h,String txt,int fuente) {
		this.setText(txt);
		this.setForeground(Colors.WHITE);
		this.setBounds(x,y,w,h);
		this.setFont(new Font("Tahoma",Font.PLAIN,fuente));
		this.setHorizontalAlignment(JLabel.LEFT);
		this.setVerticalAlignment(JLabel.CENTER);
	}
}