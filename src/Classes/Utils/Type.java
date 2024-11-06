package Classes.Utils;
public enum Type {
    INT     ("int"    ) ,
    DOUBLE  ("double" ) ,
    BOOLEAN ("boolean") ,
    CHAR    ("char"   ) ,
    STRING  ("String" ) ,
    NULL    ("null"   ) ;

    private String value;
    private Type(String value) {
        this.value = value;
    }
    public String getValue() {return value;}
}