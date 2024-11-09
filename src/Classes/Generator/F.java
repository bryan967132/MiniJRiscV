package Classes.Generator;
public enum F {
    FT0  ("f0",  "ft0",  "Temporal"                                                 ),
    FT1  ("f1",  "ft1",  "Temporal"                                                 ),
    FT2  ("f2",  "ft2",  "Temporal"                                                 ),
    FT3  ("f3",  "ft3",  "Temporal"                                                 ),
    FT4  ("f4",  "ft4",  "Temporal"                                                 ),
    FT5  ("f5",  "ft5",  "Temporal"                                                 ),
    FT6  ("f6",  "ft6",  "Temporal"                                                 ),
    FT7  ("f7",  "ft7",  "Temporal"                                                 ),
    FS0  ("f8",  "fs0",  "Registro de guardado"                                     ),
    FS1  ("f9",  "fs1",  "Registro de guardado"                                     ),
    FA0  ("f10", "fa0",  "Argumento de funcion/v de retorno/Devolver v sin el stack"),
    FA1  ("f11", "fa1",  "Argumento de funcion/v de retorno/Devolver v sin el stack"),
    FA2  ("f12", "fa2",  "Argumento de funcion/Recibe v sin el stack"               ),
    FA3  ("f13", "fa3",  "Argumento de funcion/Recibe v sin el stack"               ),
    FA4  ("f14", "fa4",  "Argumento de funcion/Recibe v sin el stack"               ),
    FA5  ("f15", "fa5",  "Argumento de funcion/Recibe v sin el stack"               ),
    FA6  ("f16", "fa6",  "Argumento de funcion/Recibe v sin el stack"               ),
    FA7  ("f17", "fa7",  "Argumento de funcion/Recibe v sin el stack"               ),
    FS2  ("f18", "fs2",  "Registro de guardado"                                     ),
    FS3  ("f19", "fs3",  "Registro de guardado"                                     ),
    FS4  ("f20", "fs4",  "Registro de guardado"                                     ),
    FS5  ("f21", "fs5",  "Registro de guardado"                                     ),
    FS6  ("f22", "fs6",  "Registro de guardado"                                     ),
    FS7  ("f23", "fs7",  "Registro de guardado"                                     ),
    FS8  ("f24", "fs8",  "Registro de guardado"                                     ),
    FS9  ("f25", "fs9",  "Registro de guardado"                                     ),
    FS10 ("f26", "fs10", "Registro de guardado"                                     ),
    FS11 ("f27", "fs11", "Registro de guardado"                                     ),
    FT8  ("f28", "ft8",  "Temporal"                                                 ),
    FT9  ("f29", "ft9",  "Temporal"                                                 ),
    FT10 ("f30", "ft10", "Temporal"                                                 ),
    FT11 ("f31", "ft11", "Temporal"                                                 );

    public final String v;
    public final String n;
    public final String descripcion;
    private F(String v, String n, String descripcion) {
        this.v = v;
        this.n = n;
        this.descripcion = descripcion;
    }
}