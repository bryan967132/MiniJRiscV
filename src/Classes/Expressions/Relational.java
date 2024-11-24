package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generator.F;
import Classes.Generator.R;
import Classes.Generator.RiscVGen;
import Classes.Utils.StackValue;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Relational extends Expression {
    private Expression exp1;
    private String sign;
    private Expression exp2;
    public Relational(int line, int column, Expression exp1, String sign, Expression exp2) {
        super(line, column, TypeExp.RELATIONAL);
        this.exp1 = exp1;
        this.sign = sign;
        this.exp2 = exp2;
    }
    public void exec(Env env, RiscVGen gen) {
        switch (this.sign) {
            case "==":
                equal(env, gen);
                break;
            case "!=":
                notEqual(env, gen);
                break;
            case ">=":
                greaterEqual(env, gen);
                break;
            case "<=":
                lessEqual(env, gen);
                break;
            case ">":
                greater(env, gen);
                break;
            case "<":
                less(env, gen);
                break;
            default:
                break;
        }
    }
    public void equal(Env env, RiscVGen gen) {
        this.exp1.exec(env, gen);
        this.exp2.exec(env, gen);

        StackValue value2 = env.popObject();
        StackValue value1 = env.popObject();

        if(value2.type == Type.DOUBLE) {
            gen.popFloat(F.FT0.n);
        } else {
            gen.pop(R.T0.n);
        }

        if(value1.type == Type.DOUBLE) {
            gen.popFloat(F.FT1.n);
        } else {
            gen.pop(R.T1.n);
        }

        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                if(value1.type != Type.DOUBLE) gen.fcvtsw(F.FT1.n, R.T1.n);
                if(value2.type != Type.DOUBLE) gen.fcvtsw(F.FT0.n, R.T0.n);
                gen.callBuiltin("equal");
                env.pushObject(new StackValue(Type.BOOLEAN, 4, env.depth));
                return;
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            env.pushObject(new StackValue(Type.NULL, 4, env.depth));
            return;
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            gen.add(R.A0.n, R.Z.n, R.T1.n);
            gen.add(R.A1.n, R.Z.n, R.T0.n);
            gen.callBuiltin("equalStrings");
            env.pushObject(new StackValue(Type.BOOLEAN, 4, env.depth));
            return;
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        env.pushObject(new StackValue(Type.NULL, 4, env.depth));
    }
    public void notEqual(Env env, RiscVGen gen) {
        this.exp1.exec(env, gen);
        this.exp2.exec(env, gen);

        StackValue value2 = env.popObject();
        StackValue value1 = env.popObject();

        if(value2.type == Type.DOUBLE) {
            gen.popFloat(F.FT0.n);
        } else {
            gen.pop(R.T0.n);
        }

        if(value1.type == Type.DOUBLE) {
            gen.popFloat(F.FT1.n);
        } else {
            gen.pop(R.T1.n);
        }

        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                if(value1.type != Type.DOUBLE) gen.fcvtsw(F.FT1.n, R.T1.n);
                if(value2.type != Type.DOUBLE) gen.fcvtsw(F.FT0.n, R.T0.n);
                gen.callBuiltin("notEqual");
                env.pushObject(new StackValue(Type.BOOLEAN, 4, env.depth));
                return;
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            env.pushObject(new StackValue(Type.NULL, 4, env.depth));
            return;
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            gen.add(R.A0.n, R.Z.n, R.T1.n);
            gen.add(R.A1.n, R.Z.n, R.T0.n);
            gen.callBuiltin("notEqualStrings");
            env.pushObject(new StackValue(Type.BOOLEAN, 4, env.depth));
            return;
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        env.pushObject(new StackValue(Type.NULL, 4, env.depth));
    }
    public void greaterEqual(Env env, RiscVGen gen) {
        this.exp1.exec(env, gen);
        this.exp2.exec(env, gen);

        StackValue value2 = env.popObject();
        StackValue value1 = env.popObject();

        if(value2.type == Type.DOUBLE) {
            gen.popFloat(F.FT0.n);
        } else {
            gen.pop(R.T0.n);
        }

        if(value1.type == Type.DOUBLE) {
            gen.popFloat(F.FT1.n);
        } else {
            gen.pop(R.T1.n);
        }

        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                if(value1.type != Type.DOUBLE) gen.fcvtsw(F.FT1.n, R.T1.n);
                if(value2.type != Type.DOUBLE) gen.fcvtsw(F.FT0.n, R.T0.n);
                gen.callBuiltin("greaterEqual");
                env.pushObject(new StackValue(Type.BOOLEAN, 4, env.depth));
                return;
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            env.pushObject(new StackValue(Type.NULL, 4, env.depth));
            return;
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            gen.add(R.A0.n, R.Z.n, R.T1.n);
            gen.add(R.A1.n, R.Z.n, R.T0.n);
            gen.callBuiltin("greaterEqualStrings");
            env.pushObject(new StackValue(Type.BOOLEAN, 4, env.depth));
            return;
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        env.pushObject(new StackValue(Type.NULL, 4, env.depth));
    }
    public void lessEqual(Env env, RiscVGen gen) {
        this.exp1.exec(env, gen);
        this.exp2.exec(env, gen);

        StackValue value2 = env.popObject();
        StackValue value1 = env.popObject();

        if(value2.type == Type.DOUBLE) {
            gen.popFloat(F.FT0.n);
        } else {
            gen.pop(R.T0.n);
        }

        if(value1.type == Type.DOUBLE) {
            gen.popFloat(F.FT1.n);
        } else {
            gen.pop(R.T1.n);
        }

        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                if(value1.type != Type.DOUBLE) gen.fcvtsw(F.FT1.n, R.T1.n);
                if(value2.type != Type.DOUBLE) gen.fcvtsw(F.FT0.n, R.T0.n);
                gen.callBuiltin("lessEqual");
                env.pushObject(new StackValue(Type.BOOLEAN, 4, env.depth));
                return;
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            env.pushObject(new StackValue(Type.NULL, 4, env.depth));
            return;
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            gen.add(R.A0.n, R.Z.n, R.T1.n);
            gen.add(R.A1.n, R.Z.n, R.T0.n);
            gen.callBuiltin("lessEqualStrings");
            env.pushObject(new StackValue(Type.BOOLEAN, 4, env.depth));
            return;
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        env.pushObject(new StackValue(Type.NULL, 4, env.depth));
    }
    public void greater(Env env, RiscVGen gen) {
        this.exp1.exec(env, gen);
        this.exp2.exec(env, gen);

        StackValue value2 = env.popObject();
        StackValue value1 = env.popObject();

        if(value2.type == Type.DOUBLE) {
            gen.popFloat(F.FT0.n);
        } else {
            gen.pop(R.T0.n);
        }

        if(value1.type == Type.DOUBLE) {
            gen.popFloat(F.FT1.n);
        } else {
            gen.pop(R.T1.n);
        }

        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                if(value1.type != Type.DOUBLE) gen.fcvtsw(F.FT1.n, R.T1.n);
                if(value2.type != Type.DOUBLE) gen.fcvtsw(F.FT0.n, R.T0.n);
                gen.callBuiltin("greater");
                env.pushObject(new StackValue(Type.BOOLEAN, 4, env.depth));
                return;
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            env.pushObject(new StackValue(Type.NULL, 4, env.depth));
            return;
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            gen.add(R.A0.n, R.Z.n, R.T1.n);
            gen.add(R.A1.n, R.Z.n, R.T0.n);
            gen.callBuiltin("greaterStrings");
            env.pushObject(new StackValue(Type.BOOLEAN, 4, env.depth));
            return;
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        env.pushObject(new StackValue(Type.NULL, 4, env.depth));
    }
    public void less(Env env, RiscVGen gen) {
        this.exp1.exec(env, gen);
        this.exp2.exec(env, gen);

        StackValue value2 = env.popObject();
        StackValue value1 = env.popObject();

        if(value2.type == Type.DOUBLE) {
            gen.popFloat(F.FT0.n);
        } else {
            gen.pop(R.T0.n);
        }

        if(value1.type == Type.DOUBLE) {
            gen.popFloat(F.FT1.n);
        } else {
            gen.pop(R.T1.n);
        }

        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                if(value1.type != Type.DOUBLE) gen.fcvtsw(F.FT1.n, R.T1.n);
                if(value2.type != Type.DOUBLE) gen.fcvtsw(F.FT0.n, R.T0.n);
                gen.callBuiltin("less");
                env.pushObject(new StackValue(Type.BOOLEAN, 4, env.depth));
                return;
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            env.pushObject(new StackValue(Type.NULL, 4, env.depth));
            return;
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            gen.add(R.A0.n, R.Z.n, R.T1.n);
            gen.add(R.A1.n, R.Z.n, R.T0.n);
            gen.callBuiltin("lessStrings");
            env.pushObject(new StackValue(Type.BOOLEAN, 4, env.depth));
            return;
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        env.pushObject(new StackValue(Type.NULL, 4, env.depth));
    }
}