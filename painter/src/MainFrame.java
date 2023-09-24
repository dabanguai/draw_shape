import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author 15968
 * @version 1.0
 * @description: TODO
 * @date 2023/9/21 8:52
 */

// 定义表示按钮的接口
interface Button {
    JButton createButton(String args);
    void addListener(JButton jButton, ActionListener actionListener);
}

// 设计一个抽象形状类，并提供 抽象可重写的draw()方法
abstract class Shape{
    private Shape shape;
    abstract void draw(Graphics g);
}

// Painter类，可绘制不同形状
// 需要继承 JPanel， 因为需要在JPanel中实现组件的添加
class Painter extends JPanel {
    private Shape goalShape;

    public void setGoalShape(Shape goalShape) {
        this.goalShape = goalShape;
        repaint();
    }

    // paintComponent 创建一个 Graphics类型的对象
    // protected void paintComponent(Graphics g) {
    //     if (ui != null) {
    //         Graphics scratchGraphics = (g == null) ? null : g.create();
    //         try {
    //             ui.update(scratchGraphics, this);
    //         }
    //         finally {
    //             scratchGraphics.dispose();
    //         }
    //     }
    // }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (goalShape != null) {
            goalShape.draw(g);
        }
   }
}

// 按钮实现类
class GoalButton implements Button {
    @Override
    public JButton createButton(String args) {
        String shapeName = args;
        JButton goalButton = new JButton(shapeName);
        return goalButton;
    }

    @Override
    public void addListener(JButton jButton, ActionListener actionListener) {
        jButton.addActionListener(actionListener);
    }
}
// class CircleButton implements Button {
//
//     @Override
//     public JButton createButton() {
//         JButton circleButton = new JButton("绘制圆形");
//         return circleButton;
//     }
//
//     @Override
//     public void addListener(JButton button, ActionListener actionListener) {
//         button.addActionListener(actionListener);
//     }
// }
public class MainFrame extends JFrame {

    // 绘制主界面
    public MainFrame(){
        // 添加panel，利用ComponentUI
        Painter painter = new Painter();
        // getContentPane() 返回一个 Container 对象
        getContentPane().add(painter, BorderLayout.CENTER);

        // 创建不同形状对应的按钮
        GoalButton myButton = new GoalButton();
        JButton circleButton = myButton.createButton("绘制圆形");
        JButton rectangleButton = myButton.createButton("绘制矩形");
        JButton triangleButton = myButton.createButton("绘制三角形");

        // 添加监听事件,点击按钮时重新绘制
        myButton.addListener(circleButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 绘制图像
                painter.setGoalShape(new MyCircle());
            }
        });
        myButton.addListener(rectangleButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painter.setGoalShape(new Rectangle());
            }
        });
        myButton.addListener(triangleButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painter.setGoalShape(new Triangle());
            }
        });

        // 添加按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(circleButton);
        buttonPanel.add(rectangleButton);
        buttonPanel.add(triangleButton);
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

class MyCircle extends Shape {
    @Override
    void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(50, 50, 100, 100);
    }
}

class Rectangle extends Shape {
    @Override
    void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(50, 50, 100, 100);
    }
}

class Triangle extends Shape {
    @Override
    void draw(Graphics g) {
        Polygon p  = new Polygon();
        p.addPoint(50, 150);
        p.addPoint(150, 150);
        p.addPoint(100, 50);
        g.setColor(Color.BLUE);
        g.fillPolygon(p);
    }
}