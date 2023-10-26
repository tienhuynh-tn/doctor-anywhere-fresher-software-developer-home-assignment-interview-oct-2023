package com.tienhuynhtn.controller;

import com.tienhuynhtn.basemodels.BasePaginationRequest;
import com.tienhuynhtn.basemodels.BaseResponse;
import com.tienhuynhtn.handler.ResponseBuilder;
import com.tienhuynhtn.response.TaskResponse;
import com.tienhuynhtn.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Task", description = "Task API")
@RestController
@RequestMapping(value = "/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @SecurityRequirements
    @GetMapping
    public ResponseEntity<BaseResponse<List<TaskResponse>>> getAllSensitiveWords(
            @ModelAttribute BasePaginationRequest basePaginationRequest) {

        List<TaskResponse> responses = taskService.getAllTasks(basePaginationRequest);

        if (responses.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseBuilder.generateResponse(
                "Get list of all tasks successfully!",
                HttpStatus.OK,
                responses
        );
    }

    @SecurityRequirements
    @GetMapping(value = "/count")
    public ResponseEntity<BaseResponse<Integer>> count() {

        int response = taskService.count();

        return ResponseBuilder.generateResponse(
                "Count list of all tasks successfully!",
                HttpStatus.OK,
                response
        );
    }
}
