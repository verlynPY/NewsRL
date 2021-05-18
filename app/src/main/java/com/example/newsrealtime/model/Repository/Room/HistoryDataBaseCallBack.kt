package com.example.newsrealtime.model.Repository.Room


/*
class HistoryDataBaseCallBack(private val scope: CoroutineScope) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        /*INSTANCE?.let { database ->
            scope.launch {
                populateDatabase(database.historyDao())
            }
        }*/
    }

    suspend fun populateDatabase(historyDao: HistoryDao) {
        // Delete all content here.

        historyDao.delete()

        // Add sample words.
        var history = History(1,"Tesla")
        historyDao.insert(history)
        history = History(2,"Apple")
        historyDao.insert(history)

        // TODO: Add your own words!
    }
}*/