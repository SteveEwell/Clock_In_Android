package io.ewell.clockin;

import java.util.*;

/**
 *
 * ${PACKAGE_NAME}
 *
 * Created by steve on 3/28/15.
 *
 * Copyright (c) 2015 steve. All rights reserved.
 */

public class BarcodeGen {

    private String convertEmpNumToUPC (String input) {
        String output ="";
        String zero= "0";

        int evenSum = 0;
        int oddSum = 0;
        int checksumDigit;

        for (int i = 0; i < (12 - input.length()); i++) {
            output += zero;
        }

        output += input;

        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(output.charAt(i));
            if (i % 2 == (output.length() == 12 ? 0 : 1)) {
                evenSum += digit;
            } else {
                oddSum += digit;
            }

        }

        checksumDigit = (10 - (evenSum + (oddSum * 3)) % 10);

        if (checksumDigit >=10) {
            checksumDigit = 0;
        }

        output += checksumDigit;

        return output;
    }

    private String convertEmpNumToBinaryEncoding (String input) {

        String output;
        String lefthandParity = "0000";
        String[] lefthandParities = new String[] {
                "OOOOOO",
                "OOEOEE",
                "OOEEOE",
                "OOEEEO",
                "OEOOEE",
                "OEEOOE",
                "OEEEOO",
                "OEOEOE",
                "OEOEEO",
                "OEEOEO"
        };


        Map<String,String> mParityEncodingTableMap = new HashMap<>();
        List<Map> parityEncodingTable = new ArrayList<>();
        // Start 0
        mParityEncodingTableMap.put("O", "0001101");
        mParityEncodingTableMap.put("E", "0100111");
        mParityEncodingTableMap.put("R", "1110010");
        parityEncodingTable.add(mParityEncodingTableMap);
        mParityEncodingTableMap = new HashMap<>();

        // Start 1
        mParityEncodingTableMap.put("O", "0011001");
        mParityEncodingTableMap.put("E", "0110011");
        mParityEncodingTableMap.put("R", "1100110");
        parityEncodingTable.add(mParityEncodingTableMap);
        mParityEncodingTableMap = new HashMap<>();

        // Start 2
        mParityEncodingTableMap.put("O", "0010011");
        mParityEncodingTableMap.put("E", "0011011");
        mParityEncodingTableMap.put("R", "1101100");
        parityEncodingTable.add(mParityEncodingTableMap);
        mParityEncodingTableMap = new HashMap<>();

        // Start 3
        mParityEncodingTableMap.put("O", "0111101");
        mParityEncodingTableMap.put("E", "0100001");
        mParityEncodingTableMap.put("R", "1000010");
        parityEncodingTable.add(mParityEncodingTableMap);
        mParityEncodingTableMap = new HashMap<>();

        // Start 4
        mParityEncodingTableMap.put("O", "0100011");
        mParityEncodingTableMap.put("E", "0011101");
        mParityEncodingTableMap.put("R", "1011100");
        parityEncodingTable.add(mParityEncodingTableMap);
        mParityEncodingTableMap = new HashMap<>();

        // Start 5
        mParityEncodingTableMap.put("O", "0110001");
        mParityEncodingTableMap.put("E", "0111001");
        mParityEncodingTableMap.put("R", "1001110");
        parityEncodingTable.add(mParityEncodingTableMap);
        mParityEncodingTableMap = new HashMap<>();

        // Start 6
        mParityEncodingTableMap.put("O", "0101111");
        mParityEncodingTableMap.put("E", "0000101");
        mParityEncodingTableMap.put("R", "1010000");
        parityEncodingTable.add(mParityEncodingTableMap);
        mParityEncodingTableMap = new HashMap<>();

        // Start 7
        mParityEncodingTableMap.put("O", "0111011");
        mParityEncodingTableMap.put("E", "0010001");
        mParityEncodingTableMap.put("R", "1000100");
        parityEncodingTable.add(mParityEncodingTableMap);
        mParityEncodingTableMap = new HashMap<>();

        // Start 8
        mParityEncodingTableMap.put("O", "0110111");
        mParityEncodingTableMap.put("E", "0001001");
        mParityEncodingTableMap.put("R", "1001000");
        parityEncodingTable.add(mParityEncodingTableMap);
        mParityEncodingTableMap = new HashMap<>();

        // Start 9
        mParityEncodingTableMap.put("O", "0001011");
        mParityEncodingTableMap.put("E", "0010111");
        mParityEncodingTableMap.put("R", "1110100");
        parityEncodingTable.add(mParityEncodingTableMap);

        if (input.length() == 13) {
            lefthandParity = lefthandParities[Character.getNumericValue(input.charAt(0))];
            input = input.substring(1);
        }

        String barcode = "101";

        for (int i = 0; i < input.length(); i++) {
            int digit = Character.getNumericValue(input.charAt(i));
            if (i < lefthandParity.length()) {
                barcode += parityEncodingTable.get(digit).get(lefthandParity.substring(i,(i+1)));

                if (i == lefthandParity.length() - 1) {
                    barcode += "01010";
                }
            } else {
                barcode += parityEncodingTable.get(digit).get("R");

            }
        }
        barcode += "101";

        output = barcode;

        return output;
    }

}
