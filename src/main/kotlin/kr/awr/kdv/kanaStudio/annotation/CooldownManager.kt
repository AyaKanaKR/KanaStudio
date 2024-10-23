package kr.awr.kdv.kanaStudio.annotation

import org.bukkit.Bukkit
import org.bukkit.entity.Player

interface CooldownManager {
    fun startCooldown(player: Player, action: String, duration: Long)
    fun isOnCooldown(player: Player, action: String): Boolean
}