package example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

/**
 * A variant of {@link ImmutableEntity} with custom field serializing name.
 */
@Value
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CustomizedEntity {

    // uses for serializing
    @JsonProperty("super_customizable_property")
    String field;

    int severalWordsField;
}
