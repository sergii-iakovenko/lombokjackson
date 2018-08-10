package example;

import lombok.Data;

/**
 * Simple mutable DTO entity.
 * <p>
 * If transfer representation doesn't matter, it only needs getters/setters and the default constructor.
 */
@Data
public class DefaultEntity {

    private String field;

    private int severalWordsField;
}
