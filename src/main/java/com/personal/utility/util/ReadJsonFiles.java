package com.personal.utility.util;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.personal.utility.dto.Elements;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class ReadJsonFiles {

    public List<Elements> getIsoElements(){

        try {
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/json/elements.json"));

            List<Elements> elements = gson.fromJson(reader, new TypeToken<List<Elements>>() {}.getType());
            
            reader.close();

            return elements;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // public List<List<Object>> getMtiDetails(){

    //     try {
    //         Gson gson = new Gson();

    //         Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/json/mti.json"));

    //         List<List<Object>> elements = gson.fromJson(reader, new TypeToken<List<Elements>>() {}.getType());
            
    //         reader.close();

    //         return elements;
    //     } catch (Exception e) {
    //         System.out.println(e);
    //     }
    //     return null;
    // }
}
