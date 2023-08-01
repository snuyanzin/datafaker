package net.datafaker.transformations;

public interface Transformer<IN, OUT> {
    String LINE_SEPARATOR = System.lineSeparator();

    OUT apply(IN input, Schema<IN, ?> schema, int estimatedLength);

    default OUT apply(IN input, Schema<IN, ?> schema, int estimatedLength, int rowId) {
        // ignore rowId by default
        return apply(input, schema, estimatedLength);
    }

    OUT generate(Iterable<IN> input, final Schema<IN, ?> schema);

    OUT generate(final Schema<IN, ?> schema, int limit);
}
