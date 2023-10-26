package com.tienhuynhtn.service;

import com.tienhuynhtn.basemodels.BasePaginationRequest;
import com.tienhuynhtn.response.TaskResponse;

import java.util.List;

public interface TaskService {
    List<TaskResponse> getAllTasks(BasePaginationRequest basePaginationRequest);

    int count();
}
