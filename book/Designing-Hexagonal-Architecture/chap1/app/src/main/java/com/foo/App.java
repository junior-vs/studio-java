package com.foo;

import com.foo.framework.adapter.input.stdin.RouterViewCLIAdapter;
import com.foo.framework.adapters.input.stdin.RouterViewCLIAdapter;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

public class App {


    public static void main(String... args) {
        var cli = new RouterViewCLIAdapter();
        var type = "CORE";
        System.out.println(cli.obtainRelatedRouters(type));
    }
}
