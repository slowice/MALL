package xb.mall.common;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static void cleanDir(String path) throws IOException {
        File file = new File(path);
        FileUtils.cleanDirectory(file);
    }

    public static Integer sizeOfDir(String path){
        File file = new File(path);
        int length = 0;
        if(file.isDirectory()){
            length = file.listFiles().length;
        }
        return length;
    }
}
