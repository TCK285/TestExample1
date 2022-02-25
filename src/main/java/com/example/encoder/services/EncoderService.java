package com.example.encoder.services;

import com.example.encoder.enums.RefTable;
import com.example.commonUtility.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class EncoderService {
    @Autowired
    private UtilsService utilsService;

    public String encode (String plainText){
        String encodedText ="";
        try {
            if (!plainText.isBlank()){
                plainText = plainText.toUpperCase();

                //region NoOffsetRefTable
                String [] noOffSetRefTable = utilsService.getNoOffSetRefTable();
                HashMap<String, Integer> noOffSetRefHash = new HashMap<>();
                for (int i = 0; i < noOffSetRefTable.length; i++) {
                    noOffSetRefHash.put(noOffSetRefTable[i], i);
                }
                //endregion

                //region randomly select an offset
                int randomOffsetNumber = ((int) (Math.random() * (RefTable.values().length - 1)));
                String offSetText = RefTable.values()[randomOffsetNumber].getString();
                //endregion

                String[] encodingRefTable = utilsService.formOffSetRefTable(offSetText);
                //region form offset hash
                HashMap<Integer, String> encodingTableHash = new HashMap<>();
                for (int i = 0; i < encodingRefTable.length; i++) {
                    encodingTableHash.put(i, encodingRefTable[i]);
                }
                //endregion

                encodedText = offSetText;

                //region encode text
                int plainTextLength = plainText.length();
                String[] plainTextLetters = new String[plainTextLength];
                for (int j = 0; j < plainTextLength; j++) {
                    String letter = plainText.substring(j,j+1);
                    if (noOffSetRefHash.containsKey(letter)){
                        int number = noOffSetRefHash.get(letter);
                        encodedText += encodingTableHash.get(number);
                    }else{
                        encodedText += letter;
                    }
                }
                //endregion

            }else{
                throw new NullPointerException("Blank string for encoding");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return encodedText;
    }

}
