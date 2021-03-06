/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trains;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import utils.DatabaseConnection;

/**
 *
 * @author harish
 */
public class AddTrain extends javax.swing.JFrame {

    public static int trainNo = 0;

    /**
     * Creates new form add
     */
    public AddTrain() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTrainNo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTrainName = new javax.swing.JTextField();
        chkMonday = new javax.swing.JCheckBox();
        chkTuesday = new javax.swing.JCheckBox();
        chkWednesday = new javax.swing.JCheckBox();
        chkThursday = new javax.swing.JCheckBox();
        chkFriday = new javax.swing.JCheckBox();
        chkSaturday = new javax.swing.JCheckBox();
        chkSunday = new javax.swing.JCheckBox();
        chkAllDays = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstClasses = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboType = new javax.swing.JComboBox();
        btnAddTrain = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTrainNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrainNoActionPerformed(evt);
            }
        });
        getContentPane().add(txtTrainNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 77, -1));

        jLabel6.setText("Train No.");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, -1, -1));

        jLabel7.setText("Train Name");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, -1, -1));
        getContentPane().add(txtTrainName, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 320, -1));

        chkMonday.setText("Monday");
        getContentPane().add(chkMonday, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, -1, -1));

        chkTuesday.setText("TuesDay");
        getContentPane().add(chkTuesday, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, -1, -1));

        chkWednesday.setText("WednesDay");
        getContentPane().add(chkWednesday, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, -1, -1));

        chkThursday.setText("Thursday");
        getContentPane().add(chkThursday, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, -1, -1));

        chkFriday.setText("Friday");
        getContentPane().add(chkFriday, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        chkSaturday.setText("Saturday");
        getContentPane().add(chkSaturday, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, -1, -1));

        chkSunday.setText("Sunday");
        getContentPane().add(chkSunday, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, -1, -1));

        chkAllDays.setText("All");
        chkAllDays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAllDaysActionPerformed(evt);
            }
        });
        getContentPane().add(chkAllDays, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, -1, -1));

        jScrollPane1.setViewportView(lstClasses);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 140, 140));

        jLabel8.setText("Classes");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 70, -1, -1));

        jLabel9.setText("Type");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, -1, -1));

        getContentPane().add(comboType, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, 120, -1));

        btnAddTrain.setText("Add Train");
        btnAddTrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTrainActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddTrain, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 400, 200, 50));

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        txtTrainNo.setText("");
        try {
            //train type
            String q = "select Typename from train_type order by typename";
            System.out.println(q);
            ResultSet t = utils.DatabaseConnection.executeQuery(q);
            ArrayList type_list = new ArrayList();
            while (t.next()) {
                type_list.add(t.getString("typename"));
            }
            final DefaultComboBoxModel model = new DefaultComboBoxModel(type_list.toArray());
            comboType.setModel(model);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //class list box
        try {
            //train type

            String q = "select Classname from class order by classname";
            System.out.println(q);
            ResultSet t = utils.DatabaseConnection.executeQuery(q);

            final DefaultListModel model1 = new DefaultListModel();
            lstClasses.setModel(model1);
            while (t.next()) {
                model1.addElement(t.getString("Classname"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentShown

    private void chkAllDaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAllDaysActionPerformed
        if (chkAllDays.isSelected() == true) {
            chkMonday.setSelected(true);
            chkTuesday.setSelected(true);
            chkWednesday.setSelected(true);
            chkThursday.setSelected(true);
            chkFriday.setSelected(true);
            chkSaturday.setSelected(true);
            chkSunday.setSelected(true);

        } else {
            chkMonday.setSelected(false);
            chkTuesday.setSelected(false);
            chkWednesday.setSelected(false);
            chkThursday.setSelected(false);
            chkFriday.setSelected(false);
            chkSaturday.setSelected(false);
            chkSunday.setSelected(false);

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_chkAllDaysActionPerformed
    private boolean isValidatedData() {
        if (txtTrainNo.getText().equals("")) {
            return false;
        }
        if (txtTrainName.getText().equals("")) {
            return false;
        }
        if (!chkAllDays.isSelected() && !chkMonday.isSelected() && !chkTuesday.isSelected() && !chkWednesday.isSelected() && !chkThursday.isSelected()
                && !chkFriday.isSelected() && !chkSaturday.isSelected() && !chkSunday.isSelected()) {
            return false;
        }
        if (lstClasses.getSelectedIndices().length <= 0) {
            return false;
        }
        return true;
    }
    private void btnAddTrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTrainActionPerformed
        // TODO add your handling code here:
        if (isValidatedData()) {
            int train_no = Integer.parseInt(txtTrainNo.getText());

            String train_name = txtTrainName.getText();
            String train_type = String.valueOf(comboType.getSelectedItem());
            String q = "insert into " + DatabaseConnection.TABLE_TRAIN_INFO + "(train_no,train_name,type) "
                    + "values(" + train_no + ",upper('" + train_name + "'),upper('" + train_type + "'))";

            System.out.println(q);
            if (DatabaseConnection.execute(q)) {
                JOptionPane.showMessageDialog(null, " Train not added");
            } else {
                ArrayList list = new ArrayList();
                if (chkAllDays.isSelected()) {
                    list.add("all");
                } else {
                    if (chkMonday.isSelected()) {
                        list.add("Monday");
                    }
                    if (chkTuesday.isSelected()) {
                        list.add("Tuesday");
                    }
                    if (chkWednesday.isSelected()) {
                        list.add("Wednesday");

                    }
                    if (chkThursday.isSelected()) {
                        list.add("Thursday");
                    }
                    if (chkFriday.isSelected()) {
                        list.add("Friday");
                    }
                    if (chkSaturday.isSelected()) {
                        list.add("Saturday");
                    }
                    if (chkSunday.isSelected()) {
                        list.add("Sunday");
                    }
                }


                //listbox
                ArrayList l = (ArrayList) lstClasses.getSelectedValuesList();
                for (int i = 0; i < l.size(); i++) {
                    System.out.println(l.get(i));
                }


                for (int i = 0; i < list.size(); i++) {
                    String q1 = "insert into " + DatabaseConnection.TABLE_TRAIN_AND_DAYS + "(train_no,dayname) values"
                            + "(" + train_no + ",'" + list.get(i) + "')";
                    if (DatabaseConnection.execute(q1)) {
                        System.out.println("dayname query problem");
                    } else {
                        System.out.println("dayname query run" + list.get(i));
                        
                    }
                }
                for (int i = 0; i < l.size(); i++) {
                    String q1 = "insert into "
                            + DatabaseConnection.TABLE_TRAIN_AND_CLASS + "(train_no,classname) values"
                            + "(" + train_no + ",'" + l.get(i) + "')";
                    if (DatabaseConnection.execute(q1)) {
                        System.out.println("class name query problem");
                    } else {
                        System.out.println("class name query run" + l.get(i));
                    }
                }

                trainNo = Integer.parseInt(txtTrainNo.getText());
                new trains.AddStationsForTrain().setVisible(true);
                this.setVisible(false);
            }

        }
    }//GEN-LAST:event_btnAddTrainActionPerformed
/**
 * 
 * @param evt */
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new main.EmpOptionsPage().setVisible(true);
        this.setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    private void txtTrainNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrainNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrainNoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(AddTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddTrain().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddTrain;
    private javax.swing.JButton btnBack;
    private javax.swing.JCheckBox chkAllDays;
    private javax.swing.JCheckBox chkFriday;
    private javax.swing.JCheckBox chkMonday;
    private javax.swing.JCheckBox chkSaturday;
    private javax.swing.JCheckBox chkSunday;
    private javax.swing.JCheckBox chkThursday;
    private javax.swing.JCheckBox chkTuesday;
    private javax.swing.JCheckBox chkWednesday;
    private javax.swing.JComboBox comboType;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstClasses;
    private javax.swing.JTextField txtTrainName;
    private javax.swing.JTextField txtTrainNo;
    // End of variables declaration//GEN-END:variables
}
