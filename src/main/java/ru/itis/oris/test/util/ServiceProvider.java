package ru.itis.oris.test.util;

import ru.itis.oris.test.model.PostDaoImpl;
import ru.itis.oris.test.model.UserDao;
import ru.itis.oris.test.model.UserDaoImpl;
import ru.itis.oris.test.servise.AuthService;
import ru.itis.oris.test.servise.PostServiceImpl;

public class ServiceProvider {

    private static final ServiceProvider INSTANCE = new ServiceProvider();

    private final PostService postService;
    private final AuthService authService;

    private ServiceProvider() {
        // lfj
        PostDao postDao = new PostDaoImpl();
        UserDao userDao = new UserDaoImpl();

        // сервис
        this.postService = new PostServiceImpl(postDao);
        this.authService = new AuthServiceImpl(userDao);
    }

    public static ServiceProvider getInstance() {
        return INSTANCE;
    }

    public PostService getPostService() {
        return postService;
    }

    public AuthService getAuthService() {
        return authService;
    }
}