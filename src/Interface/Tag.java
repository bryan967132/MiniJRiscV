package Interface;
import java.awt.Color;
import java.awt.event.MouseEvent;
import Controller.Controller;
import Templates.Button;
import Templates.Colors;
public class Tag extends Button {
    Button close;
    Controller controller;
    IDE ide;
    public Tag(int id,IDE ide,Controller controller) {
        this.ide = ide;
        this.controller = controller;
        this.setBounds(220,80,150,25);
        this.setLayout(null);
        this.setOpaque(false);
        this.setDesign(Colors.MEDIUMCOLOR2);
        IconFile icnf = controller.pjs.get(id);
        close = new Button("×");
        text(icnf.name,Colors.WHITE,12);
    }
    public void text(String texto,Color color,int tamano) {
        button();
		this.add(new PJName(10,0,this.getWidth() - 50,this.getHeight(),texto,tamano));
	}
    private void button() {
        close.locationSize(this.getWidth() - 25,0,25,25);
        close.text(Colors.WHITE,15);
        close.setDesign(Colors.MEDIUMCOLOR2);
        close.setHoverColor(Colors.LIGHTCOLOR);
        close.addMouseListener(this);
        this.add(close);
	}
    public void close() {
        this.removeAll();
        ide.indexFilePJ = -1;
        ide.editorArea.editor.setText("");
        ide.remove(this);
        ide.repaint();
    }
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == close) {
            close();
        }
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}