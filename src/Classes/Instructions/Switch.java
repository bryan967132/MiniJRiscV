package Classes.Instructions;
import java.util.ArrayList;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generator.R;
import Classes.Generator.RiscVGen;
import Classes.Utils.TypeInst;
public class Switch extends Instruction {
    Expression arg;
    ArrayList<Case> cases;
    Block _default;
    public Switch(int line, int column, Expression arg, ArrayList<Case> cases, Block _default) {
        super(line, column, TypeInst.SWITCH);
        this.arg = arg;
        this.cases = cases;
        this._default = _default;
    }
    public void exec(Env env, RiscVGen gen) {
        if(env.previous == null) {
            gen.addComment("========== SWITCH ===========");
            final String endSwitchLabel = gen.getLabel();
            env.breakLabel = endSwitchLabel;
            
            ArrayList<String> labels = new ArrayList<>();
            gen.addComment("=========== CASOS ===========");
            this.cases.forEach(case_ -> {
                case_._case.exec(env, gen);
                final String labelCase = gen.getLabel();
                labels.add(labelCase);
                this.arg.exec(env, gen);
                env.popObject();
                gen.pop();
                env.popObject();
                gen.pop(R.T1.n);
                gen.beq(R.T0.n, R.T1.n, labelCase);
            });
            gen.addComment("========= FIN CASOS =========");

            if(this._default != null) {
                final String labelDefault = gen.getLabel();
                labels.add(labelDefault);
                gen.j(labelDefault);
            } else {
                gen.j(endSwitchLabel);
            }

            for(int i = 0; i < this.cases.size(); i ++) {
                gen.addLabel(labels.get(i));
                this.cases.get(i).block.exec(env, gen);
            }

            if(this._default != null) {
                gen.addLabel(labels.get(labels.size() - 1));
                this._default.exec(env, gen);
            }

            gen.addLabel(endSwitchLabel);

            gen.addComment("======== FIN SWITCH =========");
        }
    }
}
