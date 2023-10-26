package com.tienhuynhtn.controller;

import com.tienhuynhtn.basemodels.BaseResponse;
import com.tienhuynhtn.dto.TokenDTO;
import com.tienhuynhtn.handler.ResponseBuilder;
import com.tienhuynhtn.request.AuthenticateRequest;
import com.tienhuynhtn.service.AuthenticateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "Authentication", description = "Authenticate API")
@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticateController {

    @Autowired
    private AuthenticateService authenticateService;

    @Operation(summary = "Authenticate an account", description = "[Unauthenticated] Authenticate an account by username and password")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Authenticated Request", required = true, content = @Content(
            examples = {
                    @ExampleObject(name = "User Authenticate Request", value = "{\n" +
                            "  \"username\": \"user\",\n" +
                            "  \"password\": \"tienhuynh-tn-user\"" +
                            "}")
            }))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully!", content = @Content(examples = {
                    @ExampleObject(value = "{\n" +
                            "    \"accessToken\": \"token\",\n" +
                            "    \"tokenType\": \"Bearer\"" +
                            "}")})),
            @ApiResponse(responseCode = "400", description = "Bad Request!", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Resource Not Found!", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error!", content = @Content(schema = @Schema(hidden = true)))
    })
    @SecurityRequirements
    @PostMapping
    public ResponseEntity<BaseResponse<TokenDTO>> authenticate(
            HttpServletRequest request,
            @RequestBody AuthenticateRequest authenticateRequest) {

        TokenDTO token = authenticateService.authenticate(request, authenticateRequest);

        return ResponseBuilder.generateResponse(
                "Authenticate successfully!",
                HttpStatus.OK,
                token
        );
    }
}
