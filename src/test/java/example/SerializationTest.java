package example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class SerializationTest {

    final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        mapper.registerModule(new ParameterNamesModule());
    }

    private <T> T pass(T entity, Class<T> clazz) throws IOException {
        String json = mapper.writeValueAsString(entity);
        System.out.println(clazz + "\n" + json + "\n");
        return mapper.readValue(json, clazz);
    }

    @Test
    public void customizedEntityTest() throws IOException {
        CustomizedEntity entity = new CustomizedEntity("hello", 42);
        assertEquals(entity, pass(entity, CustomizedEntity.class));
    }

    @Test
    public void customizedEntityAndNullsTest() throws IOException {
        CustomizedEntity entity = new CustomizedEntity(null, 42);
        assertEquals(entity, pass(entity, CustomizedEntity.class));
    }

    @Test
    public void defaultEntityTest() throws IOException {
        DefaultEntity entity = new DefaultEntity();
        entity.setField("hello");
        entity.setSeveralWordsField(42);
        assertEquals(entity, pass(entity, DefaultEntity.class));
    }

    @Test
    public void defaultEntityWithAllArgsTest() throws IOException {
        DefaultEntityWithAllArgConstructor entity = new DefaultEntityWithAllArgConstructor("hello", 42);
        assertEquals(entity, pass(entity, DefaultEntityWithAllArgConstructor.class));
    }

    @Test
    public void defaultEntityWithAllArgsAndNullsTest() throws IOException {
        DefaultEntityWithAllArgConstructor entity = new DefaultEntityWithAllArgConstructor(null, 42);
        assertEquals(entity, pass(entity, DefaultEntityWithAllArgConstructor.class));
    }

    @Test
    public void immutableEntityTest() throws IOException {
        ImmutableEntity entity = new ImmutableEntity("hello", 42);
        assertEquals(entity, pass(entity, ImmutableEntity.class));
    }

    @Test
    public void immutableEntityWithBuilderTest() throws IOException {
        ImmutableEntityWithBuilder entity = ImmutableEntityWithBuilder.builder().field("hello").severalWordsField(42).build();
        assertEquals(entity, pass(entity, ImmutableEntityWithBuilder.class));
    }

    @Test
    public void immutableEntityWithBuilderAndNullsTest() throws IOException {
        ImmutableEntityWithBuilder entity = ImmutableEntityWithBuilder.builder().severalWordsField(42).build();
        assertEquals(entity, pass(entity, ImmutableEntityWithBuilder.class));
    }

    @Test
    public void immutableEntityWithIgnoredFields() throws IOException {
        ImmutableEntityWithIgnoredFields entity = new ImmutableEntityWithIgnoredFields("hello");
        assertEquals(entity, pass(entity, ImmutableEntityWithIgnoredFields.class));
    }

    @Test
    public void immutableEntityWithIgnoredAndNullsFields() throws IOException {
        ImmutableEntityWithIgnoredFields entity = new ImmutableEntityWithIgnoredFields(null);
        assertEquals(entity, pass(entity, ImmutableEntityWithIgnoredFields.class));
    }

    @Test
    public void immutableEntityWithMultiConstructorsTest() throws IOException {
        ImmutableEntityWithMultipleConstructors entity = new ImmutableEntityWithMultipleConstructors("hello", 42);
        assertEquals(entity, pass(entity, ImmutableEntityWithMultipleConstructors.class));
    }

    @Test
    public void immutableEntityWithMultiConstructorsAndNullsTest() throws IOException {
        ImmutableEntityWithMultipleConstructors entity = new ImmutableEntityWithMultipleConstructors(null, 42);
        assertEquals(entity, pass(entity, ImmutableEntityWithMultipleConstructors.class));
    }

    @Test
    public void complexEntityWithoutIgnoredTest() throws IOException {
        ComplexEntity entity = new ComplexEntity("hello", 42);
        assertEquals(entity, pass(entity, ComplexEntity.class));
    }

    @Test
    public void complexEntityFullTest() throws IOException {
        ComplexEntity entity = ComplexEntity.builder()
                .a(42)
                .b("hello")
                .someField(12L)
                .field("world")
                .severalWordsField(42)
                .build();
        ComplexEntity passedEntity = pass(entity, ComplexEntity.class);

        assertEquals(entity, passedEntity);
        assertNull(passedEntity.getB());
    }
}
