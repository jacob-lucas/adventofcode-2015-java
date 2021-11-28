package com.jacoblucas.adventofcode2015.day12;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacoblucas.adventofcode2015.utils.ObjectMapperFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

public class JsonTreeWalker {
    private List<Integer> collected;

    public JsonNode read(final String json) throws JsonProcessingException {
        final ObjectMapper objectMapper = ObjectMapperFactory.get();
        return objectMapper.readTree(json);
    }

    public List<Integer> collectNumbers(final JsonNode node, final String ignore) {
        collected = new ArrayList<>();
        walk(node, ignore);
        return collected;
    }

    private void walk(final JsonNode node, final String ignore) {
        if (node.isArray()) {
            // list of objects
            node.elements().forEachRemaining(e -> walk(e, ignore));
        } else if (node.isObject()) {
            // map of strings to values
            final boolean shouldIgnore = StreamSupport.stream(Spliterators.spliteratorUnknownSize(node.fields(), Spliterator.ORDERED), false)
                    .filter(field -> field.getValue().isValueNode())
                    .anyMatch(field -> field.getValue().asText().equals(ignore));

            if (!shouldIgnore) {
                node.fields().forEachRemaining(f -> walk(f.getValue(), ignore));
            }
        } else if (node.isValueNode()) {
            // value
//            System.out.println("[" + node.getNodeType().name() + "] " + node.asText());
            if (node.isNumber()) {
                collected.add(node.intValue());
            }
        }
    }

//    public boolean contains(final JsonNode node, final String value) {
//        final Queue<JsonNode> queue = new ArrayDeque<>();
//        queue.add(node);
//        while (!queue.isEmpty()) {
//            JsonNode n = queue.remove();
////            if (n.isObject()) {
////                // map of strings to values
////                n.fields().forEachRemaining(f -> queue.add(f.getValue()));
////            } else if (n.isArray()) {
////                // list of objects
////                n.elements().forEachRemaining(queue::add);
//            if (n.isValueNode()) {
//                // value
////                System.out.println("[" + n.getNodeType().name() + "] " + n.asText());
//                if (n.asText().equals(value)) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
}
