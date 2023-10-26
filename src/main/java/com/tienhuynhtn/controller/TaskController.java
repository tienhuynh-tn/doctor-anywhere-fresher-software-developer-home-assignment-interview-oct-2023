package com.tienhuynhtn.controller;

import com.tienhuynhtn.basemodels.BasePaginationRequest;
import com.tienhuynhtn.basemodels.BaseResponse;
import com.tienhuynhtn.config.OpenApiConfig;
import com.tienhuynhtn.constant.RoleConstant;
import com.tienhuynhtn.handler.ResponseBuilder;
import com.tienhuynhtn.request.TaskRequest;
import com.tienhuynhtn.response.TaskResponse;
import com.tienhuynhtn.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Map;

@Tag(name = "Task", description = "Task API")
@RestController
@RequestMapping(value = "/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Get all tasks", description = "[User] Get the list of all tasks")
    @Parameters(value = {
            @Parameter(name = "sort",
                    in = ParameterIn.QUERY,
                    description = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending by id. Multiple sort criteria are supported.",
                    example ="[\"title,asc\", \"description,desc\"]",
                    array = @ArraySchema(schema = @Schema(implementation = String.class), maxItems = 3),
                    allowReserved = true)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully!", content = @Content(examples = {
                    @ExampleObject(value = "[\n" +
                            "    {\n" +
                            "      \"id\": 1,\n" +
                            "      \"title\": \"Task no 1\",\n" +
                            "      \"description\": \"Task no 1 description\",\n" +
                            "      \"completed\": false\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"id\": 2,\n" +
                            "      \"title\": \"Task no 2\",\n" +
                            "      \"description\": \"Task no 2 description\",\n" +
                            "      \"completed\": false\n" +
                            "    }\n" +
                            "  ]")})),
            @ApiResponse(responseCode = "400", description = "Bad Request!", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Unauthenticated!", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", description = "Unauthorized!", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Resource Not Found!", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error!", content = @Content(schema = @Schema(hidden = true)))
    })
    @SecurityRequirement(name = OpenApiConfig.securitySchemeName)
    @RolesAllowed({RoleConstant.USER})
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

    @Operation(summary = "Count list of all tasks", description = "[User] Count list of all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully!", content = @Content(examples = {@ExampleObject(value = "10")})),
            @ApiResponse(responseCode = "400", description = "Bad Request!", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Unauthenticated!", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", description = "Unauthorized!", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Resource Not Found!", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error!", content = @Content(schema = @Schema(hidden = true)))
    })
    @SecurityRequirement(name = OpenApiConfig.securitySchemeName)
    @RolesAllowed({RoleConstant.USER})
    @GetMapping(value = "/count")
    public ResponseEntity<BaseResponse<Integer>> count() {

        int response = taskService.count();

        return ResponseBuilder.generateResponse(
                "Count list of all tasks successfully!",
                HttpStatus.OK,
                response
        );
    }

    @SecurityRequirement(name = OpenApiConfig.securitySchemeName)
    @RolesAllowed({RoleConstant.USER})
    @GetMapping(value = "/{id}")
    public ResponseEntity<BaseResponse<TaskResponse>> getById(@PathVariable("id") Long id) {

        TaskResponse response = taskService.getById(id);

        return ResponseBuilder.generateResponse(
                "Get a task by id successfully!",
                HttpStatus.OK,
                response
        );
    }

    @SecurityRequirement(name = OpenApiConfig.securitySchemeName)
    @RolesAllowed({RoleConstant.USER})
    @PutMapping(value = "/{id}")
    public ResponseEntity<BaseResponse<TaskResponse>> updateById(
            @PathVariable("id") Long id,
            @RequestBody TaskRequest taskRequest) {

        TaskResponse response = taskService.updateById(id, taskRequest);

        return ResponseBuilder.generateResponse(
                "Update task by id successfully!",
                HttpStatus.OK,
                response
        );
    }

    @SecurityRequirement(name = OpenApiConfig.securitySchemeName)
    @RolesAllowed({RoleConstant.USER})
    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<TaskResponse>> patchUpdateById(
            @PathVariable("id") Long id,
            @RequestBody Map<String, Object> fields) {

        TaskResponse response = taskService.patchUpdateById(id, fields);

        return ResponseBuilder.generateResponse(
                "Patch update a task by id successfully!",
                HttpStatus.OK,
                response
        );
    }

    @SecurityRequirement(name = OpenApiConfig.securitySchemeName)
    @RolesAllowed({RoleConstant.USER})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {

        taskService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @SecurityRequirement(name = OpenApiConfig.securitySchemeName)
    @RolesAllowed({RoleConstant.USER})
    @PostMapping
    public ResponseEntity<BaseResponse<TaskResponse>> create(@RequestBody TaskRequest taskRequest) {

        TaskResponse response = taskService.create(taskRequest);

        return ResponseBuilder.generateResponse(
                "Create a task successfully!",
                HttpStatus.CREATED,
                response
        );
    }
}
