 /**
 * Model containing engine and ui of the trusted list navigation
 *
 *  <p>
 *      This program was made as Software engineering final project
 *  </p>
 *
 *  <p>
 *      This project was made by
 *      <ul>
 *        <li>Ferrari Davide</li>
 *        <li>Cazzador Lorenzo</li>
 *        <li>Corrò Simone</li>
 *        <li>Piron Samuel</li>
 *      </ul>
 *
 */
 module trustedlist {
    // Requires
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.lineawesome;
    requires org.kordamp.bootstrapfx.core;
    requires org.junit.jupiter.api;

    // Opens
     opens trustedlist.controllers to javafx.fxml;
     opens trustedlist.controllers.panes to javafx.fxml;
     opens trustedlist.controllers.cards to javafx.fxml;

    // Exports
    exports trustedlist;
    exports trustedlist.models.DTO;
    exports trustedlist.models.exceptions;
    exports trustedlist.models.engine;
 }