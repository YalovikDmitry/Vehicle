package by.gmail.vehicle.data.entity.vehicle

//const val vehicleCoordinateTableName = "coordinate"
//const val vehicleCoordinateTableNameId = "coordinate_id"
//
//@Entity(tableName = vehicleCoordinateTableName)
internal data class CoordinateResponse(

//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = vehicleCoordinateTableNameId)
    val latitude: Double,
    val longitude: Double
)