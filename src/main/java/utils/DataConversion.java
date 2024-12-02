package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataConversion {
    public static List<Integer> convStringLineToInt(String stringLine) {
        List<Integer> retList = new ArrayList<>();
        Scanner s = new Scanner(stringLine);

        while (s.hasNext()) {
            retList.add(s.nextInt());
        }

        return retList;
    }
}
