package dtos;

import java.io.Serializable;

public class Request implements Serializable {
    private int command;
    private String descriptor;

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    private byte[] data;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public Request(int command, String descriptor) {
        this.command = command;
        this.descriptor = descriptor;
    }
}
