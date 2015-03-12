package com.miningwolf;

import io.nodyn.Callback;
import io.nodyn.CallbackResult;
import io.nodyn.ExitHandler;
import io.nodyn.Nodyn;
import io.nodyn.runtime.NodynConfig;
import io.nodyn.runtime.RuntimeFactory;
import io.nodyn.NoOpExitHandler;

public class test {

    private static final String SCRIPT = "" +
            "var main = require('./app.js');";


   public static void main(String[] args) throws InterruptedException {
        // Use DynJS runtime
        RuntimeFactory factory = RuntimeFactory.init(
            test.class.getClassLoader(), 
            RuntimeFactory.RuntimeType.DYNJS);

        // Set config to run main.js
        NodynConfig config = new NodynConfig( new String[] { "-e", SCRIPT } );

        // Create a new Nodyn and run it
        Nodyn nodyn = factory.newRuntime(config);
        nodyn.setExitHandler( new NoOpExitHandler() );
        try {
            int exitCode = nodyn.run();
            if (exitCode != 0) {
                throw new IllegalArgumentException();
            }
        } catch (Throwable t) {
            throw new IllegalArgumentException( t );
        }
    }
}