package com.oxygensend.atipiera.mapper;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface Mapper<T, R> {
    R map(T t) throws Exception;

    List<R> map(Collection<T> t) throws Exception;
}
