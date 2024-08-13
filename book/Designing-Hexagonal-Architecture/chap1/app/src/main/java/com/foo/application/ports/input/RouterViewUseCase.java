package com.foo.application.ports.input;

import com.foo.domain.Router;
import java.util.function.Predicate;
import java.util.List;

public interface RouterViewUseCase {


    List<Router> getRouters(Predicate<Router> filter);
}
