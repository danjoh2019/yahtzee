module com.danjoh2019 {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.danjoh2019;
    exports com.danjoh2019.controllers;
    opens com.danjoh2019.controllers to javafx.fxml;
}
