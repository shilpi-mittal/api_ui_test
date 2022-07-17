package utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// holds utils to help deal with json responses
public final class JsonUtils {
    private static final Logger LOG = LoggerFactory.getLogger(JsonUtils.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    // converts JSON Response string into DTO class object
    public static <T> T getDto(String json, Class<T> classType) {
        T dto = null;
        try {
            dto = objectMapper.readValue(json, classType);
        } catch (JsonParseException e) {
            LOG.error("Unable to parse jsonString to dto. Invalid type content: ", e);
        } catch (JsonMappingException e) {
            LOG.error("JsonString does not match with Dto: ", e);
        } catch (IOException e) {
            LOG.error("Unable to parse string to dto: ", e);
        }
        return dto;
    }

    // converts JSON Response string into List of DTO class objects
    public static <T> List<T> getListOfDto(String json, Class<T> classType) {
        List<T> dto = null;
        try {
            dto = objectMapper.readValue(json, new TypeReference<List<T>>(){});

        } catch (JsonParseException e) {
            LOG.error("Unable to parse jsonString to dto. Invalid type content: ", e);
        } catch (JsonMappingException e) {
            LOG.error("JsonString does not match with Dto: ", e);
        } catch (IOException e) {
            LOG.error("Unable to parse string to dto: ", e);
        }
        return dto;
    }

    public static String getJson(User user) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);

    }
}
