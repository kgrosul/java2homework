package k.grosul.savefromhackers;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.RequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Базовый класс для получения информации из API
 */
public abstract class BaseAPI {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static final String SERVER_ADDRESS = String.format("http://%s:%d", HOST, PORT);

    protected final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected abstract HttpClient getClient();

    protected <T> T getResponse(String method, String additionalUri, JavaType valueType, T defaultValue) throws IOException {
        return OBJECT_MAPPER.readValue(getContentFromUri(method, additionalUri), valueType);
    }

    protected HttpResponse getResponse(String method, String parameters) throws IOException {
        RequestBuilder requestBuilder = RequestBuilder.create(method).setUri(getFullUri(parameters));
        return getClient().execute(requestBuilder.build());
    }

    private String getFullUri(String additionalUri) {
        return String.format("%s/%s", SERVER_ADDRESS,additionalUri);
    }

    private InputStream getContentFromUri(String method, String additionalUri) throws IOException {
        RequestBuilder requestBuilder = RequestBuilder.create(method).setUri(getFullUri(additionalUri));
        HttpResponse response = getClient().execute(requestBuilder.build());
        HttpEntity entity = response.getEntity();
        return entity.getContent();
    }
}