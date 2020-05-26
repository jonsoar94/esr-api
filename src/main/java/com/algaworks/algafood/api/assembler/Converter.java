package com.algaworks.algafood.api.assembler;

import java.util.List;

/**
 *
 * @param <T> The domain class
 * @param <S> The DTO class
 * @param <U> The Input DTO class
 */
public interface Converter<T, S, U> {

    public T toDomain(U inputDto);

    public S toDto(T domain);

    public List<S> toCollectionDTO(List<T> list);
}
