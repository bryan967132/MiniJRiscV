package Classes.Utils;
public class ReturnValue {
    public boolean isTmp;
    public String tmp;
    public String strValue;
    public int intValue;
    public double dblValue;
    public Type type;
    public String trueLbl;
    public String falseLbl;
    public boolean isTrue;
    public ReturnValue(Type type) {
        this.type = type;
    }
    public ReturnValue(boolean isTmp, Type type) {
        this.isTmp = isTmp;
        this.type = type;
    }
    public ReturnValue(boolean isTmp, int intValue, Type type) {
        this.isTmp = isTmp;
        this.strValue = String.valueOf(intValue);
        this.intValue = intValue;
        this.type = type;
    }
    public ReturnValue(boolean isTmp, double dblValue, Type type) {
        this.isTmp = isTmp;
        this.strValue = String.valueOf(dblValue);
        this.dblValue = dblValue;
        this.type = type;
    }
    public ReturnValue(boolean isTmp, String tmp, Type type) {
        this.isTmp = isTmp;
        this.tmp = tmp;
        this.strValue = tmp;
        this.type = type;
    }
    public ReturnValue(boolean isTmp, Type type, String trueLabel, String falseLabel) {
        this.isTmp = isTmp;
        this.type = type;
        this.trueLbl = trueLabel;
        this.falseLbl = falseLabel;
    }
    public ReturnValue(boolean isTmp, Type type, String trueLabel, String falseLabel, boolean isTrue) {
        this.isTmp = isTmp;
        this.type = type;
        this.trueLbl = trueLabel;
        this.falseLbl = falseLabel;
        this.isTrue = isTrue;
    }
}