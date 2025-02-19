package xyz.qwewqa.relive.simulator.core.presets.condition

import xyz.qwewqa.relive.simulator.core.stage.condition.NamedCondition
import xyz.qwewqa.relive.simulator.core.stage.actor.School

val SeishoOnlyCondition = schoolCondition(School.Seisho)
val RinmeikanOnlyCondition = schoolCondition(School.Rinmeikan)
val FrontierOnlyCondition = schoolCondition(School.Frontier)
val SiegfeldOnlyCondition = schoolCondition(School.Siegfeld)
val SeiranOnlyCondition = schoolCondition(School.Seiran)

fun schoolCondition(school: School) =
    NamedCondition(school.name) { it.dress.character.school == school }
