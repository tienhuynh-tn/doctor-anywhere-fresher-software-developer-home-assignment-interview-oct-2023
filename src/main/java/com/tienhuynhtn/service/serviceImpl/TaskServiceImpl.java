package com.tienhuynhtn.service.serviceImpl;

import com.tienhuynhtn.basemodels.BasePaginationRequest;
import com.tienhuynhtn.constant.DefaultSortPropertyConstant;
import com.tienhuynhtn.entity.TaskEntity;
import com.tienhuynhtn.mapper.TaskMapper;
import com.tienhuynhtn.repository.TaskRepository;
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

import java.util.List;
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
}
