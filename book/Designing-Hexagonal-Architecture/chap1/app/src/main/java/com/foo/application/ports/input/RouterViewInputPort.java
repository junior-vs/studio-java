package com.foo.application.ports.input;


import com.foo.application.ports.output.RouterViewOutputPort;
import com.foo.domain.Router;


import java.util.List;
import java.util.function.Predicate;

public class RouterViewInputPort implements RouterViewUseCase {

    private RouterViewOutputPort routerListOutputPort;

    public RouterViewInputPort(RouterViewOutputPort routerViewOutputPort) {
        this.routerListOutputPort = routerViewOutputPort;
    }

    @Override
    public List<Router> getRouters(Predicate<Router> filter) {
        var routers = routerListOutputPort.fetchRouters();
        return Router.retrieveRouter(routers, filter);
    }


}