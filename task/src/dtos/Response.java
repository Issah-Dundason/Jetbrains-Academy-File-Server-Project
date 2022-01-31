package dtos;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 55568743123l;
    private int statusCode;
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

    public void setData(byte[] bytes) {
        data = bytes;
    }
}
