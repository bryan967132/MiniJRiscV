package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generator.F;
import Classes.Generator.R;
import Classes.Generator.RiscVGen;
import Classes.Instructions.Print;
import Classes.Utils.Operations;
import Classes.Utils.StackValue;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Arithmetic extends Expression {
    private Expression exp1;
    private String sign;
    private Expression exp2;
    private Type type;
    public Arithmetic(int line, int column, Expression exp1, String sign, Expression exp2) {
        super(line, column, TypeExp.ARITHMETIC_OP);
        this.exp1 = exp1;
        this.sign = sign;
        this.exp2 = exp2;
        this.type = Type.NULL;
    }
    public void exec(Env env, RiscVGen gen) {
        switch (this.sign) {
            case "+":
                plus(env, gen);
                break;
            case "-":
                if(exp1 != null) {
                    minus(env, gen);
                } else {
                    uminus(env, gen);
                }
                break;
            case "*":
                mult(env, gen);
                break;
            case "/":
                div(env, gen);
                break;
            case "^":
                pow(env, gen);
                break;
            case "%":
                mod(env, gen);
                break;
            default:
                break;
        }
    }
    public void plus(Env env, RiscVGen gen) {
        this.exp1.exec(env, gen);
        this.exp2.exec(env, gen);

        StackValue value2 = env.popObject();
        StackValue value1 = env.popObject();

        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 >= 5 || t2 >= 5) ? Operations.plus[t1][t2] : Type.NULL;

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

        if(type != Type.NULL) {
            if(type == Type.INT) {
                gen.add(R.T0.n, R.T0.n, R.T1.n);
                gen.push(R.T0.n);
                env.pushObject(new StackValue(type, 4, env.depth));
                return;
            }
            if(type == Type.DOUBLE) {
                if(value1.type != Type.DOUBLE) gen.fcvtsw(F.FT1.n, R.T1.n);
                if(value2.type != Type.DOUBLE) gen.fcvtsw(F.FT0.n, R.T0.n);
                gen.fadd(F.FT0.n, F.FT1.n, F.FT0.n);
                gen.pushFloat(F.FT0.n);
                env.pushObject(new StackValue(type, 4, env.depth));
                return;
            }
            if(type == Type.STRING) {
                if(value1.type == value2.type && value1.type == Type.STRING) {
                    gen.add(R.A0.n, R.Z.n, R.T1.n);
                    gen.add(R.A1.n, R.Z.n, R.T0.n);
                    gen.callBuiltin("concatString");
                    env.pushObject(new StackValue(type, 4, env.depth));
                    return;
                }
                // OTROS CASOS VÁLIDOS
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp1.line, exp1.column);
        env.pushObject(new StackValue(Type.NULL, 4, env.depth));
    }
    public void minus(Env env, RiscVGen gen) {
        this.exp1.exec(env, gen);
        this.exp2.exec(env, gen);

        StackValue value2 = env.popObject();
        StackValue value1 = env.popObject();

        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 >= 5 || t2 >= 5) ? Operations.minus[t1][t2] : Type.NULL;

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

        if(type != Type.NULL) {
            if(type == Type.INT) {
                gen.sub(R.T0.n, R.T1.n, R.T0.n);
                gen.push(R.T0.n);
                env.pushObject(new StackValue(type, 4, env.depth));
                return;
            }
            if(type == Type.DOUBLE) {
                if(value1.type != Type.DOUBLE) gen.fcvtsw(F.FT1.n, R.T1.n);
                if(value2.type != Type.DOUBLE) gen.fcvtsw(F.FT0.n, R.T0.n);
                gen.fsub(F.FT0.n, F.FT1.n, F.FT0.n);
                gen.pushFloat(F.FT0.n);
                env.pushObject(new StackValue(type, 4, env.depth));
                return;
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp1.line, exp1.column);
        env.pushObject(new StackValue(Type.NULL, 4, env.depth));
    }
    public void uminus(Env env, RiscVGen gen) {
        this.exp2.exec(env, gen);

        StackValue value2 = env.popObject();
        type = value2.type;

        if(value2.type == Type.DOUBLE) {
            gen.popFloat(F.FT0.n);
        } else {
            gen.pop(R.T0.n);
        }

        if(type != Type.NULL) {
            if(type == Type.INT) {
                gen.li(R.T1.n, 0);
                gen.sub(R.T0.n, R.T1.n, R.T0.n);;
                gen.push(R.T0.n);;
                env.pushObject(new StackValue(type, 4, env.depth));
                return;
            }
            if(type == Type.DOUBLE) {
                if(value2.type != Type.DOUBLE) gen.fcvtsw(F.FT1.n, R.T1.n);
                gen.li(R.T1.n, 0);
                gen.fmvsx(F.FT1.n, R.T1.n);
                gen.fsub(F.FT0.n, F.FT1.n, F.FT0.n);
                gen.pushFloat(F.FT0.n);
                env.pushObject(new StackValue(type, 4, env.depth));
                return;
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp2.line, exp2.column);
        env.pushObject(new StackValue(Type.NULL, 4, env.depth));
    }
    public void mult(Env env, RiscVGen gen) {
        this.exp1.exec(env, gen);
        this.exp2.exec(env, gen);

        StackValue value2 = env.popObject();
        StackValue value1 = env.popObject();

        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 >= 5 || t2 >= 5) ? Operations.mult[t1][t2] : Type.NULL;

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

        if(type != Type.NULL) {
            if(type == Type.INT) {
                gen.mul(R.T0.n, R.T1.n, R.T0.n);
                gen.push(R.T0.n);
                env.pushObject(new StackValue(type, 4, env.depth));
                return;
            }
            if(type == Type.DOUBLE) {
                if(value1.type != Type.DOUBLE) gen.fcvtsw(F.FT1.n, R.T1.n);
                if(value2.type != Type.DOUBLE) gen.fcvtsw(F.FT0.n, R.T0.n);
                gen.fmul(F.FT0.n, F.FT1.n, F.FT0.n);
                gen.pushFloat(F.FT0.n);
                env.pushObject(new StackValue(type, 4, env.depth));
                return;
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp1.line, exp1.column);
        env.pushObject(new StackValue(Type.NULL, 4, env.depth));
    }
    public void div(Env env, RiscVGen gen) {
        this.exp1.exec(env, gen);
        this.exp2.exec(env, gen);

        StackValue value2 = env.popObject();
        StackValue value1 = env.popObject();

        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 >= 5 || t2 >= 5) ? Operations.div[t1][t2] : Type.NULL;

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

        if(type != Type.NULL) {
            if(type == Type.DOUBLE) {
                if(value1.type != Type.DOUBLE) gen.fcvtsw(F.FT1.n, R.T1.n);
                if(value2.type != Type.DOUBLE) gen.fcvtsw(F.FT0.n, R.T0.n);

                final String labelError = gen.getLabel();
                final String labelEnd = gen.getLabel();

                gen.fmvsx(F.FT2.n, R.Z.n);
                gen.feqs(R.T0.n, F.FT0.n, F.FT2.n);
                gen.bne(R.T0.n, R.Z.n, labelError);
                gen.fdiv(F.FT0.n, F.FT1.n, F.FT0.n);
                gen.pushFloat(F.FT0.n);
                gen.j(labelEnd);

                gen.addLabel(labelError);
                new Print(0, 0, new Primitive(0, 0, "Math Error: Zero Division\nnull", Type.STRING), true).exec(env, gen);
                final String ieee754 = Classes.Generator.Utils.numberToF32(-1);
                gen.li(R.T0.n, ieee754);
                gen.push();
                gen.addLabel(labelEnd);

                env.pushObject(new StackValue(type, 4, env.depth));
                return;
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp1.line, exp1.column);
        env.pushObject(new StackValue(Type.NULL, 4, env.depth));
    }
    public void pow(Env env, RiscVGen gen) {
    }
    public void mod(Env env, RiscVGen gen) {
        this.exp1.exec(env, gen);
        this.exp2.exec(env, gen);

        StackValue value2 = env.popObject();
        StackValue value1 = env.popObject();

        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 >= 5 || t2 >= 5) ? Operations.mod[t1][t2] : Type.NULL;

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

        if(type != Type.NULL) {
            if(type == Type.INT) {
                final String labelError = gen.getLabel();
                final String labelEnd = gen.getLabel();

                gen.beq(R.T0.n, R.Z.n, labelError);
                gen.rem(R.T0.n, R.T1.n, R.T0.n);
                gen.push(R.T0.n);
                gen.j(labelEnd);
                
                gen.addLabel(labelError);
                new Print(0, 0, new Primitive(0, 0, "Math Error: Zero Remainder\nnull", Type.STRING), true).exec(env, gen);
                gen.li(R.T0.n, -1);
                gen.push(R.T0.n);
                gen.addLabel(labelEnd);

                env.pushObject(new StackValue(type, 4, env.depth));
                return;
            }
            if(type == Type.DOUBLE) {
                if(value1.type != Type.DOUBLE) gen.fcvtsw(F.FT1.n, R.T1.n);
                if(value2.type != Type.DOUBLE) gen.fcvtsw(F.FT0.n, R.T0.n);

                final String labelError = gen.getLabel();
                final String labelEnd = gen.getLabel();

                gen.fmvsx(F.FT2.n, R.Z.n);
                gen.feqs(R.T0.n, F.FT0.n, F.FT2.n);
                gen.bne(R.T0.n, R.Z.n, labelError);
                gen.fdiv(F.FT2.n, F.FT1.n, F.FT0.n); // COCIENTE: FT0 = FT1 / FT0
                gen.fcvtws(R.T0.n, F.FT2.n);         // COCIENTE A ENTERO: T0
                gen.fcvtsw(F.FT2.n, R.T0.n);         // COCIENTE A FLOTANTE (ELIMINA PARTE DECIMAL): FT2
                gen.fmul(F.FT2.n, F.FT2.n, F.FT0.n); // COCIENTE ENTERO POR DIVISOR: FT2 = FT2 * FT0
                gen.fsub(F.FT0.n, F.FT1.n, F.FT2.n); // CALCULAR MODULO: FT0 = FT1 - FT2
                gen.pushFloat(F.FT0.n);
                gen.j(labelEnd);

                gen.addLabel(labelError);
                new Print(0, 0, new Primitive(0, 0, "Math Error: Zero Remainder\nnull", Type.STRING), true).exec(env, gen);
                final String ieee754 = Classes.Generator.Utils.numberToF32(-1);
                gen.li(R.T0.n, ieee754);
                gen.push();
                gen.addLabel(labelEnd);

                env.pushObject(new StackValue(type, 4, env.depth));
                return;
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp1.line, exp1.column);
        env.pushObject(new StackValue(Type.NULL, 4, env.depth));
    }
}