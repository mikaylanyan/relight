@file:Suppress("PropertyName")

package xyz.qwewqa.relive.simulator.core.gen

import kotlinx.serialization.Serializable

@Serializable
data class GenPassiveSkill(
    val description: Map<String, String>,
    val icon_id: Int,
    val skill_option1_id: Int,
    val skill_option1_target_id: Int,
    val skill_option1_values: List<Int>,
    val skill_option2_id: Int,
    val skill_option2_target_id: Int,
    val skill_option2_values: List<Int>,
    val skill_option3_id: Int,
    val skill_option3_target_id: Int,
    val skill_option3_values: List<Int>,
    val skill_option4_id: Int,
    val skill_option4_target_id: Int,
    val skill_option4_values: List<Int>,
    val skill_option5_id: Int,
    val skill_option5_target_id: Int,
    val skill_option5_values: List<Int>,
    val _id_: Int
)

val valuesGenPassiveSkill = loadMasterData<GenPassiveSkill>(dataGenPassiveSkill)
