MeLogsta

Standard settings:

	Show all notifications as single notifications, i.e. each log type (debug, warn,...) is shown in an updated notification.
	Save all logs in memory. View them by selecting the notification.
	Log all logs to LogCat, nothing is changed here, so you can still use LogCat.
	

Some usefull functions:
        
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

	// Disable all notifications
	Log.dis/enableAllNotifications()

IMPORTANT: Add the Activity to your embedding manifest:

        <activity android:name="de.uvwxy.melogsta.ActivityShowLogs"></activity>

