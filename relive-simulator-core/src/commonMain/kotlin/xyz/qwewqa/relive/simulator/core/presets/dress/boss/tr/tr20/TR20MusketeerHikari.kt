package xyz.qwewqa.relive.simulator.core.presets.dress.boss.tr.tr20

import xyz.qwewqa.relive.simulator.core.presets.dress.boss.tr.trEventBonusPassive
import xyz.qwewqa.relive.simulator.core.stage.HitMode
import xyz.qwewqa.relive.simulator.core.stage.actor.ActType
import xyz.qwewqa.relive.simulator.core.stage.actor.Attribute
import xyz.qwewqa.relive.simulator.core.stage.actor.actsOf
import xyz.qwewqa.relive.simulator.core.stage.actor.defaultDressStats
import xyz.qwewqa.relive.simulator.core.stage.autoskill.AbnormalResistPassiveA
import xyz.qwewqa.relive.simulator.core.stage.autoskill.BossElementResistPassive
import xyz.qwewqa.relive.simulator.core.stage.autoskill.new
import xyz.qwewqa.relive.simulator.core.stage.buff.BuffCategory
import xyz.qwewqa.relive.simulator.core.stage.buff.Buffs
import xyz.qwewqa.relive.simulator.core.stage.dress.Dress
import xyz.qwewqa.relive.simulator.core.stage.loadout.ActorLoadout
import xyz.qwewqa.relive.simulator.core.stage.strategy.FixedStrategy
import xyz.qwewqa.relive.simulator.core.stage.actor.Character
import xyz.qwewqa.relive.simulator.core.stage.actor.DamageType
import xyz.qwewqa.relive.simulator.core.stage.actor.Position

val tr20MusketeerHikari =
    ActorLoadout(
        "TR20 Musketeer Hikari",
        Dress(
            name = "Musketeer Hikari",
            character = Character.Hikari,
            attribute = Attribute.Moon,
            damageType = DamageType.Special,
            position = Position.None,
            stats =
                defaultDressStats.copy(
                    hp = 3_500_000,
                    actPower = 2300,
                    normalDefense = 650,
                    specialDefense = 650,
                    agility = 1,
                ),
            acts =
                actsOf(
                    ActType.Act1("Confusing Concerto", 2) {
                      targetAoe().act {
                        attack(
                            modifier = 150,
                            hitCount = 4,
                        )
                        applyContinuousBuff(
                            effect = Buffs.ConfusionBuff,
                            turns = 2,
                        )
                      }
                    },
                    ActType.Act2("Single Slash", 2) {
                      targetFront().act {
                        attack(
                            modifier = 100,
                            hitCount = 1,
                        )
                      }
                    },
                    ActType.Act3("Violent Slash", 2) {
                      targetFront().act {
                        attack(
                            modifier = 150,
                            hitCount = 1,
                        )
                      }
                    },
                    ActType.Act4("Triple Slash", 2) {
                      targetBack(3).act {
                        attack(
                            modifier = 70,
                            hitCount = 3,
                        )
                      }
                    },
                    ActType.Act5("Violent Triple Slash", 2) {
                      targetBack(3).act {
                        attack(
                            modifier = 100,
                            hitCount = 3,
                        )
                      }
                    },
                    ActType.Act6("Counter Concerto", 2) {
                      targetAoe().act {
                        attack(
                            modifier = 150,
                            hitCount = 2,
                        )
                      }
                    },
                    ActType.Act7("Ironclad Dance", 2) {
                      targetSelf().act {
                        applyContinuousBuff(
                            effect = Buffs.NormalBarrierBuff,
                            value = 10000,
                            turns = 3,
                        )
                        applyContinuousBuff(
                            effect = Buffs.SpecialBarrierBuff,
                            value = 10000,
                            turns = 3,
                        )
                      }
                    },
                    ActType.Act8("Accuracy Concerto", 2) {
                      targetSelf().act {
                        applyContinuousBuff(
                            effect = Buffs.PerfectAimBuff,
                            turns = 1,
                        )
                      }
                      targetAoe().act {
                        attack(
                            modifier = 150,
                            hitCount = 3,
                        )
                      }
                    },
                    ActType.Act9("Purifying Dance", 2) {
                      targetSelf().act { removeContinuous(BuffCategory.Negative) }
                      targetAoe().act { removeContinuous(BuffCategory.Positive) }
                    },
                    ActType.Act10("Inspiring Gust", 2) {
                      targetSelf().act {
                        applyContinuousBuff(
                            effect = Buffs.ActPowerUpBuff,
                            value = 50,
                            turns = 3,
                        )
                        applyContinuousBuff(
                            effect = Buffs.NormalDefenseUpBuff,
                            value = 50,
                            turns = 3,
                        )
                        applyContinuousBuff(
                            effect = Buffs.SpecialDefenseUpBuff,
                            value = 50,
                            turns = 3,
                        )
                      }
                    },
                    ActType.ClimaxAct("Blue Days Pierce NEO", 2) {
                      targetAoe().act {
                        attack(
                            modifier = 200,
                            hitCount = 4,
                        )
                        applyContinuousBuff(
                            effect = Buffs.ConfusionBuff,
                            turns = 2,
                        )
                      }
                    },
                    ActType.ConfusionAct("Slash", 2) {
                      targetAllyRandom().act {
                        attack(
                            modifier = 105,
                            hitCount = 1,
                        )
                      }
                    },
                ),
            autoSkills =
                listOf(
                    trEventBonusPassive(1090024),
                    AbnormalResistPassiveA.new(100),
                    BossElementResistPassive.new(50),
                ),
        ),
    )

val tr20MusketeerHikariStrategy = FixedStrategy {
  val boss = this.team.actors.values.first()

  when (turn) {
    1 -> {
      +boss[ActType.Act7]
      +boss[ActType.Act3]
      +boss[ActType.Act4]
    }
    2 -> {
      +boss[ActType.Act10]
      +boss[ActType.Act3]
      +boss[ActType.Act5]
    }
    3 -> {
      +boss[ActType.ClimaxAct]
      +boss[ActType.Act8]
      +boss[ActType.Act6]
    }
    4 -> {
      +boss[ActType.Act9]
      +boss[ActType.Act3]
      +boss[ActType.Act5]
    }
    5 -> {
      +boss[ActType.Act10]
      +boss[ActType.Act1]
      +boss[ActType.Act6]
    }
    6 -> {
      +boss[ActType.Act7]
      +boss[ActType.Act8]
      +boss[ActType.Act6]
    }
    else -> {
      error("Unsupported.")
    }
  }
}

val tr20MusketeerHikariDiff4 =
    ActorLoadout(
        "TR20 Musketeer Hikari Difficulty 4",
        Dress(
            name = "Musketeer Hikari",
            character = Character.Hikari,
            attribute = Attribute.Moon,
            damageType = DamageType.Special,
            position = Position.None,
            stats =
                defaultDressStats.copy(
                    hp = 150_000_000,
                    actPower = 10000,
                    normalDefense = 650,
                    specialDefense = 650,
                    agility = 1,
                ),
            acts =
                actsOf(
                    ActType.Act1("Confusing Concerto", 2) {
                      targetAoe().act {
                        attack(
                            modifier = 150,
                            hitCount = 4,
                        )
                        applyContinuousBuff(
                            effect = Buffs.ConfusionBuff,
                            turns = 2,
                        )
                      }
                    },
                    ActType.Act2("Single Slash", 2) {
                      targetFront().act {
                        attack(
                            modifier = 100,
                            hitCount = 1,
                        )
                      }
                    },
                    ActType.Act3("Violent Slash", 2) {
                      targetFront().act {
                        attack(
                            modifier = 150,
                            hitCount = 1,
                        )
                      }
                    },
                    ActType.Act4("Triple Slash", 2) {
                      targetBack(3).act {
                        attack(
                            modifier = 70,
                            hitCount = 3,
                        )
                      }
                    },
                    ActType.Act5("Violent Triple Slash", 2) {
                      targetBack(3).act {
                        attack(
                            modifier = 100,
                            hitCount = 3,
                        )
                      }
                    },
                    ActType.Act6("Counter Concerto", 2) {
                      targetAoe().act {
                        attack(
                            modifier = 150,
                            hitCount = 2,
                        )
                      }
                    },
                    ActType.Act7("Ironclad Dance", 2) {
                      targetSelf().act {
                        applyContinuousBuff(
                            effect = Buffs.NormalBarrierBuff,
                            value = 10000,
                            turns = 3,
                        )
                        applyContinuousBuff(
                            effect = Buffs.SpecialBarrierBuff,
                            value = 10000,
                            turns = 3,
                        )
                      }
                    },
                    ActType.Act8("Accuracy Concerto", 2) {
                      targetSelf().act {
                        applyContinuousBuff(
                            effect = Buffs.PerfectAimBuff,
                            turns = 1,
                        )
                      }
                      targetAoe().act {
                        attack(
                            modifier = 150,
                            hitCount = 3,
                        )
                      }
                    },
                    ActType.Act9("Purifying Dance", 2) {
                      targetSelf().act { removeContinuous(BuffCategory.Negative) }
                      targetAoe().act { removeContinuous(BuffCategory.Positive) }
                    },
                    ActType.Act10("Inspiring Gust", 2) {
                      targetSelf().act {
                        applyContinuousBuff(
                            effect = Buffs.ActPowerUpBuff,
                            value = 50,
                            turns = 3,
                        )
                        applyContinuousBuff(
                            effect = Buffs.NormalDefenseUpBuff,
                            value = 50,
                            turns = 3,
                        )
                        applyContinuousBuff(
                            effect = Buffs.SpecialDefenseUpBuff,
                            value = 50,
                            turns = 3,
                        )
                      }
                    },
                    ActType.ClimaxAct("Blue Days Pierce NEO", 2) {
                      targetAoe().act {
                        attack(
                            modifier = 99999,
                            hitCount = 4,
                            mode = HitMode.FIXED,
                        )
                        applyContinuousBuff(
                            effect = Buffs.ConfusionBuff,
                            turns = 2,
                        )
                      }
                    },
                    ActType.ConfusionAct("Slash", 2) {
                      targetAllyRandom().act {
                        attack(
                            modifier = 105,
                            hitCount = 1,
                        )
                      }
                    },
                ),
            autoSkills =
                listOf(
                    trEventBonusPassive(1090024),
                    AbnormalResistPassiveA.new(100),
                    BossElementResistPassive.new(50),
                ),
        ),
    )

val tr20MusketeerHikariDiff4Strategy = FixedStrategy {
  val boss = this.team.actors.values.first()

  when (turn) {
    1 -> {
      +boss[ActType.Act7]
      +boss[ActType.Act3]
      +boss[ActType.Act4]
    }
    2 -> {
      +boss[ActType.Act10]
      +boss[ActType.Act3]
      +boss[ActType.Act5]
    }
    3 -> {
      +boss[ActType.ClimaxAct]
      +boss[ActType.Act8]
      +boss[ActType.Act6]
    }
    4 -> {
      +boss[ActType.Act9]
      +boss[ActType.Act3]
      +boss[ActType.Act5]
    }
    5 -> {
      +boss[ActType.Act10]
      +boss[ActType.Act1]
      +boss[ActType.Act6]
    }
    6 -> {
      +boss[ActType.Act7]
      +boss[ActType.Act8]
      +boss[ActType.Act6]
    }
    else -> {
      error("Unsupported.")
    }
  }
}
