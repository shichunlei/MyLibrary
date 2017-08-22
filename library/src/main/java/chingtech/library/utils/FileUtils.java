package chingtech.library.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {

    private FileUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static File sdDir = null;

    /**
     * 检测Sdcard是否存在
     *
     * @return
     */
    public static boolean isExitsSdcard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 得到SD卡根目录
     *
     * @return
     */
    public static String getSDPath() {
        if (isExitsSdcard()) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
        }
        return sdDir.getAbsolutePath();
    }

    /**
     * 在SD卡下创建文件夹
     *
     * @return
     */
    public static void createFolders(String folder) {
        File pictureFileDir = new File(getSDPath(), folder);
        if (!pictureFileDir.exists()) {
            pictureFileDir.mkdirs();
        }
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath
     * @return
     */
    public static boolean fileIsExists(String filePath) {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 删除文件
     *
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath) {
        boolean isDel;
        if (fileIsExists(filePath)) {
            File f = new File(filePath);
            isDel = f.delete();
        } else {
            isDel = true;
        }

        return isDel;
    }

    /**
     * 将文件路径转化成uri路径
     *
     * @param path
     * @param fileNmae
     * @return
     */
    public static Uri fileToUri(String path, String fileNmae) {
        File file = new File(path, fileNmae);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Uri.fromFile(file);
    }

    /**
     * 读取资源文件夹内文件内容
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String readFromAssets(Context context, String fileName) {
        String str = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            str = readTextFromSDcard(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 将传入的is一行一行解析读取出来出来
     *
     * @param is
     */
    private static String readTextFromSDcard(InputStream is) throws Exception {
        InputStreamReader reader         = new InputStreamReader(is, "utf-8");
        BufferedReader    bufferedReader = new BufferedReader(reader);
        StringBuilder     buffer         = new StringBuilder();
        String            str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        return buffer.toString();//把读取的数据返回
    }

}
