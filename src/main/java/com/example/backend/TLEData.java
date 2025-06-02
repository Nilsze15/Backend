package com.example.backend;



public class TLEData {
    public String line1;
    public String line2;

    public TLEData(String data){
        //System.out.println(data);
        String[] lines = data.split("\\\\r\\\\n");
        //System.out.println(lines.length);
        this.line1 = lines[1].trim();
        this.line2 = lines[2].trim();
    }



    public String getttLine1(String tleData){
        String[] lines = tleData.split("\\r?\\n?");

        String name = lines[0].trim(); // "UWE-3"
        String line1 = lines[1].trim(); // "1 39446U 13066AG ..."
        String line2 = lines[2].trim(); // "2 39446  ..."
        System.out.println("TLE-String: [" + line1 + "]");
        return line1;
    }
    // Zeilen aufsplitten

    public String getttLine2(String tleData){

        String[] lines = tleData.split("\\r?\\n");

        String name = lines[0].trim(); // "UWE-3"
        String line1 = lines[1].trim(); // "1 39446U 13066AG ..."
        String line2 = lines[2].trim(); // "2 39446  ..."
        System.out.println("TLE-String: [" + line2 + "]");
        return line2;
    }

    public String getLine1(){
        return line1;
    }
    public String getLine2(){
        return line2;
    }

}
