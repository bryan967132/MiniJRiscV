package Classes.Generator;
import java.nio.ByteBuffer;
import java.util.ArrayList;
public class Utils {
    public static ArrayList<Integer> stringTo32BitsArray(String str) {
        ArrayList<Integer> resultado = new ArrayList<>();
        int elementIndex = 0;
        int intRepresentation = 0;
        int shift = 0;
        while (elementIndex < str.length()) {
            intRepresentation |= (str.charAt(elementIndex) << shift);
            shift += 8;
            if (shift >= 32) {
                resultado.add(intRepresentation);
                intRepresentation = 0;
                shift = 0;
            }
            elementIndex++;
        }
        if (shift > 0) {
            resultado.add(intRepresentation);
        } else {
            resultado.add(0);
        }
        return resultado;
    }
    public static ArrayList<Integer> stringTo1ByteArray(String str) {
        ArrayList<Integer> resultado = new ArrayList<>();
        int elementIndex = 0;
        while (elementIndex < str.length()) {
            resultado.add((int) str.charAt(elementIndex));
            elementIndex++;
        }
        resultado.add(0);
        return resultado;
    }
    public static String numberToF32(float number) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putFloat(number);
        int integer = buffer.getInt(0);
        String hexRepr = Integer.toHexString(integer).toUpperCase();
        return "0x" + hexRepr;
    }
}
