package kr.lfin.exam.services;

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
        User user = User.createUser(userVO.getEmail(), userVO.getPassword(), userVO.getName(), userVO.getPhone());
        userRepository.save(user);
    }

    public void update(Long id, UserVO userVO) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (userVO.getPassword() != null) {
                user.setPassword(userVO.getPassword());
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
}


