package xyz.qwewqa.relive.simulator.core.presets.dress.boss.tr.tr29

import xyz.qwewqa.relive.simulator.core.presets.dress.boss.tr.trEventBonusPassive2023
import xyz.qwewqa.relive.simulator.core.stage.HitMode
import xyz.qwewqa.relive.simulator.core.stage.actor.*
import xyz.qwewqa.relive.simulator.core.stage.autoskill.AbnormalResistPassiveA
import xyz.qwewqa.relive.simulator.core.stage.autoskill.BossElementResistPassive
import xyz.qwewqa.relive.simulator.core.stage.autoskill.new
import xyz.qwewqa.relive.simulator.core.stage.buff.*
import xyz.qwewqa.relive.simulator.core.stage.dress.Dress
import xyz.qwewqa.relive.simulator.core.stage.loadout.ActorLoadout
import xyz.qwewqa.relive.simulator.core.stage.strategy.FixedStrategy
import xyz.qwewqa.relive.simulator.core.stage.actor.Character
import xyz.qwewqa.relive.simulator.core.stage.actor.DamageType
import xyz.qwewqa.relive.simulator.core.stage.actor.Position

val tr29CheerTsukasa =
    ActorLoadout(
        "TR29 Cheer Tsukasa",
        Dress(
            name = "Cheer Tsukasa",
            character = Character.Tsukasa,
            attribute = Attribute.Wind,
            damageType = DamageType.Special,
            position = Position.None,
            stats =
            defaultDressStats.copy(
                hp = 8_750_000,
                actPower = 2300,
                normalDefense = 650,
                specialDefense = 650,
                agility = 1,
            ),
            acts =
            actsOf(
                ActType.Act1("Slash", 2) {
                  targetFront().act {
                    attack(
                        modifier = 100,
                        hitCount = 1,
                    )
                  }
                },
                ActType.Act2("Strong Slash", 2) {
                  targetFront().act {
                    attack(
                        modifier = 200,
                        hitCount = 1,
                    )
                  }
                },
                ActType.Act3("Triple Slash", 2) {
                  targetFront(3).act {
                    attack(
                        modifier = 80,
                        hitCount = 3,
                    )
                  }
                },
                ActType.Act4("Violent Triple Slash", 2) {
                  targetFront(3).act {
                    attack(
                        modifier = 120,
                        hitCount = 3,
                    )
                  }
                },
                ActType.Act5("Counter Concerto", 2) {
                  targetAoe().act {
                    attack(
                        modifier = 150,
                        hitCount = 2,
                    )
                  }
                },
                ActType.Act6("Perfect Aim Flurry", 2) {
                  targetSelf().act {
                    applyContinuousBuff(
                        effect = Buffs.PerfectAimBuff,
                        turns = 1,
                    )
                  }
                  targetAoe().act {
                    attack(
                        modifier = 120,
                        hitCount = 3,
                    )
                  }
                },
                ActType.Act7("FireDance", 2) {
                  targetAoe().act {
                    attack(
                        modifier = 150,
                        hitCount = 2,
                    )
                    applyContinuousBuff(
                        effect = Buffs.BurnBuff,
                        value = 7000,
                        turns = 3,
                    )
                  }
                },
                ActType.Act8("Ironclad Dance", 2) {
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
                ActType.Act9("Purifying Dance", 2) {
                  targetSelf().act { removeContinuous(BuffCategory.Negative) }
                  targetAoe().act { removeContinuous(BuffCategory.Positive) }
                },
                ActType.ClimaxAct("Shining Hope★ NEO", 2) {
                  targetAoe().act {
                    attack(
                        modifier = 200,
                        hitCount = 4,
                    )
                    applyContinuousBuff(
                        effect = Buffs.BurnBuff,
                        value = 7000,
                        turns = 3,
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
                trEventBonusPassive2023(2010021),
                AbnormalResistPassiveA.new(100),
                BossElementResistPassive.new(50),
            ),
        ),
    )

val tr29CheerTsukasaStrategy = FixedStrategy {
  val boss = this.team.actors.values.first()

  when (turn) {
    1 -> {
      +boss[ActType.Act8]
      +boss[ActType.Act1]
      +boss[ActType.Act4]
    }
    2 -> {
      +boss[ActType.Act8]
      +boss[ActType.Act2]
      +boss[ActType.Act4]
    }
    3 -> {
      +boss[ActType.ClimaxAct]
      +boss[ActType.Act6]
      +boss[ActType.Act9]
    }
    4 -> {
      +boss[ActType.Act8]
      +boss[ActType.Act2]
      +boss[ActType.Act4]
    }
    5 -> {
      +boss[ActType.Act7]
      +boss[ActType.Act2]
      +boss[ActType.Act5]
    }
    6 -> {
      +boss[ActType.Act9]
      +boss[ActType.Act4]
      +boss[ActType.Act6]
    }
    else -> error("Not supported.")
  }
}

val tr29CheerTsukasaDiff4 =
    ActorLoadout(
        "TR29 Cheer Tsukasa Difficulty 4",
        Dress(
            name = "Cheer Tsukasa",
            character = Character.Tsukasa,
            attribute = Attribute.Wind,
            damageType = DamageType.Special,
            position = Position.None,
            stats =
            defaultDressStats.copy(
                hp = 1_350_000_000,
                actPower = 10000,
                normalDefense = 650,
                specialDefense = 650,
                agility = 1,
            ),
            acts =
            actsOf(
                ActType.Act1("Slash", 2) {
                  targetFront().act {
                    attack(
                        modifier = 100,
                        hitCount = 1,
                    )
                  }
                },
                ActType.Act2("Strong Slash", 2) {
                  targetFront().act {
                    attack(
                        modifier = 200,
                        hitCount = 1,
                    )
                  }
                },
                ActType.Act3("Triple Slash", 2) {
                  targetFront(3).act {
                    attack(
                        modifier = 80,
                        hitCount = 3,
                    )
                  }
                },
                ActType.Act4("Violent Triple Slash", 2) {
                  targetFront(3).act {
                    attack(
                        modifier = 120,
                        hitCount = 3,
                    )
                  }
                },
                ActType.Act5("Counter Concerto", 2) {
                  targetAoe().act {
                    attack(
                        modifier = 150,
                        hitCount = 2,
                    )
                  }
                },
                ActType.Act6("Perfect Aim Flurry", 2) {
                  targetSelf().act {
                    applyContinuousBuff(
                        effect = Buffs.PerfectAimBuff,
                        turns = 1,
                    )
                  }
                  targetAoe().act {
                    attack(
                        modifier = 120,
                        hitCount = 3,
                    )
                  }
                },
                ActType.Act7("FireDance", 2) {
                  targetAoe().act {
                    attack(
                        modifier = 99999,
                        hitCount = 2,
                        mode = HitMode.FIXED,
                    )
                    applyContinuousBuff(
                        effect = Buffs.BurnBuff,
                        value = 99999,
                        turns = 3,
                    )
                  }
                },
                ActType.Act8("Ironclad Dance", 2) {
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
                ActType.Act9("Purifying Dance", 2) {
                  targetSelf().act { removeContinuous(BuffCategory.Negative) }
                  targetAoe().act { removeContinuous(BuffCategory.Positive) }
                },
                ActType.ClimaxAct("Shining Hope★ NEO", 2) {
                  targetAoe().act {
                    attack(
                        modifier = 99999,
                        hitCount = 4,
                        mode = HitMode.FIXED,
                    )
                    applyContinuousBuff(
                        effect = Buffs.BurnBuff,
                        value = 99999,
                        turns = 3,
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
                trEventBonusPassive2023(2010021),
                AbnormalResistPassiveA.new(100),
                BossElementResistPassive.new(50),
            ),
        ),
    )

val tr29CheerTsukasaDiff4Strategy = FixedStrategy {
  val boss = this.team.actors.values.first()

  when (turn) {
    1 -> {
      +boss[ActType.Act8]
      +boss[ActType.Act1]
      +boss[ActType.Act4]
    }
    2 -> {
      +boss[ActType.Act8]
      +boss[ActType.Act2]
      +boss[ActType.Act4]
    }
    3 -> {
      +boss[ActType.ClimaxAct]
      +boss[ActType.Act6]
      +boss[ActType.Act9]
    }
    4 -> {
      +boss[ActType.Act8]
      +boss[ActType.Act2]
      +boss[ActType.Act4]
    }
    5 -> {
      +boss[ActType.Act7]
      +boss[ActType.Act2]
      +boss[ActType.Act5]
    }
    6 -> {
      +boss[ActType.Act9]
      +boss[ActType.Act4]
      +boss[ActType.Act6]
    }
    else -> {
      error("Unsupported.")
    }
  }
}
