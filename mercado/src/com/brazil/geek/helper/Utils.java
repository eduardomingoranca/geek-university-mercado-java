package com.brazil.geek.helper;

import java.text.*;
import java.util.Date;
import java.util.Locale;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Utils {

    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    static NumberFormat nf = new DecimalFormat("R$ #,##0.00",
            new DecimalFormatSymbols(new Locale("pt", "BR")));

    public static String dateToString(Date data) {
        return sdf.format(data);
    }

    public static String doubleToString(Double valor) {
        return nf.format(valor);
    }

    public static Double stringToDouble(String valor) {
        try {
          return (Double) nf.parse(valor);
        } catch (ParseException e) {
            return null;
        }
    }

    public static void pausar(int segundos) {
        try {
            SECONDS.sleep(segundos);
        } catch (InterruptedException e) {
            System.out.println("Erro ao pausar por " + segundos + " segundos.");
        }
    }


}
