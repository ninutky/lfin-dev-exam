package kr.lfin.exam.services;

import kr.lfin.exam.domains.User;
import kr.lfin.exam.domains.UserVO;
import kr.lfin.exam.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
