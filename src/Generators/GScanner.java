package Generators;
import java.io.File;
public class GScanner {
    public static void main(String[] args) {
        jflex.Main.generate(
            new File(
                "src/Language/Scanner.jflex"
            )
        );
    }
}