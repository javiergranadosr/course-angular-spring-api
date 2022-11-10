package com.jgr.api.clients.mappers;

public interface IMapper<DTO, E> {
    E mapper(DTO in);
}
