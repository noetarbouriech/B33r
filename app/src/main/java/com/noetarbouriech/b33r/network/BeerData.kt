package com.noetarbouriech.b33r.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    @SerialName("num_hits")
    var numHits: Int,
    var hits: List<Beer>
)

@Serializable
data class Beer(
    val id: String,
    val name: String,
    val nameDisplay: String? = null,
    val description: String? = null,
    val abv: String? = null,
    val ibu: String? = null,
    val glasswareId: Int? = null,
    val srmId: Int? = null,
    val availableId: Int? = null,
    val styleId: Int? = null,
    val isOrganic: String? = null,
    val isRetired: String? = null,
    val labels: Labels? = null,
    val status: String? = null,
    val statusDisplay: String? = null,
    val originalGravity: String? = null,
    val createDate: String? = null,
    val updateDate: String? = null,
    val glass: Glass? = null,
    val srm: Srm? = null,
    val available: Available? = null,
    val style: Style? = null
)

@Serializable
data class Labels(
    val icon: String,
    val medium: String,
    val large: String,
    val contentAwareIcon: String,
    val contentAwareMedium: String,
    val contentAwareLarge: String
)

@Serializable
data class Glass(
    val id: Int,
    val name: String,
    val createDate: String
)

@Serializable
data class Srm(
    val id: Int,
    val name: String,
    val hex: String
)

@Serializable
data class Available(
    val id: Int,
    val name: String,
    val description: String
)

@Serializable
data class Style(
    val id: Int,
    val categoryId: Int,
    val category: Category,
    val name: String,
    val shortName: String? = null,
    val description: String? = null,
)

@Serializable
data class Category(
    val id: Int,
    val name: String,
)
