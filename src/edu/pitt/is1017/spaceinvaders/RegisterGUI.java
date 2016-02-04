package edu.pitt.is1017.spaceinvaders;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class RegisterGUI extends JPanel {

    /**
     * Registration screen for SpaceInvaders
     * 
     * @author Tanika Velingker
     * @version 1.0
     **/

    private static final long serialVersionUID = 1L;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private User user = null;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JPasswordField txtConfirmPassword;

    /**
     * Create the panel for Registration screen.
     */
    public RegisterGUI() {
        
        setLayout(null);
        

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEmail.setBounds(50, 150, 36, 17);
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.add(lblEmail);
        
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtEmail.setBounds(199, 150, 200, 23);
        add(txtEmail);
        txtEmail.setColumns(6);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 200, 63, 17);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblPassword);
        
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtPassword.setBounds(199, 200, 200, 23);
        add(txtPassword);
        
        JButton btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRegister.setBounds(96, 310, 102, 25);
        add(btnRegister);

        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnCancel.setBounds(256, 310, 90, 25);
        add(btnCancel);
        
        JLabel lbFirstName = new JLabel("First Name:");
        lbFirstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbFirstName.setBounds(50, 60, 102, 20);
        add(lbFirstName);
        
        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblLastName.setBounds(50, 105, 102, 20);
        add(lblLastName);
        
        txtFirstName = new JTextField();
        txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtFirstName.setColumns(6);
        txtFirstName.setBounds(199, 60, 200, 23);
        add(txtFirstName);
        
        txtLastName = new JTextField();
        txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtLastName.setColumns(6);
        txtLastName.setBounds(199, 105, 200, 23);
        add(txtLastName);
        
        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblConfirmPassword.setBounds(50, 250, 131, 20);
        add(lblConfirmPassword);
        
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtConfirmPassword.setBounds(199, 250, 200, 23);
        add(txtConfirmPassword);
        
        JLabel lblSpaceInvadersLogin = new JLabel("Space Invaders Registration");
        lblSpaceInvadersLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblSpaceInvadersLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblSpaceInvadersLogin.setBounds(76, 11, 306, 27);
        add(lblSpaceInvadersLogin);
        
        setVisible(true);
        revalidate();
        repaint();
        
        Border border = getBorder();
        Border margin = new EmptyBorder(20,20,20,20);
        setBorder(new CompoundBorder(border, margin));

        // Action listener for Cancel button
        
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Action listener for Register button
        
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                char passwordChar[] = txtPassword.getPassword();
                String passwordText = new String(passwordChar);
                char confirmPasswordChar[] = txtConfirmPassword.getPassword();
                String confirmPasswordText = new String(confirmPasswordChar);
                if (txtEmail.getText().equals("")
                        || txtLastName.getText().equals("")
                        || txtFirstName.getText().equals("")
                        || passwordText.equals("")
                        || confirmPasswordText.equals("")) {
                    JOptionPane
                            .showMessageDialog(null,
                                    "First Name, Last Name, Email, and Passwords are required.");
                } else {
                    // compare passwords
                    if (!passwordText.equals(confirmPasswordText)) {
                        JOptionPane.showMessageDialog(null, passwordText
                                + confirmPasswordText
                                + "Passwords do not match. Please try again.");
                    } else {
                        /*System.out.println(txtLastName.getText()
                                + txtFirstName.getText() + txtEmail.getText()
                                + passwordText); */
                        user = new User(txtLastName.getText(), txtFirstName
                                .getText(), txtEmail.getText(), passwordText);
                        /*System.out.println(user.getUserID()
                                + user.getFirstName() + user.getLastName()
                                + user.getEmail() + passwordText); */
                        if (user.getUserID() > 0)
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Hello " + user.getFirstName() + " "
                                            + user.getLastName()
                                            + "! Welcome to Space Invaders!");
                        else
                            JOptionPane
                            .showMessageDialog(null,
                                    "Error registering your email. Please contact Technical Support.");                            
                        System.exit(0);

                    }
                }
            }
        });
    }
}
