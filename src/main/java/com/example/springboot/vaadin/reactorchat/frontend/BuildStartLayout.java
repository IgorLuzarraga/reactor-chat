package com.example.springboot.vaadin.reactorchat.frontend;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class BuildStartLayout extends VerticalLayout {
    final private String START_BUTTON_TEXT = "Your name";
    final private String DEFAULT_USER_NAME = "user";
    private HorizontalLayout startLayout;
    private TextField usernameField;
    private Button startButton;
    private MainView mainView;
    private IBuildChatLayout buildChatLayout;
    private String userName = DEFAULT_USER_NAME;

    @Autowired
    public BuildStartLayout(IBuildChatLayout buildChatLayout) {
        this.buildChatLayout = buildChatLayout;
        buildLayout();
        addListeners();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void eventClickStartButton(){
        startButton.click();
    }

    private void addStartLayout(){
        add(startLayout);
    }

    private void removeStartLayout(){
        remove(startLayout);
    }

    private void buildLayout(){
        startLayout = new HorizontalLayout();
        usernameField = new TextField();
        startButton = new Button(START_BUTTON_TEXT);

        usernameField.focus();

        startLayout.add(usernameField, startButton);
        addStartLayout();
    }

    private void addListeners(){
        usernameField.addKeyPressListener(Key.ENTER,
                click -> eventForUserName());

        startButton.addClickListener(click -> eventForUserName());
    }

    private void eventForUserName(){
        setUserName(usernameField.getValue());
        removeStartLayout();

        // start the chat
       buildChatLayout.startChat(getUserName());
    }
}

