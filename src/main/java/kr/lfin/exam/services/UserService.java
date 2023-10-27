package kr.lfin.exam.services;

import jakarta.transaction.Transactional;
import kr.lfin.exam.common.exception.DeletedResourceException;
import kr.lfin.exam.common.exception.DuplicateEmailException;
import kr.lfin.exam.common.exception.ResourceNotFoundException;
import kr.lfin.exam.domains.User;
import kr.lfin.exam.domains.UserVO;
import kr.lfin.exam.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public void insert(UserVO userVO) {
        User user = User.createUser(userVO.getEmail(), userVO.getPassword(), userVO.getName(), userVO.getPhone(), userVO.getDeleted());
        if (userRepository.existsByEmail(userVO.getEmail())) {
            throw new DuplicateEmailException();
        }
        userRepository.save(user);
    }



    @Transactional
    public void update(Long id, UserVO userVO) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if(user.getDeleted()){
                throw new DeletedResourceException();
            }
            if (userVO.getPassword() != null) {
                user.setPassword(User.bcryptHashPassword(userVO.getPassword()));
            }
            if (userVO.getName() != null) {
                user.setName(userVO.getName());
            }
            if (userVO.getPhone() != null) {
                user.setPhone(userVO.getPhone());
            }
            userRepository.save(user);

        } else {
            throw new ResourceNotFoundException();
        }
    }

    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        user.setDeleted(true);
        userRepository.save(user);
    }

}


