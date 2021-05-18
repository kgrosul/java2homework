package k.grosul.savefromhackers;

import com.fasterxml.jackson.databind.JavaType;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public final class UserAPI extends BaseAPI {
    private final CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    public List<UserDto> getAll() {
        JavaType userList = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, UserDto.class);
        try {
            return getResponse(HttpGet.METHOD_NAME, "allUsers", userList, List.of());
        } catch (IOException e) {
            return new LinkedList<>();
        }
    }

    public void AddUser(User user) throws IOException{
        String parameters = String.format("addUser?name=%s&" +
                        "birth=%s&" +
                        "passportNumber=%s&" +
                        "passportGiven=%s&" +
                        "passportRegistration=%s",
                user.getName().strip().replace(' ', '$'),
                user.getBirth().strip().replace(' ', '$'),
                user.getPassportNumber().strip().replace(' ', '$'),
                user.getPassportGiven().strip().replace(' ', '$'),
                user.getPassportRegistration().strip().replace(' ', '$'));
        getResponse(HttpGet.METHOD_NAME, parameters);
    }


    @Override
    protected HttpClient getClient() {
        return httpClient;
    }
}