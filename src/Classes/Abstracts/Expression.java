package Classes.Abstracts;
import Classes.Env.Env;
import Classes.Generator.RiscVGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.TypeExp;
import Classes.Utils.TypeSent;
public abstract class Expression extends Sentence {
    public TypeExp typeExp;
    public String trueLbl;
    public String falseLbl;
    public Expression(int line, int column, TypeExp typeExp) {
        super(line, column, TypeSent.EXPRESSION);
        this.typeExp = typeExp;
    }
    public abstract ReturnValue exec(Env env, RiscVGen riscgen);
}