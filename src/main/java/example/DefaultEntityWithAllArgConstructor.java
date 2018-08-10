package example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Simple mutable DTO entity with all arg constructor only.
 */
@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class DefaultEntityWithAllArgConstructor {

    private String field;

    private int severalWordsField;
}
