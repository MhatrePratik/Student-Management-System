import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

class Charts extends JFrame {

    Container c;

    Charts() {
        c = getContentPane();
        c.setLayout(new FlowLayout());

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sfact = cfg.buildSessionFactory();

        Session session = null;
        Transaction t = null;

        DefaultCategoryDataset d1 = new DefaultCategoryDataset();

        try {
            session = sfact.openSession();

            Query query = session.createQuery("from Student order by rno ASC ");//here persistent class name is Student
            List<Student> stu = (List<Student>) query.list();

            session.close();

            for (Student m : stu) {
                d1.addValue(m.getm1(), m.getName(), "Subject 1");
                d1.addValue(m.getm2(), m.getName(), "Subject 2");
                d1.addValue(m.getm3(), m.getName(), "Subject 3");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JDialog(), "se" + e);
            System.out.println(e);

        } finally {
            // session.close();
            System.out.println("Charts Viewed");
        }

        JFreeChart chart = ChartFactory.createBarChart("Student's Marks", "Subjects", "Marks",
                d1, PlotOrientation.VERTICAL, true, false, false);

        ChartPanel cp = new ChartPanel(chart);
        setContentPane(cp);


        int width = 600;    /* Width of the image */
        int height = 400;   /* Height of the image */

        File barchart = new File("Bar_Chart_HQL.png");
        try {
            ChartUtilities.saveChartAsJPEG(barchart, chart, width, height);
        } catch (IOException e) {
            System.out.println("Problem saving chart as jpeg " + e);
        }

        setTitle("Charts Data");
        setLocationRelativeTo(null);
        setSize(400, 300);
        setVisible(true);
    }
}