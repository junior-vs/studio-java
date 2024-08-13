package com.foo.domain;

public class RouterId {

    private String id;

    public RouterId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "RouterId{" +
                "id='" + id + '\'' +
                '}';
    }
}
