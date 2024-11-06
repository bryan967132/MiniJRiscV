package Generators;
import java.io.File;
public class GScannerFOut {
    public static void main(String[] args) {
        jflex.Main.generate(
            new File(
                "src/LanguageOut/ScannerFOut.jflex"
            )
        );
    }
}