


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class DeleteFile {

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }


    public static void main(String[] args) throws IOException {
        String encoding = "GBK";
        File file = new File("D://bb.txt");
        List<String> list = new ArrayList<>();
        if (file.isFile() && file.exists()) {
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file), encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                list.add(lineTxt);
            }
            read.close();

        }
        for (String string : list) {
            boolean b = deleteDir(new File("D://tmp/" + string));
            if (b) {
                System.out.println("删除目录成功" + "D://tmp/" + string);
            }
        }
    }


}
