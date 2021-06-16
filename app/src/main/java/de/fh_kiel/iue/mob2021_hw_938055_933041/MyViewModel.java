package de.fh_kiel.iue.mob2021_hw_938055_933041;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyViewModel extends ViewModel {

    private MutableLiveData<ArrayList<CryptoData>> users;
    public LiveData<ArrayList<CryptoData>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<ArrayList<CryptoData>>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
    }








}
