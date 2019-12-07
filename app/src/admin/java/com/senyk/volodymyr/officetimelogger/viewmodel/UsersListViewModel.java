package com.senyk.volodymyr.officetimelogger.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.officetimelogger.helpers.ResourcesProvider;
import com.senyk.volodymyr.officetimelogger.mappers.dtoui.UsersMapper;
import com.senyk.volodymyr.officetimelogger.models.dto.UserDto;
import com.senyk.volodymyr.officetimelogger.models.ui.UserUi;
import com.senyk.volodymyr.officetimelogger.repository.TimeLoggerRepository;

import java.util.List;

public class UsersListViewModel extends BaseViewModel {
    private final TimeLoggerRepository repository;
    private final UsersMapper usersMapper;
    private final ResourcesProvider resourcesProvider;

    private MutableLiveData<List<UserUi>> users = new MutableLiveData<>();

    public UsersListViewModel(TimeLoggerRepository repository, UsersMapper usersMapper, ResourcesProvider resourcesProvider) {
        this.repository = repository;
        this.usersMapper = usersMapper;
        this.resourcesProvider = resourcesProvider;
    }

    public LiveData<List<UserUi>> getUsers() {
        return this.users;
    }

    public void loadUsers() {
        this.users.setValue(usersMapper.convertToUiList(repository.getUsers()));
    }

    public void addNewUser(String firstName, String lastName, String middleName) {
        int newNumber = repository.addUser(new UserDto(firstName, lastName, middleName));
        if (newNumber != -1) {
            loadUsers();
        } else {
            message.setValue("An error occurred");
        }
    }

    public void resetPassword(int userId) {
        if (repository.resetPassword(userId)) {
            message.setValue(resourcesProvider.getPasswordSuccessfullyResetMessage());
        } else {
            message.setValue("An error occurred");
        }
    }
}
