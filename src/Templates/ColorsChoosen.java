package Templates;
import java.awt.Color;
public class ColorsChoosen {
	// TEMA VSCODE 0
	// TEMA GITHUB 1
	// TEMA ECPLIPSE 2
	public static Color getLightColor(int n) {
		switch(n) {
			case 0: return getColor(60, 60, 60);// rgb(60, 60, 60)
			case 1: return getColor(48, 54, 61);// rgb(48, 54, 61)
			case 2: return getColor(128, 128, 128);// rgb(128, 128, 128)
			default: return getColor(60, 60, 60);// rgb(60, 60, 60)
		}
	}

	public static Color getMediumColor1(int n) {
		switch(n) {
			case 0: return getColor(42, 45, 46);// rgb(42, 45, 46)
			case 1: return getColor(22, 27, 34);// rgb(22, 27, 34)
			case 2: return getColor(81, 86, 88);// rgb(81, 86, 88)
			default: return getColor(42, 45, 46);// rgb(42, 45, 46)
		}
	}

	public static Color getMediumColor2(int n) {
		switch(n) {
			case 0: return getColor(37, 37, 38);// rgb(37, 37, 38)
			case 1: return getColor(13, 17, 23);// rgb(13, 17, 23)
			case 2: return getColor(64, 64, 64);// rgb(64, 64, 64)
			default: return getColor(37, 37, 38);// rgb(37, 37, 38)
		}
	}

	public static Color getDarkColor(int n) {
		switch(n) {
			case 0: return getColor(30, 30, 30);// rgb(30, 30, 30)
			case 1: return getColor(13, 17, 23);// rgb(13, 17, 23)
			case 2: return getColor(47, 47, 47);// rgb(47, 47, 47)
			default: return getColor(30, 30, 30);// rgb(30, 30, 30)
		}
	}

	public static Color getColor1(int n) {
		switch(n) {
			case 0: return getColor(0, 93, 202);// rgb(0, 93, 202)
			case 1: return getColor(0, 93, 202);// rgb(0, 93, 202)
			case 2: return getColor(43, 122, 11);// rgb(43, 122, 11)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getColor2(int n) {
		switch(n) {
			case 0: return getColor(0, 117, 255);// rgb(0, 117, 255)
			case 1: return getColor(31, 36, 44);// rgb(31, 36, 44)
			case 2: return getColor(91, 179, 24);// rgb(91, 179, 24)
			default: return getColor(31, 36, 44);// rgb(31, 36, 44)
		}
	}

	public static Color getColor3(int n) {
		switch(n) {
			case 0: return getColor(44, 140, 255);// rgb(44, 140, 255)
			case 1: return getColor(48, 54, 61);// rgb(48, 54, 61)
			case 2: return getColor(125, 206, 19);// rgb(125, 206, 19)
			default: return getColor(48, 54, 61);// rgb(48, 54, 61)
		}
	}

	public static Color getRed(int n) {
		switch(n) {
			case 0: return getColor(232, 17, 35);// rgb(232, 17, 35)
			case 1: return getColor(232, 17, 35);// rgb(232, 17, 35)
			case 2: return getColor(232, 17, 35);// rgb(232, 17, 35)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getWhite(int n) {
		switch(n) {
			case 0: return getColor(255, 255, 255);// rgb(255, 255, 255)
			case 1: return getColor(255, 255, 255);// rgb(255, 255, 255)
			case 2: return getColor(255, 255, 255);// rgb(255, 255, 255)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getKeyWord1(int n) {
		switch(n) {
			case 0: return getColor(78, 201, 176);// rgb(78, 201, 176)
			case 1: return getColor(201, 209, 217);// rgb(201, 209, 217)
			case 2: return getColor(204, 108, 29);// rgb(204, 108, 29)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getKeyWord2(int n) {
		switch(n) {
			case 0: return getColor(197, 134, 192);// rgb(197, 134, 192)
			case 1: return getColor(255, 123, 114);// rgb(255, 123, 114)
			case 2: return getColor(204, 108, 29);// rgb(204, 108, 29)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getKeyWord3(int n) {
		switch(n) {
			case 0: return getColor(86, 156, 214);// rgb(86, 156, 214)
			case 1: return getColor(121, 192, 255);// rgb(121, 192, 255)
			case 2: return getColor(204, 108, 29);// rgb(204, 108, 29)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getKeyWord4(int n) {
		switch(n) {
			case 0: return getColor(75, 183, 242);// rgb(75, 183, 242)
			case 1: return getColor(255, 166, 87);// rgb(255, 166, 87)
			case 2: return getColor(204, 108, 29);// rgb(204, 108, 29)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getKeyWord5(int n) {
		switch(n) {
			case 0: return getColor(78, 201, 176);// rgb(78, 201, 176)
			case 1: return getColor(201, 209, 217);// rgb(201, 209, 217)
			case 2: return getColor(18, 144, 195);// rgb(18, 144, 195)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getNumber(int n) {
		switch(n) {
			case 0: return getColor(181, 206, 168);// rgb(181, 206, 168)
			case 1: return getColor(121, 192, 255);// rgb(121, 192, 255)
			case 2: return getColor(104, 151, 187);// rgb(104, 151, 187)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getString(int n) {
		switch(n) {
			case 0: return getColor(206, 145, 120);// rgb(206, 145, 120)
			case 1: return getColor(165, 214, 255);// rgb(165, 214, 255)
			case 2: return getColor(23, 198, 163);// rgb(23, 198, 163)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getChar(int n) {
		switch(n) {
			case 0: return getColor(206, 145, 120);// rgb(206, 145, 120)
			case 1: return getColor(165, 214, 255);// rgb(165, 214, 255)
			case 2: return getColor(23, 198, 163);// rgb(23, 198, 163)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getFNC(int n) {
		switch(n) {
			case 0: return getColor(220, 220, 170);// rgb(220, 220, 170)
			case 1: return getColor(210, 168, 255);// rgb(210, 168, 255)
			case 2: return getColor(30, 181, 64);// rgb(30, 181, 64)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getUseFNC(int n) {
		switch(n) {
			case 0: return getColor(220, 220, 170);// rgb(220, 220, 170)
			case 1: return getColor(210, 168, 255);// rgb(210, 168, 255)
			case 2: return getColor(167, 236, 33);// rgb(167, 236, 33)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getComment(int n) {
		switch(n) {
			case 0: return getColor(106, 153, 85);// rgb(106, 153, 85)
			case 1: return getColor(139, 148, 158);// rgb(139, 148, 158)
			case 2: return getColor(128, 128, 128);// rgb(128, 128, 128)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getVariable(int n) {
		switch(n) {
			case 0: return getColor(140, 220, 254);// rgb(140, 220, 254)
			case 1: return getColor(121, 192, 255);// rgb(121, 192, 255)
			case 2: return getColor(242, 242, 0);// rgb(242, 242, 0)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getUseVariable(int n) {
		switch(n) {
			case 0: return getColor(140, 220, 254);// rgb(140, 220, 254)
			case 1: return getColor(121, 192, 255);// rgb(121, 192, 255)
			case 2: return getColor(243, 236, 121);// rgb(243, 236, 121)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getUseVariable2(int n) {
		switch(n) {
			case 0: return getColor(156, 220, 254);// rgb(156, 220, 254)
			case 1: return getColor(121, 192, 255);// rgb(121, 192, 255)
			case 2: return getColor(243, 236, 121);// rgb(243, 236, 121)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getPar1(int n) {
		switch(n) {
			case 0: return getColor(218, 112, 214);// rgb(218, 112, 214)
			case 1: return getColor(255, 255, 255);// rgb(255, 255, 255)
			case 2: return getColor(255, 255, 255);// rgb(255, 255, 255)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getPar2(int n) {
		switch(n) {
			case 0: return getColor(27, 159, 255);// rgb(27, 159, 255)
			case 1: return getColor(255, 255, 255);// rgb(255, 255, 255)
			case 2: return getColor(255, 255, 255);// rgb(255, 255, 255)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getPar3(int n) {
		switch(n) {
			case 0: return getColor(255, 215, 0);// rgb(255, 215, 0)
			case 1: return getColor(255, 255, 255);// rgb(255, 255, 255)
			case 2: return getColor(255, 255, 255);// rgb(255, 255, 255)
			default: return getColor(255, 255, 255);// rgb(255, 255, 255)
		}
	}

	public static Color getError(int n) {
		switch(n) {
			case 0: return getColor(232, 17, 35);// rgb(232, 17, 35)
			case 1: return getColor(232, 17, 35);// rgb(232, 17, 35)
			case 2: return getColor(232, 17, 35);// rgb(232, 17, 35)
			default: return getColor(232, 17, 35);// rgb(232, 17, 35)
		}
	}

	private static Color getColor(int r, int g, int b) {
		return new Color(r, g, b);
	}
}