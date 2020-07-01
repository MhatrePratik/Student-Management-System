import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class MainFrame extends JFrame {

    Container c;
    JButton btnAdd, btnView, btnUpdate, btnDelete, btnCharts;

    MainFrame() {
        c = getContentPane();
        c.setLayout(new FlowLayout());
        c.setBackground(Color.yellow);


        btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Arial", Font.ITALIC|Font.BOLD, 12));

        btnView = new JButton("View");
        btnView.setFont(new Font("Arial", Font.ITALIC|Font.BOLD, 12));

        btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Arial", Font.ITALIC|Font.BOLD, 12));

        btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Arial", Font.ITALIC|Font.BOLD, 12));

        btnCharts = new JButton("Charts");
        btnCharts.setFont(new Font("Arial", Font.ITALIC|Font.BOLD, 12));


        c.add(Box.createRigidArea(new Dimension(100, 50)));
        c.add(btnAdd);
        c.add(Box.createRigidArea(new Dimension(100, 75)));
        c.add(btnView);
        c.add(Box.createRigidArea(new Dimension(250, 25)));
        c.add(btnUpdate);
        c.add(Box.createRigidArea(new Dimension(350, 25)));
        c.add(btnDelete);
        c.add(Box.createRigidArea(new Dimension(450, 25)));
        c.add(btnCharts);

        ActionListener e1 = (ae) -> {
            AddFrame af = new AddFrame();
            dispose();
        };
        btnAdd.addActionListener(e1);

        ActionListener e2 = (ae) -> {
            ViewFrame vf = new ViewFrame();
            dispose();
        };
        btnView.addActionListener(e2);

        ActionListener e3 = (ae) -> {
            UpdateFrame uf = new UpdateFrame();
            dispose();
        };
        btnUpdate.addActionListener(e3);

        ActionListener e4 = (ae) -> {
            DeleteFrame df = new DeleteFrame();
            dispose();
        };
        btnDelete.addActionListener(e4);

        ActionListener e5 = (ae) -> {
            Charts charts = new Charts();
            //dispose();
        };
        btnCharts.addActionListener(e5);


        setTitle("S.M.S");
        setSize(310, 370);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

    }

}