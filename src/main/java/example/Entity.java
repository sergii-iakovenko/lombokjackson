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
 * The {@link DefaultEntity} becomes immutable.
 * It's a {@link Value}, so fields are efficiently final private. The default Jackson serialization model not longer
 * works.
 *
 * Alternative to {@link BaselineEntity}.
 * Avoids usage of {@link JsonCreator} constructor with {@link JsonProperty} annotated arguments.
 */
@Value
@Builder(builderClassName = "JsonBuilder")                  // points on explicit defined empty class
@JsonDeserialize(builder = Entity.JsonBuilder.class)        // using that class for json deserializing
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Entity {

    String field;

    int severalWordsField;

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
