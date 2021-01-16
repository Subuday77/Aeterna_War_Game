package com.aeterna.game.actions;

import com.aeterna.game.beans.BackupLine;
import com.aeterna.game.beans.GlobalMap;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


@Component
public class BackUpManipulations {
    @Autowired
    GlobalMap globalMap;
    @Autowired
    HttpServletRequest servletRequest;
    FileWriter myWriter;
    @Autowired
    Gson gson;

    @PostConstruct
    public void createFile() {
        try {
            File file = new File("C:\\Windows\\Temp\\aeterna.save");
            if (file.createNewFile()) {
                myWriter = new FileWriter("C:\\Windows\\Temp\\aeterna.save");
//                myWriter.write("");
               myWriter.close();
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }

    }

    public ResponseEntity<?> saveState(GlobalMap globalMap) {
        String turn = servletRequest.getHeader("turn");
        int stepNumber = servletRequest.getIntHeader("stepNumber");
        BackupLine backupLine = new BackupLine(turn,stepNumber,globalMap);
        try {
            myWriter = new FileWriter("C:\\Windows\\Temp\\aeterna.save", true);
            myWriter.append(gson.toJson(backupLine) + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            return new ResponseEntity<>("Can't wright to file", HttpStatus.FORBIDDEN);

        }
//        System.out.println(stepNumber);
//        System.out.println(globalMap);
        long numberOfLines = countLines();
//        System.out.println(numberOfLines);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    public ResponseEntity<?> restoreState () {
        deleteLastLine();
        BackupLine backupLine = gson.fromJson(getLastLine(), BackupLine.class);
        return new ResponseEntity<>(backupLine, HttpStatus.OK);
    }

    private static long countLines() {
        try {
            return Files.lines(Paths.get("C:\\Windows\\Temp\\aeterna.save"), Charset.defaultCharset()).count();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            return -1;
        }
    }

    private static String getLastLine () {
        long limit = countLines();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Windows\\Temp\\aeterna.save"));
            for (int i = 1; i <= limit; ++i) {
                br.readLine();
                if (i==limit-1){
                    return br.readLine();
                }
            }
            br.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return "A vot hui!";
    }

    public static void deleteLastLine() {
        try {
            File file = new File("C:\\Windows\\Temp\\aeterna.save.new");
            PrintWriter pw = new PrintWriter(new FileWriter("C:\\Windows\\Temp\\aeterna.save.new"));
            BufferedReader br = new BufferedReader(new FileReader("C:\\Windows\\Temp\\aeterna.save"));
            long limit = countLines();
            if (countLines() > 1) {
            for (int i = 1; i < limit; i++) {
                    pw.println(br.readLine());
                    pw.flush();
                }
                br.close();
                pw.close();
                deleteFileAndRename();
            }
            br.close();
            pw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    private static void deleteFileAndRename() {
        File file = new File("C:\\Windows\\Temp\\aeterna.save");
        file.delete();
        file = new File("C:\\Windows\\Temp\\aeterna.save.new");
        file.renameTo(new File("C:\\Windows\\Temp\\aeterna.save"));
    }
}
