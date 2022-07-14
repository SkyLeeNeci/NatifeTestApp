package karpenko.diploma.natifetestapp.pojo

import com.google.gson.annotations.SerializedName

data class DataResult(
    @SerializedName("data") val result: List<DataObject>
)
