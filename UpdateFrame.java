import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import org.hibernate.*;
import org.hibernate.cfg.*;

class UpdateFrame extends JFrame {

    Container c;
    JLabel lblUpdateRno, lblUpdateName, lblUpdateSub1, lblUpdateSub2, lblUpdateSub3;
    JTextField txtUpdateRno, txtUpdateName, txtUpdateSub1, txtUpdateSub2, txtUpdateSub3;
    JButton btnUpdate, btnBack;

    UpdateFrame() {
        c = getContentPane();
        c.setLayout(new FlowLayout());
        c.setBackground(Color.yellow);

        lblUpdateRno = new JLabel("Enter Rno:");
        lblUpdateRno.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 12));
        lblUpdateName = new JLabel("Enter Name:");
        lblUpdateName.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 12));
        lblUpdateSub1 = new JLabel("Enter Sub marks 1:");
        lblUpdateSub1.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 12));
        lblUpdateSub2 = new JLabel("Enter Sub marks 2:");
        lblUpdateSub2.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 12));
        lblUpdateSub3 = new JLabel("Enter Sub marks 3:");
        lblUpdateSub3.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 12));

        txtUpdateRno = new JTextField(15);
        txtUpdateName = new JTextField(15);
        txtUpdateSub1 = new JTextField(15);
        txtUpdateSub2 = new JTextField(15);
        txtUpdateSub3 = new JTextField(15);

        btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 12));
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 12));

        c.add(lblUpdateRno);
        c.add(txtUpdateRno);
        c.add(lblUpdateName);
        c.add(txtUpdateName);
        c.add(lblUpdateSub1);
        c.add(txtUpdateSub1);
        c.add(lblUpdateSub2);
        c.add(txtUpdateSub2);
        c.add(lblUpdateSub3);
        c.add(txtUpdateSub3);
        c.add(btnUpdate);
        c.add(btnBack);


        ActionListener e1 = (ae) -> {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            SessionFactory sfact = cfg.buildSessionFactory();


            Session session = null;
            Transaction t = null;


            try {
                session = sfact.openSession();
                System.out.println("Open Update Frame ");
                t = session.beginTransaction();

                int rn = Integer.parseInt(txtUpdateRno.getText());

                Student s = (Student) session.get(Student.class, rn);

                if (s != null) {

                    String name = txtUpdateName.getText().replaceAll("[^a-zA-Z0-9\\s]", "").toUpperCase();
                    int m1 = Integer.parseInt(txtUpdateSub1.getText().trim());
                    int m2 = Integer.parseInt(txtUpdateSub2.getText().trim());
                    int m3 = Integer.parseInt(txtUpdateSub3.getText().trim());

                    if (name.length() >= 2 && name.length() <= 27) {
                        if (m1 <= 100 && m1 >= 0 && m2 <= 100 && m2 >= 0 && m3 <= 100 && m3 >= 0) {
                            s.setName(name);
                            s.setm1(m1);
                            s.setm2(m2);
                            s.setm3(m3);

                            session.update(s);
                            t.commit();
                            System.out.println("record updated ");
                            JOptionPane.showMessageDialog(c, "Student Data Updated");
                            txtUpdateName.setText("");
                            txtUpdateRno.setText("");
                            txtUpdateSub1.setText("");
                            txtUpdateSub2.setText("");
                            txtUpdateSub3.setText("");
                        } else {
                            JOptionPane.showMessageDialog(c, "Enter marks in the range 0 to 100");
                        }
                    } else {
                        JOptionPane.showMessageDialog(c, "Entered name in not valid");
                    }
                } else {
                    System.out.println(rn + " does not exists ");
                    JOptionPane.showMessageDialog(c, "Roll no does not exists!");
                }

            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(c, "Fill all the fields correctly" + npe);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(c, "Fill all the fields correctly");
            } catch (Exception e) {
                assert t != null;
                t.rollback();
                System.out.println("Roll no doesn't exists " + e);
                JOptionPane.showMessageDialog(c, e);

            } finally {
                assert session != null;
                session.close();
                System.out.println("close");
            }
        };
        btnUpdate.addActionListener(e1);


        ActionListener e2 = (ae) -> {

            MainFrame mf = new MainFrame();
            dispose();
        };
        btnBack.addActionListener(e2);

        setTitle("Update Student Details");
        setSize(250, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
}
