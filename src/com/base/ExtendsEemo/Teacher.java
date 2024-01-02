package com.base.ExtendsEemo;
/*
* 继承父类People，共同拥有的属性。
* */
public class Teacher extends People{
    private String skill;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void getInfo(){
        System.out.println("教师的名字"+getName()+"-----所拥有技能"+skill);
    }

    @Override
    public void printInfo(){
        System.out.println("重写父类"+toString());
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "skill='" + skill + '\'' +
                '}';
    }
}
