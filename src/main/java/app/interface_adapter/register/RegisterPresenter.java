package app.interface_adapter.register;

import app.use_case.register.RegisterOutputBoundary;
import app.interface_adapter.ViewManagerModel;

import app.use_case.register.RegisterOutputData;
import app.interface_adapter.create_event.CreateEventViewModel;

// TODO: GO STRAIGHT TO THE CREATE_EVENT VIEW

// import main.java.java.interface_adapter.login.LoginState;
// import main.java.java.interface_adapter.login.LoginViewModel

/**
 * The Presenter for the register Use Case
 */
public class RegisterPresenter implements RegisterOutputBoundary {
    private final RegisterViewModel registerViewModel;
    // private final LoginViewModel loginViewModel;
    private final CreateEventViewModel createEventViewModel;
    private final ViewManagerModel viewManagerModel;

    public RegisterPresenter(ViewManagerModel viewManagerModel,
                           CreateEventViewModel createEventViewModel,
                           RegisterViewModel registerViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createEventViewModel = createEventViewModel;
        this.registerViewModel = registerViewModel;
        // this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(RegisterOutputData response) {
        // OLD: On success, switch to the login view.
        // TODO: ON SUCCESS SWITCH TO THE CREATE EVENT VIEW
        // TODO: Code create_event view


        // final LoginState loginState = loginViewModel.getState();
        // loginState.setUsername(response.getUsername());
        // this.loginViewModel.setState(loginState);
        // loginViewModel.firePropertyChanged();

        // viewManagerModel.setState(loginViewModel.getViewName());
        // viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final RegisterState registerState = registerViewModel.getState();
        registerState.setUsernameError(error);
        registerViewModel.firePropertyChanged();
    }

    // TODO: switch to createEventview
    @Override
    public void switchToCreateEventView() {
        // viewManagerModel.setState(createEventViewModel.getViewName());
        // TODO fix this later
        viewManagerModel.setState("THIS IS A TEST, THIS IS A TEST");
        viewManagerModel.firePropertyChanged();
    }
}
