package Controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generator.RiscVGen;
import Classes.Instructions.Function;
import Classes.Instructions.MainMethod;
import Classes.Utils.Outs;
import Classes.Utils.TypeInst;
import Interface.IDE;
import Interface.IconFile;
import Interface.Path;
import Language.Parser;
import Language.ParserF;
import Language.Scanner;
import Language.ScannerF;
import LanguageOut.ParserFOut;
import LanguageOut.ScannerFOut;
import Painter.WordPainter;
public class Controller {
    public ArrayList<IconFile> pjs = new ArrayList<>();
    public int existPJFile(String path) {
        for(int i = 0; i < pjs.size(); i ++) {
            if(pjs.get(i).path.equals(path)) {
                return i;
            }
        }
        return -1;
    }
    public int countPJ() {
        return pjs.size();
    }
    public void setFormat(JTextPane editor) {
        try {
            StyledDocument doc = editor.getStyledDocument();
            String input = doc.getText(0, doc.getLength());
            WordPainter painter = new WordPainter();
            ScannerF scanner = new ScannerF(input, painter);
            painter.setStyle(editor);
            ParserF parser = new ParserF(scanner, painter);
            parser.parse();
        }
        catch(Exception e) {}
    }
    public void setFormatOut(JTextPane output) {
        try {
            StyledDocument doc = output.getStyledDocument();
            String input = doc.getText(0, doc.getLength());
            WordPainter painter = new WordPainter();
            ScannerFOut scanner = new ScannerFOut(input, painter);
            painter.setStyle(output);
            ParserFOut parser = new ParserFOut(scanner, painter);
            parser.parse();
        }
        catch(Exception e) {}
    }
    @SuppressWarnings("unchecked")
    public void analyze(IDE ide, int index, JTextPane editor, JTextPane console) {
        IconFile currentFile = pjs.get(index);
        try {
            Outs.resetOuts();
            StyledDocument doc = editor.getStyledDocument();
            String input = doc.getText(0, doc.getLength());
            Scanner scanner = new Scanner(input);
            Parser parser = new Parser(scanner);
            ArrayList<Instruction> execute = (ArrayList<Instruction>) parser.parse().value;
            Classes.Utils.Outs.resetOuts();
            Env global = new Env(null, "Global");
            RiscVGen c3dGen = new RiscVGen();
            c3dGen.enableGlobal();
            MainMethod mainMethod = null;
            for(Instruction instruction : execute) {
                try {
                    if(instruction.typeInst == TypeInst.MAIN) {
                        mainMethod = (MainMethod) instruction;
                    } else if(instruction.typeInst == TypeInst.INIT_FUNCTION) {
                        ((Function) instruction).save(global, c3dGen);
                    } else if(instruction.typeInst == TypeInst.INIT_ID) {
                        instruction.exec(global, c3dGen);
                    }
                } catch(Exception e) {}
            }
            for(Instruction instruction : execute) {
                try {
                    if(instruction.typeInst == TypeInst.INIT_FUNCTION) {
                        instruction.exec(global, c3dGen);
                    }
                } catch(Exception e) {}
            }
            c3dGen.enableMain();
            if(mainMethod != null) {
                mainMethod.exec(global, c3dGen);
            }
            if(!Outs.getStringOuts().equals("")) {
                console.setText("MiniJ: " + currentFile.name + "\n" + Outs.getStringOuts());
            } else {
                c3dGen.generateFinalCode();
                console.setText("/* " + currentFile.name + " */\n" + "\n" + c3dGen.getFinalCode());
                setFormatOut(console);
            }
        } catch (Exception e) {
            String outPrint = "MiniJ: " + currentFile.name + "\n";
            outPrint += Outs.getStringOuts();
            console.setText(outPrint);
        }
    }
    public void saveOLCPJ(int index, JTextPane editor) {
        try {
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(pjs.get(index).path), 
                    "utf-8"
                )
            );
            StyledDocument doc = editor.getStyledDocument();
            String input = doc.getText(0, doc.getLength());
            writer.write(input);
            writer.close();
        } catch (Exception e) {}
    }
    public String readInput(String path) {
        try {
            File archivo = new File(path);
            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String text = "";
            String line;
            while ((line = br.readLine()) != null) {
                text += line;
                if(br.ready()) {
                    text += "\n";
                }
            }
            br.close();
            fis.close();
            return text;
        } catch (Exception e) {
            return "java.io.FileNotFoundException";
        }
    }
    public void serialize() {
        try {
            Path[] pjs1 = new Path[pjs.size()];
            for(int i = 0; i < pjs.size(); i ++) {
                pjs1[i] = new Path(pjs.get(i).id, pjs.get(i).path);
            }
            FileOutputStream file = new FileOutputStream("bin/Files");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(pjs1);
            output.close();
            file.close();
        }
        catch(Exception e) {}
    }
    public void deserialize(IDE ide) {
        try {
            FileInputStream file = new FileInputStream("bin/Files");
            ObjectInputStream input = new ObjectInputStream(file);
            Path[] pjs1 = (Path[]) input.readObject();
            input.close();
            file.close();
            pjs = new ArrayList<>();
            int i = 0;
            for(Path path : pjs1) {
                pjs.add(new IconFile(i, new File(path.path), ide, this));
                i ++;
            }
            Collections.sort(pjs);
            for(i = 0; i < pjs.size(); i ++) {
                pjs.get(i).id = i;
            }
        }
        catch (Exception e) {}
    }
}