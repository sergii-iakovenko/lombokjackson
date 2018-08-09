package example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Data
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, property="clazz")
class A {
    final int a;
}

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@EqualsAndHashCode(callSuper = true, exclude = "b")
class B extends A {

    @JsonIgnore
    String b;

    long someField;

    B(int a, String b, long someField) {
        super(a);
        this.b = b;
        this.someField = someField;
    }
}

/**
 * Complex {@link Entity}, with all serializing features.
 */
@Value
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(builder = ComplexEntity.JsonBuilder.class)
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
     * All args constructor.
     */
    @Builder(builderClassName = "JsonBuilder")
    public ComplexEntity(int a, String b, long someField, String superCustomizableProperty, int severalWordsField) {
        super(a, b, someField);
        this.field = superCustomizableProperty;
        this.severalWordsField = severalWordsField;
    }

    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class JsonBuilder {
    }
}
