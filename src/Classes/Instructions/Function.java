package Classes.Instructions;
import java.util.ArrayList;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generator.R;
import Classes.Generator.RiscVGen;
import Classes.Utils.Metadata;
import Classes.Utils.Parameter;
import Classes.Utils.StackValue;
import Classes.Utils.Type;
import Classes.Utils.TypeInst;
public class Function extends Instruction {
    public String id;
    public ArrayList<Parameter> params;
    public Block block;
    public Type type;
    public Function(int line, int column, String id, ArrayList<Parameter> params, Block block, Type type) {
        super(line, column, TypeInst.INIT_FUNCTION);
        this.id = id;
        this.params = params;
        this.block = block;
        this.type = type;
    }
    public void exec(Env env, RiscVGen gen) {
        final int baseSize = 0; // | ra | fp |

        final int paramSize = this.params.size(); // | ra | fp | p1 | p2 | ... | pn |

        final Env localEnv = new Env(env, this.id, baseSize + paramSize);
        this.block.exec(localEnv, gen);
        final ArrayList<StackValue> localFrame = localEnv.frame;
        final int localSize = localFrame.size(); // | ra | fp | p1 | p2 | ... | pn | l1 | l2 | ... | ln |

        final int returnSize = 1;

        final int totalSize = baseSize + paramSize + localSize + returnSize;
        env.functions.put(this.id, new Metadata(totalSize, this.type));

        final ArrayList<String> mainInsts = gen.riscVMain;
        final ArrayList<String> funcInsts = new ArrayList<>();
        gen.riscVMain = funcInsts;

        for(int i = 0; i < this.params.size(); i ++) {
            env.pushObject(new StackValue(this.params.get(i).id, this.params.get(i).type, 4, baseSize + i));
        }

        localFrame.forEach(localVar -> {
            env.pushObject(new StackValue(localVar, 4, Type.LOCAL));
        });

        env.isInsideFunction = true;
        env.frameDclIndex = 0;
        env.returnLabel = gen.getLabel();

        gen.addComment("==== DECLARACION FUNCION ====");
        gen.addLabel(this.id);
        
        this.block.exec(env, gen);
        
        gen.addLabel(env.returnLabel);
        
        gen.add(R.T0.n, R.Z.n, R.S0.n);
        gen.lw(R.RA.n, R.T0.n);
        gen.jalr(R.Z.n, R.RA.n, "0");
        gen.addComment("== FIN DECLARACION FUNCION ==");

        // Limpiar Metadatos
        for(int i = 0; i < paramSize + localSize; i ++) {
            env.stack.pop(); // Adelantar aún más SP
        }

        gen.riscVMain = mainInsts;

        funcInsts.forEach(inst -> {
            gen.riscVFunctions.add(inst);
        });

        env.isInsideFunction = false;
    }
}