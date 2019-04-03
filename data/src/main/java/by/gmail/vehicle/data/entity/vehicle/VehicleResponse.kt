package by.gmail.vehicle.data.entity.vehicle

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName

internal const val vehicleTableName = "vehicle"
internal const val idColumnName = "id"
internal const val coordinateColumnName = "coordinate"


@Entity(tableName = vehicleTableName)
internal class VehicleResponse(

    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = idColumnName)
    val id: Int,

    @SerializedName("coordinate")
    @Embedded
    val coordinate: CoordinateResponse,

    @SerializedName("fleetType")
    val fleetType: String,

    @SerializedName("heading")
    val heading: Double
)