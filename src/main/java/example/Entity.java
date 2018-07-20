package example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

/**
 * Example of a json serializable entity through builder.
 *
 * Avoids usage of {@link JsonCreator} and {@link JsonProperty} in constructor.
 */
@Value
@Builder(builderClassName = "JsonBuilder")                  // points on explicit defined empty class
@JsonDeserialize(builder = Entity.JsonBuilder.class)        // using that class for json deserializing
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Entity {

    private String field;

    private int severalWordsField;

    /**
     * This static class must be explicitly defined while lombok {@link Builder} can't apply annotations to generated
     * builder class.
     *
     * It will be extended by lombok with actual builder logic at compile time.
     */
    @JsonPOJOBuilder(withPrefix = "")        // makes lombok's generated builder method names corresponding with jackson
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class JsonBuilder {
    }
}
