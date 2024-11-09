package Classes.Generator;
public class Builtins {
    public static void concatString(RiscVGen gen) {
        gen.addComment("Guardando en el stack la dirección en heap de la cadena concatenada");
        gen.push(R.T6.n);
        gen.addComment("Copiando la 1er cadena en el heap");
        String end1 = gen.getLabel();
        String loop1 = gen.getLabel();
        gen.addLabel(loop1);
        gen.lb(R.T1.n, R.A0.n);
        gen.beq(R.T1.n, R.Z.n, end1);
        gen.sb(R.T1.n, R.T6.n);
        gen.addi(R.T6.n, R.T6.n, 1);
        gen.addi(R.A0.n, R.A0.n, 1);
        gen.j(loop1);
        gen.addLabel(end1);
        gen.addComment("Copiando la 2da cadena en el heap");
        String end2 = gen.getLabel();
        String loop2 = gen.getLabel();
        gen.addLabel(loop2);
        gen.lb(R.T1.n, R.A1.n);
        gen.beq(R.T1.n, R.Z.n, end2);
        gen.sb(R.T1.n, R.T6.n);
        gen.addi(R.T6.n, R.T6.n, 1);
        gen.addi(R.A1.n, R.A1.n, 1);
        gen.j(loop2);
        gen.addLabel(end2);
        gen.addComment("Agregando el caracter nulo al final");
        gen.sb(R.Z.n, R.T6.n);
        gen.addi(R.T6.n, R.T6.n, 1);
        gen.ret();
    }
    public static void equalStrings(RiscVGen gen) {
        String loop = gen.getLabel();
        String labelTrue = gen.getLabel();
        String labelFalse = gen.getLabel();
        String end = gen.getLabel();
        gen.addLabel(loop);
        gen.lb(R.T1.n, R.A1.n);
        gen.lb(R.T0.n, R.A0.n);
        gen.bne(R.T1.n, R.T0.n, labelFalse);
        gen.beq(R.T1.n, R.Z.n, labelTrue);
        gen.addi(R.A1.n, R.A1.n, 1);
        gen.addi(R.A0.n, R.A0.n, 1);
        gen.j(loop);
        gen.addLabel(labelTrue);
        gen.li(R.T0.n, 1);
        gen.push(R.T0.n);
        gen.j(end);
        gen.addLabel(labelFalse);
        gen.li(R.T0.n, 0);
        gen.push(R.T0.n);
        gen.addLabel(end);
        gen.ret();
    }
    public static void notEqualStrings(RiscVGen gen) {
        String loop = gen.getLabel();
        String labelTrue = gen.getLabel();
        String labelFalse = gen.getLabel();
        String end = gen.getLabel();
        gen.addLabel(loop);
        gen.lb(R.T1.n, R.A1.n);
        gen.lb(R.T0.n, R.A0.n);
        gen.bne(R.T1.n, R.T0.n, labelFalse);
        gen.beq(R.T1.n, R.Z.n, labelTrue);
        gen.addi(R.A1.n, R.A1.n, 1);
        gen.addi(R.A0.n, R.A0.n, 1);
        gen.j(loop);
        gen.addLabel(labelTrue);
        gen.li(R.T0.n, 0);
        gen.push(R.T0.n);
        gen.j(end);
        gen.addLabel(labelFalse);
        gen.li(R.T0.n, 1);
        gen.push(R.T0.n);
        gen.addLabel(end);
        gen.ret();
    }
    public static void lessOrEqual(RiscVGen gen) {
        gen.fles(R.T0.n, F.FT1.n, F.FT0.n);
        gen.push(R.T0.n);
        gen.ret();
    }
    public static void greatOrEqual(RiscVGen gen) {
        gen.fges(R.T0.n, F.FT1.n, F.FT0.n);
        gen.push(R.T0.n);
        gen.ret();
    }
    public static void less(RiscVGen gen) {
        gen.flts(R.T0.n, F.FT1.n, F.FT0.n);
        gen.push(R.T0.n);
        gen.ret();
    }
    public static void great(RiscVGen gen) {
        gen.fgts(R.T0.n, F.FT1.n, F.FT0.n);
        gen.push(R.T0.n);
        gen.ret();
    }
    public static void equal(RiscVGen gen) {
        gen.feqs(R.T0.n, F.FT0.n, F.FT1.n);
        gen.push(R.T0.n);
        gen.ret();
    }
    public static void notEqual(RiscVGen gen) {
        String trueLabel = gen.getLabel();
        String endLabel = gen.getLabel();
        gen.feqs(R.T0.n, F.FT0.n, F.FT1.n);
        gen.beq(R.T0.n, R.Z.n, trueLabel);
        gen.li(R.T0.n, 0);
        gen.push(R.T0.n);
        gen.j(endLabel);
        gen.addLabel(trueLabel);
        gen.li(R.T0.n, 1);
        gen.push(R.T0.n);
        gen.addLabel(endLabel);
        gen.ret();
    }
    public static void printBoolean(RiscVGen gen) {
        String labelFalse = gen.getLabel();
        String labelEnd = gen.getLabel();
        gen.beq(R.A0.n, R.Z.n, labelFalse);
        gen.push(R.T6.n);
        for(char chr : "true\0".toCharArray()) {
            gen.li(R.T0.n, chr);
            gen.sb(R.T0.n, R.T6.n);
            gen.addi(R.T6.n, R.T6.n, 1);
        }
        gen.pop(R.A0.n);
        gen.li(R.A7.n, 4);
        gen.ecall();
        gen.j(labelEnd);
        gen.addLabel(labelFalse);
        gen.push(R.T6.n);
        for(char chr : "false\0".toCharArray()) {
            gen.li(R.T0.n, chr);
            gen.sb(R.T0.n, R.T6.n);
            gen.addi(R.T6.n, R.T6.n, 1);
        }
        gen.pop(R.A0.n);
        gen.li(R.A7.n, 4);
        gen.ecall();
        gen.addLabel(labelEnd);
        gen.ret();
    }
}