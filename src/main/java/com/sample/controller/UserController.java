package com.sample.controller;

import com.sample.dto.request.UserCreationRequest;
import com.sample.dto.request.UserUpdateRequest;
import com.sample.dto.response.UserDetailResponse;
import com.sample.exception.InvalidDataException;
import com.sample.model.Status;
import com.sample.service.UserService;
import com.sample.util.ValueOfEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserController {

    private final UserService userService;

    @Operation(summary = "Add new user", description = "Return user ID")
    @PostMapping(path = "/add", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public long createUser(@Valid @RequestBody UserCreationRequest request) {
        try {
            return userService.addUser(request);
        } catch (Exception e) {
            throw new InvalidDataException("Update user unsuccessful, Please try again");
        }
    }

    @Operation(summary = "Update user", description = "Return message")
    @PutMapping(path = "/upd", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(ACCEPTED)
    public void updateUser(@Valid @RequestBody UserUpdateRequest request) {
        try {
            userService.updateUser(request);
        } catch (Exception e) {
            throw new InvalidDataException("Update user unsuccessful, Please try again");
        }
    }

    @Operation(summary = "Change user status", description = "Return message")
    @PatchMapping(path = "/user/{id}/change-status", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(ACCEPTED)
    public void changeStatus(@PathVariable int id,
                             @RequestParam @ValueOfEnum(message = "status must be any of enum (active,inactive,none)", enumClass = Status.class) String status) {
        try {
            userService.changeStatus(id, Status.valueOf(status.toUpperCase()));
        } catch (Exception e) {
            throw new InvalidDataException("Change status unsuccessful, Please try again");
        }
    }

    @Operation(summary = "Delete user", description = "Return no content")
    @DeleteMapping(path = "/del/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable("id") @Min(1) int id) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            throw new InvalidDataException("Delete user unsuccessful, Please try again");
        }
    }

    @Operation(summary = "Get user detail", description = "Return user detail")
    @GetMapping(path = "/user/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public UserDetailResponse getUser(@PathVariable("id") @Min(1) int id) {
        try {
            return userService.getUserDetail(id);
        } catch (Exception e) {
            throw new InvalidDataException("Delete user unsuccessful, Please try again");
        }
    }

    @Operation(summary = "Get all users", description = "Return list of users")
    @GetMapping(path = "/list", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public List<UserDetailResponse> getUsers() {
        try {
            return userService.getAllUsers();
        } catch (Exception e) {
            throw new InvalidDataException("Delete user unsuccessful, Please try again");
        }
    }

}
