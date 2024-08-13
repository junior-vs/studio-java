package com.foo.framework.adapters.input.stdin;


import com.foo.application.ports.input.RouterViewInputPort;
import com.foo.application.ports.input.RouterViewUseCase;
import com.foo.domain.Router;
import com.foo.domain.RouterType;
import com.foo.framework.adapters.output.file.RouterViewFileAdapter;

import java.util.List;

public class RouterViewCLIAdapter {

    RouterViewUseCase routerViewUseCase;

    public RouterViewCLIAdapter() {
        setAdapters();

    }

    public List<Router> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(
                Router.filterRouterByType(RouterType.valueOf(type)));
    }

    private void setAdapters() {
        this.routerViewUseCase = new RouterViewInputPort(RouterViewFileAdapter.getInstance());
    }
}
