package isp.lab11;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * @author Radu Miron
 * @version 1
 */
public class FileUtils {
    private static final File MESSAGE_DIR = new File("messages");

    private FileUtils() {
    }

    public static void writeMessage(String aid, String message) {
        File file = getAircraftFile(aid);

        try {
            if (file.exists()) {
                Files.writeString(file.toPath(), message + "\n", StandardOpenOption.APPEND);
            } else {
                Files.writeString(file.toPath(), message + "\n", StandardOpenOption.CREATE_NEW);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getAircraftFile(String aid) {
        if (!MESSAGE_DIR.exists()) {
            MESSAGE_DIR.mkdirs();
        }

        return new File(MESSAGE_DIR, aid + ".txt");
    }
}
