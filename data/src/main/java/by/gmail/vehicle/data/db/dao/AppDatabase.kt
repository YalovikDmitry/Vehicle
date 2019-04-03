package by.gmail.vehicle.data.db.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import by.gmail.vehicle.data.entity.vehicle.VehicleResponse

@Database(entities = [VehicleResponse::class], version = 1)
internal abstract class AppDatabase: RoomDatabase() {

    companion object {

        private const val DB_NAME = "vehicleDb"

        fun create(contex: Context): AppDatabase {
            return Room.databaseBuilder(contex, AppDatabase::class.java,
                DB_NAME
            ).build()
        }
    }

     abstract fun getVehicleDao(): VehicleDao
}