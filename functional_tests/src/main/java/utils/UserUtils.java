package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import dto.User;
import constants.TestConstants;


// holds utils to help call Category API and extracting Category details
public class UserUtils {
    private static final Logger LOG = LoggerFactory.getLogger(UserUtils.class);

    // return path for users api
    public static String getUsersApiPath() {
        String path = RestUtils.getBaseUrl() + TestConstants.USERS_PATH;
        LOG.info("Users API path :" + path);
        return path;
    }

    // return path for user by ID api
    public static String getUserByIdApiPath(long userId) {
        String path = getUsersApiPath() + "/" + userId;
        LOG.info("User By ID API path :" + path);
        return path;
    }

    // calls get Users API, and returns the JSON Response
    public static Response getUsers() {
        String path = getUsersApiPath();
        return RestUtils.getRequest(path);
    }

    // converts JSON response into DTO class object
    public static List<User> extractUsers(Response usersResponse) {
        List<User> users = JsonUtils.getListOfDto(usersResponse.asString(), User.class);
        LOG.debug("Extracted Users :" + users);
        return users;
    }

    // calls get User by ID API, and returns the JSON Response
    public static Response getUserById(long userId) {
        String path = getUserByIdApiPath(userId);
        return RestUtils.getRequest(path);
    }

    // converts JSON response into DTO class object
    public static User extractUser(Response usersResponse) {
        User user = JsonUtils.getDto(usersResponse.asString(), User.class);
        LOG.debug("Extracted Users :" + user);
        return user;
    }

    // calls get User by ID API, and returns the JSON Response
    public static Response postUser(User user) throws JsonProcessingException {
        String path = getUsersApiPath();
        return RestUtils.postRequest(path, JsonUtils.getJson(user));
    }
}
