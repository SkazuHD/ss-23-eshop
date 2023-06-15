package org.eshop.ui;
import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.ui.frames.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
// Import-Anweisung für unser JLabel
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GuiEmployee extends javax.swing.JFrame {
        private javax.swing.JPanel jPanel1 = new javax.swing.JPanel();


        // Die sichtbaren Elemente werden als Objektvariablen angelegt:
        private javax.swing.JMenuBar jMenuBar =
                new javax.swing.JMenuBar();

        private javax.swing.JList Produkte =
                new javax.swing.JList<Products>();

        private javax.swing.JButton Mitarbeiter =
                new javax.swing.JButton( "Mitarbeiter" );
        private javax.swing.JFormattedTextField Search =
                new javax.swing.JFormattedTextField("SearchWidget");

        private javax.swing.JButton Produckte =
                new javax.swing.JButton("Produckte");
        private javax.swing.JButton Logout =
                new  javax.swing.JButton("Logout");
        private javax.swing.JPanel pannel =
                new javax.swing.JPanel();

        private javax.swing.JPanel pannel2 =
                new javax.swing.JPanel();

       private javax.swing.JMenuBar menu =
       new javax.swing.JMenuBar();
       Shop shop;

    private javax.swing.JPanel pannel1 =
            new javax.swing.JPanel();




    /**
         * Der Konstruktor.
         */
        public GuiEmployee(Shop shop) {
            this.shop = shop;
                // Was soll bei Klick auf das System-X rechts oben passieren:
                // Das Programm soll gänzlich beendet werden.
                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                // Beachten Sie, dass Swing gar nicht so selten auf Elemente der
                // Vorgänger-Bibliothek awt zugreift. Auch Das BorderLayout gehört
                // eigentlich zum awt. Das JPanel bekommt sein Layout:

                jPanel1.setLayout( new BorderLayout() );

                // Die Implementierung von ActionListenern sehen Sie in den
                // Codebeispielen für das FlowLayout (einzelner Listener für einen
                // einzelnen Button) oder auch beim GridLayout (ein Listener für ein
                // ganzes Array von Buttons. Hier verzichten wir auf die Implementierung
                // eines Listeners, da das BorderLayout im Grunde nur Container
                // enthalten wird, nie Controls wie die JButtons, die wir hier benützt
                // haben.

                // Hier werden die JButtons dem Panel hinzugefügt


                jPanel1.add(Produkte,java.awt.BorderLayout.CENTER);
               // jPanel1.add(jButton5, java.awt.BorderLayout.LINE_END);

                jPanel1.add(pannel,java.awt.BorderLayout.LINE_END);
                pannel.setPreferredSize(new Dimension(400,2));

                 jPanel1.add(pannel1, java.awt.BorderLayout.PAGE_START);
                 pannel1.add(jMenuBar);


                pannel1.setBackground(new Color(50));

                jMenuBar.setBackground(
                        new Color(50)
                );
                jMenuBar.setBorderPainted(true);
                jMenuBar.setBorder(new EmptyBorder(10,10,10,10));
               jMenuBar.add(menu);

                menu.add(Mitarbeiter,BorderLayout.LINE_START);
                menu.add(Search);
                menu.setBackground(new Color(50));
                //menu.setBorderPainted(true);
                menu.setBorder(new EmptyBorder(10,10,10,10));
                Search.setPreferredSize(new Dimension(300,10));
                menu.add(Logout);
                menu.add(Produckte);

                Produkte.setListData(shop.getProductSet().toArray());
                // Das Panel zum aktiven, sichtbaren Inhalt des JFrame machen:
                this.getContentPane().add ( jPanel1 ) ;
                // Alle Elemente auf kleinstmögliche Größe bringen
                pack();

                this.setVisible(true);

                // Wir lassen unseren Dialog anzeigen

        }







        /**
         * Zeigt ein JFrame im BorderLayout mit je einem Button pro Bereich an.
         *
         * Jedes Java-Programm beginnt bei einer Methode main() zu laufen, so auch
         * dieses.
         */


}

      /*  JDialog meinJDialog = new JDialog();
                meinJDialog.setTitle("JMenuBar für unser Java Tutorial Beispiel.");
                        // Wir setzen die Breite auf 450 und die Höhe 300 Pixel
                        meinJDialog.setSize(450,300);
                        // Zur Veranschaulichung erstellen wir hier eine Border
                        Border bo = new LineBorder(Color.yellow);
                        // Erstellung einer Menüleiste
                        JMenuBar bar = new JMenuBar();
                        // Wir setzen unsere Umrandung für unsere JMenuBar
                        bar.setBorder(bo);
                        // Erzeugung eines Objektes der Klasse JMenu
                        JMenu menu = new JMenu("Ich bin ein JMenu");
                        // Menü wird der Menüleiste hinzugefügt
                        bar.add(menu);
                        // Menüleiste wird für den Dialog gesetzt
                        meinJDialog.setJMenuBar(bar);
                        // Wir lassen unseren Dialog anzeigen
                        meinJDialog.setVisible(true);
                        }
*/




