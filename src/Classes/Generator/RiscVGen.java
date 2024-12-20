package Classes.Generator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class RiscVGen {
    private int labelCount;
    private ArrayList<String> riscVCode;
    public ArrayList<String> riscVMain;
    public ArrayList<String> riscVFunctions;
    private Set<String> usedBuiltins;
    public RiscVGen() {
        this.labelCount = 0;
        this.riscVCode = new ArrayList<>();
        riscVMain = new ArrayList<>();
        this.riscVFunctions = new ArrayList<>();
        this.usedBuiltins = new HashSet<>();
    }
    public String getLabel() {
        String label = "L" + labelCount;
        labelCount++;
        return label;
    }
    public void addLabel(String label) {
        riscVMain.add(label + ":");
    }
    public void addComment(String comment) {
        riscVMain.add("# " + comment);
    }
    public void genFinalCode() {
        addComment("******* FIN PROGRAMA ********");
        li(R.A7.n, 10);
        ecall();
        addComment("******* FIN PROGRAMA ********");
        if(usedBuiltins.contains("lessEqual")         || usedBuiltins.contains("greaterEqual")        ||
            usedBuiltins.contains("less")             || usedBuiltins.contains("greater")               ||
            usedBuiltins.contains("equal")            || usedBuiltins.contains("notEqual")              ||
            usedBuiltins.contains("lessEqualStrings") || usedBuiltins.contains("greaterEqualStrings") ||
            usedBuiltins.contains("lessStrings")      || usedBuiltins.contains("greaterStrings")        ||
            usedBuiltins.contains("equalStrings")     || usedBuiltins.contains("notEqualStrings")       ||
            usedBuiltins.contains("printBoolean")     || usedBuiltins.contains("concatString")) {
            addComment("========= Builtins ==========");
            if(usedBuiltins.contains("lessEqual")) {
                addComment("--------- lessEqual ---------");
                addLabel("lessEqual");
                Builtins.lessEqual(this);
                addComment("------- Fin lessEqual -------");
            }
            if(usedBuiltins.contains("greaterOrEqual")) {
                addComment("------- greaterEqual --------");
                addLabel("greaterEqual");
                Builtins.greaterEqual(this);
                addComment("----- Fin greaterEqual ------");
            }
            if(usedBuiltins.contains("less")) {
                addComment("----------- less ------------");
                addLabel("less");
                Builtins.less(this);
                addComment("--------- Fin less ----------");
            }
            if(usedBuiltins.contains("greater")) {
                addComment("---------- greater ----------");
                addLabel("greater");
                Builtins.greater(this);
                addComment("-------- Fin greater --------");
            }
            if(usedBuiltins.contains("equal")) {
                addComment("----------- equal -----------");
                addLabel("equal");
                Builtins.equal(this);
                addComment("--------- Fin equal ---------");
            }
            if(usedBuiltins.contains("notEqual")) {
                addComment("--------- notEqual ----------");
                addLabel("notEqual");
                Builtins.notEqual(this);
                addComment("------- Fin notEqual --------");
            }
            if(usedBuiltins.contains("lessEqualStrings")) {
                addComment("----- lessEqualStrings ------");
                addLabel("lessEqualStrings");
                Builtins.lessEqualStrings(this);
                addComment("--- Fin lessEqualStrings ----");
            }
            if(usedBuiltins.contains("greaterEqualStrings")) {
                addComment("---- greaterEqualStrings ----");
                addLabel("greaterEqualStrings");
                Builtins.greaterEqualStrings(this);
                addComment("-- Fin greaterEqualStrings --");
            }
            if(usedBuiltins.contains("lessStrings")) {
                addComment("-------- lessStrings --------");
                addLabel("lessStrings");
                Builtins.lessStrings(this);
                addComment("------ Fin lessStrings ------");
            }
            if(usedBuiltins.contains("greaterStrings")) {
                addComment("------ greaterStrings -------");
                addLabel("greaterStrings");
                Builtins.greaterStrings(this);
                addComment("----- Fin greaterStrings ----");
            }
            if(usedBuiltins.contains("equalStrings")) {
                addComment("------- equalStrings --------");
                addLabel("equalStrings");
                Builtins.equalStrings(this);
                addComment("----- Fin equalStrings ------");
            }
            if(usedBuiltins.contains("notEqualStrings")) {
                addComment("----- notEqualStrings -------");
                addLabel("notEqualStrings");
                Builtins.notEqualStrings(this);
                addComment("--- Fin notEqualStrings -----");
            }
            if(usedBuiltins.contains("printBoolean")) {
                addComment("------- printBoolean --------");
                addLabel("printBoolean");
                Builtins.printBoolean(this);
                addComment("----- Fin printBoolean ------");
            }
            if(usedBuiltins.contains("concatString")) {
                addComment("------- concatString --------");
                addLabel("concatString");
                Builtins.concatString(this);
                addComment("----- Fin concatString ------");
            }
            addComment("======= Fin Builtins ========");
        }
        if(riscVFunctions.size() > 0) {
            addComment("==== FUNCIONES FORANEAS =====");
            riscVMain.addAll(riscVFunctions);
            addComment("== FIN FUNCIONES FORANEAS ===");
        }
        riscVCode.add(".data");
        riscVCode.add("heap:");
        riscVCode.add(".text");
        riscVCode.add("\tla t6, heap");
        riscVCode.add("global:");
        riscVCode.addAll(riscVMain);
    }
    public String getFinalCode() {
        return String.join("\n", riscVCode);
    }
    // ======================= OPERACIONES ========================
    public void add(String rd, String rs1, String rs2) {
        riscVMain.add("\tadd " + rd + ", " + rs1 + ", " + rs2);
    }
    public void sub(String rd, String rs1, String rs2) {
        riscVMain.add("\tsub " + rd + ", " + rs1 + ", " + rs2);
    }
    public void mul(String rd, String rs1, String rs2) {
        riscVMain.add("\tmul " + rd + ", " + rs1 + ", " + rs2);
    }
    public void div(String rd, String rs1, String rs2) {
        riscVMain.add("\tdiv " + rd + ", " + rs1 + ", " + rs2);
    }
    public void rem(String rd, String rs1, String rs2) {
        riscVMain.add("\trem " + rd + ", " + rs1 + ", " + rs2);
    }
    public void addi(String rd, String rs1, int inmediato) {
        riscVMain.add("\taddi " + rd + ", " + rs1 + ", " + inmediato);
    }
    public void fadd(String rd, String rs1, String rs2) {
        riscVMain.add("\tfadd.s " + rd + ", " + rs1 + ", " + rs2);
    }
    public void fsub(String rd, String rs1, String rs2) {
        riscVMain.add("\tfsub.s " + rd + ", " + rs1 + ", " + rs2);
    }
    public void fmul(String rd, String rs1, String rs2) {
        riscVMain.add("\tfmul.s " + rd + ", " + rs1 + ", " + rs2);
    }
    public void fdiv(String rd, String rs1, String rs2) {
        riscVMain.add("\tfdiv.s " + rd + ", " + rs1 + ", " + rs2);
    }
    // ========================== CARGAS ==========================
    public void sw(String rs1, String rs2) {
        riscVMain.add("\tsw " + rs1 + ", 0(" + rs2 + ")");
    }
    public void sw(String rs1, String rs2, int inmediato) {
        riscVMain.add("\tsw " + rs1 + ", " + inmediato + "(" + rs2 + ")");
    }
    public void sb(String rs1, String rs2) {
        riscVMain.add("\tsb " + rs1 + ", 0(" + rs2 + ")");
    }
    public void sb(String rs1, String rs2, int inmediato) {
        riscVMain.add("\tsb " + rs1 + ", " + inmediato + "(" + rs2 + ")");
    }
    public void lw(String rd, String rs1) {
        riscVMain.add("\tlw " + rd + ", 0(" + rs1 + ")");
    }
    public void lw(String rd, String rs1, int inmediato) {
        riscVMain.add("\tlw " + rd + ", " + inmediato + "(" + rs1 + ")");
    }
    public void lb(String rd, String rs1) {
        riscVMain.add("\tlb " + rd + ", 0(" + rs1 + ")");
    }
    public void lb(String rd, String rs1, int inmediato) {
        riscVMain.add("\tlb " + rd + ", " + inmediato + "(" + rs1 + ")");
    }
    public void li(String rd, int inmediato) {
        riscVMain.add("\tli " + rd + ", " + inmediato);
    }
    public void li(String rd, String inmediato) {
        riscVMain.add("\tli " + rd + ", " + inmediato);
    }
    public void la(String rd, String label) {
        riscVMain.add("\tla " + rd + ", " + label);
    }
    public void fli(String rd, int inmediato) {
        riscVMain.add("\tfli.s " + rd + ", " + inmediato);
    }
    public void fmvs(String rd, String rs1) {
        riscVMain.add("\tfmv.s " + rd + ", " + rs1);
    }
    public void fmvsx(String rd, String rs1) {
        riscVMain.add("\tfmv.s.x " + rd + ", " + rs1);
    }
    public void fmvwx(String rd, String rs1) {
        riscVMain.add("\tfmv.w.x " + rd + ", " + rs1);
    }
    public void flw(String rd, String rs1) {
        riscVMain.add("\tflw " + rd + ", 0(" + rs1 + ")");
    }
    public void flw(String rd, String rs1, int inmediato) {
        riscVMain.add("\tflw " + rd + ", " + inmediato + "(" + rs1 + ")");
    }
    public void fsw(String rs1, String rs2) {
        riscVMain.add("\tfsw " + rs1 + ", 0(" + rs2 + ")");
    }
    public void fsw(String rs1, String rs2, int inmediato) {
        riscVMain.add("\tfsw " + rs1 + ", " + inmediato + "(" + rs2 + ")");
    }
    public void fcvtsw(String rd, String rs1) {
        riscVMain.add("\tfcvt.s.w " + rd + ", " + rs1);
    }
    public void fcvtws(String rd, String rs1) {
        riscVMain.add("\tfcvt.w.s " + rd + ", " + rs1);
    }
    // =================== SALTOS CONDICIONALES ===================
    public void beq(String rs1, String rs2, String label) {  // rs1 == rs2
        riscVMain.add("\tbeq " + rs1 + ", " + rs2 + ", " + label);
    }
    public void bne(String rs1, String rs2, String label) {  // rs1 != rs2
        riscVMain.add("\tbne " + rs1 + ", " + rs2 + ", " + label);
    }
    public void blt(String rs1, String rs2, String label) { // rs1 < rs2
        riscVMain.add("\tblt " + rs1 + ", " + rs2 + ", " + label);
    }
    public void bge(String rs1, String rs2, String label) { // rs1 >= rs2
        riscVMain.add("\tbge " + rs1 + ", " + rs2 + ", " + label);
    }
    // ================== SALTOS INCONDICIONALES ==================
    public void jal(String label) {
        riscVMain.add("\tjal " + label);
    }
    public void jalr(String rd, String rs1, String imm) {
        riscVMain.add("\tjalr " + rd + ", " + rs1 + ", " + imm);
    }
    public void j(String label) {
        riscVMain.add("\tj " + label);
    }
    // ================= COMPARACIONES FLOTANTES ==================
    public void feqs(String rd, String rs1, String rs2) { // rs1 == rs2
        riscVMain.add("\tfeq.s " + rd + ", " + rs1 + ", " + rs2);
    }
    public void flts(String rd, String rs1, String rs2) { // rs1 < rs2
        riscVMain.add("\tflt.s " + rd + ", " + rs1 + ", " + rs2);
    }
    public void fles(String rd, String rs1, String rs2) { // rs1 <= rs2
        riscVMain.add("\tfle.s " + rd + ", " + rs1 + ", " + rs2);
    }
    public void fgts(String rd, String rs1, String rs2) { // rs1 > rs2
        riscVMain.add("\tflt.s " + rd + ", " + rs2 + ", " + rs1);
    }
    public void fges(String rd, String rs1, String rs2) { // rs1 >= rs2
        riscVMain.add("\tfle.s " + rd + ", " + rs2 + ", " + rs1);
    }
    // =========================== PUSH ===========================
    public void push() {
        addi(R.SP.n, R.SP.n, -4); // 4 bytes = 32 bits
        sw(R.T0.n, R.SP.n);
    }
    public void push(String rd) {
        addi(R.SP.n, R.SP.n, -4); // 4 bytes = 32 bits
        sw(rd, R.SP.n);
    }
    public void pushFloat() {
        addi(R.SP.n, R.SP.n, -4); // 4 bytes = 32 bits
        fsw(F.FT0.n, R.SP.n);
    }
    public void pushFloat(String rd) {
        addi(R.SP.n, R.SP.n, -4); // 4 bytes = 32 bits
        fsw(rd, R.SP.n);
    }
    // =========================== POP ============================
    public void pop() {
        lw(R.T0.n, R.SP.n);
        addi(R.SP.n, R.SP.n, 4);
    }
    public void pop(String rd) {
        lw(rd, R.SP.n);
        addi(R.SP.n, R.SP.n, 4);
    }
    public void popFloat() {
        flw(F.FT0.n, R.SP.n);
        addi(R.SP.n, R.SP.n, 4);
    }
    public void popFloat(String rd) {
        flw(rd, R.SP.n);
        addi(R.SP.n, R.SP.n, 4);
    }
    // =========================== RET ============================
    public void ret() {
        riscVMain.add("\tret");
    }
    // ========================== ECALL ===========================
    public void ecall() {
        riscVMain.add("\tecall");
    }
    // ========================== PRINTS ==========================
    public void printFloat() {
        li(R.A7.n, 2);
        ecall();
    }
    public void printInt() {
        li(R.A7.n, 1);
        ecall();
    }
    public void printInt(String rd) {
        if(!rd.equals(R.A0.n)) {
            push(R.A0.n);
            add(R.A0.n, rd, R.Z.n);
        }
        li(R.A7.n, 1);
        ecall();
        if(!rd.equals(R.A0.n)) {
            pop(R.A0.n);
        }
    }
    public void printChar() {
        li(R.A7.n, 11);
        ecall();
    }
    public void printChar(String rd) {
        if(!rd.equals(R.A0.n)) {
            push(R.A0.n);
            add(R.A0.n, rd, R.Z.n);
        }
        li(R.A7.n, 11);
        ecall();
        if(!rd.equals(R.A0.n)) {
            pop(R.A0.n);
        }
    }
    public void printString() {
        li(R.A7.n, 4);
        ecall();
    }
    public void printString(String rd) {
        if(!rd.equals(R.A0.n)) {
            push(R.A0.n);
            add(R.A0.n, rd, R.Z.n);
        }
        li(R.A7.n, 4);
        ecall();
        if(!rd.equals(R.A0.n)) {
            pop(R.A0.n);
        }
    }
    // ====================== CALL BUILTINS =======================
    public void callBuiltin(String builtinName) {
        usedBuiltins.add(builtinName);
        jal(builtinName);
    }
}