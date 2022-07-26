import LocalDataBase.Companion.VERSION_DATABASE
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cardgamescore.local_data_source.dao.PlayerDaoDataSource
import com.example.cardgamescore.model.Player

@Database(entities = [Player::class], version = VERSION_DATABASE)
abstract class LocalDataBase : RoomDatabase() {

    abstract fun playerDao(): PlayerDaoDataSource

    companion object {
        const val VERSION_DATABASE = 1
        private const val DATABASE_NAME = "player_database"

        fun getInstance(context: Context): LocalDataBase =
            Room.databaseBuilder(
                context,
                LocalDataBase::class.java,
                DATABASE_NAME
            )
                .allowMainThreadQueries()
                .build()
    }
}
