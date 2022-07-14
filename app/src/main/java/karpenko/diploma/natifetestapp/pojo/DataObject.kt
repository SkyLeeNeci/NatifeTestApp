package karpenko.diploma.natifetestapp.pojo

import com.google.gson.annotations.SerializedName

data class DataObject(
    @SerializedName("images") val images: DataImage
)
