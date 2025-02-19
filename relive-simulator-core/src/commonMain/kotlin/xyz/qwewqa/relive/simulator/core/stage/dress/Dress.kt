package xyz.qwewqa.relive.simulator.core.stage.dress

import xyz.qwewqa.relive.simulator.core.stage.actor.ActData
import xyz.qwewqa.relive.simulator.core.stage.actor.ActType
import xyz.qwewqa.relive.simulator.core.stage.actor.Attribute
import xyz.qwewqa.relive.simulator.core.stage.actor.StatData
import xyz.qwewqa.relive.simulator.core.stage.autoskill.AutoSkill
import xyz.qwewqa.relive.simulator.core.stage.actor.Character
import xyz.qwewqa.relive.simulator.core.stage.actor.DamageType
import xyz.qwewqa.relive.simulator.core.stage.actor.Position

data class Dress(
    val id: Int = -1,
    val name: String,
    val character: Character,
    val attribute: Attribute,
    val damageType: DamageType,
    val position: Position,
    val positionValue: Int = 0,
    val stats: StatData,
    val acts: Map<ActType, ActData>,
    val autoSkills: List<AutoSkill>,
    val unitSkill: List<AutoSkill> = emptyList(),
    val multipleCA: Boolean = false,
    val blueprint: DressBlueprint? = null,
)
