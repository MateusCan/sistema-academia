package br.com.mleite.sistema.academia.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomizacaoDateDeserializador extends JsonDeserializer<Date> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String date = parser.getText();
        try {
            return dateFormat.parse(date);
        } catch (Exception e) {
            throw new IOException("Could not parse date: " + date);
        }
    }
}