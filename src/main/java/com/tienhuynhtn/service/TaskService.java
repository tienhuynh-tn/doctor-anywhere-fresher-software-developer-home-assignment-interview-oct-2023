package com.tienhuynhtn.service;

import com.tienhuynhtn.basemodels.BasePaginationRequest;
import com.tienhuynhtn.request.TaskRequest;
import com.tienhuynhtn.response.TaskResponse;

import java.util.List;
import java.util.Map;

public interface TaskService {
    List<TaskResponse> getAllTasks(BasePaginationRequest basePaginationRequest);

    int count();

    TaskResponse getById(Long id);

    TaskResponse updateById(Long id, TaskRequest taskRequest);

    TaskResponse patchUpdateById(Long id, Map<String, Object> fields);

    void deleteById(Long id);
}
