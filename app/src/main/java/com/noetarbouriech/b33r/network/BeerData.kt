package com.noetarbouriech.b33r.network

import kotlinx.serialization.Serializable

@Serializable
data class Beer(
    val id: String,
    val name: String,
    val nameDisplay: String,
    val description: String,
    val abv: String,
    val ibu: String,
    val glasswareId: Int,
    val srmId: Int,
    val availableId: Int,
    val styleId: Int,
    val isOrganic: String,
    val isRetired: String,
    val labels: Labels,
    val status: String,
    val statusDisplay: String,
    val originalGravity: String,
    val createDate: String,
    val updateDate: String,
    val glass: Glass,
    val srm: Srm,
    val available: Available,
    val style: Style
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
    val shortName: String,
    val description: String,
    val ibuMin: String,
    val ibuMax: String,
    val abvMin: String,
    val abvMax: String,
    val srmMin: String,
    val srmMax: String,
    val ogMin: String,
    val fgMin: String,
    val fgMax: String,
    val createDate: String,
    val updateDate: String
)

@Serializable
data class Category(
    val id: Int,
    val name: String,
    val createDate: String
)
