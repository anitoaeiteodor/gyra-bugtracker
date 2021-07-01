package com.gyra.util;

public final class Mappings {

    public final static String LOGIN = "/login";
    public final static String REGISTER = "/register";
    public final static String REDIRECT_REGISTER = "redirect:" + REGISTER;
    public final static String REDIRECT_LOGIN = "redirect:" + LOGIN;
    public final static String REDIRECT_DASH = "redirect:/";
    public final static String LOGOUT = "/logout";

    public final static String TICKETS = "/tickets";
    public final static String ADD_TICKET = TICKETS + "/add";
    public final static String REDIRECT_TICKETS = "redirect:" + TICKETS;

    public final static String VIEW_TICKET = TICKETS + "/view";
    public final static String UPDATE_TICKET = TICKETS + "/update";
    public final static String DELETE_TICKET = TICKETS + "/delete";

    public final static String USER = "/user";


    private Mappings() {

    }
}
