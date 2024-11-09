package Classes.Generator;
public enum R {
    Z   ("x0",  "zero", "Argumento 0/ siempre vale 0/ solo lectura"            ),
    RA  ("x1",  "ra",   "Direccion de retorno a una subrutina"                 ),
    SP  ("x2",  "sp",   "Puntero de pila"                                      ),
    GP  ("x3",  "gp",   "Puntero de global/ usado para acceder al .bss y .data"),
    TP  ("x4",  "tp",   "Puntero de hilo/ puntero de hilo de ejecucion"        ),
    T0  ("x5",  "t0",   "Temporal 0"                                           ),
    T1  ("x6",  "t1",   "Temporal 1"                                           ),
    T2  ("x7",  "t2",   "Temporal 2"                                           ),
    S0  ("x8",  "s0",   "Guardado 0/Frame pointer"                             ),
    S1  ("x9",  "s1",   "Guardado 1/Como temporal"                             ),
    A0  ("x10", "a0",   "Argumento 0/v de retorno/Devolver v sin el stack"     ),
    A1  ("x11", "a1",   "Argumento 1/v de retorno/Devolver v sin el stack"     ),
    A2  ("x12", "a2",   "Argumento de funcion"                                 ),
    A3  ("x13", "a3",   "Argumento de funcion"                                 ),
    A4  ("x14", "a4",   "Argumento de funcion"                                 ),
    A5  ("x15", "a5",   "Argumento de funcion"                                 ),
    A6  ("x16", "a6",   "Argumento de funcion"                                 ),
    A7  ("x17", "a7",   "Argumento de funcion"                                 ),
    S2  ("x18", "s2",   "Registro de guardado"                                 ),
    S3  ("x19", "s3",   "Registro de guardado"                                 ),
    S4  ("x20", "s4",   "Registro de guardado"                                 ),
    S5  ("x21", "s5",   "Registro de guardado"                                 ),
    S6  ("x22", "s6",   "Registro de guardado"                                 ),
    S7  ("x23", "s7",   "Registro de guardado"                                 ),
    S8  ("x24", "s8",   "Registro de guardado"                                 ),
    S9  ("x25", "s9",   "Registro de guardado"                                 ),
    S10 ("x26", "s10",  "Registro de guardado"                                 ),
    S11 ("x27", "s11",  "Registro de guardado"                                 ),
    T3  ("x28", "t3",   "Temporal"                                             ),
    T4  ("x29", "t4",   "Temporal"                                             ),
    T5  ("x30", "t5",   "Temporal"                                             ),
    T6  ("x31", "t6",   "Temporal"                                             );

    public final String v;
    public final String n;
    public final String description;
    private R(String v, String n, String description) {
        this.v = v;
        this.n = n;
        this.description = description;
    }
}