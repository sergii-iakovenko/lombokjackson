package example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

/**
 * Example of a json serializable entity through constructor.
 *
 * The {@link DefaultEntity} becomes immutable.
 * It is a {@link Value}, so fields are efficiently final private. The default Jackson serialization model not longer
 * works.
 *
 * Uses {@link JsonCreator} constructor.
 */
@Value
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BaselineEntity {

    String field;

    int severalWordsField;

    /**
     * Explicit all args constructor.
     *
     * Deserializing doesn't works without {@link JsonProperty}.
     */
    @JsonCreator
    public BaselineEntity(
            @JsonProperty("field") String field,
            @JsonProperty("several_words_field") int severalWordsField) {
        this.field = field;
        this.severalWordsField = severalWordsField;
    }
}
