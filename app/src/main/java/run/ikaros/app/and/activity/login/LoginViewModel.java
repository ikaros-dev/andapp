package run.ikaros.app.and.activity.login;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import run.ikaros.app.and.R;
import run.ikaros.app.and.activity.login.data.LoginRepository;
import run.ikaros.app.and.activity.login.data.Result;
import run.ikaros.app.and.api.User;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public Result<User> login(String baseUrl, String username, String password) {
        // can be launched in a separate asynchronous job
        Result<User> result = loginRepository.login(baseUrl, username, password);

        if (result instanceof Result.Success) {
            User data = ((Result.Success<User>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getNickname())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
        return result;
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}