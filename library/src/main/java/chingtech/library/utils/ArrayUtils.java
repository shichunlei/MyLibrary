package chingtech.library.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * *    ***********    ***********    **
 * *    ***********    ***********    **
 * *    **             **             **
 * *    **             **             **
 * *    **             **             **
 * *    ***********    **             **
 * *    ***********    **             **
 * *             **    **             **
 * *             **    **             **
 * *             **    **             **
 * *    ***********    ***********    ***********
 * *    ***********    ***********    ***********
 * </p>
 * MyLibrary
 * Package chingtech.library.utils
 * Description:
 * Created by 师春雷
 * Created at 18/2/2 上午9:32
 */
public class ArrayUtils {

    private static final String DELIMITER = ",";

    /**
     * Judge whether a array is null.
     *
     * @param array
     * @return
     */
    public static <T> boolean isEmpty(T[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * Judge whether a array is not null.
     *
     * @param array
     * @return
     */
    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    /**
     * 遍历数组
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> String traverseArray(T[] array) {
        return traverseArray(array, DELIMITER);
    }

    /**
     * 遍历数组
     *
     * @param array
     * @param delimiter
     * @param <T>
     * @return
     */
    public static <T> String traverseArray(T[] array, String delimiter) {
        if (isNotEmpty(array)) {
            int           len     = array.length;
            StringBuilder builder = new StringBuilder(len);
            int           i       = 0;
            for (T t : array) {
                if (t == null) {
                    continue;
                }
                builder.append(t.toString());
                i++;
                if (i < len) {
                    builder.append(delimiter);
                }
            }
            return builder.toString();
        }
        return null;
    }

    /**
     * 数组转换为List列表
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> arrayToList(T[] array) {
        if (isEmpty(array)) {
            return null;
        }

        List<T> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }

        return list;
    }
}
