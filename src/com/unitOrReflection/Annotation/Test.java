package com.unitOrReflection.Annotation;

@Demo(name="测试",list={"列表1","列表2"})
public class Test {
    @Demo2("特殊")
    // @Demo2(value = "测试")
    public void medTest(){}

}
