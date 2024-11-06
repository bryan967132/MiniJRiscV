package Painter;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import Templates.Colors;
public class WordPainter {
    public JTextPane box;
    private StyleContext sc;
    public StyledDocument doc;
    public WordPainter() {
        box = new JTextPane();
        sc = new StyleContext();
        doc = new DefaultStyledDocument(sc);
    }
    public void setStyle(JTextPane editor) {
        doc = editor.getStyledDocument();
        DEFAULT(0,doc.getLength());
    }
    public void RW1(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.KEYWORD1);
        doc.setCharacterAttributes(startPos,endPos,attr,true);
    }
    public void RW2(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.KEYWORD2);
        doc.setCharacterAttributes(startPos,endPos,attr,true);
    }
    public void RW3(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.KEYWORD3);
        doc.setCharacterAttributes(startPos,endPos,attr,true);
    }
    public void RW4(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.KEYWORD4);
        doc.setCharacterAttributes(startPos,endPos,attr,true);
    }
    public void RW5(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.KEYWORD5);
        doc.setCharacterAttributes(startPos,endPos,attr,true);
    }
    public void STRING(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.STRING);
        doc.setCharacterAttributes(startPos,endPos,attr,true);
    }
    public void CHAR(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.CHAR);
        doc.setCharacterAttributes(startPos,endPos,attr,true);
    }
    public void FNC(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.FNC);
        doc.setCharacterAttributes(startPos,endPos,attr,true);
    }
    public void USEFNC(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.USEFNC);
        doc.setCharacterAttributes(startPos,endPos,attr,true);
    }
    public void VARIABLE(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.VARIABLE);
        doc.setCharacterAttributes(startPos,endPos,attr,true);
    }
    public void USEVARIABLE(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.USEVARIABLE);
        doc.setCharacterAttributes(startPos,endPos,attr,true);
    }
    public void USEVARIABLE2(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.USEVARIABLE2);
        doc.setCharacterAttributes(startPos,endPos,attr,true);
    }
    public void NUMBER(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.NUMBER);
        doc.setCharacterAttributes(startPos,endPos,attr,true);
    }
    public void BRKT(int nIb, int startPos, int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr, Colors.bracketPair[nIb % Colors.bracketPair.length]);
        doc.setCharacterAttributes(startPos, endPos, attr, true);
    }
    public void SYMBS1(int startPos, int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr, Colors.PAR1);
        doc.setCharacterAttributes(startPos, endPos, attr, true);
    }
    public void COMMENT(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.COMMENT);
        doc.setCharacterAttributes(startPos,endPos,attr,true);
    }
    public void ERROR(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.COMMENT);
        StyleConstants.setStrikeThrough(attr,true);
        doc.setCharacterAttributes(startPos,endPos,attr,true);
    }
    public void SERROR(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.ERROR);
        StyleConstants.setUnderline(attr,true);
        doc.setCharacterAttributes(startPos,endPos,attr,true);
    }
    public void DEFAULT(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.WHITE);
        doc.setCharacterAttributes(startPos,endPos,attr,true);
    }
}