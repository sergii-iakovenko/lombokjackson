package example;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * The {@link Entity} version with multiple constructors.
 */
@Value
@AllArgsConstructor  // if an explicit constructor exists, @Value doesn't create all args constructor. It repairs this.
@Builder(builderClassName = "JsonBuilder")
@JsonDeserialize(builder = MultipleConstructorsEntity.JsonBuilder.class)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MultipleConstructorsEntity {

    String field;

    int severalWordsField;

    /**
     * Second, non all args constructor.
     */
    MultipleConstructorsEntity(String field) {
        this.field = field;
        this.severalWordsField = 42;
    }

    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class JsonBuilder {
    }
}
