package ui;
import models.User;
import models.Book;
import dao.BookDAO;
import dao.BorrowDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentFrame extends JFrame {
    private User user;
    private DefaultTableModel model;
    public StudentFrame(User user) {
        this.user = user;
        setTitle("Student - Library");
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
        JButton btnBorrow = new JButton("Borrow Selected");
        btnBorrow.addActionListener(e -> {
            int r = table.getSelectedRow();
            if (r==-1) return;
            int id = (int) model.getValueAt(r,0);
            if (BorrowDAO.borrow(user.getId(), id)) {
                JOptionPane.showMessageDialog(this,"Borrowed successfully");
                loadBooks();
            } else JOptionPane.showMessageDialog(this,"Cannot borrow (no stock)");
        });
        JPanel top = new JPanel();
        top.add(btnBorrow);
        add(top, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
    }
    private void loadBooks() {
        model.setRowCount(0);
        List<Book> books = BookDAO.getAll();
        for (Book b: books) model.addRow(new Object[]{b.getId(), b.getTitle(), b.getAuthor(), b.getQuantity()});
    }
}
