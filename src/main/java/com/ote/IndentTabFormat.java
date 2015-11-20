package com.ote;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Olivier on 20/11/2015.
 */
public final class IndentTabFormat {

    public static Collection<String> format(Collection<String> param) {

        Map<Integer, Integer> maxLengthPerRow = new HashMap<Integer, Integer>(25);

        param.stream().
                // split by '|' --> List<List<String>>
                map(e -> Arrays.asList(e.split("\\|", -1))).
                // remove first and last element (corresponding to '|' --> List<List<String>>
                map(e -> e.subList(1, e.size() - 1)).
                // consume the stream to populate the map with the max length for each column
                forEach(e -> {
                    for (int i = 0; i < e.size(); i++) {
                        String curElem = e.get(i).trim();
                        int curLength = curElem.length();
                        Integer curMax = maxLengthPerRow.get(i);
                        if (curMax != null) {
                            if (curLength > curMax) {
                                maxLengthPerRow.put(i, curLength);
                            }
                        } else {
                            maxLengthPerRow.put(i, curLength);
                        }
                    }
                });

        return param.stream().
                // split by '|' --> List<List<String>>
                map(e -> Arrays.asList(e.split("\\|", -1))).
                // remove first and last element (corresponding to '|' --> List<List<String>>
                map(e -> e.subList(1, e.size() - 1)).
                // transform each row's content with the correct format --> List<List<String>>
                map(e -> {
                    for (int i = 0; i < e.size(); i++) {
                        StringBuilder sb = new StringBuilder().
                                append(i == 0 ? "| " : " ").
                                append(StringUtils.rightPad(e.get(i).trim(), maxLengthPerRow.get(i))).
                                append(" |");
                        e.set(i, sb.toString());
                    }
                    return e;
                }).
                // concatenate each row's content --> List<String>
                map(e -> e.stream().collect(Collectors.joining())).
                // consume the stream to return a list
                collect(Collectors.toList());
    }
}
