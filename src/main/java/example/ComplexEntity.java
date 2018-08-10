package example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

@Getter
@EqualsAndHashCode
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "clazz")
abstract class A {
    int a;

    A(int a) {
        this.a = a;
    }
}

@Getter
@EqualsAndHashCode(callSuper = true, exclude = "b")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
class B extends A {

    @JsonIgnore
    String b;

    long someField;

    B(int a, String b, long someField) {
        super(a);
        this.b = b;
        this.someField = someField;
    }

    @JsonCreator
    public B(int a, long someField) {
        this(a, null, someField);
    }
}

/**
 * Complex immutable entity, with all serializing features.
 */
@Value
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ComplexEntity extends B {

    @JsonProperty("super_customizable_property")
    String field;

    int severalWordsField;

    /**
     * Some other constructor.
     */
    ComplexEntity(String field, int severalWordsField) {
        this(42, null, 12, field, severalWordsField);
    }

    /**
     * Constructor used for deserialization.
     */
    @JsonCreator
    public ComplexEntity(int a, long someField, String field, int severalWordsField) {
        this(a, null, someField, field, severalWordsField);
    }

    /**
     * All args constructor.
     */
    @Builder
    public ComplexEntity(int a, String b, long someField, String field, int severalWordsField) {
        super(a, b, someField);
        this.field = field;
        this.severalWordsField = severalWordsField;
    }
}
