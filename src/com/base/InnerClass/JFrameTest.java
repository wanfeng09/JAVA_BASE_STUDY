package com.base.InnerClass;

import javax.swing.*;

/*
* 匿名内部类的使用场景
* GUI编程
* */
public class JFrameTest {
    public static void main(String[] args) {
        // 创建窗口
        JFrame win = new JFrame("首页");
        // 创建桌布
        JPanel page = new JPanel();
        win.add(page);
        // 添加按钮
        JButton btn = new JButton("登录");
        page.add(btn);
        // 点击事件
//        btn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(win,"已登录"); // 弹窗
//            }
//        });


        // 以上代码简化
        btn.addActionListener(e -> JOptionPane.showMessageDialog(win,"已登录"));

        // 窗口大小设置
        win.setSize(300,300);
        // 居中显示
        win.setLocationRelativeTo(null);
        // 关闭窗口退出程序
        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 窗口显示
        win.setVisible(true);
    }
}
