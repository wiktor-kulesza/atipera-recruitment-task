package com.example.atipera.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class BranchDetailsDeserializer extends StdDeserializer<BranchDetails> {

    public BranchDetailsDeserializer() {
        this(null);
    }

    public BranchDetailsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public BranchDetails deserialize(JsonParser jp, DeserializationContext context)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        String name = node.get("name").asText();
        String commitSha = node.get("commit").get("sha").asText();
        return BranchDetails.builder()
                .name(name)
                .lastCommitSha(commitSha)
                .build();
    }
}