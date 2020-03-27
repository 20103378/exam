package com.example.utils;
import com.example.domain.Ztree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:FileNameUtil
 * @Description:
 * @author:
 * @data:2017/10/24
 */
public class FileUtil {


    public static Ztree visitDir(Ztree root)
    {
        Ztree tree = new Ztree();
        File dir = new File(root.getHref());
        if (!dir.exists()) {
            return tree;
        }
        if (dir.isFile()) {
            return tree;
        }
        File[] files = dir.listFiles();
        List<Ztree> children = root.getNodes();
        if (children == null) {
            children = new ArrayList<Ztree>();
            root.setNodes(children);
        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].isHidden()) {
                continue;
            }
            if (files[i].isDirectory()) {

                Ztree child = new Ztree();
                child.setText(files[i].getName());
                child.setHref(files[i].getAbsolutePath());
                visitDir(child);
                children.add(child);
            } else {
                Ztree child = new Ztree();
                child.setText(files[i].getName());
                child.setHref(files[i].getAbsolutePath());
                child.setNodes(null);
                children.add(child);
            }
        }
        root.setHref("#");
        return root;
    }





    public static boolean isWord2003(String filePath)
    {
        return filePath.matches("^.+\\.(?i)(doc)$");
    }

    public static boolean isWord2007(String filePath)
    {
        return filePath.matches("^.+\\.(?i)(docx)$");
    }


    public static boolean isExcel2003(String filePath)
    {
        return filePath.matches("^.+\\.(?i)(xls)$");

    }

    public static boolean isExcel2007(String filePath)
    {

        return filePath.matches("^.+\\.(?i)(xlsx)$");

    }

    public static boolean isPDF(String filePath)
    {

        return filePath.matches("^.+\\.(?i)(pdf)$");

    }
    /**
     * 字符串保存到.txt文件
     * @param str
     * @param filename
     */
    public static void StringToFile(String str, String filename)
    {
        try
        {
            //创建文件对象
            File file = new File(filename);
            // 向文件写入对象写入信息
            FileWriter fileWriter = new FileWriter(file);

            // 写文件
            fileWriter.write(str);
            // 关闭
            fileWriter.close();
        }
        catch (IOException e)
        {
            //
            e.printStackTrace();
        }
    }

    /**
     * .txt文件保存为html文件
     * @param filePath
     * @param htmlPosition
     */
    public static void txtToHtml(String filePath, String htmlPosition) {
        try {
//                        String encoding = "GBK";
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                // 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                // 写文件
                FileOutputStream fos = new FileOutputStream(new File(htmlPosition));
                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                BufferedWriter bw = new BufferedWriter(osw);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    bw.write(lineTxt + "</br>");
                }
                bw.close();
                osw.close();
                fos.close();
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }
}