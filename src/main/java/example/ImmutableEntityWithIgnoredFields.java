package example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

/**
 * Immutable entity with ignored fields.
 */
@Value
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(Include.NON_NULL)
public class ImmutableEntityWithIgnoredFields {

    String field;

    @JsonIgnore
    int severalWordsField;

    @JsonCreator
    public ImmutableEntityWithIgnoredFields(String field) {
        this.field = field;

        this.severalWordsField = 42;
    }
}
