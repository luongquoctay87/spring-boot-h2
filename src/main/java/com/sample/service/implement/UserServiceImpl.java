package com.sample.service.implement;

import com.sample.dto.request.UserCreationRequest;
import com.sample.dto.request.UserUpdateRequest;
import com.sample.dto.response.UserDetailResponse;
import com.sample.exception.ResourceNotFoundException;
import com.sample.model.Status;
import com.sample.model.User;
import com.sample.repository.UserRepository;
import com.sample.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public int addUser(UserCreationRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .status(Status.valueOf(request.getStatus().toUpperCase()))
                .build();
        userRepository.save(user);

        log.info("User created!, id={}", user.getId());

        return user.getId();
    }

    @Override
    public int updateUser(UserUpdateRequest request) {
        User user = getById(request.getId());

        if(StringUtils.hasLength(request.getFirstName())) {
            user.setFirstName(request.getFirstName());
        }
        if(StringUtils.hasLength(request.getLastName())) {
            user.setLastName(request.getLastName());
        }
        if(StringUtils.hasLength(request.getPhone())) {
            user.setPhone(request.getPhone());
        }
        if(StringUtils.hasLength(request.getEmail())) {
            user.setEmail(request.getEmail());
        }
        user.setStatus(Status.valueOf(request.getStatus().toUpperCase()));

        userRepository.save(user);

        log.info("User updated!, id={}", user.getId());

        return request.getId();
    }

    @Override
    public int changeStatus(int userId, Status status) {
        User user = getById(userId);
        user.setStatus(status);
        userRepository.save(user);

        log.info("Status changed!, id={}", user.getId());

        return user.getId();
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
        log.info("User deleted!, id={}", userId);
    }

    @Override
    public UserDetailResponse getUserDetail(int userId) {
        User user = getById(userId);

        log.info("Find out user!, id={}", user.getId());

        return UserDetailResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .status(user.getStatus())
                .build();
    }

    @Override
    public List<UserDetailResponse> getAllUsers() {
        List<User> userList = userRepository.findAll();

        log.info("Get user list successfully, size={} items", userList.size());

        return userList.stream().map(user -> UserDetailResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .status(user.getStatus())
                .build())
                .toList();
    }

    private User getById(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Not found user"));
    }
}
