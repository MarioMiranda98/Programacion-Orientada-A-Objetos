import java.awt.event.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;

public class Score extends JPanel implements ActionListener{
    JTextField txtuser;
    JTextArea info;
    JLabel user;
    JButton register;
    JButton gamers;
    Connection conn=null;
    Statement stm=null;
    ResultSet res=null;
    JFrame ventana;
    String name;
    public Score(JFrame frm){
        ventana=frm;
        setLayout(null);
        txtuser=new JTextField();
        user=new JLabel("Usuario");
        register=new JButton("JUGAR");
        gamers=new JButton("VER JUGADORES");
        info=new JTextArea();
        txtuser.setBounds(20,50,100,25);
        user.setBounds(150,50,100,25);
        register.setBounds(20,120,100,50);
        gamers.setBounds(140,120,150,50);
        info.setBounds(10,200,250,500);
        add(txtuser);
        add(user);
        add(register);
        add(gamers);
        add(info);
        info.setVisible(false);
        info.setEditable(false);
        register.addActionListener(this);
        gamers.addActionListener(this);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/flappyscore","root","");
            stm=conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        JButton btn=(JButton)ae.getSource();

        if(btn==register){
            name=txtuser.getText();
            try {
                stm.executeUpdate("insert into users values('"+name+"',0)");
            } catch (SQLException ex) {
                Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                conn.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
            }
            ventana.setVisible(false);
        }

        if(btn==gamers){
            info.setText("");
            info.setVisible(true);
            try {
            res=stm.executeQuery("select * from users order by points DESC");
        } catch (SQLException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(res.next()){
                info.append("Jugador:"+res.getString("user"));
                info.append("                      Points:"+res.getInt("points")+"\n\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
}
