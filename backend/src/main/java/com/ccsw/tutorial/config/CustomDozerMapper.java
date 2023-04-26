package com.ccsw.tutorial.config;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class CustomDozerMapper extends DozerBeanMapper {

    public <S, D> List<D> mapList(List<S> sourceList, Class<D> destinationClass) {
        return sourceList.stream().map(source -> map(source, destinationClass)).collect(Collectors.toList());
    }

    public <S, D> Page<D> mapPage(Page<S> sourcePage, Class<D> destinationClass) {
        List<D> destinationList = mapList(sourcePage.getContent(), destinationClass);
        return new PageImpl<>(destinationList, sourcePage.getPageable(), sourcePage.getTotalElements());
    }
}
