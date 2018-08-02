package example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

/**
 * Complex {@link Entity}, with all serializing features.
 */
@Value
@JsonDeserialize(builder = ComplexEntity.JsonBuilder.class)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ComplexEntity {

    @JsonProperty("super_customizable_property")
    String field;

    @JsonIgnore
    String secondField;

    int severalWordsField;

    /**
     * Some constructor for tests.
     */
    ComplexEntity(String field, int severalWordsField) {
        this(field, null, severalWordsField);
    }

    /**
     * Explicit all args constructor.
     */
    @Builder(builderClassName = "JsonBuilder")
    public ComplexEntity(
            String superCustomizableProperty,
            String secondField,
            int severalWordsField) {
        this.field = superCustomizableProperty;
        this.secondField = secondField;
        this.severalWordsField = severalWordsField;
    }

    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class JsonBuilder {
    }
}
