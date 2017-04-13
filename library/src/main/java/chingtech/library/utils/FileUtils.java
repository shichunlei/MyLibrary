package chingtech.library.utils;

import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

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

}
