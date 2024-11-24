package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generator.R;
import Classes.Generator.RiscVGen;
import Classes.Utils.StackValue;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Logic extends Expression {
    private Expression exp1;
    private String sign;
    private Expression exp2;
    public Logic(int line, int column, Expression exp1, String sign, Expression exp2) {
        super(line, column, TypeExp.LOGIC);
        this.exp1 = exp1;
        this.sign = sign;
        this.exp2 = exp2;
    }
    public void exec(Env env, RiscVGen gen) {
        switch (this.sign) {
            case "&&":
                and(env, gen);
                break;
            case "||":
                or(env, gen);
                break;
            case "!":
                not(env, gen);
                break;
            default:
                break;
        }
    }
    public void and(Env env, RiscVGen gen) {
        exp1.exec(env, gen);
        exp2.exec(env, gen);

        StackValue value2 = env.popObject();
        StackValue value1 = env.popObject();

        if(value1.type != Type.BOOLEAN) {
            env.setError("Los tipos no son válidos para operaciones lógicas", exp1.line, exp1.column);
            env.pushObject(new StackValue(Type.NULL, 4, env.depth));
            return;
        }
        if(value2.type == Type.BOOLEAN) {
            final String labelFalse = gen.getLabel();
            final String labelEnd = gen.getLabel();

            gen.pop(R.T0.n);
            gen.beq(R.T0.n, R.Z.n, labelFalse);
            gen.pop(R.T0.n);
            gen.beq(R.T0.n, R.Z.n, labelFalse);
            gen.li(R.T0.n, 1);
            gen.push();
            gen.j(labelEnd);
            gen.addLabel(labelFalse);
            gen.li(R.T0.n, 0);
            gen.push();
            gen.addLabel(labelEnd);
            env.pushObject(new StackValue(Type.BOOLEAN, 4, env.depth));
            return;
        }
        env.setError("Los tipos no son válidos para operaciones lógicas", exp2.line, exp2.column);
        env.pushObject(new StackValue(Type.NULL, 4, env.depth));
    }
    public void or(Env env, RiscVGen gen) {
        exp1.exec(env, gen);
        exp2.exec(env, gen);

        StackValue value2 = env.popObject();
        StackValue value1 = env.popObject();

        if(value1.type != Type.BOOLEAN) {
            env.setError("Los tipos no son válidos para operaciones lógicas", exp1.line, exp1.column);
            env.pushObject(new StackValue(Type.NULL, 4, env.depth));
            return;
        }
        if(value2.type == Type.BOOLEAN) {
            final String labelTrue = gen.getLabel();
            final String labelEnd = gen.getLabel();

            gen.pop(R.T0.n);
            gen.bne(R.T0.n, R.Z.n, labelTrue);
            gen.pop(R.T0.n);
            gen.bne(R.T0.n, R.Z.n, labelTrue);
            gen.li(R.T0.n, 0);
            gen.push();
            gen.j(labelEnd);
            gen.addLabel(labelTrue);
            gen.li(R.T0.n, 1);
            gen.push();
            gen.addLabel(labelEnd);
            env.pushObject(new StackValue(Type.BOOLEAN, 4, env.depth));
            return;
        }
        env.setError("Los tipos no son válidos para operaciones lógicas", exp2.line, exp2.column);
        env.pushObject(new StackValue(Type.NULL, 4, env.depth));
    }
    public void not(Env env, RiscVGen gen) {
        exp2.exec(env, gen);

        StackValue value2 = env.popObject();

        if(value2.type == Type.BOOLEAN) {
            final String labelTrue = gen.getLabel();
            final String labelEnd = gen.getLabel();

            gen.pop(R.T0.n);
            gen.beq(R.T0.n, R.Z.n, labelTrue);
            gen.li(R.T0.n, 0);
            gen.push();
            gen.j(labelEnd);
            gen.addLabel(labelTrue);
            gen.li(R.T0.n, 1);
            gen.push();
            gen.addLabel(labelEnd);
            env.pushObject(new StackValue(Type.BOOLEAN, 4, env.depth));
            return;
        }
        env.setError("Los tipos no son válidos para operaciones lógicas", exp2.line, exp2.column);
        env.pushObject(new StackValue(Type.NULL, 4, env.depth));
    }
}