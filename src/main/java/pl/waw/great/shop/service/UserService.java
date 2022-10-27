package pl.waw.great.shop.service;

import org.springframework.stereotype.Service;
import pl.waw.great.shop.exception.UserWithGivenNameExistsException;
import pl.waw.great.shop.model.dto.UserDto;
import pl.waw.great.shop.model.mapper.UserMapper;
import pl.waw.great.shop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto create(UserDto userDto) {
        this.userRepository.findUserByTitle(userDto.getName())
                .ifPresent(a -> {
                    throw new UserWithGivenNameExistsException(userDto.getName());
                });

        return userMapper.userToDto(this.userRepository.create(userMapper.dtoToUser(userDto)));
    }

    public boolean delete(String name) {
        return this.userRepository.delete(name);
    }
}
