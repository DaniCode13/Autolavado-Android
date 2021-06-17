package com.example.autolavado.utilities;

public class Utilities {
    public static final String DB_NAME = "autolavado" ;
    //constants
    public static String TABLE_ENTRIES = "entries";
    public static String ID = "id";
    public static String PRICE = "price";
    public static String TYPE_ENTRY = "type_entry";


    public static final String CREATE_TABLE_ENTRY = "CREATE TABLE " + TABLE_ENTRIES + "(" +
            PRICE + " TEXT, " +
            TYPE_ENTRY + " TEXT)";


    public static String []OPTIONS_TYPE_ENTRIES = {"Carro", "Camioneta","Moto"};

}
