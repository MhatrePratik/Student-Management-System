import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class AddFrame extends JFrame {

    Container c;
    JLabel lblRno, lblName, lblSub1, lblSub2, lblSub3;
    JTextField txtRno, txtName, txtSub1, txtSub2, txtSub3;
    JButton btnSave, btnBack;

    AddFrame() {
        c = getContentPane();
        c.setLayout(new FlowLayout());
        c.setBackground(Color.yellow);

        lblRno = new JLabel("Enter Rno:");
        lblRno.setFont(new Font("Arial", Font.ITALIC|Font.BOLD, 12));
        lblName = new JLabel("Enter Name:");
        lblName.setFont(new Font("Arial", Font.ITALIC|Font.BOLD, 12));
        lblSub1 = new JLabel("Enter Sub marks 1:");
        lblSub1.setFont(new Font("Arial", Font.ITALIC|Font.BOLD, 12));
        lblSub2 = new JLabel("Enter Sub marks 2:");
        lblSub2.setFont(new Font("Arial", Font.ITALIC|Font.BOLD, 12));
        lblSub3 = new JLabel("Enter Sub marks 3:");
        lblSub3.setFont(new Font("Arial", Font.ITALIC|Font.BOLD, 12));

        txtRno = new JTextField(15);
        txtName = new JTextField(15);
        txtSub1 = new JTextField(15);
        txtSub2 = new JTextField(15);
        txtSub3 = new JTextField(15);

        btnSave = new JButton("Save");
        btnSave.setFont(new Font("Arial", Font.ITALIC|Font.BOLD, 12));
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.ITALIC|Font.BOLD, 12));

        c.add(lblRno);
        c.add(txtRno);
        c.add(lblName);
        c.add(txtName);
        c.add(lblSub1);
        c.add(txtSub1);
        c.add(lblSub2);
        c.add(txtSub2);
        c.add(lblSub3);
        c.add(txtSub3);
        c.add(btnSave);
        c.add(btnBack);

        //Back Button
        ActionListener e1 = (ae) -> {
            MainFrame af = new MainFrame();
            dispose();
        };
        btnBack.addActionListener(e1);


        //SAVE BUTTON
        ActionListener e2 = (ae) -> {

            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            SessionFactory sfact = cfg.buildSessionFactory();

            Session session = null;
            Transaction t = null;
            
            try {
                session = sfact.openSession();
                System.out.println("open");
                t = session.beginTransaction();

                String name = txtName.getText().trim();
                int rn = Integer.parseInt(txtRno.getText().trim());
                int m1 = Integer.parseInt(txtSub1.getText().trim());
                int m2 = Integer.parseInt(txtSub2.getText().trim());
                int m3 = Integer.parseInt(txtSub3.getText().trim());

                if ( rn >0 ) {
                    if (name.length() >= 2 && name.length() <= 27 && name.matches("^[a-zA-Z\\s]*$")) {
                        if (m1 <= 100 && m1 >= 0 && m2 <= 100 && m2 >= 0 && m3 <= 100 && m3 >= 0) {
                            Student s = new Student(rn, name, m1, m2, m3);
                            session.save(s);
                            t.commit();
                            System.out.println("Record Added!");
                            JOptionPane.showMessageDialog(c, "Student Data Added");
                            System.out.println("Roll is: " + rn + " Name is: " + name + " " + m1 + " " + m2 + " " + m3);
                            txtName.setText("");
                            txtRno.setText("");
                            txtSub1.setText("");
                            txtSub2.setText("");
                            txtSub3.setText("");
                        } else {
                            JOptionPane.showMessageDialog(c, "Enter marks in the range 0 to 100");
                        }
                    }else {
                        JOptionPane.showMessageDialog(c, "Entered name in not valid");
                    }
                } else {
                    JOptionPane.showMessageDialog(c, "Entered roll no in not valid");
                }
            } catch (NumberFormatException nfe) {
                //System.out.println(nfe);
                JOptionPane.showMessageDialog(c, "Fill all the fields correctly");
            } catch (Exception e) {
                assert t != null;
                t.rollback();
                //System.out.println("Issues " + e);
                JOptionPane.showMessageDialog(c, "Roll No Already Exists.");
            } finally {
                assert session != null;
                session.close();
                System.out.println("Closed!");
            }
        };
        btnSave.addActionListener(e2);


        setTitle("Add Student");
        setSize(250, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

    }
}
