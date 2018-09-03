package controlador;

import modelo.modelDetallesMedicamentos;

/**
 *
 * @author AMolinaDeveloper
 */
public class controllerDetallesMedicamentos {
       private static String op;
private static int cod;
private static int codcomp;
private static int codmed;
public static String mntoDetallesMedic(){
modelDetallesMedicamentos.setOp(getOp());
modelDetallesMedicamentos.setCod(getCod());
modelDetallesMedicamentos.setCodmed(getCodmed());
modelDetallesMedicamentos.setCodcomp(getCodcomp());
return modelDetallesMedicamentos.mntoDetallesMed();
}

    public static String getOp() {
        return op;
    }

    public static int getCod() {
        return cod;
    }

    public static int getCodcomp() {
        return codcomp;
    }

    public static int getCodmed() {
        return codmed;
    }

    public static void setOp(String aOp) {
        op = aOp;
    }

    public static void setCod(int aCod) {
        cod = aCod;
    }

    public static void setCodcomp(int aCodcomp) {
        codcomp = aCodcomp;
    }

    public static void setCodmed(int aCodmed) {
        codmed = aCodmed;
    }
}
