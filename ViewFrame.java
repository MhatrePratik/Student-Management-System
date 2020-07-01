import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.List;
import java.util.*;

class ViewFrame extends JFrame {

    Container c;
    TextArea txtView;
    JButton btnBack;

    ViewFrame() {

        c = getContentPane();
        c.setLayout(new FlowLayout());
        c.setBackground(Color.yellow);

        txtView = new TextArea(12, 70);
        btnBack = new JButton("Back");

        c.add(txtView);
        c.add(btnBack);

        Font font = new Font("Monospaced", Font.BOLD, 15);
        txtView.setFont(font);
        txtView.setForeground(Color.BLACK);

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sfact = cfg.buildSessionFactory();

        Session session = null;
        Transaction t = null;

        int roll = 0;
        String name = "";
        int marks1 = 0;
        int marks2 = 0;
        int marks3 = 0;
        int count = 1;

        try {

            session = sfact.openSession();
            System.out.println("HbOpViewopen ");
            List<Student> stu = new ArrayList<>();
            stu = session.createQuery("from Student order by rno ASC ").list();

            for (Student s : stu) {
                roll = s.getRno();
                name = s.getName();
                marks1 = s.getm1();
                marks2 = s.getm2();
                marks3 = s.getm3();

                String str = "(" + count++ + ") " + "Roll No: " + roll + "; Name: " + name + ";" +
                        "\n    Subject 1: " + marks1 + "; Subject 2: " + marks2 + "; Subject 3: " + marks3 + "\n" + "\n";
                System.out.println(str);
                txtView.append(str);
            }

        } catch (Exception e) {
            System.out.println("Issue " + e);

        } finally {
            session.close();
            System.out.println("HbOpViewclose");

        }

        ActionListener e1 = (ae) -> {
            MainFrame af = new MainFrame();
            dispose();
        };
        btnBack.addActionListener(e1);


        setTitle("View Student Data");
        setSize(720, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        txtView.setEditable(false);
    }
}