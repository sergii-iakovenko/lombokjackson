package example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Simple mutable DTO class.
 *
 * If transfer representation doesn't matter, it's only needs accessors and the default constructor.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultEntity {

    private String field;

    private int severalWordsField;
}
