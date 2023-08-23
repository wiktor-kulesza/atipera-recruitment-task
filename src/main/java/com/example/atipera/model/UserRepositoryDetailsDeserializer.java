package com.example.atipera.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * Custom deserializer for converting JSON data into UserRepositoryDetails objects.
 * This class extends the StdDeserializer class provided by Jackson library.
 *
 * @since 22-08-2023
 * @author Wiktor Kulesza
 */
public class UserRepositoryDetailsDeserializer extends StdDeserializer<UserRepositoryDetails> {

    public UserRepositoryDetailsDeserializer() {
        this(null);
    }

    public UserRepositoryDetailsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public UserRepositoryDetails deserialize(JsonParser jp, DeserializationContext context)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        String name = node.get("name").asText();
        String login = node.get("owner").get("login").asText();
        return UserRepositoryDetails.builder()
                .name(name)
                .login(login)
                .build();
    }
}
