/**
 * @author 15968
 * @version 1.0
 * @description: TODO
 * @date 2023/9/20 22:41
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 抽象形状类
abstract class Shape {
    abstract void draw(Graphics g);
}

// 具体Painter类，用于绘制不同的形状
class Painter extends JPanel {
    private Shape currentShape;

    public void setShape(Shape shape) {
        this.currentShape = shape;
        repaint(); // 通知界面重新绘制
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentShape != null) {
            currentShape.draw(g);
        }
    }
}

public class MainFrame extends JFrame {
    private Painter painter;

    public MainFrame() {
        painter = new Painter();
        getContentPane().add(painter, BorderLayout.CENTER);

        // 创建按钮以选择不同的形状
        JButton circleButton = new JButton("绘制圆形");
        JButton rectangleButton = new JButton("绘制矩形");

        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painter.setShape(new Circle());
            }
        });

        rectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painter.setShape(new Rectangle());
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(circleButton);
        buttonPanel.add(rectangleButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setTitle("Shape Painter");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }
}

// 具体形状类
class Circle extends Shape {
    @Override
    void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(50, 50, 100, 100);
    }
}

class Rectangle extends Shape {
    @Override
    void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(50, 50, 150, 100);
    }
}
