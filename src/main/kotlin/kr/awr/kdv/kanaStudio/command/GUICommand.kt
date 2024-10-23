package kr.awr.kdv.kanaStudio.command

import kr.awr.kdv.kanaStudio.gui.GUIManager
import kr.awr.kdv.kanaStudio.gui.MainMenu
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GUICommand(private val menu: GUIManager) : CommandExecutor {

    private lateinit var menu2: MainMenu

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        // MainMenu 인스턴스를 사용하여 GUI를 여는 코드
        val player = sender as? Player


        return true
    }
}
