package br.com.cast.turmaformacao.taskmanager.util;

import android.content.Context;

/**
 * Created by Administrador on 16/09/2015.
 */
public final class ApplicationUtil {

    private static Context applicationContext;

    private ApplicationUtil(){
        super();
    }

    public static Context getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(Context applicationContext) {
        ApplicationUtil.applicationContext = applicationContext;
    }
}
