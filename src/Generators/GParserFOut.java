package Generators;
public class GParserFOut {
    public static void main(String[] args) {
        generate();
    }
    public static void generate() {
        try {
            java_cup.Main.main(
                new String[] {
                    "-destdir",
                    "src/LanguageOut",
                    "-symbols",
                    "TOK",
                    "-parser",
                    "ParserFOut",
                    "src/LanguageOut/ParserFOut.cup"
                }
            );
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}