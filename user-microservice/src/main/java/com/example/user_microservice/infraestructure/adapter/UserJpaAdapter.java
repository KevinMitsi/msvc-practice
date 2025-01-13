package com.example.user_microservice.infraestructure.adapter;

import com.example.user_microservice.domain.persistence.IUserPersistencePort;
import com.example.user_microservice.infraestructure.entity.UserEntity;
import com.example.user_microservice.infraestructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<UserEntity> getAll() {
        return userRepository.findAll(PageRequest.of(0,10, Sort.by(Sort.Direction.ASC, "id")));
    }

    @Override
    @Transactional
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public List<UserEntity> getAllIn(List<Long> ids) {
        return userRepository.findAllById(ids);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
