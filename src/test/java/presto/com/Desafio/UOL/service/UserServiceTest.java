package presto.com.Desafio.UOL.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import presto.com.Desafio.UOL.domain.user.UserEntity;
import presto.com.Desafio.UOL.domain.user.UserGroups;
import presto.com.Desafio.UOL.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Nested
    class getAll{

        @Test
        void shouldListAllUsersWhenPageIsReceived(){
//            Arrange
            List<UserEntity> userList = Arrays.asList(
                new UserEntity(1L, "User 1", "email@email.com", "1239393939", "Codename 1", UserGroups.LIGADAJUSTICA),
                new UserEntity(2L, "User 2", "email2@email.com", "1239393939", "Codename 2", UserGroups.LIGADAJUSTICA)
            );

            Pageable pageable = PageRequest.of(0, 2);

            Page<UserEntity> page = new PageImpl<>(userList);
            doReturn(page).when(userRepository).findAll(pageable);

//            Act
            var output = userService.getAll(pageable);

//            Assert
            assertEquals(2, output.getTotalElements());
            assertEquals("User 1", output.getContent().get(0).getName());
            assertEquals("User 2", output.getContent().get(1).getName());
        }


    }
}