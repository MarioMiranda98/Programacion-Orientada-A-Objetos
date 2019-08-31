import javax.swing.JFrame;

public class FlappyBird extends JFrame{
    public static void main(String[] args) {
        JFrame frm=new JFrame("FLAPPY BIRD");
        Pintado lienzo=new Pintado(frm);
        frm.setLocation(500,100);
        frm.add(lienzo);
        frm.pack();
        frm.setVisible(true);
        frm.setResizable(false);
    }
}