/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

/**
 *
 * @author ramy
 */
public class Frame extends javax.swing.JFrame implements ItemListener
{
    private StoreDialog storeDialog;
    private boolean discard=true;
    private static Deck deck;
    private static CardPanel[] panels;
    private static final int verticalGap=50;
    private static final int horizontalGap=30;
    private static int money;
    private int tax= 7;
    Checkbox easyCheckbox;
    Checkbox mediumCheckbox;
    Checkbox hardCheckbox;
    CheckboxGroup group;
    
    public void loadGame(int index)
    {
        Game game= Store.getInstance().getGames().get(index);
        money= game.getMoney();
        tax= game.getTax();
        easyCheckbox.setState(tax==5);
        mediumCheckbox.setState(tax==7);
        hardCheckbox.setState(tax==10);
        deck.setAllToKeep();
        deck.mix();
        for(int i=0; i<5; i++)
        {
            panels[i].setCard(deck.getCard(i));
            panels[i].repaint();
            panels[i].setAllowDiscard(true);
        }
        setDiscard(true);
        label.setText("Discard four cards max");
        button.setText("Discard");
    }
    
    public void saveGame(String name, int index)
    {
        Game game= new Game(name,money,tax,new Date());
        Store.getInstance().getGames().set(index, game);
    }
    
    public StoreDialog getStoreDialog()
    {
        if(storeDialog== null)
        {
            storeDialog= new StoreDialog(this,true);
        }
        return storeDialog;
    }
    
    public void setDiscard(boolean discard)
    {
        saveMenuItem.setEnabled(!discard);
        this.discard= discard;
    }
    
    public Frame() 
    {
        initComponents();
        label.setEditable(false);
        moneyTextField.setEditable(false);
        money=100;
        FlowLayout layout=new FlowLayout(FlowLayout.LEADING,horizontalGap,verticalGap);
        panels= new CardPanel[5];
        deck= new Deck();
        pane.setLayout(layout);
        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        deck.mix();
        for(int i=0; i<5; i++)
        {
            panels[i]= new CardPanel();
            panels[i].setCard(deck.getCard(i));
            pane.add(panels[i]);
            panels[i].addMouseListener(panels[i]);
        }
        
        group= new CheckboxGroup();
        easyCheckbox= new Checkbox("Easy (tax= 5)",group,false);
        mediumCheckbox= new Checkbox("Medium (tax= 7)",group,true);
        hardCheckbox= new Checkbox("Hard (tax= 10)",group,false);
        easyCheckbox.setBounds(560, 360, 150, 30);
        mediumCheckbox.setBounds(560,390,150,30);
        hardCheckbox.setBounds(560,420,150,30);
        Font font= new Font("Lucida Grande",0,14);
        easyCheckbox.setFont(font);
        mediumCheckbox.setFont(font);
        hardCheckbox.setFont(font);
        easyCheckbox.addItemListener(this);
        mediumCheckbox.addItemListener(this);
        hardCheckbox.addItemListener(this);
        this.add(easyCheckbox);
        this.add(mediumCheckbox);
        this.add(hardCheckbox);
    }
    
    @Override
    public void itemStateChanged(ItemEvent event) 
    {   
        Object object= event.getItemSelectable();
        if(object== easyCheckbox)
        {
            tax= 5;
        }
        else if(object== mediumCheckbox)
        {
            tax= 7;
        }
        else if(object== hardCheckbox)
        {
            tax= 10;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button = new javax.swing.JButton();
        pane = new javax.swing.JPanel();
        label = new javax.swing.JTextField();
        moneyTextField = new javax.swing.JTextField();
        difficulyLabel = new java.awt.Label();
        menuBar = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        loadMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                Frame.this.windowClosing(evt);
            }
        });

        button.setText("Discard");
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout paneLayout = new org.jdesktop.layout.GroupLayout(pane);
        pane.setLayout(paneLayout);
        paneLayout.setHorizontalGroup(
            paneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 741, Short.MAX_VALUE)
        );
        paneLayout.setVerticalGroup(
            paneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 220, Short.MAX_VALUE)
        );

        label.setText("Discard four cards max");
        label.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelActionPerformed(evt);
            }
        });

        moneyTextField.setText("Money: 100 $");
        moneyTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moneyTextFieldActionPerformed(evt);
            }
        });

        difficulyLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        difficulyLabel.setText("Difficulty");

        menu.setText("Game");

        loadMenuItem.setText("Load");
        loadMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuItemPressed(evt);
            }
        });
        menu.add(loadMenuItem);

        saveMenuItem.setText("Save");
        saveMenuItem.setEnabled(false);
        saveMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuItemPressed(evt);
            }
        });
        menu.add(saveMenuItem);

        menuBar.add(menu);

        setJMenuBar(menuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(304, 304, 304)
                        .add(moneyTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 186, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(38, 38, 38)
                        .add(pane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(142, 142, 142)
                                .add(button, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 159, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(89, 89, 89)
                                .add(label, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 262, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(89, 89, 89)
                        .add(difficulyLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(moneyTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(32, 32, 32)
                .add(pane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(26, 26, 26)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(button, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(difficulyLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(9, 9, 9)))
                .add(49, 49, 49)
                .add(label, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        if(discard)
        {
            Score score;
            setDiscard(false);
            button.setText("Continue");
            deck.discard();
            for(int i=0; i<5; i++)
            {
                panels[i].setCard(deck.getCard(i));
                panels[i].repaint();
                panels[i].setAllowDiscard(false);
            }
            score=deck.getScore();
            label.setText("Score: " + score.toString() +
                    ", You gained " + score.getValue() + "$");
            money-= tax;
            money+=score.getValue();
            moneyTextField.setText("Money: " + money + "$");
        }
        else
        {
            deck.setAllToKeep();
            deck.mix();
            for(int i=0; i<5; i++)
            {
                panels[i].setCard(deck.getCard(i));
                panels[i].repaint();
                panels[i].setAllowDiscard(true);
            }
            setDiscard(true);
            label.setText("Discard four cards max");
            button.setText("Discard");
        }
    }//GEN-LAST:event_buttonActionPerformed

    private void labelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_labelActionPerformed

    private void moneyTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moneyTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moneyTextFieldActionPerformed

    private void menuItemPressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuItemPressed
        // TODO add your handling code here:
        getStoreDialog().getList().setListData(Store.getInstance().getGames().toArray());
        Component component= evt.getComponent();
        if(component== saveMenuItem)
        {
            System.out.println("Save menu item pressed");
            getStoreDialog().setMode(StoreDialog.Mode.Save);
            getStoreDialog().setVisible(true);
        }
        else if(component== loadMenuItem)
        {
            System.out.println("Load menu item pressed");
            getStoreDialog().setMode(StoreDialog.Mode.Load);
            getStoreDialog().setVisible(true);
        }
    }//GEN-LAST:event_menuItemPressed

    private void windowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowClosing
        // TODO add your handling code here:
        Store.getInstance().synchronize();
    }//GEN-LAST:event_windowClosing
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() 
            {
                new Frame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button;
    private java.awt.Label difficulyLabel;
    private javax.swing.JTextField label;
    private javax.swing.JMenuItem loadMenuItem;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField moneyTextField;
    private javax.swing.JPanel pane;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables

}


