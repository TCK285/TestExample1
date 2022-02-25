package com.example.encoder.services;

import com.example.commonUtility.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class DecoderService {
    @Autowired
    private UtilsService utilsService;

    public String decode (String encodedText){
        String decodedText = "";
        try {
            if (!encodedText.isBlank()){
                encodedText = encodedText.toUpperCase();

                String encodingText = encodedText.substring(0,1);
                boolean validEncodingText = false;
                String[] noOffSetRefTable = utilsService.getNoOffSetRefTable();
                HashMap<Integer, String> noOffSetRefHash = new HashMap<>();
                for (int i = 0; i < noOffSetRefTable.length; i++) {
                    noOffSetRefHash.put(i, noOffSetRefTable[i]);
                    if (noOffSetRefTable[i].equals(encodingText)){
                        validEncodingText = true;
                    }
                }

                if (!validEncodingText){
                    throw new RuntimeException("Invalid encoding text");
                }

                //region get offset text and form table with offset
                String[] offSetRefTable = utilsService.formOffSetRefTable(encodingText);
                HashMap<String, Integer> offSetRefHash = new HashMap<>();
                for (int i = 0; i < offSetRefTable.length; i++) {
                    offSetRefHash.put(offSetRefTable[i], i);
                }
                //endregion

                //region decode
                for (int i = 1; i < encodedText.length(); i++) {
                    String letter = encodedText.substring(i, i+1);

                    if (offSetRefHash.containsKey(letter)){
                        int number = offSetRefHash.get(letter);
                        decodedText += noOffSetRefHash.get(number);
                    }else{
                        decodedText += letter;
                    }

                }
                //endregion

            }else{
                throw new NullPointerException("Blank string for decoding");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return decodedText;
    }
}
