package com.file.Recursion;

import java.io.File;
import java.io.IOException;

/*
* 文件搜索【QQ.exe】
* 找到D盘下的所有一级的文件对象
* 遍历，判断是文件还是目录
* 如果是文件，判断是否是自己想要的
* 如果是目录，进入目录，重复以上步骤
*
* */
public class SearchFile {
    public static void main(String[] args) throws IOException {
        // 搜索较慢【D盘放的内容过大】
        // seach(new File("D:/"),"QQ.exe");
        seach(new File("D:\\Program Files\\Tencent"),"QQ.exe");
        // 删除文件
        // del(new File("D:\\java\\demo\\fileTest"));
    }

    /**
     * 去盘符下搜索某个文件
     * @param dir 目录
     * @param name 要搜索的文件名
     */
    public static void seach(File dir,String name) throws IOException {
        // 无法搜索
        if(dir == null || !dir.exists() || dir.isFile()){
            return;
        }

        // 存在目录，获取一级目录对象
        File[] files = dir.listFiles();
        if(files != null && files.length > 0){
            for (File f: files){
                if(f.isFile()){
                    if(f.getName().contains(name)){
                        System.out.println("找到了该文件"+ f.getAbsolutePath()); // D:\Program Files\Tencent\QQ\Bin\QQ.exe
                        // 启动
                        Runtime run = Runtime.getRuntime();
                        run.exec(f.getAbsolutePath());
                    }
                }else{
                    // 是目录，重复过程【递归】
                    seach(f,name);
                }
            }
        }
    }

    /**
     * 删除文件 【注意，只能删除空目录】
     * @param dir 目录
     */
    public static void  del(File dir){
        if(dir == null || !dir.exists()){
            return;
        }
        if(dir.isFile()){
            dir.delete();
            return;
        }
        File[] files = dir.listFiles();
        if(files == null){
            return;
        }
        // 有内容的目录，删除完里面的内容，在删除掉该目录
        for (File file:files){
            if(file.isFile()){
                file.delete();
            }else{
                del(file);
            }
        }
        // 删除该目录
        dir.delete();
        System.out.println("删除成功");
    }
}
