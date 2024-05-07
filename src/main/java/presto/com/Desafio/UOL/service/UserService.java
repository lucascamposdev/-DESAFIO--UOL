package presto.com.Desafio.UOL.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import presto.com.Desafio.UOL.domain.user.UserEntity;
import presto.com.Desafio.UOL.domain.user.UserRequestDto;
import presto.com.Desafio.UOL.infra.CodenameHandler;
import presto.com.Desafio.UOL.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private CodenameHandler codenameHandler;

    public UserEntity register(UserRequestDto dto){
        var newUser = new UserEntity(dto);
        String codename = codenameHandler.getCodename(dto.getUsergroup());
        newUser.setCodename(codename);
        return this.repository.save(newUser);
    }

    public Page<UserEntity> getAll(Pageable page){
        return this.repository.findAll(page);
    }
}
