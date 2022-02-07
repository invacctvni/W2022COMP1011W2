module com.example.w2022comp1011w2 {
    requires javafx.controls;
    requires javafx.fxml;
    //added javasql.
    requires java.sql;

    opens com.example.w2022comp1011w2 to javafx.fxml;
    exports com.example.w2022comp1011w2;
}