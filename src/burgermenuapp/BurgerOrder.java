
package burgermenuapp;

public class BurgerOrder {
    private final double PRECIO_BASE = 8.0;

    public double calcularExtras(boolean doble, boolean queso, boolean extraPatatas, boolean ketchup, boolean barbacoa, boolean mostaza, boolean thai) {
        double precioExtras = 0;
        if (doble) precioExtras += 2;
        if (queso) precioExtras += 0.5;
        if (extraPatatas) precioExtras += 1;
        if (ketchup) precioExtras += 0.5;
        if (barbacoa) precioExtras += 0.5;
        if (mostaza) precioExtras += 0.5;
        if (thai) precioExtras += 0.5;
        return precioExtras;
    }

    public double calcularSubtotal(double extras, boolean recogidaLocal) {
        double subtotal = PRECIO_BASE + extras;
        if (recogidaLocal) subtotal *= 0.8; // Aplicar descuento del 20%
        return subtotal;
    }

    public double calcularIVA(double subtotal) {
        return subtotal * 0.21; // IVA del 21%
    }

    public double calcularTotal(double subtotal, double iva) {
        return subtotal + iva;
    }

    public double getPrecioBase() {
        return PRECIO_BASE;
    }
    
}
