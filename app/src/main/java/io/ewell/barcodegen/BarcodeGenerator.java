package io.ewell.barcodegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * io.ewell.barcodegen
 * <p/>
 * Created by steve on 3/30/15.
 * <p/>
 * Copyright (c) 2015 steve. All rights reserved.
 */
public class BarcodeGenerator {

    private final String employeeNumber;
    private String mutableEmployeeNumber;
    private String upc;
    private String binary;

    public BarcodeGenerator(String employeeNumber) {
        this.employeeNumber = employeeNumber;
        this.mutableEmployeeNumber = employeeNumber;
        this.upc = employeeNumberAsUPC();
        this.mutableEmployeeNumber = this.upc;
        this.binary = employeeNumberAsBinary();
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getUpc() {
        return upc;
    }

    public String getBinary() {
        return binary;
    }

    private String employeeNumberAsUPC() {
        String output ="";
        String zero= "0";

        int evenSum = 0;
        int oddSum = 0;
        int checksumDigit;

        for (int i = 0; i < (12 - this.mutableEmployeeNumber.length()); i++) {
            output += zero;
        }

        output += this.mutableEmployeeNumber;

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

    private String employeeNumberAsBinary() {
        String output = "";
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


        Map<String,String> parityEncodingTableMap = new HashMap<>();
        List<Map> parityEncodingTable = new ArrayList<>();
        // Start 0
        parityEncodingTableMap.put("O", "0001101");
        parityEncodingTableMap.put("E", "0100111");
        parityEncodingTableMap.put("R", "1110010");
        parityEncodingTable.add(parityEncodingTableMap);
        parityEncodingTableMap = new HashMap<>();

        // Start 1
        parityEncodingTableMap.put("O", "0011001");
        parityEncodingTableMap.put("E", "0110011");
        parityEncodingTableMap.put("R", "1100110");
        parityEncodingTable.add(parityEncodingTableMap);
        parityEncodingTableMap = new HashMap<>();

        // Start 2
        parityEncodingTableMap.put("O", "0010011");
        parityEncodingTableMap.put("E", "0011011");
        parityEncodingTableMap.put("R", "1101100");
        parityEncodingTable.add(parityEncodingTableMap);
        parityEncodingTableMap = new HashMap<>();

        // Start 3
        parityEncodingTableMap.put("O", "0111101");
        parityEncodingTableMap.put("E", "0100001");
        parityEncodingTableMap.put("R", "1000010");
        parityEncodingTable.add(parityEncodingTableMap);
        parityEncodingTableMap = new HashMap<>();

        // Start 4
        parityEncodingTableMap.put("O", "0100011");
        parityEncodingTableMap.put("E", "0011101");
        parityEncodingTableMap.put("R", "1011100");
        parityEncodingTable.add(parityEncodingTableMap);
        parityEncodingTableMap = new HashMap<>();

        // Start 5
        parityEncodingTableMap.put("O", "0110001");
        parityEncodingTableMap.put("E", "0111001");
        parityEncodingTableMap.put("R", "1001110");
        parityEncodingTable.add(parityEncodingTableMap);
        parityEncodingTableMap = new HashMap<>();

        // Start 6
        parityEncodingTableMap.put("O", "0101111");
        parityEncodingTableMap.put("E", "0000101");
        parityEncodingTableMap.put("R", "1010000");
        parityEncodingTable.add(parityEncodingTableMap);
        parityEncodingTableMap = new HashMap<>();

        // Start 7
        parityEncodingTableMap.put("O", "0111011");
        parityEncodingTableMap.put("E", "0010001");
        parityEncodingTableMap.put("R", "1000100");
        parityEncodingTable.add(parityEncodingTableMap);
        parityEncodingTableMap = new HashMap<>();

        // Start 8
        parityEncodingTableMap.put("O", "0110111");
        parityEncodingTableMap.put("E", "0001001");
        parityEncodingTableMap.put("R", "1001000");
        parityEncodingTable.add(parityEncodingTableMap);
        parityEncodingTableMap = new HashMap<>();

        // Start 9
        parityEncodingTableMap.put("O", "0001011");
        parityEncodingTableMap.put("E", "0010111");
        parityEncodingTableMap.put("R", "1110100");
        parityEncodingTable.add(parityEncodingTableMap);

        if (this.mutableEmployeeNumber.length() == 13) {
            lefthandParity = lefthandParities[Character.getNumericValue(this.mutableEmployeeNumber.charAt(0))];
            this.mutableEmployeeNumber = this.mutableEmployeeNumber.substring(1);
        }

        String barcode = "101";

        for (int i = 0; i < this.mutableEmployeeNumber.length(); i++) {
            int digit;
            digit = Character.getNumericValue(this.mutableEmployeeNumber.charAt(i));
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
