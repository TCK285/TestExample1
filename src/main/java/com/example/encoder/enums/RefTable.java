package com.example.encoder.enums;

public enum RefTable {
    A ("A"),
    B ("B"),
    C ("C"),
    D ("D"),
    E ("E"),
    F ("F"),
    G ("G"),
    H ("H"),
    I ("I"),
    J ("J"),
    K ("K"),
    L ("L"),
    M ("M"),
    N ("N"),
    O ("O"),
    P ("P"),
    Q ("Q"),
    R ("R"),
    S ("S"),
    T ("T"),
    U ("U"),
    V ("V"),
    W ("W"),
    X ("X"),
    Y ("Y"),
    Z ("Z"),
    ZERO ("0"),
    ONE ("1"),
    TWO ("2"),
    THREE ("3"),
    FOUR ("4"),
    FIVE ("5"),
    SIX ("6"),
    SEVEN ("7"),
    EIGHT ("8"),
    NINE ("9"),
    LP ("("),
    RP (")"),
    ASTERISK ("*"),
    PLUS ("+"),
    COMMA (","),
    MINUS ("-"),
    PERIOD ("."),
    FS ("/");

    private String string;
    RefTable(String string){this.string = string;}

    public String getString(){return string;}

}
