/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.multicastproject.form;

import com.mycompany.multicastproject.entity.Group;
import com.mycompany.multicastproject.entity.Message;
import com.mycompany.multicastproject.entity.User;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;
import javax.swing.DefaultListModel;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.event.DocumentListener;

/**
 *
 * @author acer
 */
public class Multicast extends javax.swing.JFrame {
    private static final DefaultListModel<String> listModelMessage = new DefaultListModel<>();
    private static final DefaultListModel<String> listModelUser = new DefaultListModel<>();
    private static final DefaultListModel<Group> listModelGroup = new DefaultListModel<>();
 
//    private List<Group> groups;
    
    
    
    public static DefaultListModel<String> getAllUser(){
        return listModelUser;
    }
    
    public void setbuttonSend(){
        buttonSend.setVisible(true);
    }
    
    public Multicast() {
        initComponents();
        setupButton();
        lblGroupNotify.setText(null);
        lblSendNotify.setText(null);
        lblUserNotify.setText(null);
    }

    private void setupButton(){
        javax.swing.event.DocumentListener documentListener = new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                checkButtonEnable();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                checkButtonEnable();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                checkButtonEnable();
            }
        };
        inputIp.getDocument().addDocumentListener(documentListener);
        inputPort.getDocument().addDocumentListener(documentListener);
    }
    
        private void checkButtonEnable() {
        // Kiểm tra nếu cả hai trường nhập đều không rỗng
            boolean isInputAValid = !inputIp.getText().isBlank();
            boolean isInputBValid = !inputPort.getText().isBlank();

            // Bật nút buttonA nếu cả hai trường đều có văn bản
            buttonJoin.setEnabled(isInputAValid && isInputBValid);
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
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listGroup = new javax.swing.JList<>(listModelGroup);
        jLabel3 = new javax.swing.JLabel();
        inputIp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        inputPort = new javax.swing.JTextField();
        buttonLeave = new javax.swing.JButton();
        buttonJoin = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        lblGroupNotify = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listUser = new javax.swing.JList<>(listModelUser);
        btnSend = new javax.swing.JButton();
        lblUserNotify = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listMessage = new javax.swing.JList<>(listModelMessage);
        jLabel6 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        nameGroup = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        inputSend = new javax.swing.JTextPane();
        buttonSend = new javax.swing.JButton();
        lblSendNotify = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1055, 409));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Groups");

        listGroup.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                listGroupFocusLost(evt);
            }
        });
        listGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listGroupMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listGroupMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(listGroup);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Input IP:");

        inputIp.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        inputIp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputIpMouseClicked(evt);
            }
        });
        inputIp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputIpActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Input Port:");

        inputPort.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        inputPort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputPortMouseClicked(evt);
            }
        });
        inputPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputPortActionPerformed(evt);
            }
        });

        buttonLeave.setBackground(new java.awt.Color(255, 51, 51));
        buttonLeave.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 16)); // NOI18N
        buttonLeave.setForeground(new java.awt.Color(255, 255, 255));
        buttonLeave.setText("Leave");
        buttonLeave.setEnabled(false);
        buttonLeave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonLeaveMouseClicked(evt);
            }
        });

        buttonJoin.setBackground(new java.awt.Color(51, 255, 51));
        buttonJoin.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 18)); // NOI18N
        buttonJoin.setForeground(new java.awt.Color(255, 255, 255));
        buttonJoin.setText("Join");
        buttonJoin.setToolTipText("");
        buttonJoin.setEnabled(false);
        buttonJoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonJoinActionPerformed(evt);
            }
        });

        btnCreate.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        btnCreate.setText("Create Group");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        lblGroupNotify.setForeground(new java.awt.Color(255, 51, 51));
        lblGroupNotify.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGroupNotify.setText("Group error!");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGroupNotify, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputIp)
                            .addComponent(inputPort)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnCreate)
                        .addGap(32, 32, 32)
                        .addComponent(buttonJoin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonLeave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonLeave, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonJoin, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblGroupNotify)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Users");
        jLabel5.setToolTipText("");

        listUser.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        listUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listUserMouseClicked(evt);
            }
        });
        listUser.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listUserValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(listUser);

        btnSend.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 16)); // NOI18N
        btnSend.setText("Send");
        btnSend.setMaximumSize(new java.awt.Dimension(74, 27));
        btnSend.setMinimumSize(new java.awt.Dimension(74, 27));
        btnSend.setPreferredSize(new java.awt.Dimension(74, 27));
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        lblUserNotify.setForeground(new java.awt.Color(255, 51, 51));
        lblUserNotify.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserNotify.setText("User error!");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                    .addComponent(lblUserNotify, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblUserNotify)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        listMessage.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(listMessage);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("User Name");

        name.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        name.setEnabled(false);
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Group Name");

        nameGroup.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        nameGroup.setEnabled(false);
        nameGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameGroupActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 16)); // NOI18N
        jLabel1.setText("Message:");

        inputSend.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        inputSend.setEnabled(false);
        inputSend.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputSendKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(inputSend);

        buttonSend.setBackground(new java.awt.Color(0, 153, 255));
        buttonSend.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 16)); // NOI18N
        buttonSend.setForeground(new java.awt.Color(255, 255, 255));
        buttonSend.setText("Send");
        buttonSend.setEnabled(false);
        buttonSend.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSendActionPerformed(evt);
            }
        });

        lblSendNotify.setForeground(new java.awt.Color(255, 51, 51));
        lblSendNotify.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSendNotify.setText("Send chat error!");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSendNotify, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonSend, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(name)
                                .addGap(124, 124, 124))
                            .addComponent(nameGroup)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(nameGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(buttonSend, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblSendNotify)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputIpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputIpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputIpActionPerformed

    private void inputPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputPortActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void nameGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameGroupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameGroupActionPerformed

    private void buttonJoinActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_buttonJoinActionPerformed
        listGroup.setEnabled(true);
        Group tmpGroup = listGroup.getSelectedValue();
        if( inputPort.getText().isBlank() || inputIp.getText().isBlank() ){
            lblGroupNotify.setText("IP and Port can't empty");
            // Tạo một Timer để đặt lại memo sau 3 giây
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    lblGroupNotify.setText(null); // Xóa thông báo sau 3 giây
                }
            }, 3000); // 3000 milliseconds = 3 seconds
            return;
        }
        System.out.println("Value"+!inputSend.isEnabled());
        try {
            Login.client.joinGroup(InetAddress.getByName(inputIp.getText()),  Integer.parseInt(inputPort.getText()), tmpGroup == null ? inputIp.getText() : tmpGroup.getNameGroup());
            inputSend.setEnabled(true);
            buttonSend.setEnabled(true);
            System.out.println("Value"+!inputSend.isEnabled());
            buttonJoin.setEnabled(false);
            buttonLeave.setEnabled(true);
            nameGroup.setText( tmpGroup == null ? inputIp.getText() : tmpGroup.getNameGroup());
        } catch (UnknownHostException e) {
        lblGroupNotify.setText("Invalid IP address"); // Hiển thị lỗi khi địa chỉ IP không hợp lệ
        }
    }//GEN-LAST:event_buttonJoinActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        CreateNewGroup newGroup = new CreateNewGroup(this, true);
        newGroup.setLocationRelativeTo(null);
        newGroup.setVisible(true);
        
        Group returnedGroup = newGroup.getGroup();
        listModelGroup.addElement(returnedGroup);
        Login.client.sendCreateGroup(returnedGroup);
    }//GEN-LAST:event_btnCreateActionPerformed

    private void listUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listUserMouseClicked
        System.out.println(listUser.getSelectedValue());
    }//GEN-LAST:event_listUserMouseClicked

    private void listUserValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listUserValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_listUserValueChanged

    private void listGroupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listGroupMouseClicked
        Group tmpGroup = listGroup.getSelectedValue();
        if(tmpGroup!=null){
            inputIp.setText(tmpGroup.getIP().getHostAddress());
            inputPort.setText(String.valueOf(tmpGroup.getPort()));
            inputIp.setEnabled(false);
            inputPort.setEnabled(false);
            buttonJoin.setEnabled(true);
        }
        else{
            inputIp.setEnabled(true);
            inputPort.setEnabled(true);
            buttonJoin.setEnabled(false);
        }

    }//GEN-LAST:event_listGroupMouseClicked

    private void inputSendKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputSendKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            evt.consume();
            if(!inputSend.getText().isEmpty()){
                buttonSendActionPerformed(null); 
                lblSendNotify.setText("Please, enter text!");
                
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        lblSendNotify.setText(null); // Xóa thông báo sau 3 giây
                    }
                }, 3000); // 3000 milliseconds = 3 seconds
            }
        }
    }//GEN-LAST:event_inputSendKeyPressed

    private void buttonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSendActionPerformed
        String tmpMessage = this.inputSend.getText();
        if(tmpMessage.isEmpty()){
                lblSendNotify.setText("Please, enter text!");
                Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    lblSendNotify.setText(null); // Xóa thông báo sau 3 giây
                }
            }, 3000); // 3000 milliseconds = 3 seconds
            return;
        }
        Login.client.sendMessage(inputSend.getText());
        inputSend.setText(null);
            //           lblSendNotify.setVisible(false)
        
    }//GEN-LAST:event_buttonSendActionPerformed

    private void buttonLeaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLeaveMouseClicked
        Login.client.leaveGroup();
        listGroup.setEnabled(false);
        inputSend.setEnabled(false);
        buttonSend.setEnabled(false);
        buttonJoin.setEnabled(true);
        nameGroup.setText("");
        buttonLeave.setEnabled(false);
        listModelMessage.removeAllElements();
    }//GEN-LAST:event_buttonLeaveMouseClicked

    private void listGroupMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listGroupMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_listGroupMousePressed

    private void listGroupFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_listGroupFocusLost
        inputIp.setEnabled(true);
        inputPort.setEnabled(true);
//        checkButtonEnable();
    }//GEN-LAST:event_listGroupFocusLost

    private void inputIpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputIpMouseClicked
        if(!inputIp.getText().isBlank()&&!inputPort.getText().isBlank()&&!buttonJoin.isEnabled()){
            buttonJoin.setEnabled(true);
        }
    }//GEN-LAST:event_inputIpMouseClicked

    private void inputPortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputPortMouseClicked
               if(!inputIp.getText().isBlank()&&!inputPort.getText().isBlank()&&!buttonJoin.isEnabled()){
            buttonJoin.setEnabled(true);
        }
    }//GEN-LAST:event_inputPortMouseClicked

    public static void addMessage (Message message){
        listModelMessage.addElement(message.toString());
    }
    public static void addMessageIntoGroup ( String message  ){
        listModelMessage.addElement(message);
    }
    public void setName(String name){
        this.name.setText(name);
    }
    public void setEnableButtonJoin( boolean enable){
        this.buttonJoin.setEnabled(enable);
    }
    public void setEnableButtonLeave(boolean enable){
        this.buttonLeave.setEnabled(enable);
    }
    public void setEnableButtonSend(boolean enable){
        this.buttonSend.setEnabled(enable);
    }
    public static void addUserModel(String name){
        listModelUser.addElement(name);
    }
    public static void reset(Set<User> userSet){
        userSet.forEach(user -> {
            if( !listModelUser.contains(user.getUsername())){
                addUserModel(user.getUsername());
            }
        });
    }

    public static void resetGroup(Set<Group> groupSet){
        listModelGroup.removeAllElements();
        groupSet.forEach(group -> {
            listModelGroup.addElement(group);
        });
    }
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
            java.util.logging.Logger.getLogger(Multicast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Multicast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Multicast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Multicast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               //pass
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton buttonJoin;
    private javax.swing.JButton buttonLeave;
    private javax.swing.JButton buttonSend;
    private javax.swing.JTextField inputIp;
    private javax.swing.JTextField inputPort;
    private javax.swing.JTextPane inputSend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblGroupNotify;
    private javax.swing.JLabel lblSendNotify;
    private javax.swing.JLabel lblUserNotify;
    private javax.swing.JList<Group> listGroup;
    private javax.swing.JList<String> listMessage;
    private javax.swing.JList<String> listUser;
    private javax.swing.JTextField name;
    private javax.swing.JTextField nameGroup;
    // End of variables declaration//GEN-END:variables
}
