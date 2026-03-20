import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {

    JTextField txtTitle, txtCategory, txtDate, txtContent;
    JButton btnAdd, btnUpdate, btnDelete, btnClear, btnPreview;
    JTable table;
    DefaultTableModel model;

    public Main() {
        SwingUtilities.invokeLater(() -> {
            setTitle("Personal Note Maker");
            setSize(800, 550);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            JPanel formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(4, 2, 10, 10));
            formPanel.setBorder(BorderFactory.createTitledBorder("Create Your Note"));

            formPanel.add(new JLabel("Note Title:"));
            txtTitle = new JTextField();
            formPanel.add(txtTitle);

            formPanel.add(new JLabel("Category:"));
            txtCategory = new JTextField();
            formPanel.add(txtCategory);

            formPanel.add(new JLabel("Date:"));
            txtDate = new JTextField();
            formPanel.add(txtDate);

            formPanel.add(new JLabel("Content:"));
            txtContent = new JTextField();
            formPanel.add(txtContent);

            add(formPanel, BorderLayout.NORTH);

            model = new DefaultTableModel();
            model.addColumn("Title");
            model.addColumn("Category");
            model.addColumn("Date");
            model.addColumn("Content");

            table = new JTable(model);
            table.setFillsViewportHeight(true);

            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());

            btnAdd = new JButton("Save Note");
            btnUpdate = new JButton("Edit Selected");
            btnDelete = new JButton("Remove Note");
            btnClear = new JButton("Clear Form");
            btnPreview = new JButton("Quick Preview");

            buttonPanel.add(btnAdd);
            buttonPanel.add(btnUpdate);
            buttonPanel.add(btnDelete);
            buttonPanel.add(btnClear);
            buttonPanel.add(btnPreview);  
            add(buttonPanel, BorderLayout.SOUTH);

            btnAdd.addActionListener(this);
            btnUpdate.addActionListener(this);
            btnDelete.addActionListener(this);
            btnClear.addActionListener(this);
            btnPreview.addActionListener(this);  

            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        txtTitle.setText(model.getValueAt(selectedRow, 0).toString());
                        txtCategory.setText(model.getValueAt(selectedRow, 1).toString());
                        txtDate.setText(model.getValueAt(selectedRow, 2).toString());
                        txtContent.setText(model.getValueAt(selectedRow, 3).toString());
                    }
                }
            });

            setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String title = txtTitle.getText();
        String category = txtCategory.getText();
        String date = txtDate.getText();
        String content = txtContent.getText();

        if (title.isEmpty() || content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Wait! Give your note a title and some content first.");
            return;
        }

        if (e.getSource() == btnAdd) {
            model.addRow(new Object[]{title, category, date, content});
            JOptionPane.showMessageDialog(this, "Note saved to your list!");
            clearFields();
        }

        if (e.getSource() == btnUpdate) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                model.setValueAt(title, selectedRow, 0);
                model.setValueAt(category, selectedRow, 1);
                model.setValueAt(date, selectedRow, 2);
                model.setValueAt(content, selectedRow, 3);
                JOptionPane.showMessageDialog(this, "Changes saved successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Pick a note from the table below to edit.");
            }
        }

        if (e.getSource() == btnDelete) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Note deleted.");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Please select which note you want to remove.");
            }
        }

        if (e.getSource() == btnClear) {
            clearFields();
        }

        if (e.getSource() == btnPreview) {  
            JOptionPane.showMessageDialog(this, "📝 Note Preview:\n\n" +
                    "Title: " + title + "\n" +
                    "Category: " + category + "\n" +
                    "Date: " + date + "\n" +
                    "Content: " + content);
        }
    }

    private void clearFields() {
        txtTitle.setText("");
        txtCategory.setText("");
        txtDate.setText("");
        txtContent.setText("");
        table.clearSelection();
    }

    public static void main(String[] args) {
        new Main();
    }
}
