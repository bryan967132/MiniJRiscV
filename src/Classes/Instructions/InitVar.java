package Classes.Instructions;
import java.util.ArrayList;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Expressions.Primitive;
import Classes.Generator.R;
import Classes.Generator.RiscVGen;
import Classes.Utils.IDValue;
import Classes.Utils.StackValue;
import Classes.Utils.Type;
import Classes.Utils.TypeInst;
public class InitVar extends Instruction {
    ArrayList<IDValue> inits;
    Type type;
    public InitVar(int line, int column, ArrayList<IDValue> inits, Type type) {
        super(line, column, TypeInst.INITVAR);
        this.inits = inits;
        this.type = type;
    }
    public void exec(Env env, RiscVGen gen) {
        if(env.previous == null) {
            gen.addComment("======== DECLARACION ========");
            for(IDValue idvalue : inits) {
                if(idvalue.value != null) {
                    idvalue.value.exec(env, gen);
                    if(env.isInsideFunction) {
                        final StackValue localObj = env.getFrameLocal(env.frameDclIndex);
                        gen.pop();
                        StackValue value = env.popObject();
                        // StackValue value = env.getTopObject();

                        gen.addi(R.T1.n, R.S0.n, -localObj.offset * 4);
                        gen.sw(R.T0.n, R.T1.n);

                        localObj.type = value.type;

                        env.frameDclIndex ++;
                        continue;
                    }
                    env.tagObject(idvalue.id);
                } else {
                    switch(type) {
                        case INT:
                            new Primitive(line, column, 0, type).exec(env, gen);
                            break;
                        case DOUBLE:
                            new Primitive(line, column, 0.0, type).exec(env, gen);
                            break;
                        case BOOLEAN:
                            new Primitive(line, column, true, type).exec(env, gen);
                            break;
                        case CHAR:
                            new Primitive(line, column, '\0', type).exec(env, gen);
                            break;
                        case STRING:
                            new Primitive(line, column, "", type).exec(env, gen);
                            break;
                        default:
                            new Primitive(line, column, "null", Type.NULL).exec(env, gen);
                            break;
                    }

                    if(env.isInsideFunction) {
                        final StackValue localObj = env.getFrameLocal(env.frameDclIndex);
                        gen.pop();
                        StackValue value = env.popObject();
                        // StackValue value = env.getTopObject();

                        gen.addi(R.T1.n, R.S0.n, -localObj.offset * 4);
                        gen.sw(R.T0.n, R.T1.n);

                        localObj.type = value.type;

                        env.frameDclIndex ++;
                        continue;
                    }
                    env.tagObject(idvalue.id);
                }
            }
            gen.addComment("====== FIN DECLARACION ======");
        } else {
            for(IDValue idvalue : inits) {
                env.pushObjectFrame(new StackValue(idvalue.id));
            }
        }
    }
}