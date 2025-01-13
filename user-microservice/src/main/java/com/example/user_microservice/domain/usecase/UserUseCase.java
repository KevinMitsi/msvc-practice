package com.example.user_microservice.domain.usecase;

import com.example.user_microservice.domain.http.FeignConnectionPort;
import com.example.user_microservice.domain.persistence.IUserPersistencePort;
import com.example.user_microservice.domain.service.IUserServicePort;
import com.example.user_microservice.infraestructure.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort persistencePort;
    private final FeignConnectionPort feignConnectionPort;

    public UserUseCase(IUserPersistencePort persistencePort, FeignConnectionPort feignConnectionPort) {
        this.persistencePort = persistencePort;
        this.feignConnectionPort = feignConnectionPort;
    }

    @Override
    public Page<UserEntity> getAll() {
        return persistencePort.getAll();
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        if(existsByEmail(userEntity.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        return persistencePort.save(userEntity);

    }

    @Override
    public UserEntity findById(Long id) {
        return persistencePort.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        persistencePort.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return persistencePort.existsByEmail(email);
    }

    @Override
    public List<UserEntity> getAllIn(List<Long> ids) {
        return persistencePort.getAllIn(ids);
    }
    @Override
    public void delete(Long id) {
        persistencePort.delete(id);
        feignConnectionPort.removeDeletedStudent(id);
    }
}
