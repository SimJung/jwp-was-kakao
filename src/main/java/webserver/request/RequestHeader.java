package webserver.request;

import webserver.Cookie;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RequestHeader {
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String HOST = "Host";
    public static final String COOKIE = "Cookie";

    private Map<String, Object> header;
    private Map<String, String> parameters;

    private RequestHeader(Map<String, Object> header, Map<String, String> parameters) {
        this.header = header;
        this.parameters = parameters;
    }

    public static RequestHeader of(Map<String, Object> header, Map<String, String> params) {
        return new RequestHeader(header, params);
    }

    public static RequestHeader empty() {
        return new RequestHeader(new HashMap<>(), new HashMap<>());
    }

    public void addHeader(String key, Object value) {
        header.put(key, value);
    }

    public Integer getContentLength() {
        return Optional.ofNullable(findInHeader(CONTENT_LENGTH))
                .map(Integer::parseInt)
                .orElse(null);
    }

    private String findInHeader(String key) {
        if (header.containsKey(key)) {
            return (String) header.get(key);
        }
        return null;
    }

    public String getHost() {
        return findInHeader(HOST);
    }

    public String getParameter(String key) {
        if (parameters.containsKey(key)) {
            return parameters.get(key);
        }
        return null;
    }

    public void setParameters(Map<String, String> params) {
        this.parameters = params;
    }

    public void addParameter(String key, String value) {
        this.parameters.put(key, value);
    }

    public List<Cookie> getCookies() {
        if (header.containsKey(COOKIE)) {
            return (List<Cookie>) header.get(COOKIE);
        }
        return Collections.emptyList();
    }

    public String getHeader(String key) {
        return findInHeader(key);
    }

}
