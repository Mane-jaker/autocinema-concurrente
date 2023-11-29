module com.example.autocinema {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.autocinema to javafx.fxml;
    opens  com.example.autocinema.controllers;
    exports com.example.autocinema;
    exports com.example.autocinema.models;
    exports  com.example.autocinema.controllers;
}