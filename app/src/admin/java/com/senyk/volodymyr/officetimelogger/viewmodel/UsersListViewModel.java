package com.senyk.volodymyr.officetimelogger.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.officetimelogger.helpers.ResourcesProvider;
import com.senyk.volodymyr.officetimelogger.mappers.dtoui.UsersMapper;
import com.senyk.volodymyr.officetimelogger.models.dto.UserDto;
import com.senyk.volodymyr.officetimelogger.models.ui.UserUi;
import com.senyk.volodymyr.officetimelogger.repository.TimeLoggerRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UsersListViewModel extends BaseViewModel {
    private final TimeLoggerRepository repository;
    private final UsersMapper usersMapper;
    private final ResourcesProvider resourcesProvider;

    private MutableLiveData<List<UserUi>> users = new MutableLiveData<>();

    public LiveData<List<UserUi>> getUsers() {
        return this.users;
    }

    public UsersListViewModel(TimeLoggerRepository repository, UsersMapper usersMapper, ResourcesProvider resourcesProvider) {
        super("UsersListVM");
        this.repository = repository;
        this.usersMapper = usersMapper;
        this.resourcesProvider = resourcesProvider;
    }

    public void loadUsers() {
        this.repository.getUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainSingleObserver<List<UserDto>>() {
                    @Override
                    public void onSuccess(List<UserDto> usersDtos) {
                        users.setValue(usersMapper.convertToUiList(usersDtos));
                    }
                });
    }

    public void addNewUser(String firstName, String lastName, String middleName) {
        this.repository.addUser(new UserDto(firstName, lastName, middleName))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainCompletableObserver() {
                    @Override
                    public void onComplete() {
                        loadUsers();
                    }
                });
    }

    public void resetPassword(int userId) {
        this.repository.resetPassword(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainCompletableObserver() {
                    @Override
                    public void onComplete() {
                        message.setValue(resourcesProvider.getPasswordSuccessfullyResetMessage());
                    }
                });
    }
}
