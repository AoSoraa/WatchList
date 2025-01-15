package cz.osu.fladlu.be_watchlist.service.impl;

import cz.osu.fladlu.be_watchlist.model.dto.user.UserCreateDTO;
import cz.osu.fladlu.be_watchlist.model.dto.user.UserDTO;
import cz.osu.fladlu.be_watchlist.model.entity.User;
import cz.osu.fladlu.be_watchlist.repository.UserRepository;
import cz.osu.fladlu.be_watchlist.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }
    @Override
    public UserDTO getUserById(Long id) {
        return modelMapper.map(userRepository.findById(id), UserDTO.class);
    }

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(user -> modelMapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        return modelMapper.map(userRepository.save(modelMapper.map(userCreateDTO, User.class)), UserDTO.class);
    }

    @Override
    public UserDTO updateUser(Long id,UserCreateDTO userDTO) {
        var userFromDb = userRepository.findById(id).orElseThrow( () -> new RuntimeException("User not found"));
        modelMapper.map(userDTO, userFromDb);
        return modelMapper.map(userRepository.save(userFromDb), UserDTO.class);

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
