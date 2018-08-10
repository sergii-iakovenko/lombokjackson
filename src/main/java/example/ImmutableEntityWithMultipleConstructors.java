package example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Immutable entity with multiple constructors.
 */
@Value
@AllArgsConstructor  // if an explicit constructor exists, @Value doesn't create all args constructor. It repairs this.
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(Include.NON_NULL)
public class ImmutableEntityWithMultipleConstructors {

    String field;

    int severalWordsField;

    /**
     * Second, non all args constructor.
     */
    public ImmutableEntityWithMultipleConstructors(String field) {
        this.field = field;
        this.severalWordsField = 42;
    }
}
