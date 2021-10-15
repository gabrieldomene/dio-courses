package org.dio.aula6;

import java.util.stream.Collectors;

public class StringLines {
    public static void main(String[] args) {

        String html = "<html>\n<head>\n</head>\n<body>\n</body>\n</html>";
        System.out.println(html.lines()
                .map(s -> "[TAG]::" + s)
                .filter(s -> s.contains("head"))
                .collect(Collectors.joining()));
//        System.out.println(html);
    }
}
