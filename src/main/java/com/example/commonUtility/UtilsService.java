package com.example.commonUtility;

import com.example.encoder.enums.RefTable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UtilsService {

    public String[] formOffSetRefTable(String offsetText){
        String[] referenceTable = new String[RefTable.values().length];
        try {
            if(!offsetText.isBlank()){
                //region form nooffsethash
                String[] noOffSetRefTable = getNoOffSetRefTable();
                HashMap<String, Integer> noOffSetRefHash = new HashMap<>();
                for (int i = 0; i < noOffSetRefTable.length; i++) {
                    noOffSetRefHash.put(noOffSetRefTable[i], i);
                }
                //endregion

                //region form offSetRefTable
                if (noOffSetRefHash.containsKey(offsetText)){
                    int offset = noOffSetRefHash.get(offsetText);
                    int i = 0;
                    for (RefTable ref :
                            RefTable.values()) {
                        i = ref.ordinal()+offset;
                        if (i > RefTable.values().length-1){
                            referenceTable[i- RefTable.values().length] = ref.getString();
                        }else{
                            referenceTable[i] = ref.getString();
                        }
                    }
                }
                //endregion
            }else{
                throw new NullPointerException("String for offset is null");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return referenceTable;
    }

    public String[] getNoOffSetRefTable(){
        //region NoOffsetRefTable
        String[] noOffSetRefTable = new String[RefTable.values().length];
        for (RefTable ref :
                RefTable.values()) {
            noOffSetRefTable[ref.ordinal()] = ref.getString();
        }
        //endregion
        return noOffSetRefTable;
    }
}
