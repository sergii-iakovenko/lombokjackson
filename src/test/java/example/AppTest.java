package example;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

public class AppTest {

    final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void entityTest() throws IOException {
        Entity entity = new Entity( "hello", 42);
        String json = mapper.writeValueAsString(entity);
        System.out.println(json);
        Entity read = mapper.readValue(json, Entity.class);
        assertEquals(entity, read);
    }

    @Test
    public void customEntityTest() throws IOException {
        CustomizedEntity entity = new CustomizedEntity( "hello", 42);
        String json = mapper.writeValueAsString(entity);
        System.out.println(json);
        CustomizedEntity read = mapper.readValue(json, CustomizedEntity.class);
        assertEquals(entity, read);
    }
    @Test
    public void multipleConstructorsEntityTest() throws IOException {
        MultipleConstructorsEntity entity = new MultipleConstructorsEntity( "hello");
        String json = mapper.writeValueAsString(entity);
        System.out.println(json);
        MultipleConstructorsEntity read = mapper.readValue(json, MultipleConstructorsEntity.class);
        assertEquals(entity, read);
    }
}
