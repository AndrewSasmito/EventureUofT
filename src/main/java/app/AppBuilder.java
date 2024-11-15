package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

//import app.data_access.InMemoryUserDataAccessObject;
import app.data_access.FirebaseDAO;
import app.entity.User.CommonUserFactory;
import app.entity.User.UserFactory;
import app.interface_adapter.ViewManagerModel;
import app.interface_adapter.register.RegisterController;
import app.interface_adapter.register.RegisterPresenter;
import app.interface_adapter.register.RegisterViewModel;
import app.interface_adapter.create_event.CreateEventViewModel;
import app.use_case.register.RegisterInputBoundary;
import app.use_case.register.RegisterInteractor;
import app.use_case.register.RegisterOutputBoundary;
import app.view.RegisterView;
import app.view.ViewManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *  * The AppBuilder class is responsible for putting together the pieces of
 *  * our CA architecture; piece by piece.
 *  * <p/>
 *  * This is done by adding each View and then adding related Use Cases.
 */
@Component
public class AppBuilder {
    //TODO: This is the last part that we should work on after developing
    // the the other folders

    // TODO: Note: cardlayout makes it so that one view can be seen at a time, refer to swing docs
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    //    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
//    private final FirebaseDAO firebaseDAO = new FirebaseDAO();
    @Autowired
    private FirebaseDAO firebaseDAO;

    private RegisterView registerView;
    private RegisterViewModel registerViewModel;

    // TODO: NEW FOR OUR PROJECT
    private CreateEventViewModel createEventViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addRegisterView() {

        this.registerViewModel = new RegisterViewModel();
        this.registerView = new RegisterView(registerViewModel);
        cardPanel.add(registerView, registerView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addRegisterUseCase() {
        final RegisterOutputBoundary registerOutputBoundary = new RegisterPresenter(viewManagerModel,
                createEventViewModel, registerViewModel);
        final RegisterInputBoundary userRegisterInteractor = new RegisterInteractor(
                firebaseDAO, registerOutputBoundary, userFactory);

        final RegisterController controller = new RegisterController(userRegisterInteractor);
        registerView.setRegisterController(controller);
        return this;
    }


    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        if (java.awt.GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment detected. Skipping GUI initialization.");
            return null;
        }

        final JFrame application = new JFrame("EventureUofT");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // make GUI fullscreen
        application.setExtendedState(JFrame.MAXIMIZED_BOTH);

        application.add(cardPanel);

        viewManagerModel.setState(registerView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }

}
