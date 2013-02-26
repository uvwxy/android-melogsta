MeLogsta

        
        // import de.uvwxy.melogsta.Log instead of android.util.Log
        
        // set the Context in your Activity/Service/..
        Log.setContext(getContext())
        
        // Toggle all logging
        Log.setLogAnything(true/false)
        
        // Enable all LogCat logging
        Log.setLogToLogCat(true/false)
        
        // Save any logs to be displayed later?
        Log.setAllLogToHistory(true/false)
        
        // Remove all notifications
        Log.cancelAllNotifications()
        
        // Set your email adress for "Send Mail" bugreport button
        Log.setEmailAddress("code@uvwxy.de")