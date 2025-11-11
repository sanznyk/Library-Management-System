package ui;
import dao.UserDAO;
import models.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField tfUser;
    private JPasswordField pfPass;
    public LoginFrame() {
        setTitle("Library Login");
        setSize(350,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
    }
    private void init() {
        tfUser = new JTextField(15);
        pfPass = new JPasswordField(15);
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> doLogin());
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.gridx = 0; c.gridy = 0; p.add(new JLabel("Username:"), c);
        c.gridx = 1; p.add(tfUser, c);
        c.gridx = 0; c.gridy = 1; p.add(new JLabel("Password:"), c);
        c.gridx = 1; p.add(pfPass, c);
        c.gridx = 1; c.gridy = 2; p.add(btnLogin, c);
        add(p);
    }
    private void doLogin() {
        String user = tfUser.getText().trim();
        String pass = new String(pfPass.getPassword());
        User u = UserDAO.authenticate(user, pass);
        if (u != null) {
            JOptionPane.showMessageDialog(this, "Welcome, " + u.getUsername());
            dispose();
            if ("admin".equals(u.getRole())) new AdminFrame(u).setVisible(true);
            else new StudentFrame(u).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
