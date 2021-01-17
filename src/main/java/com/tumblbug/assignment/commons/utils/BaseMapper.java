package com.tumblbug.assignment.commons.utils;

import lombok.AccessLevel;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.GenericTypeResolver;

@SuppressWarnings(value = "unchecked")
public abstract class BaseMapper<S, D> {

    protected final ModelMapper mapper;

    @Getter(AccessLevel.PROTECTED)
    private final Class<S> sourceTypeClass;

    @Getter(AccessLevel.PROTECTED)
    private final Class<D> destinationTypeClass;

    public BaseMapper() {
        this.mapper = new ModelMapper();
        this.mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        Class[] classes = GenericTypeResolver.resolveTypeArguments(getClass(), BaseMapper.class);
        this.sourceTypeClass = classes[0];
        this.destinationTypeClass = classes[1];

        this.configMap(this.mapper.createTypeMap(sourceTypeClass, destinationTypeClass));
        this.configMapReverse(this.mapper.createTypeMap(destinationTypeClass, sourceTypeClass));
    }

    protected void configMap(TypeMap<S, D> typeMap) {}

    protected void configMapReverse(TypeMap<D, S> typeMap) {}

    public D map(S source) {
        return mapper.map(source, destinationTypeClass);
    }

    public S mapReverse(D destination) {
        return mapper.map(destination, sourceTypeClass);
    }

}