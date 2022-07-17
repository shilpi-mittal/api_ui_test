package api_tests.step_definitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import static org.junit.Assert.*;

import dto.User;
import utils.UserUtils;

public class UsersApiValidationSteps {
    private static final Logger LOG = LoggerFactory.getLogger(UsersApiValidationSteps.class);
    private User user;
    private User userToCreate;
    private List<User> users;
    private Response usersResponse;

    @When("I call get Users API")
    public void getUsers() {
        LOG.info("---- Calling Get all users ----");
        usersResponse = UserUtils.getUsers();
        if (usersResponse.getStatusCode() == HttpStatus.SC_OK) {
            users = UserUtils.extractUsers(usersResponse);
        }
    }

    @When("I call get User API with ID {int}")
    public void getUserById(int userId) {
        LOG.info("---- Validating user details for ID " + userId + "----");
        usersResponse = UserUtils.getUserById(userId);
        if (usersResponse.getStatusCode() == HttpStatus.SC_OK) {
            user = UserUtils.extractUser(usersResponse);
        }
    }

    @Then("I should see {string} message")
    public void validateResponseStatus(String status) {
        LOG.debug("API response status code received :" + usersResponse.getStatusCode());
//        String statusCode = status.split(" ")[0];
//        String statusLine = status.split(" ")[1].replace(" ", "");
//        assertEquals("\nStatus code returned is not as expected,\n", usersResponse.getStatusCode(), Integer.parseInt(statusCode));
        assertTrue("\nStatus Line returned is not as expected,\n", usersResponse.getStatusLine().contains(status));
    }

    @And("I should see {int} users in response")
    public void validateNumberOfUsersInResponse(int usersCount) {
        assertEquals("\nNumber of users returned is not as expected,\n", users.size(), usersCount);
    }

    @And("I should I see user ID as {int} in response")
    public void validateUserIdInResponse(long expectedUserId) {
        assertEquals("\nUser ID returned is not as expected,\n", user.getId(), (Long) expectedUserId);
    }


    @And("I should I see user name as {string} in response")
    public void iShouldISeeUserNameAsInResponse(String expectedUserName) {
        assertEquals("\nUser Name returned is not as expected,\n", user.getName(), expectedUserName);
    }

    @When("I post Users API with details as following:")
    public void postUser(DataTable table) throws JsonProcessingException {
        List<String> data = table.asLists(String.class).get(1);
        userToCreate = new User(data);
        usersResponse = UserUtils.postUser(userToCreate);
        if (usersResponse.getStatusCode() == HttpStatus.SC_CREATED) {
            user = UserUtils.extractUser(usersResponse);
        }
    }

    @And("I should see user details same as what was posted")
    public void validateCreatedUser() {
        assertEquals("\nUser Name is not as expected,\n", user.getName(), userToCreate.getName());
        assertEquals("\nUser Username is not as expected,\n", user.getUsername(), userToCreate.getUsername());
        assertEquals("\nUser Email is not as expected,\n", user.getEmail(), userToCreate.getEmail());
        assertEquals("\nUser Address Street is not as expected,\n", user.getAddress().getStreet(), userToCreate.getAddress().getStreet());
        assertEquals("\nUser Address Suite is not as expected,\n", user.getAddress().getSuite(), userToCreate.getAddress().getSuite());
        assertEquals("\nUser Address City is not as expected,\n", user.getAddress().getCity(), userToCreate.getAddress().getCity());
        assertEquals("\nUser Address Zipcode is not as expected,\n", user.getAddress().getZipcode(), userToCreate.getAddress().getZipcode());
        assertEquals("\nUser Address Geo Lat is not as expected,\n", user.getAddress().getGeo().getLat(), userToCreate.getAddress().getGeo().getLat());
        assertEquals("\nUser Address Geo Lng is not as expected,\n", user.getAddress().getGeo().getLng(), userToCreate.getAddress().getGeo().getLng());
        assertEquals("\nUser Phone is not as expected,\n", user.getPhone(), userToCreate.getPhone());
        assertEquals("\nUser Website is not as expected,\n", user.getWebsite(), userToCreate.getWebsite());
        assertEquals("\nUser Company Name is not as expected,\n", user.getCompany().getName(), userToCreate.getCompany().getName());
        assertEquals("\nUser Company CatchPhrase is not as expected,\n", user.getCompany().getCatchPhrase(), userToCreate.getCompany().getCatchPhrase());
        assertEquals("\nUser Company Bs is not as expected,\n", user.getCompany().getBs(), userToCreate.getCompany().getBs());
    }
}
