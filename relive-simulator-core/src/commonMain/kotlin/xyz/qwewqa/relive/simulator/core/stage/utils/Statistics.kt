package xyz.qwewqa.relive.simulator.core.stage.utils

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow
import xyz.qwewqa.relive.simulator.common.MarginResult
import xyz.qwewqa.relive.simulator.common.SimulationMarginResultType
import xyz.qwewqa.relive.simulator.common.StatisticsSummary
import xyz.qwewqa.relive.simulator.core.stage.MarginStageResult
import xyz.qwewqa.relive.simulator.core.stage.marginResultType

fun List<Double>.statistics() =
    if (isEmpty()) {
      null
    } else {
      val sorted = sorted()
      val mean = sorted.average()
      val standardDeviation = sorted.map { (it - mean).pow(2) }.average().pow(0.5)
      val min = sorted.first().toDouble()
      val q1 = sorted[sorted.size / 4]
      val median = sorted[sorted.size / 2]
      val q3 = sorted[sorted.size * 3 / 4]
      val max = sorted.last().toDouble()
      StatisticsSummary(mean, standardDeviation, min, q1, median, q3, max)
    }

fun resultMarginKernelDensityEstimate(
    data: List<Double>,
    h: Double,
    count: Int
): Map<Double, Double> {
  return epanechnikovKernelDensityEstimate(data, h, count)
}

fun chooseBandwidth(data: List<Double>): Double =
    if (data.isEmpty()) {
      1.0
    } else {
      // Intuitively bandwidth should be higher when average damage is higher.
      0.02 * data.average() * data.size.toDouble().pow(-0.2)
    }

fun epanechnikovKernelDensityEstimate(
    data: List<Double>,
    h: Double,
    count: Int
): Map<Double, Double> {
  if (data.isEmpty()) return emptyMap()
  require(count >= 2)
  fun kernel(x: Double) = 0.75 * (1 - x * x)
  val min = data.min() - h
  val max = data.max() + h
  val step = (max - min) / (count - 1)
  val results = Array(count) { 0.0 }
  for (x in data) {
    val lb = ceil((x - h - min) / step).toInt()
    val ub = floor((x + h - min) / step).toInt()
    for (i in lb..ub) {
      results[i] += kernel((x - (min + i * step)) / h) / h / data.size
    }
  }
  return (0 until count).associate { min + it * step to results[it] }
}

fun Map<String, List<MarginStageResult>>.summarize(
    count: Int = 2000
): Map<SimulationMarginResultType, Map<String?, MarginResult>> =
    SimulationMarginResultType.values().associateWith { type ->
      val typeValues = values.flatten().filter { it.marginResultType() == type }
      val h: Double
      val typeResults =
          typeValues.run {
            val damage = map { it.damage }
            val margins = map { it.margin }
            h = chooseBandwidth(damage)
            MarginResult(
                resultMarginKernelDensityEstimate(damage, h, count),
                damage.statistics(),
                resultMarginKernelDensityEstimate(margins, h, count),
                margins.statistics(),
            )
          }
      if (values.size == 1) {
        mapOf(
            null to typeResults,
        )
      } else {
        mapValues { (_, results) ->
          results
              .filter { it.marginResultType() == type }
              .run {
                val damage = map { it.damage }
                val margins = map { it.margin }
                val scale = size.toDouble() / typeValues.size
                MarginResult(
                    resultMarginKernelDensityEstimate(damage, h, count).mapValues { (_, v) ->
                      v * scale
                    },
                    damage.statistics(),
                    resultMarginKernelDensityEstimate(margins, h, count).mapValues { (_, v) ->
                      v * scale
                    },
                    margins.statistics(),
                )
              }
        } +
            mapOf(
                null to typeResults,
            )
      }
    }
