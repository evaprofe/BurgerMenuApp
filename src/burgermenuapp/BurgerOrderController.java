
package burgermenuapp;

import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;



public class BurgerOrderController {
    @FXML private ComboBox<String> cmbHamburguesa, cmbPan, cmbPatatas, cmbBebida;
    @FXML private CheckBox chkDoble, chkQueso, chkExtraPatatas, chkKetchup, chkBarbacoa, chkMostaza, chkThai;
    @FXML
    private RadioButton radLocal;
    @FXML private Label lblPrecioBase, lblPrecioExtras, lblPrecioSubtotal, lblPrecioIVA, lblPrecioTotal;

    private final  BurgerOrder burgerOrder;

    public BurgerOrderController() {
        this.burgerOrder = new BurgerOrder();
    }

    @FXML
    public void initialize() {
        cmbHamburguesa.getItems().addAll("Pollo", "Cerdo", "Ternera (+1?)", "Vegana (+1)?");
        cmbPan.getItems().addAll("Normal", "Integral", "Centeno");
        cmbPatatas.getItems().addAll("Fritas", "Gajo", "Caseras (+1?)");
        cmbBebida.getItems().addAll("Refresco de cola", "Refresco de naranja", "Refresco de limón", "Agua", "Cerveza");

        cmbHamburguesa.setValue("Pollo");
        cmbPan.setValue("Normal");
        cmbPatatas.setValue("Fritas");
        cmbBebida.setValue("Refresco de cola");

        actualizarPrecio();
    }

    @FXML
    private void actualizarPrecio() {
        // Calcular extras
        double extras = burgerOrder.calcularExtras(
                chkDoble.isSelected(),
                chkQueso.isSelected(),
                chkExtraPatatas.isSelected(),
                chkKetchup.isSelected(),
                chkBarbacoa.isSelected(),
                chkMostaza.isSelected(),
                chkThai.isSelected()
        );

        // Calcular subtotal
        boolean recogidaLocal = radLocal.isSelected();
        double subtotal = burgerOrder.calcularSubtotal(extras, recogidaLocal);

        // Calcular IVA y total
        double iva = burgerOrder.calcularIVA(subtotal);
        double total = burgerOrder.calcularTotal(subtotal, iva);

        // Actualizar etiquetas
        lblPrecioBase.setText(String.format("%.2f?", burgerOrder.getPrecioBase()));
        lblPrecioExtras.setText(String.format("%.2f?", extras));
        lblPrecioSubtotal.setText(String.format("%.2f?", subtotal));
        lblPrecioIVA.setText(String.format("%.2f?", iva));
        lblPrecioTotal.setText(String.format("%.2f?", total));
    }

    @FXML
    private void realizarPedido() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("¡Gracias por tu pedido!");
        alert.setContentText("Tu pedido ha sido registrado correctamente.\nPrecio total: " + lblPrecioTotal.getText());
        alert.showAndWait();
    }
}
