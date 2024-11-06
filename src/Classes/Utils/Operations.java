package Classes.Utils;
public class Operations {
    public static Type[][] plus = {
        {Type.INT,      Type.DOUBLE,    Type.INT,       Type.INT,       Type.STRING},
        {Type.DOUBLE,   Type.DOUBLE,    Type.DOUBLE,    Type.DOUBLE,    Type.STRING},
        {Type.INT,      Type.DOUBLE,    Type.NULL,      Type.NULL,      Type.STRING},
        {Type.INT,      Type.DOUBLE,    Type.NULL,      Type.STRING,    Type.STRING},
        {Type.STRING,   Type.STRING,    Type.STRING,    Type.STRING,    Type.STRING}
    };
    public static Type[][] minus = {
        {Type.INT,      Type.DOUBLE,    Type.INT,       Type.INT,       Type.NULL},
        {Type.DOUBLE,   Type.DOUBLE,    Type.DOUBLE,    Type.DOUBLE,    Type.NULL},
        {Type.INT,      Type.DOUBLE,    Type.NULL,      Type.NULL,      Type.NULL},
        {Type.INT,      Type.DOUBLE,    Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL,      Type.NULL}
    };
    public static Type[][] mult = {
        {Type.INT,      Type.DOUBLE,    Type.NULL,      Type.INT,       Type.NULL},
        {Type.DOUBLE,   Type.DOUBLE,    Type.NULL,      Type.DOUBLE,    Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL,      Type.NULL},
        {Type.INT,      Type.DOUBLE,    Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL,      Type.NULL}
    };
    public static Type[][] div = {
        {Type.DOUBLE,   Type.DOUBLE,    Type.NULL,      Type.DOUBLE,    Type.NULL},
        {Type.DOUBLE,   Type.DOUBLE,    Type.NULL,      Type.DOUBLE,    Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL,      Type.NULL},
        {Type.DOUBLE,   Type.DOUBLE,    Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL,      Type.NULL}
    };
    public static Type[][] pow = {
        {Type.INT,      Type.DOUBLE,    Type.NULL,      Type.NULL,      Type.NULL},
        {Type.DOUBLE,   Type.DOUBLE,    Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL,      Type.NULL}
    };
    public static Type[][] mod = {
        {Type.INT,      Type.DOUBLE,    Type.NULL,      Type.NULL,      Type.NULL},
        {Type.DOUBLE,   Type.DOUBLE,    Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL,      Type.NULL},
        {Type.NULL,     Type.NULL,      Type.NULL,      Type.NULL,      Type.NULL}
    };
}