package personal.luissanchez.scheduler.db;

/**
 * Created by Usuario on 18/06/2016.
 */

import android.provider.BaseColumns;

public class TaskContract {
    public static final String DB_NAME = "personal.luissanchez.scheduler.db";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks";
        public static final String COL_TASK_TITLE = "title";
        public static final String COL_TASK_TIME = "time";
        // TODO: Add "isCompleted" to database
    }
}
