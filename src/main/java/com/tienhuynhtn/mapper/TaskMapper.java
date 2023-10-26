package com.tienhuynhtn.mapper;

import com.tienhuynhtn.entity.TaskEntity;
import com.tienhuynhtn.response.TaskResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TaskMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskMapper.class);

    @Autowired
    private ModelMapper modelMapper;

    public TaskResponse convertTaskEntityToTaskResponse(TaskEntity taskEntity) {
        return Objects.isNull(taskEntity)
                ? null
                : modelMapper.map(taskEntity, TaskResponse.class);
    }
}
