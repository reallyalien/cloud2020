//package com.ot.springcloud.writer;
//
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.*;
//import java.util.List;
//
//public class LocalFileWriter {
//
//    private String fileName;
//
//    private String path = "D:/test";
//
//    public LocalFileWriter(String fileName) {
//        this.fileName = fileName;
//        FileOutputStream fos = null;
//        XSSFWorkbook wb = null;
//        if (!isExist(path, fileName)) {
//            try {
//                wb = new XSSFWorkbook();
//                fos = new FileOutputStream(new File(path, fileName));
//                fos.flush();
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public static boolean isExist(String path, String fileName) {
//        File file = new File(path, fileName);
//        return file.exists();
//    }
//
//    public void writer(List<Integer> data) {
//        FileInputStream fis = null;
//        FileOutputStream fos = null;
//        try {
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}
