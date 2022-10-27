package pl.waw.great.shop.service;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import pl.waw.great.shop.exception.UserWithGivenNameExistsException;
import pl.waw.great.shop.model.User;
import pl.waw.great.shop.model.dto.UserDto;
import pl.waw.great.shop.model.mapper.UserMapper;
import pl.waw.great.shop.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceTest {

    private static final String NAME = "Mikolaj";
    private User user;
    private UserDto userDto;

    UserRepository userRepository = mock(UserRepository.class);

    @Spy
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @InjectMocks
    UserService userService;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @BeforeEach
    void setUp() {
        this.user = new User(NAME);
        this.userDto = new UserDto(NAME);
    }

    @Test
    void create() {
        when(userRepository.create(any())).thenReturn(this.user);

        UserDto savedDto = this.userService.create(this.userDto);

        assertEquals(savedDto.getName(), this.userDto.getName());
    }

    @Test
    void createUserWithExistingNameShouldThrowException() {
        when(userRepository.findUserByTitle(anyString())).thenReturn(Optional.of(this.user));

        Assertions.assertThrows(UserWithGivenNameExistsException.class, () ->{
            this.userService.create(this.userDto);
        });
    }

    @Test
    void delete() {
        when(userRepository.delete(anyString())).thenReturn(true);
        boolean isDeleted = this.userService.delete(NAME);
        assertTrue(isDeleted);
    }
}