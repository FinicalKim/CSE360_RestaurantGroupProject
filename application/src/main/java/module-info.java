module restaurant_project {
    requires javafx.controls;
    requires javafx.fxml;

    opens restaurant_project to javafx.fxml;
    exports restaurant_project;
}
