package Calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;

public class PerCalendar {
    public  static void menu(JFrame jf,String ss,int year,int month,int day){
        if(month>=1&&month<=12){

        }else{
            month=12;
        }
        //获取当前的窗口主体
        Container ctainer=jf.getContentPane();
        //采用流式布局
        ctainer.setLayout(new FlowLayout());
        //设置‘年’字
        JLabel field_year=new JLabel("年");
        //设置与之对应的输入框
        JTextField field1=new JTextField();
        //限制输入的位数
        field1.setColumns(4);
        //设置‘月’字
        JLabel field_month=new JLabel("月");
        //设置与之对应的输入框
        JTextField field2=new JTextField();
        //限制输入的位数
        field2.setColumns(2);
        //设置‘日’字
        JLabel field_day=new JLabel("日");
        //设置与之对应的输入框
        JTextField field3=new JTextField();
        //限制输入的位数
        field3.setColumns(2);
        //设置一个跳转按钮
        JButton button=new JButton("跳转");
        //将以上组件装进窗口中
        ctainer.add(field1);
        ctainer.add(field_year);
        ctainer.add(field2);
        ctainer.add(field_month);
        ctainer.add(field3);
        ctainer.add(field_day);
        ctainer.add(button);
        //创建一个文本域
        JTextArea list=new JTextArea();
        //设置文本标题
        list.setBorder(BorderFactory.createTitledBorder(year+"年"+month+"月"+day+"日"));
        //将文本域添加进窗口
        jf.add(list);
        //设置文本域大小
        list.setBounds(10,10,550,300);
        //设置文本域内容
        list.setText(ss);
        list.setEditable(false);
        //以下三个addKeyListener分别对年月日的三个输入框进行了限制，限制其只能输入数字。
        field1.addKeyListener(new KeyAdapter() {
            @Override

            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                int keychar=e.getKeyChar();
                if(keychar>=KeyEvent.VK_0&&keychar<=KeyEvent.VK_9){

                }else{
                    e.consume();
                }
            }
        });
        field2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                int keychar=e.getKeyChar();
                if(keychar>=KeyEvent.VK_0&&keychar<=KeyEvent.VK_9){

                }else{
                    e.consume();
                }
            }
        });
        field3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                int keychar=e.getKeyChar();
                if(keychar>=KeyEvent.VK_0&&keychar<=KeyEvent.VK_9){

                }else{
                    e.consume();
                }
            }
        });
        //为提交按钮绑定刷新事件
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar calendar=Calendar.getInstance();
                //给年月日一个初始化数值
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH)+1;
                int day=calendar.get(Calendar.DATE);
                if(field1.getText().isEmpty()){

                }else{
                    //获得输入框输入的年份
                    year= Integer.parseInt(field1.getText());
                }
                if(field2.getText().isEmpty()){

                }else{
                    //获得输入框输入的月份
                    month= Integer.parseInt(field2.getText());
                }
                if(field3.getText().isEmpty()){

                }else{
                    //获得输入框输入的天
                    day= Integer.parseInt(field3.getText());
                }
                String S=date(year,month,day);
                jf.getContentPane().removeAll();
                menu(jf, ss, year, month, day);
            }
        });
    }
    public  static String date(int year,int month,int day){
        //判断输入的日是否合法
        if(day>=1&&day<=31){

        }else{
            //如果不合法,则看其月份，如果为2月,则修改其日为28号,其他月份则修改日为30，避免后台发生错误.
            if(month==2){
                day=28;
            }else {
                day=30;
            }
        }
        Calendar calendar=Calendar.getInstance();
        calendar.set(year,month-1,1);
        //获取第一天的星期
        int firstDay=calendar.get(Calendar.DAY_OF_WEEK);
        //将月份设置改为输入月份的下个月
        calendar.set(year,month,1);
        //将天数减一，就跳转到了输入月份的最后一天
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        //获取这个月的总天数
        int days=calendar.get(Calendar.DAY_OF_MONTH);
        //创建一个StringBuilder,来对文本进行存储
        StringBuilder ss= new StringBuilder();
        //打印日历的表头
        ss.append("星期日\t星期一\t星期二\t星期三\t星期四\t星期五\t星期六\t");
        //文本换行
        ss.append("\n");
        //输入日期
        int count=0;
        for(int i = 1;i<firstDay-1;i++){
            ss.append("\t");
            count++;
        }
        for(int i =1;i<=days;i++){
            //为了使得日期能够对其，当天数大于9以后，则变成2位数，将其进行对齐
            if(i<10){
                if(i==day){
                    //将跳转日期进行特殊‘*’标记,方便辨别
                    ss.append(" " + "*").append(i).append("\t");
                }else {
                    ss.append(" ").append(i).append("\t");
                }
            }else {
                if(i==day){
                    //将跳转日期进行特殊‘*’标记,方便辨别
                    ss.append("*").append(i).append("\t");
                }else {
                    ss.append(+i).append("\t");
                }
            }
            count++;
            //如果够一个星期，进行换行
            if(count%7==0){
                ss.append("\n");
            }
        }
        return ss.toString();
    }
    public static void main(String[] args){
        Calendar calendar =Calendar.getInstance();
        //获取当前日期的年月日
        int year= calendar.get(Calendar.YEAR);
        //因为获取到的月份比实际少一，每年的一月对应数字为0，则进行+1处理
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DATE);
        String s=date(year,month,day);
        JFrame jf=new JFrame();
        jf.setLayout(null);
        //设置大小为700*300
        jf.setSize(700,300);
        jf.setVisible(true);
        //为关闭窗口的关闭绑定事件
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu(jf,s,year,month,day);
    }
}
