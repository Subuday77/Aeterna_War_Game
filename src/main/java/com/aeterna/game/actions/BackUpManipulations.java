package com.aeterna.game.actions;

import com.aeterna.game.beans.BackupLine;
import com.aeterna.game.beans.County;
import com.aeterna.game.beans.GlobalMap;
import com.aeterna.game.beans.Unit;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.apache.commons.io.FileDeleteStrategy;

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
    @Autowired
    Gson gson;
    static FileWriter myWriter;
    static final String folderPath = "C:\\Aeterna";
    static final String path = "C:\\Aeterna\\aeterna.save";
    static final String tempPath = "C:\\Aeterna\\aeterna.save.new";
    static File folder = new File(folderPath);
    static File file = new File(path);
    static File tempFile = new File(tempPath);

    @PostConstruct
    public ResponseEntity<?> createFile() {
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Folder created.");
            } else {
                System.out.println("Folder not created.");
            }
        }
        try {
            if (file.createNewFile()) {
                myWriter = new FileWriter(path);
//                myWriter.write("");
                myWriter.close();
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            return new ResponseEntity<>(false, HttpStatus.OK);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> saveState(GlobalMap globalMap) {
        String turn = servletRequest.getHeader("turn");
        int stepNumber = servletRequest.getIntHeader("stepNumber");
        BackupLine backupLine = new BackupLine(turn, stepNumber, globalMap);
        if (!gson.toJson(backupLine).equals(getLastLine(0))) {
            try {
                myWriter = new FileWriter(path, true);
                myWriter.append(gson.toJson(backupLine) + "\n");
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                return new ResponseEntity<>("Can't wright to file", HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    public ResponseEntity<?> restoreState() {
        long limit = countLines();
        if (countLines() > 1) {
            deleteLastLine();
        }
        BackupLine backupLine = gson.fromJson(getLastLine(0), BackupLine.class);
        // System.out.println(backupLine);
        return new ResponseEntity<>(backupLine, HttpStatus.OK);
    }

    public ResponseEntity<?> checkForOldGame() {
        return new ResponseEntity<>(countLines() > 0, HttpStatus.OK);
    }

    public ResponseEntity<?> lastLine(int searchType) {
        int countReduce = 0;
        BackupLine backupLine = gson.fromJson(getLastLine(countReduce), BackupLine.class);
        String activeUser = backupLine.getTurn();
        if (searchType == 1) {
            do {
                backupLine = gson.fromJson(getLastLine(countReduce), BackupLine.class);
                countReduce++;
            } while (activeUser.equals(backupLine.getTurn()) && countLines() - countReduce > 0);
            for (County county : backupLine.getGlobalMap().getCounties()) {
                for (Unit unit : county.getArmy()) {
                    if (!unit.getId().equals("None")) {
                        if (!unit.getName().equals("Cavalry")) {
                            unit.setNumberOfSteps(1);
                        } else {
                            unit.setNumberOfSteps(2);
                        }
                    }
                }
            }
            return new ResponseEntity<>(backupLine, HttpStatus.OK);
        }
        return new ResponseEntity<>(backupLine, HttpStatus.OK);
    }

    public ResponseEntity<?> makeNewFile() {
        deleteFile();
        createFile();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private static long countLines() {
        try {
            return Files.lines(Paths.get(path), Charset.defaultCharset()).count();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            return -1;
        }
    }

    private static String getLastLine(int countReduce) {
        String res = "";
        long limit = countLines() - countReduce;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            for (int i = 1; i <= limit; ++i) {
                if (i != limit) {
                    br.readLine();
                } else {
                    res = br.readLine();
                    br.close();
                    return res;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return "A vot hui!";
    }


    public static void deleteLastLine() {
        try {
            //File file = new File(tempPath);
            PrintWriter pw = new PrintWriter(new FileWriter(tempPath));
            BufferedReader br = new BufferedReader(new FileReader(path));
            long limit = countLines();
            for (int i = 1; i < limit; i++) {
                pw.println(br.readLine());
                pw.flush();
            }
            br.close();
            pw.close();
            deleteFile();
            renameFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    private static void deleteFile() {
        try {
            FileDeleteStrategy.FORCE.delete(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void renameFile() {
        tempFile.renameTo(file);
    }
}
