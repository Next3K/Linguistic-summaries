module model {
    requires java.desktop;
    requires static lombok;
//    requires com.fasterxml.jackson.core;
//    requires com.fasterxml.jackson.databind;
//    requires org.apache.commons.collections4;
//
//    exports org.example.util;
    exports org.example.model;
    opens org.example.model;
    //exports org.example.model.signals;
    //exports org.example.model.conversion;
}