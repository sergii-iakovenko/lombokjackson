package example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

/**
 * The {@link Entity} version with custom field serializing name.
 */
@Value
@JsonDeserialize(builder = CustomizedEntity.JsonBuilder.class)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CustomizedEntity {

    @JsonProperty("super_customizable_property")    // uses for serializing
    private String field;

    private int severalWordsField;

    /**
     * Explicit all args constructor.
     *
     * The {@link Builder} is hire now, and uses declared constructors argument names to generate builder methods names.
     */
    @Builder(builderClassName = "JsonBuilder")
    public CustomizedEntity(
            String superCustomizableProperty,   // generates builder method name that uses for deserializing
            int severalWordsField) {
        this.field = superCustomizableProperty;
        this.severalWordsField = severalWordsField;
    }

    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class JsonBuilder {
    }
}
