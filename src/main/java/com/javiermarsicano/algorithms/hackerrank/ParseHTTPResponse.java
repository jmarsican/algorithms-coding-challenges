package com.javiermarsicano.algorithms.hackerrank;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/*
Response sample:
 {"data":"key=IAfpK, age=58, key=WNVdi, age=64, key=jp9zt, age=47, key=0Sr4C, age=68, key=CGEqo, age=76, key=IxKVQ,
 age=79, key=eD221, age=29, key=XZbHV, age=32, key=k1SN5, age=88, key=4SCsU, age=65, key=q3kG6, age=33, key=MGQpf,
 age=13, key=Kj6xW, age=14, key=tg2VM, age=30, key=WSnCU, age=24, key=f1Vvz, age=46, key=dOS7A, age=72, key=tDojg,
 age=82, key=nZyJA, age=48, key=R8JTk, age=29, key=005Ot, age=66, key=HHROm, age=12, key=5yzG8, age=51, key=xMJ5D,
 age=38, key=TXtVu, age=82, key=Hz38B, age=84, key=WfObU, age=27, key=mmqYB, age=14, key=4Z3Ay, age=62, key=x3B0i,
 age=55, key=QCiQB, age=72, key=zGtmR, age=66, key=nlIN9, age=8, key=hKalB, age=50, key=Na33O, age=17, key=jMeXm,
 age=15, key=OO2Mc, age=32, key=hhowx, age=34, key=gLMJf, age=60, key=PblX6, age=66, key=8Vm5W, age=22, key=oZKd6,
 age=88, key=RXNfQ, age=25, key=3yy0p, age=64, key=FrQbL, age=80, key=vlUkk, age=55, key=DP8po, age=80, key=EroX6,
 age=84, key=3bsll, age=86, key=QhZjA, age=85, key=wm6uc, age=74, key=MC1FM, age=75"}

 sample result: 30
* */
class ParseHTTPResponse {

    private static final int THRESHOLD_AGE = 50;

    public static void main (String[] args) {
        System.setProperty("http.agent", "Chrome");
        try {
            URL url = new URL("https://coderbyte.com/api/challenges/json/age-counting"); //result = 128
            try {
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();

                /*your solution here*/

                //read response
                StringBuilder sb = new StringBuilder();
                Reader reader = new BufferedReader(
                        new InputStreamReader(
                                inputStream, Charset.forName(StandardCharsets.UTF_8.name())
                        )
                );
                int c = 0;
                while ((c = reader.read()) != -1) {
                    sb.append((char) c);
                }

                //parse response (JSON) with no library
                String response = sb.toString();
                String data = response.split(":")[1];
                data = data.substring(1, data.length()-2);

                //process data value (String) to get all values
                data = data.replace(",", "");
                String[] values = data.split(" ");

                //find ages above threshold
                int count = 0;
                for (int i = 1; i <= values.length; i += 2) { //just odd values belong to ages
                    int age = Integer.parseInt(values[i].split("=")[1]);
                    if (age >= THRESHOLD_AGE) {
                        count++;
                    }
                }

                System.out.println(count);
            } catch (IOException ioEx) {
                System.out.println(ioEx);
            }
        } catch (MalformedURLException malEx) {
            System.out.println(malEx);
        }
    }
}
