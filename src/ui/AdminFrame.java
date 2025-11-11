package ui;
import models.User;
import models.Book;
import dao.BookDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AdminFrame extends JFrame {
    private User user;
    private DefaultTableModel model;
    public AdminFrame(User user) {
        this.user = user;
        setTitle("Admin - Library");
        setSize(700,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
        loadBooks();
    }
    private void init() {
        model = new DefaultTableModel(new Object[]{"ID","Title","Author","Qty"},0);
        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        JButton btnAdd = new JButton("Add Book");
        JButton btnDelete = new JButton("Delete Book");
        btnAdd.addActionListener(e -> addBook());
        btnDelete.addActionListener(e -> {
            int r = table.getSelectedRow();
            if (r==-1) return;
            int id = (int) model.getValueAt(r,0);
            if (BookDAO.delete(id)) {
                model.removeRow(r);
                JOptionPane.showMessageDialog(this,"Deleted");
            } else JOptionPane.showMessageDialog(this,"Delete failed");
        });
        JPanel top = new JPanel();
        top.add(btnAdd); top.add(btnDelete);
        add(top, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
    }
    private void loadBooks() {
        model.setRowCount(0);
        List<Book> books = BookDAO.getAll();
        for (Book b: books) model.addRow(new Object[]{b.getId(), b.getTitle(), b.getAuthor(), b.getQuantity()});
    }
    private void addBook() {
        JTextField tTitle = new JTextField();
        JTextField tAuthor = new JTextField();
        JTextField tQty = new JTextField();
        Object[] msg = {"Title:", tTitle, "Author:", tAuthor, "Quantity:", tQty};
        int res = JOptionPane.showConfirmDialog(this, msg, "Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (res==JOptionPane.OK_OPTION) {
            try {
                String title = tTitle.getText().trim();
                String author = tAuthor.getText().trim();
                int qty = Integer.parseInt(tQty.getText().trim());
                if (BookDAO.add(new Book(title, author, qty))) {
                    loadBooks();
                    JOptionPane.showMessageDialog(this,"Book added");
                } else JOptionPane.showMessageDialog(this,"Add failed");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,"Invalid quantity");
            }
        }
    }
}
