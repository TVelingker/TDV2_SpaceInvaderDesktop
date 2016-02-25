package edu.pitt.is1017.spaceinvader;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JPasswordField;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class LoginGUI extends JFrame {

    /**
     * Provides Graphical User Interface for Login to Space Invaders 
     * 
     * @author Tanika Velingker
     * @version 1.0
     */
    
    private static final long serialVersionUID = 1L;
    private JPanel loginPanel;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private User user=null;
    static JFrame frame = null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI();
            }
        });
    }

    /**
     * Create the frame and panel for Login screen
     */
    public LoginGUI() {
        

        frame = new JFrame("Space Invaders");
        frame.setBounds(0,0, 500, 390);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (frame.getWidth() / 2), 
                                      middle.y - (frame.getHeight() / 2));
        frame.setLocation(newLocation);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 462, 390);

        
        loginPanel = new JPanel();
        loginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        loginPanel.setLayout(null);
        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEmail.setBounds(74, 99, 58, 19);
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        loginPanel.add(lblEmail);
        
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtEmail.setBounds(174, 98, 224, 20);
        loginPanel.add(txtEmail);
        txtEmail.setColumns(6);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(96, 152, 78, 18);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        loginPanel.add(lblPassword);
        
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtPassword.setBounds(174, 153, 224, 20);
        loginPanel.add(txtPassword);
        
        JButton btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRegister.setBounds(58, 233, 100, 23);
        loginPanel.add(btnRegister);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnLogin.setBounds(180, 233, 100, 23);
        frame.getRootPane().setDefaultButton(btnLogin);
        btnLogin.requestFocus();
        loginPanel.add(btnLogin);

        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnCancel.setBounds(300, 233, 100, 23);
        loginPanel.add(btnCancel);
        
        JLabel lblSpaceInvadersLogin = new JLabel("Space Invaders Login");
        lblSpaceInvadersLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblSpaceInvadersLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblSpaceInvadersLogin.setBounds(92, 24, 306, 27);
        loginPanel.add(lblSpaceInvadersLogin);
        revalidate();
        repaint();
  
    // Action Listener for Login button        
    
    btnLogin.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            char passwordChar[] = txtPassword.getPassword();
            String passwordText = new String(passwordChar);

            if (txtEmail.getText().equals("") || passwordText.equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Please enter Email and Password");
            } else {
                user = new User(txtEmail.getText(), passwordText);
                if (user.getUserID() > 0) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Welcome back!  " + user.getFirstName() + " "
                                    + user.getLastName() + "!");

                    frame.setVisible(false);
                    Thread gameThread = new Thread() {
                        public void run() {
                            Game game = new Game();
                            game.setUser(user);
                            game.gameLoop();
                        }
                      };
                      gameThread.start();
                    
                }
                else
                    JOptionPane.showMessageDialog(null,
                            "Invalid Login Credentials. Please try again.");
            }
        }
    });

    // Action Listener for Cancel button        
    
    btnCancel.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });
   
    // Action Listener for Register button

    btnRegister.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            frame.remove(loginPanel);
            RegisterGUI regPanel = new RegisterGUI();
            frame.getContentPane().add(regPanel);
            frame.revalidate();
            frame.repaint();

        }
    });

    
    loginPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), ""));

    frame.getContentPane().add(loginPanel);
    frame.setVisible(true);


}
}
