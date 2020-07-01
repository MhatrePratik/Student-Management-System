import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import org.hibernate.*;
import org.hibernate.cfg.*;

class DeleteFrame extends JFrame {

    Container c;
    JLabel lblRno;
    JTextField txtRno;
    JButton btnDelete, btnBack;


    DeleteFrame() {

        c = getContentPane();
        c.setLayout(new FlowLayout());
        c.setBackground(Color.yellow);

        lblRno = new JLabel("Enter Rno: ");
        lblRno.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 12));
        txtRno = new JTextField(15);
        btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 12));
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 12));

        c.add(lblRno);
        c.add(txtRno);
        c.add(btnDelete);
        c.add(btnBack);

        ActionListener e1 = (ae) -> {

            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            SessionFactory sfact = cfg.buildSessionFactory();


            Session session = null;
            Transaction t = null;

            try {
                session = sfact.openSession();
                System.out.println("open DeleteFrame ");
                t = session.beginTransaction();

                int rno = Integer.parseInt(txtRno.getText());
                Student s = (Student) session.get(Student.class, rno);
                if (s != null) {
                    session.delete(s);
                    t.commit();
                    System.out.println("record deleted");
                    JOptionPane.showMessageDialog(c, "Student Data Deleted");
                    txtRno.setText("");
                } else {
                    System.out.println(rno + " does not exists ");
                    JOptionPane.showMessageDialog(c, "Roll No doesn't exists");
                }

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(c, "Enter Valid value");
            } catch (Exception e) {
                assert t != null;
                t.rollback();
                System.out.println("Issue " + e);

            } finally {
                assert session != null;
                session.close();
                System.out.println("close");
            }
        };
        btnDelete.addActionListener(e1);

        ActionListener e2 = (ae) -> {
            MainFrame af = new MainFrame();
            dispose();
        };
        btnBack.addActionListener(e2);


        setTitle("Delete Student Details");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

    }

}