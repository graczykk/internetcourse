package com.pkproject.internetcourse.application.controller.controllerinterfaces;

import com.pkproject.internetcourse.application.controller.primary.PrimaryController;

import java.io.IOException;

/**
 * Created by Piotr Fudala on 14.01.2017.
 */
public interface Controller {
    public void changeController(PrimaryController controller) throws IOException;
}
