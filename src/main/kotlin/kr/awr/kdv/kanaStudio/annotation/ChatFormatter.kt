package kr.awr.kdv.kanaStudio.annotation

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.*

interface ChatFormatter {
    fun setMessage(player: Player, message: String, color: ChatColor?)
}