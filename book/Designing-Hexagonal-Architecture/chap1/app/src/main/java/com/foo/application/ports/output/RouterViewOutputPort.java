package com.foo.application.ports.output;

import com.foo.domain.Router;

import java.util.List;

public interface RouterViewOutputPort {

    List<Router> fetchRouters();
}
