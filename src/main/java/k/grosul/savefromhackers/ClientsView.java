package k.grosul.savefromhackers;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route
public class ClientsView extends VerticalLayout {
    
    private final transient UserRepository repo;
    private final Grid<User> allUsers = new Grid<>(User.class);
    
    public ClientsView(UserRepository repo) {
        this.repo = repo;
        H1 heading = new H1("Клиенты");
        Button update = new Button(VaadinIcon.REFRESH.create());
        RouterLink orderView = new RouterLink("Назад", MainView.class);
        add(heading, update, allUsers, orderView);
        
        allUsers.setColumns(
                "name",
                "birth",
                "passportNumber",
                "passportGiven",
                "passportRegistration"
        );
        allUsers.addComponentColumn(order -> {
            Button deleteBtn = new Button(VaadinIcon.TRASH.create());
            deleteBtn.addClickListener(e -> {
                repo.delete(order);
                setAll();
            });
            return deleteBtn;
        });
        setAll();
        
        update.addClickListener(e -> setAll());
        
    }

    public void setAll() {
        allUsers.setItems(repo.findAll());
    }
    
}
