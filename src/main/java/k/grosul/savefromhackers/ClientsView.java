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
    
    private final UserAPI api = new UserAPI();
    private final Grid<UserDto> allUsers = new Grid<>(UserDto.class);
    
    public ClientsView(UserRepository repo) {
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
        setAll();
        
        update.addClickListener(e -> setAll());
        
    }

    public void setAll() {
        allUsers.setItems(api.getAll());
    }
    
}
