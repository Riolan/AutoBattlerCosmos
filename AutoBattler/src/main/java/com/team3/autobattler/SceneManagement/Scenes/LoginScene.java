/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.team3.autobattler.SceneManagement.Scenes;

import com.team3.autobattler.AutoBattler;

/**
 *
 * @author riola
 */
public class LoginScene extends javax.swing.JPanel {

    /**
     * Creates new form LoginScene
     */
    public LoginScene() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        loginButton = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        jSeparator1 = new javax.swing.JSeparator();

        setLayout(new java.awt.GridLayout(3, 3));

        jLabel1.setText("Login Scene");
        jPanel1.add(jLabel1);

        add(jPanel1);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        usernameField.setText("Username");
        jPanel2.add(usernameField);

        passwordField.setText("jPasswordField1");
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });
        jPanel2.add(passwordField);

        add(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        jPanel3.add(loginButton, java.awt.BorderLayout.CENTER);
        jPanel3.add(filler2, java.awt.BorderLayout.LINE_END);
        jPanel3.add(filler3, java.awt.BorderLayout.LINE_START);
        jPanel3.add(jSeparator1, java.awt.BorderLayout.PAGE_START);

        add(jPanel3);
    }// </editor-fold>//GEN-END:initComponents

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    /**
     * Used to start initial connection to Server.
     * @param ip
     * @param port 
     */
    private void connect(String ip, int port) {
        

    }
    
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_loginButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton loginButton;
    protected javax.swing.JPasswordField passwordField;
    protected javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}