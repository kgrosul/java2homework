package k.grosul.savefromhackers;

import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.databind.JavaType;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public final class UserAPI extends BaseAPI {
    private final CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    public List<UserDto> getAll() {
        JavaType userList = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, UserDto.class);
        try {
            return getResponse(HttpGet.METHOD_NAME, "user", userList, List.of());
        } catch (IOException e) {
            return new LinkedList<>();
        }
    }

    public void AddUser(User user) throws IOException, JSONException {
        var body = new JSONObject();
        body.put("name", user.getName());
        body.put("birth", user.getBirth());
        body.put("passportNumber", user.getPassportNumber());
        body.put("passportGiven", user.getPassportGiven());
        body.put("passportRegistration", user.getPassportRegistration());

        var request = new HttpPost(getFullUri("user"));
        request.setEntity(new StringEntity(body.toString()));
        request.setHeader("Accept", "application/json");
        request.addHeader("Content-type", "application/json");

        getClient().execute(request);

    }


    @Override
    protected HttpClient getClient() {
        return httpClient;
    }
}