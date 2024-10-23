package kr.awr.kdv.kanaStudio.chat

import kr.awr.kdv.kanaStudio.annotation.ChatFormatter
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.*

class DefaultChatFormatter : ChatFormatter {
    override fun setMessage(player: Player, message: String, color: ChatColor?) {
        // 만약 2번째 인수가 null이면 하얀색
        val finalMessage = (color?.toString() ?: ChatColor.WHITE.toString()) + message

        // 플레이어가 온라인 상태일 경우에만 메시지 전송
        player?.sendMessage(finalMessage)
    }
}
