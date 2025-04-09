package com.expertos.pruebas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        String jsonContent = """
                {
                    "nombre": "Israel Sánchez Cabrera", 
                    "edad": "19", 
                    "email": "mecago"
                }
                """;
        Persona yo;
        try {
            yo = mapper.readValue(jsonContent, Persona.class);
            System.out.println(yo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println(mapper.writeValueAsString(yo));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
