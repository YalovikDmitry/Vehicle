package by.gmail.vehicle.data.db.dao

import android.arch.persistence.room.*
import by.gmail.vehicle.data.entity.vehicle.VehicleResponse
import by.gmail.vehicle.data.entity.vehicle.idColumnName
import by.gmail.vehicle.data.entity.vehicle.vehicleTableName
import io.reactivex.Single

@Dao
internal interface VehicleDao {

    @Query("SELECT * FROM $vehicleTableName")
    fun get(): Single<List<VehicleResponse>>

    @Query("SELECT * FROM $vehicleTableName WHERE $idColumnName = (:id) LIMIT 1")
    fun getById(id:Int): Single<VehicleResponse>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vehicles: List<VehicleResponse>): Unit

    @Query("DELETE FROM $vehicleTableName")
    fun  delete(): Unit
}