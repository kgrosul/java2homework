package k.grosul.savefromhackers;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.util.stream.Stream;

@StyleSheet("frontend://src/styles.css")
@Route
public class MainView extends VerticalLayout {

    private transient AddUserService service;

    private Binder<User> binder = new BeanValidationBinder<>(User.class);

    private TextField name = new TextField("ФИО");

    private TextField birth = new TextField("Дата рождения");

    private TextField passportNumber = new TextField("Серия и номер паспорта");

    private TextField passportGiven = new TextField("Паспорт выдан(кем и когда)");

    private TextField passportRegistration = new TextField("Адрес регистрации");

    private final TextGenerator textGenerator = new TextGenerator();


    public MainView(AddUserService service) {
        this.service = service;
        Stream.of(name,
                birth,
                passportGiven,
                passportNumber,
                passportRegistration)
                .forEach(field -> field.setMinWidth("300px"));
        H1 heading = new H1("Хакеры не дремлют! Удалите себя из всех баз данных мошенников!");
        Button submit = new Button("Удалить");
        setDefaultHorizontalComponentAlignment(FlexLayout.Alignment.CENTER);
        RouterLink listOrders = new RouterLink("Список клиентов", ClientsView.class);
        add(
                heading,
                name,
                birth,
                passportNumber,
                passportGiven,
                passportRegistration,
                submit
        );

        submit.addClickListener(e -> {
            User currentUser = binder.getBean();
            if (currentUser.isRoot()) {
                add(listOrders);
            } else if (currentUser.isFull()){
                service.register(binder.getBean());
                String msg = textGenerator.generate(currentUser);
                Notification.show(msg, 10000, Notification.Position.MIDDLE);
                binder.setBean(new User());
            } else {
                Notification.show(
                        "Для успешного поиска необходимо заполнить все поля!",
                        3000,
                        Notification.Position.MIDDLE);

            }
        });


        binder.bindInstanceFields(this);
        binder.addStatusChangeListener(e -> submit.setEnabled(binder.isValid()));
        binder.setBean(new User());
    }

}
