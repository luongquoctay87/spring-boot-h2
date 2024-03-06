package com.sample.service;

import com.sample.dto.request.UserCreationRequest;
import com.sample.dto.request.UserUpdateRequest;
import com.sample.dto.response.UserDetailResponse;
import com.sample.model.Status;

import java.util.List;

public interface UserService {

    int addUser(UserCreationRequest request);

    int updateUser(UserUpdateRequest request);

    int changeStatus(int userId, Status status);

    void deleteUser(int userId);

    UserDetailResponse getUserDetail(int userId);

    List<UserDetailResponse> getAllUsers();
}
