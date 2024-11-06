package Interface;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.StyledDocument;
import Controller.Controller;
import Templates.Colors;
import Templates.Button;
import Templates.FunctionButton;
import Templates.Icons;
public class ToolBar extends JPanel implements MouseListener {
    Button newOLC, openOLC, saveAsOLC;
    Controller controller;
    FunctionButton close, minimize;
    IDE ide;
    JPanel div;
    Window w;
    public ToolBar(Controller controller, IDE ide, Window w) {
        this.w = w;
        this.ide = ide;
        this.controller = this.ide.controller;
        init();
        addOpenOLC();
        addNewOLC();
        addSaveAsOLC();
        addMinimizeButton();
        addCloseButton();
        addDivisor();
    }
    private void addOpenOLC() {
        openOLC = new Button("Abrir");
        openOLC.locationSize(20, 5, 50, 25);
        openOLC.text(Colors.WHITE, 14);
        openOLC.setDesign(Colors.MEDIUMCOLOR1);
        openOLC.setHoverColor(Colors.MEDIUMCOLOR2);
        openOLC.addMouseListener(this);
        this.add(openOLC);
    }
    private void addNewOLC() {
        newOLC = new Button("Nuevo");
        newOLC.locationSize(72, 5, 60, 25);
        newOLC.text(Colors.WHITE, 14);
        newOLC.setDesign(Colors.MEDIUMCOLOR1);
        newOLC.setHoverColor(Colors.MEDIUMCOLOR2);
        newOLC.addMouseListener(this);
        this.add(newOLC);
    }
    private void addSaveAsOLC() {
        saveAsOLC = new Button("Guardar Como");
        saveAsOLC.locationSize(134, 5, 110, 25);
        saveAsOLC.text(Colors.WHITE, 14);
        saveAsOLC.setDesign(Colors.MEDIUMCOLOR1);
        saveAsOLC.setHoverColor(Colors.MEDIUMCOLOR2);
        saveAsOLC.addMouseListener(this);
        this.add(saveAsOLC);
    }
    private void init() {
        this.setLayout(null);
        this.setBackground(Colors.MEDIUMCOLOR1);
        this.setVisible(true);
    }
    private void addDivisor() {
        div = new JPanel();
        div.setBackground(Colors.MEDIUMCOLOR2);
        div.setBounds(0, 35, 1390, 5);
        div.setVisible(true);
        this.add(div);
    }
    private void addCloseButton() {
        close = new FunctionButton();
        close.locationSize(1331, 0, 45, 35);
        close.text("×", 25);
        close.setHoverColor(Colors.RED);
        close.addMouseListener(this);
        this.add(close);
    }
    private void addMinimizeButton() {
        minimize = new FunctionButton();
        minimize.locationSize(1286, 0, 45, 35);
        minimize.text("─", 20);
        minimize.setHoverColor(Colors.LIGHTCOLOR);
        minimize.addMouseListener(this);
        this.add(minimize);
    }
    private void chooseFiles() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFileChooser file = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos MiniJ (*.mj)", "mj");
        file.setFileFilter(filter);
        file.setMultiSelectionEnabled(true);
        int selection = file.showOpenDialog(null);
        if (selection == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = file.getSelectedFiles();
            for (File olcFile : selectedFiles) {
                int index = controller.existPJFile(olcFile.getAbsolutePath());
                if (index == -1) {
                    controller.pjs.add(new IconFile(controller.countPJ(), olcFile, ide, controller));
                    controller.serialize();
                    controller.deserialize(ide);
                    ide.lookPJFiles();
                    controller.pjs.get(controller.countPJ() - 1).lookCode();
                } else {
                    controller.pjs.get(index).lookCode();
                }
            }
        }
    }
    private void createFile(String content) {
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
		}
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int seleccion = file.showDialog(null, "Seleccionar Directorio");
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File olcFile = file.getSelectedFile();
            String name;
            String message = "Nombre del Archivo MiniJ [.mj]:";
            ImageIcon icon = new ImageIcon(Icons.FILE1);
            Image img = icon.getImage();
            img = img.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            icon = new ImageIcon(img);
            do {
                
                name = (String) JOptionPane.showInputDialog(null, message, "Nuevo Proyecto", JOptionPane.PLAIN_MESSAGE, icon, null, null);
                if(name == null) break;
                else if(name.replace(" ", "").equals("")) {
                    message = "Debe Ingresar un Nombre.\nNombre del Archivo MiniJ [.mj]:";
                    continue;
                }
                File auxiliar = new File(olcFile.getAbsolutePath() + "\\" + name + ".mj");
                if(!auxiliar.exists()) {
                    try {
                        BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(
                                new FileOutputStream(auxiliar),
                                "utf-8"
                            )
                        );
                        writer.write(content);
                        writer.close();
                        controller.pjs.add(new IconFile(controller.countPJ(), auxiliar, ide, controller));
                        controller.serialize();
                        controller.deserialize(ide);
                        ide.lookPJFiles();
                        controller.pjs.get(controller.countPJ() - 1).lookCode();
                    }
                    catch(Exception e1) {}
                    break;
                }
                message = "El nuevo archivo no puede llamarse\ncon el mismo nombre de uno existente\nen el mismo directorio.\nNombre del Archivo MiniJ [.mj]:";
            } while(true);
        }
    }
    private void deleteFile(File file) {
        File[] archivos = file.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    deleteFile(archivo);
                } else {
                    archivo.delete();
                }
            }
        }
        file.delete();
    }
    private void deleteDirectories() {
        File file = new File("Dot");
        if(file.exists()) {
            deleteFile(file);
        }
        file = new File("Data");
        if(file.exists()) {
            deleteFile(file);
        }        
    }
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == openOLC) {
            chooseFiles();
        }
        else if(e.getSource() == newOLC) {
            createFile("");
        }
        else if(e.getSource() == saveAsOLC) {
            try {
                StyledDocument doc = ide.editorArea.editor.getStyledDocument();
                String input = doc.getText(0, doc.getLength());
                createFile(input);
            }
            catch(Exception e1) {}
        }
        else if(e.getSource() == close) {
            deleteDirectories();
            System.exit(0);
        }
        else if(e.getSource() == minimize) {
            w.setState(1);
        }
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}