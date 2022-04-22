package edu.hitsz;

import edu.hitsz.dao.MyRecord;
import edu.hitsz.dao.RecordDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class End {
    public JPanel getEndPanel() {
        return endPanel;
    }

    public void setEndPanel(JPanel endPanel) {
        this.endPanel = endPanel;
    }

    private JPanel endPanel;
    private JTextArea titleTextArea;
    private JTable recordTable;
    private JScrollPane recordScrollPane;
    private JButton deleteButton;

    public End(RecordDaoImpl recordDao) {
        String[] columnName = {"Rank","Name","Score","Date"};
        String[][] tableData = recordDao.listToTable();
        DefaultTableModel model = new DefaultTableModel(tableData,columnName){
            @Override
            public boolean isCellEditable(int row,int col){
                return false;
            }
        };
        recordTable.setModel(model);
        recordScrollPane.setViewportView(recordTable);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = recordTable.getSelectedRow();
                if(row != -1){
                    model.removeRow(row);
                    recordDao.deleteRecord(row-1);
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        endPanel = new JPanel();
        titleTextArea = new JTextArea();
        recordTable = new JTable();
        recordScrollPane = new JScrollPane();
        deleteButton = new JButton();

    }
}
