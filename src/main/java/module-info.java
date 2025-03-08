module com.example.emailsistemisonhali {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;

    opens com.example.emailsistemisonhali to javafx.fxml;
    exports com.example.emailsistemisonhali;
}