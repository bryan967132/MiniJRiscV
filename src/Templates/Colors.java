package Templates;
import java.awt.Color;
public class Colors {
	// TEMA VSCODE 0
	// TEMA GITHUB 1
	// TEMA ECPLIPSE 2
	private static int n = 0;
	public static Color LIGHTCOLOR    = ColorsChoosen.getLightColor(n);
	public static Color MEDIUMCOLOR1  = ColorsChoosen.getMediumColor1(n);
	public static Color MEDIUMCOLOR2  = ColorsChoosen.getMediumColor2(n);
	public static Color DARKCOLOR     = ColorsChoosen.getDarkColor(n);
	public static Color COLOR1        = ColorsChoosen.getColor1(n);
	public static Color COLOR2        = ColorsChoosen.getColor2(n);
	public static Color COLOR3        = ColorsChoosen.getColor3(n);
	public static Color RED           = ColorsChoosen.getRed(n);
	public static Color WHITE         = ColorsChoosen.getWhite(n);
	public static Color KEYWORD1      = ColorsChoosen.getKeyWord1(n);
	public static Color KEYWORD2      = ColorsChoosen.getKeyWord2(n);
	public static Color KEYWORD3      = ColorsChoosen.getKeyWord3(n);
	public static Color KEYWORD4      = ColorsChoosen.getKeyWord4(n);
	public static Color KEYWORD5      = ColorsChoosen.getKeyWord5(n);
	public static Color NUMBER        = ColorsChoosen.getNumber(n);
	public static Color STRING        = ColorsChoosen.getString(n);
	public static Color CHAR          = ColorsChoosen.getChar(n);
	public static Color FNC           = ColorsChoosen.getFNC(n);
	public static Color USEFNC        = ColorsChoosen.getUseFNC(n);
	public static Color COMMENT       = ColorsChoosen.getComment(n);
	public static Color VARIABLE      = ColorsChoosen.getVariable(n);
	public static Color USEVARIABLE   = ColorsChoosen.getUseVariable(n);
	public static Color USEVARIABLE2  = ColorsChoosen.getUseVariable2(n);
	public static Color PAR1          = ColorsChoosen.getPar1(n);
	public static Color PAR2          = ColorsChoosen.getPar2(n);
	public static Color PAR3          = ColorsChoosen.getPar3(n);
	public static Color[] bracketPair = {PAR1, PAR2, PAR3};
	public static Color ERROR         = ColorsChoosen.getError(n);
}