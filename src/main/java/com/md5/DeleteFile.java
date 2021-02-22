package com.md5;

import java.io.*;
import java.util.ArrayList;

/**
 * @Auther: huangzhigao
 * @Date: 2020/7/9
 * @Description:
 */
public class DeleteFile {

    private static boolean deleteDir(File paramFile) {
        if (paramFile.isDirectory()) {
            String[] arrayOfString = paramFile.list();
            for (byte b = 0; b < arrayOfString.length; b++) {
                boolean bool = deleteDir(new File(paramFile, arrayOfString[b]));
                if (!bool) {
                    return false;
                }
            }
        }

        return paramFile.delete();
    }


    public static void main(String[] args) throws IOException {
        String str = "GBK";
        File file = new File("D://bb.txt");
        ArrayList<String> arrayList = new ArrayList();
        if (file.isFile() && file.exists()) {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), str);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str1 = null;
            while ((str1 = bufferedReader.readLine()) != null) {
                arrayList.add(str1);
            }
            inputStreamReader.close();
        }

        for (String str1 : arrayList) {
            boolean bool = deleteDir(new File("D://tmp/" + str1));
            if (!bool) {
                System.out.println("error is:D://tmp/" + str1);
            }
        }
    }
}

