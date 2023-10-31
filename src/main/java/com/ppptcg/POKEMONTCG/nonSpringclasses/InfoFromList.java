package com.ppptcg.POKEMONTCG.nonSpringclasses;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import javax.sound.midi.SysexMessage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InfoFromList {

    public void writetheinfo(String[] info,String filename){
        try (OutputStream os = new FileOutputStream(getResourceFilePath(filename));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));) {
            for (String element : info){
                writer.write(element);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        try {
//            ClassPathResource resource = new ClassPathResource("Variant List.txt");
//            InputStream inputStream = resource.getInputStream();
//            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
//            String variantList = new String(bytes, StandardCharsets.UTF_8);
//            String[] lines = variantList.split("\\n");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public String[] gettheinfo(String filename) {
        try(InputStream is= getResourceStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        } ;
        return new String[0];
    }

    private static String getResourceFilePath(String fileName) throws IOException{
        Resource resource = new ClassPathResource(fileName);
        return resource.getFile().getAbsolutePath();
    }

    private static InputStream getResourceStream(String fileName) throws IOException {
        Resource resource = new ClassPathResource(fileName);
        return resource.getInputStream();
    }

}
