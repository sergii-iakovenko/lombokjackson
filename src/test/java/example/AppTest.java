package example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

public class AppTest {

    final ObjectMapper mapper = new ObjectMapper();

    private <T> T pass(T entity, Class<T> clazz) throws IOException {
        String json = mapper.writeValueAsString(entity);
        System.out.println(clazz + "\n" + json + "\n");
        return mapper.readValue(json, clazz);
    }


    @Test
    public void defaultEntityTest() throws IOException {
        DefaultEntity entity = new DefaultEntity("hello", 42);
        assertEquals(entity, pass(entity, DefaultEntity.class));
    }

    @Test
    public void baselineEntityTest() throws IOException {
        BaselineEntity entity = new BaselineEntity("hello", 42);
        assertEquals(entity, pass(entity, BaselineEntity.class));
    }

    @Test
    public void entityTest() throws IOException {
        Entity entity = new Entity( "hello", 42);
        assertEquals(entity, pass(entity, Entity.class));
    }

    @Test
    public void customEntityTest() throws IOException {
        CustomizedEntity entity = new CustomizedEntity( "hello", 42);
        assertEquals(entity, pass(entity, CustomizedEntity.class));
    }

    @Test
    public void multipleConstructorsEntityTest() throws IOException {
        MultipleConstructorsEntity entity = new MultipleConstructorsEntity( "hello");
        assertEquals(entity, pass(entity, MultipleConstructorsEntity.class));
    }

    @Test
    public void complexEntityPositiveTest() throws IOException {
        ComplexEntity entity = new ComplexEntity( "hello", 42);
        assertEquals(entity, pass(entity, ComplexEntity.class));
    }

    @Test
    public void complexEntityNegativeTest() throws IOException {
        ComplexEntity entity = ComplexEntity.builder()
                .superCustomizableProperty("hello")
                .secondField("world")
                .severalWordsField(42)
                .build();
        ComplexEntity passedEntity = pass(entity, ComplexEntity.class);

        assertNotEquals(entity, passedEntity);
        assertNull(passedEntity.getSecondField());
    }
}
