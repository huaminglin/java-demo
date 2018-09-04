package huaminglin.demo.jdk.jaas;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;

public class JaasDemoCallbackHandler implements CallbackHandler {
    private String name;

    public JaasDemoCallbackHandler(String name) {
        this.name = name;
    }

    @Override
    public void handle(Callback[] callbacks) {
        Callback callback = callbacks[0];
        if (callback instanceof NameCallback) {
            NameCallback nc = (NameCallback) callback;
            nc.setName(nc.getPrompt() + ": " + name);
        }
    }
}
