package Templates;
import java.awt.Color;
import javax.swing.JRadioButton;
public class RadioButton extends JRadioButton {
    Color color;
    int size;
    String text;
    public RadioButton() {
        this.setOpaque(false);
    }
    public void setText(String text,Color color,int size) {
        this.text = text;
        this.color = color;
        this.size = size;
    }
    public void setBoundsR(int x,int y,int w,int h) {
        setBounds(x,y,w,h);
        setLayout(null);
        add(new Label(20,0,w - 20,h,text,color,size,false));
    }
}