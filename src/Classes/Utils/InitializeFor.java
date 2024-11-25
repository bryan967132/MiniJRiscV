package Classes.Utils;
import java.util.ArrayList;
import Classes.Abstracts.Instruction;
public class InitializeFor {
    public Instruction inits;
    public ArrayList<Instruction> assigns;
    public InitializeFor(Instruction inits) {
        this.inits = inits;
    }
    public InitializeFor(ArrayList<Instruction> asigns) {
        this.assigns = asigns;
    }
}