package com.tienhuynhtn.service.serviceImpl;

import com.tienhuynhtn.basemodels.BasePaginationRequest;
import com.tienhuynhtn.constant.DefaultSortPropertyConstant;
import com.tienhuynhtn.entity.TaskEntity;
import com.tienhuynhtn.enums.DoctorAnywhereErrorCodeEnum;
import com.tienhuynhtn.exception.NotFoundException;
import com.tienhuynhtn.mapper.TaskMapper;
import com.tienhuynhtn.repository.TaskRepository;
import com.tienhuynhtn.request.TaskRequest;
import com.tienhuynhtn.response.TaskResponse;
import com.tienhuynhtn.service.TaskService;
import com.tienhuynhtn.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<TaskResponse> getAllTasks(BasePaginationRequest basePaginationRequest) {
        Sort.Order defaultSortOrder = new Sort.Order(Sort.Direction.ASC, DefaultSortPropertyConstant.ID);
        Pageable pageable = PaginationUtil.getPageable(basePaginationRequest, defaultSortOrder);

        Page<TaskEntity> sensitiveWordEntities = taskRepository.findAll(pageable);

        return sensitiveWordEntities.getContent().stream()
                .map(entity -> taskMapper.convertTaskEntityToTaskResponse(entity))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return (int) taskRepository.count();
    }

    @Override
    public TaskResponse getById(Long id) {
        Optional<TaskEntity> taskEntity = taskRepository.findById(id);
        if (!taskEntity.isPresent())
            throw new NotFoundException(DoctorAnywhereErrorCodeEnum.NOT_FOUND_TASK, DoctorAnywhereErrorCodeEnum.NOT_FOUND_TASK.getMessage());

        return taskMapper.convertTaskEntityToTaskResponse(taskEntity.get());
    }

    @Override
    public TaskResponse updateById(Long id, TaskRequest taskRequest) {
        Optional<TaskEntity> taskEntity = taskRepository.findById(id);
        if (!taskEntity.isPresent())
            throw new NotFoundException(DoctorAnywhereErrorCodeEnum.NOT_FOUND_TASK, DoctorAnywhereErrorCodeEnum.NOT_FOUND_TASK.getMessage());

        taskEntity.get().setTitle(taskRequest.getTitle());
        taskEntity.get().setDescription(taskRequest.getDescription());
        taskEntity.get().setCompleted(taskRequest.getCompleted());
        taskRepository.save(taskEntity.get());

        return taskMapper.convertTaskEntityToTaskResponse(taskEntity.get());
    }

    @Override
    public TaskResponse patchUpdateById(Long id, Map<String, Object> fields) {
        Optional<TaskEntity> taskEntity = taskRepository.findById(id);
        if (!taskEntity.isPresent())
            throw new NotFoundException(DoctorAnywhereErrorCodeEnum.NOT_FOUND_TASK, DoctorAnywhereErrorCodeEnum.NOT_FOUND_TASK.getMessage());

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(TaskEntity.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, taskEntity.get(), value);
        });

        TaskEntity result = taskRepository.save(taskEntity.get());

        return taskMapper.convertTaskEntityToTaskResponse(result);
    }
}
