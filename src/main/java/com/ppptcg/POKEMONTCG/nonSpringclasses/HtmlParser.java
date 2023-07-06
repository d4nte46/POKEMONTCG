package com.ppptcg.POKEMONTCG.nonSpringclasses;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class HtmlParser {
    public static void main(String[] args) {
        try {
            ClassPathResource resource = new ClassPathResource("EXTRACT THE OPTIONS.txt");
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            String htmlCode = new String(bytes, StandardCharsets.UTF_8);
            Document doc = Jsoup.parse(htmlCode);
            Elements divElements = doc.select("div");

            for (Element div : divElements) {
                String text = div.text();
                System.out.println(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
