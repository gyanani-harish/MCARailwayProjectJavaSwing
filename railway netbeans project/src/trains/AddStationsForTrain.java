/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trains;

import beans.TrainStationInfoBean;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.HashMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

import utils.DatabaseConnection;

/**
 *
 * @author Harish
 */
public class AddStationsForTrain extends javax.swing.JFrame {

    HashMap<String, String> station_name_and_code;

    public AddStationsForTrain() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAddTrain = new javax.swing.JButton();
        spnTotalStations = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stops", "Station Name", "Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnAddTrain.setText("Add Train");
        btnAddTrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTrainActionPerformed(evt);
            }
        });

        spnTotalStations.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnTotalStationsStateChanged(evt);
            }
        });

        jLabel1.setText("Total Stations");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Insert Stop Details");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(306, 306, 306)
                .addComponent(btnAddTrain, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(spnTotalStations)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spnTotalStations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddTrain, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getStationNamesListFromDB() {
        JComboBox comboBox = new JComboBox();
        String q1 = "select stationname,stationcode from " + DatabaseConnection.TABLE_STATION_CODE_AND_NAME
                + " order by stationname";
        System.out.println(q1);
        try {

            ResultSet tt = DatabaseConnection.executeQuery(q1);
            station_name_and_code = new HashMap();
            ArrayList station_list = new ArrayList();
            while (tt.next()) {
                station_name_and_code.put(tt.getString("stationname"), tt.getString("stationcode"));
                station_list.add(tt.getString("stationname"));
            }
            final DefaultComboBoxModel model1 = new DefaultComboBoxModel(station_list.toArray());
            comboBox.setModel(model1);


            DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
            dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
            comboBox.setRenderer(dlcr);

            jTable1.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        spnTotalStations.setValue(5);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);


        jTable1.getTableHeader().setDefaultRenderer(new HeaderRenderer(jTable1));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        getStationNamesListFromDB();


        jTable1.setRowHeight(25);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rows = model.getRowCount();
        for (int i = 1; i <= 200; i++) {
            model.addRow(new Object[]{"Stop " + i + "", "", ""});
        }


    }//GEN-LAST:event_formComponentShown
    /**
     * 1.validate data in this screen 
     * 2.add to database 
     * 3.validate data in this screen
     */
    private void btnAddTrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTrainActionPerformed

        

        int i;
        for (i = 0; i < Integer.parseInt(spnTotalStations.getValue().toString()); i++) {
            if (jTable1.getModel().getValueAt(i, 1).toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Provide Station Name for Row " + (i + 1));
                break;
            } else if (jTable1.getModel().getValueAt(i, 2).toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Provide Stop Time for Row " + (i + 1));
                break;
            }
        }
        if (i == Integer.parseInt(spnTotalStations.getValue().toString())) {
            System.out.println("Complete Info Available");
            //add data to database
            ArrayList<TrainStationInfoBean> list = new ArrayList<>();
            TableModel model = jTable1.getModel();
            for (i = 0; i < Integer.parseInt(spnTotalStations.getValue().toString()); i++) {
                TrainStationInfoBean obj = new TrainStationInfoBean();
                System.out.println("key= " + model.getValueAt(i, 1).toString() + " value =" + station_name_and_code.get(model.getValueAt(i, 1).toString()));
                obj.setStationCode(station_name_and_code.get(model.getValueAt(i, 1).toString()));
                obj.setStationOrder(i);
                obj.setTime(model.getValueAt(i, 2).toString());
                obj.setTrainNo(AddTrain.trainNo);
                list.add(obj);
            }
            DatabaseConnection.prepareStatementForInsertStations(list);
            //go to back screen
            JOptionPane.showMessageDialog(null, "Train added Successfully");
            new main.EmpOptionsPage().setVisible(true);
            this.setVisible(false);
        } else {
            System.err.println("Complete Info Not Available");
        }

    }//GEN-LAST:event_btnAddTrainActionPerformed

    private void spnTotalStationsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnTotalStationsStateChanged
        if (Integer.parseInt(spnTotalStations.getValue().toString()) <= 1) {
            JOptionPane.showMessageDialog(null, "Please add two or more stations");
            spnTotalStations.setValue(2);
        } else if (Integer.parseInt(spnTotalStations.getValue().toString()) > 200) {
            JOptionPane.showMessageDialog(null, "More than 200 stations are currently not allowed");
            spnTotalStations.setValue(200);
        }
    }//GEN-LAST:event_spnTotalStationsStateChanged

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddStationsForTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddStationsForTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddStationsForTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddStationsForTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
           java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddStationsForTrain().setVisible(true);
            }
        });
    }

    private static class HeaderRenderer implements TableCellRenderer {

        DefaultTableCellRenderer renderer;

        public HeaderRenderer(JTable table) {
            renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int col) {
            return renderer.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, col);
        }
    }

    public class AutoCompleteJComboBoxer extends PlainDocument {

        private final JComboBox comboBox;
        private ComboBoxModel model;
        private JTextComponent editor;
        private boolean hidePopupOnFocusLoss;

        public AutoCompleteJComboBoxer(JComboBox comboBox) {
            this.comboBox = comboBox;
            comboBox.setEditable(true);
            model = comboBox.getModel();
            editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
            editor.setDocument(this);
            // Bug 5100422 on Java 1.5: Editable JComboBox won't hide popup when tabbing out
            hidePopupOnFocusLoss = System.getProperty("java.version").startsWith("1.5");
            // Highlight whole text when focus gets lost
            editor.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    highlightCompletedText(0);
                    // Workaround for Bug 5100422 - Hide Popup on focus loss
                    if (hidePopupOnFocusLoss) {
                        AutoCompleteJComboBoxer.this.comboBox.setPopupVisible(false);
                    }
                }
            });
            // Highlight whole text when user hits enter
            editor.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        highlightCompletedText(0);
                    } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        AutoCompleteJComboBoxer.this.comboBox.setSelectedIndex(0);
                        AutoCompleteJComboBoxer.this.editor.setText(AutoCompleteJComboBoxer.this.comboBox.getSelectedItem().toString());
                        highlightCompletedText(0);
                    }
                }
            });

            // Handle initially selected object
            Object selected = comboBox.getSelectedItem();
            if (selected != null) {
                editor.setText(selected.toString());
            }
        }

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            // construct the resulting string
            String currentText = getText(0, getLength());
            String beforeOffset = currentText.substring(0, offs);
            String afterOffset = currentText.substring(offs, currentText.length());
            String futureText = beforeOffset + str + afterOffset;

            // lookup and select a matching item
            Object item = lookupItem(futureText);
            if (item != null) {
                comboBox.setSelectedItem(item);
            } else {
                // keep old item selected if there is no match
                item = comboBox.getSelectedItem();
                // imitate no insert (later on offs will be incremented by str.length(): selection won't move forward)
                offs = offs - str.length();
                // provide feedback to the user that his input has been received but can not be accepted
                comboBox.getToolkit().beep(); // when available use: UIManager.getLookAndFeel().provideErrorFeedback(comboBox);
            }

            // remove all text and insert the completed string
            super.remove(0, getLength());
            super.insertString(0, item.toString(), a);

            // if the user selects an item via mouse the the whole string will be inserted.
            // highlight the entire text if this happens.
            if (item.toString().equals(str) && offs == 0) {
                highlightCompletedText(0);
            } else {
                highlightCompletedText(offs + str.length());
                // show popup when the user types
                comboBox.setPopupVisible(true);
            }
        }

        private void highlightCompletedText(int start) {
            editor.setCaretPosition(getLength());
            editor.moveCaretPosition(start);
        }

        private Object lookupItem(String pattern) {
            Object selectedItem = model.getSelectedItem();
            // only search for a different item if the currently selected does not match
            if (selectedItem != null && startsWithIgnoreCase(selectedItem.toString(), pattern)) {
                return selectedItem;
            } else {
                // iterate over all items
                for (int i = 0, n = model.getSize(); i < n; i++) {
                    Object currentItem = model.getElementAt(i);
                    // current item starts with the pattern?
                    if (startsWithIgnoreCase(currentItem.toString(), pattern)) {
                        return currentItem;
                    }
                }
            }
            // no item starts with the pattern =&gt; return null
            return null;
        }

        // checks if str1 starts with str2 - ignores case
        private boolean startsWithIgnoreCase(String str1, String str2) {
            return str1.toUpperCase().startsWith(str2.toUpperCase());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddTrain;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JSpinner spnTotalStations;
    // End of variables declaration//GEN-END:variables
}
