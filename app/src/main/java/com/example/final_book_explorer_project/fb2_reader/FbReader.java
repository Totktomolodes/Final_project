package com.example.final_book_explorer_project.fb2_reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class FbReader{

    // Путь к тестовому файлу FB2
    private static final String EPUB_FILE = "test.fb2";

    public static void main(String[] args) {
    }

    public void start() throws Exception {

        // Создаем временную папку в папке проекта
        String filePath = new File("").getAbsolutePath();
        Path tempDir = Files.createTempDirectory(Paths.get(filePath), "temp");

        // Каталог, в который нужно сохранить все ресурсы
        String outputDir = String.valueOf(tempDir) + "\\";

        // Создаем копию файла и переименовываем расширение в txt
        try {
            copyFile(new File(EPUB_FILE), new File(outputDir + "temp.txt"));
        } catch (java.nio.file.NoSuchFileException e) {

            File tmpFls = new File(String.valueOf(tempDir));
            deleteDir(tmpFls);
        }
    }

    // Метод для удаления временной папки
    private static void deleteDir(File tmpFls) {
        File[] contents = tmpFls.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (! Files.isSymbolicLink(f.toPath())) {
                    deleteDir(f);
                }
            }
        }
        tmpFls.delete();
    }

    // Метод для копирования файла
    public static void copyFile(File src, File dest) throws IOException {
        Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}