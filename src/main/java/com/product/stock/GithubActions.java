package com.product.stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.nonNull;

public class GithubActions {

    private static final String FILE = "pom.xml";
    private static final String PROPERTIES = "properties";

    public static void main(String[] args) {
        System.out.println("Iniciando analise de dependencias");

        try {
            final var pomAsString = getPomAsString(FILE);
            System.out.println(pomAsString);
        } catch (final IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public static String getPomAsString(final String file) throws IOException {
        final var stringBuilder = new StringBuilder();
        final var reader = new BufferedReader(new FileReader(file));

        String line;
        while (nonNull((line = reader.readLine()))) {
            Pattern pattern = Pattern.compile(">(.*?)<");
            Matcher matcher = pattern.matcher(line);
            if(matcher.find()) {
                System.out.println(matcher.group(1));
            }
            stringBuilder.append(line);
            stringBuilder.append(System.lineSeparator());
        }

        reader.close();
        return stringBuilder.toString();
    }
}
